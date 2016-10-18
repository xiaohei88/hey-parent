package org.heyframework.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

	private Object obj;

	public DynamicProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before rent house");
		method.invoke(obj, args);
		System.out.println("after rent house");
		return null;
	}

}
