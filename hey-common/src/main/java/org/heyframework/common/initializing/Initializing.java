package org.heyframework.common.initializing;

/**
 * 哪个类需要初始化实现这个接口
 * 
 * @author h.l
 *
 */
public interface Initializing {

	void initializing() throws Exception;
}
