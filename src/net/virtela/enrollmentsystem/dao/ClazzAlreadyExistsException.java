package net.virtela.enrollmentsystem.dao;

public class ClazzAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 7545841051113465583L;

	public ClazzAlreadyExistsException() {
		super();
	}

	public ClazzAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClazzAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClazzAlreadyExistsException(String message) {
		super(message);
	}

	public ClazzAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
