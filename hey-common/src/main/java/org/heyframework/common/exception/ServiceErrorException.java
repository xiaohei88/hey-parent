package org.heyframework.common.exception;

public class ServiceErrorException extends ServiceException {

	private static final long serialVersionUID = -2376953483529859422L;

	public ServiceErrorException(Exception ex) {
		super(ex.getMessage());
	}

	public ServiceErrorException(String message) {
		super(message);
	}
}
