package org.heyframework.common.exception;

public class NoSuchMethodDefinitionException extends BaseException {

	private static final long serialVersionUID = -5137490819733654249L;

	public NoSuchMethodDefinitionException(Exception ex) {
		super("Could not find the method:" + ex.getMessage());
	}

	public NoSuchMethodDefinitionException(String message) {
		super("Could not find the method:" + message);
	}
}
