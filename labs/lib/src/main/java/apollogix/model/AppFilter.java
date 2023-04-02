package apollogix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AppFilter <br/>
 * <p>
 * Contains all setting filter for library
 * </p>
 * @author ThuanNH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppFilter {

	/**
	 * Use check prefix from file name
	 */
	private String strPrefix;
	
	/**
	 * Check subject from mail
	 */
	private String strSubject;
	
	/**
	 * Check sender from mail
	 */
	private String strSender;
	
	/**
	 * folder need load
	 */
	private String strFolder;
	
}
