package org.heyframework.common.exception;

public class NoSuchClassDefinitionException extends BaseException {

	private static final long serialVersionUID = -5137490819733654249L;

	public NoSuchClassDefinitionException(Exception ex) {
		super("Could not find the class:" + ex.getMessage());
	}

	public NoSuchClassDefinitionException(String message) {
		super("Could not find the class:" + message);
	}
}
