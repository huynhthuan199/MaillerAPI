package apollogix.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Result <br/>
 * <p>
 * This class is generated from tool attached
 * </p>
 * @author ThuanNH
 */
@Data
public class Result<T> {

	/**
	 * file handle
	 */
	@JsonProperty("fileName")
	private String strFileName;
	
	/**
	 * data of file
	 */
	@JsonProperty("data")
	private List<T> rowExcel;
}
