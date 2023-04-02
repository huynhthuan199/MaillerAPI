package apollogix.exception;

public class HeaderNotMapException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderNotMapException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public HeaderNotMapException(String errorMessage) {
		super(errorMessage);
	}
}
