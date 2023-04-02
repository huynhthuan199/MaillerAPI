package apollogix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AppConfig <br/>
 * <p>
 * Contains all setting for library
 * Required to be valid for all attributes
 * </p>
 * @author ThuanNH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {
	private String strHost;
	private Integer intPort;
	private String strUsername;
	private String strPassword;
	private String strProtocol;
	
	/**
	 * Contains all setting filter for library
	 */
	private AppFilter appFilter;
}
