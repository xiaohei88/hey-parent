package org.heyframework.common.exception;

/**
 * 所有自定义异常的基类
 * 
 * @author h.l
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5742227817425052267L;

	public BaseException(Exception ex) {
		super(ex);
	}

	public BaseException(String message) {
		super(message);
	}
}
