package org.heyframework.common.exception;

public class ServiceWarnException extends ServiceException {

	private static final long serialVersionUID = 1068278324462761793L;

	public ServiceWarnException(Exception ex) {
		super(ex.getMessage());
	}

	public ServiceWarnException(String message) {
		super(message);
	}
}
