package apollogix.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apollogix.annotation.ExcelColumn;
import apollogix.contant.Constant.DataType;
import apollogix.exception.HeaderNotMapException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionConvertRawData<T> {

	private static final String PATTERN_FORMAT_YYYMMDDHHMM = "yyyyMMdd HHmm";
	private static final String SETTER_METHOD = "set";

	/**
	 * Simplify the raw data conversion implementation
	 * @param excelFilePath				  - part file data excel
	 * @param clazz                       - class generic
	 * @return List<T>
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public List<T> convertRawToObject(String excelFilePath, Class<T> clazz) {

		log.info("start proccess with file: %s", excelFilePath);
		List<T> listRows = new ArrayList<>();

		if (StringUtils.isBlank(excelFilePath)) {
			return listRows;
		}

		try (
				//Get file
				InputStream inputStream = new FileInputStream(new File(excelFilePath));
				// Get workbook
				Workbook workbook = getWorkbook(inputStream, excelFilePath)) {

			// Get sheet
			Sheet sheet = workbook.getSheetAt(0);

			Field[] fields = clazz.getDeclaredFields();

			Row header = sheet.getRow(0);

			if (Objects.isNull(header)) {
				return listRows;
			}

			short headerSize = sheet.getRow(0).getLastCellNum();

			if (headerSize != fields.length) {
				throw new HeaderNotMapException("header not map by model:");
			}

			// Get all rows
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {

				Row nextRow = iterator.next();
				if (nextRow.getRowNum() == 0) {
					// Ignore header
					continue;
				}

				T object = clazz.getDeclaredConstructor().newInstance();

				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					ExcelColumn excelColumn = fields[i].getAnnotation(ExcelColumn.class);
					DataFormatter formatter = new DataFormatter();
					Cell cell = nextRow.getCell(excelColumn.index());

					String valueByCell = formatter.formatCellValue(cell);

					String fieldName = fields[i].getName();

					Class<?> fieldType = fields[i].getType();

					StringBuilder methodName = new StringBuilder();
					methodName.append(SETTER_METHOD);
					methodName.append(fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));

					Method method = object.getClass().getDeclaredMethod(methodName.toString(), fieldType);

					method.invoke(object, this.convertValueByType(valueByCell, fieldType));

				}
				listRows.add(object);
			}
			return listRows;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("file: %s, error info: %s", e.getMessage(), excelFilePath);
			return Collections.emptyList();
		}
	}

	private Object convertValueByType(String value, Class<?> dataType) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT_YYYMMDDHHMM);

		DataType type = DataType.valueOfEnum(dataType.getSimpleName());

		switch (type) {
		case STRING: {
			return value;
		}
		case INTEGER: {
			return Integer.parseInt(value);
		}
		case DOUBLE: {
			return Double.parseDouble(value);
		}
		case LOCALDATETIME: {
			return StringUtils.isNotBlank(value)
					? LocalDateTime.parse(value, formatter)
					: null;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dataType.getClass().getSimpleName());
		}
	}

	// Get Workbook
	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
}
