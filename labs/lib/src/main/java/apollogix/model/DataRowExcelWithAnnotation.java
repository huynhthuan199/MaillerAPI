package apollogix.model;

import java.time.LocalDateTime;

import apollogix.annotation.ExcelColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataRowExcelWithAnnotation {

	@ExcelColumn(index = 0, title = "UN Loc Code")
	private String strUnLocCode;
	@ExcelColumn(index = 1, title = "Terminal ID")
	private String strTerminalId;
	@ExcelColumn(index = 2, title = "ETA")
	private LocalDateTime ldtEta;
	@ExcelColumn(index = 3, title = "ETD")
	private LocalDateTime ldtEtd;
	@ExcelColumn(index = 4, title = "Ship Name")
	private String strShipName;
	@ExcelColumn(index = 5, title = "Ship Operator Voyage Out")
	private String strShipOperatorVoyageOut;
	@ExcelColumn(index = 6, title = "Lloyds ID")
	private Integer intLloydsId;
	@ExcelColumn(index = 7, title = "Cargo Cutoff Date")
	private LocalDateTime ldtCargoCutoffDate;
	@ExcelColumn(index = 8, title = "Reefer Cutoff Date")
	private LocalDateTime ldtReeferCutoffDate;
	@ExcelColumn(index = 9, title = "Operators")
	private String strOperators;
	@ExcelColumn(index = 10, title = "Operators Description")
	private String strOperatorsDescription;
	@ExcelColumn(index = 11, title = "Ship Operator Code")
	private String strShipOperatorCode;
	@ExcelColumn(index = 12, title = "Ship Operator Voyage In")
	private String strShipOperatorVoyageIn;
	@ExcelColumn(index = 13, title = "Export Receival Commencement Date")
	private LocalDateTime ldtExportReceivalCommentDate;
	@ExcelColumn(index = 14, title = "Import Availability Date")
	private LocalDateTime ldtImportAvailabilityDate;
	@ExcelColumn(index = 15, title = "Import Storage Start Date")
	private LocalDateTime ldtImportStorageStartDate;
	@ExcelColumn(index = 16, title = "Vessel Type")
	private String strVesselType;
	@ExcelColumn(index = 17, title = "Actual Arrival Date")
	private LocalDateTime ldtActualArrivalDate;
	@ExcelColumn(index = 18, title = "Actual Depart Date")
	private LocalDateTime ldtActualDepartDate;
	@ExcelColumn(index = 19, title = "Vessel Code")
	private String strVesselCode;
	@ExcelColumn(index = 20, title = "First Free Import Date")
	private LocalDateTime ldtFirstFreeImportDate;
	@ExcelColumn(index = 21, title = "Hazardous Receival Commencement Date")
	private LocalDateTime ldtHazardousReceivalCommentDate;
	@ExcelColumn(index = 22, title = "Hazardous Cutoff Date")
	private LocalDateTime ldtHazardousCutoffDate;
	@ExcelColumn(index = 23, title = "Empty Receival Commencement Date")
	private LocalDateTime ldtEmptyReceivalCommentDate;
	@ExcelColumn(index = 24, title = "Empty Cutoff Date")
	private LocalDateTime ldtEmptyCutoffDate;
}
