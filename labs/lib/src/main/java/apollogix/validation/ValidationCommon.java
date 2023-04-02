package apollogix.validation;

import java.util.function.BiPredicate;

/**
 * ValidationCommon <br/>
 * <p>
 * Contains all validation common
 * </p>
 * @author ThuanNH
 */
public final class ValidationCommon {

	private ValidationCommon() {

	}

	public static final BiPredicate<String, String> predicateCheckPrefix = (String absoluteFileName, String prefix) -> {
		return absoluteFileName.startsWith(prefix);
	};

	public static final BiPredicate<String[], String[]> validateHeaderByExcel = (String[] headerExcels,
			String[] data) -> {
		return true;
	};

}
