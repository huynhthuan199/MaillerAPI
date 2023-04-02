package apollogix.helper;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * UltiHelper <br/>
 * <p>
 * Contains method helper
 * </p>
 * @author ThuanNH
 */
public class UltiHelper {

	private UltiHelper() {
	}

	/**
	 * method convert object to JSON when apply ResfulAPI
	 */
	@Deprecated
	public static Object readJsonFromFile(File file, Class<?> objRes) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		try {
			return mapper.readValue(file, objRes);
		} catch (Exception e) {
			return null;
		} finally {
			file.deleteOnExit();
		}
	}

}
