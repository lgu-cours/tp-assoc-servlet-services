package exceptions;

public class ServiceException extends Exception {
	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -5567772660904347478L;

	public ServiceException(String message) {
		super(message);
	}
}
