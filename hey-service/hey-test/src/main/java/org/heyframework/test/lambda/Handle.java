package org.heyframework.test.lambda;

/**
 * Created by Henry on 12/15/16.
 */
@FunctionalInterface
public interface Handle<T> {

	boolean test(T t);
}
