package org.heyframework.common.exception;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = 4845449872205797354L;

	public ServiceException(Exception ex) {
		super(ex.getMessage());
	}

	public ServiceException(String message) {
		super(message);
	}
}
