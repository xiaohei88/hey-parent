package org.heyframework.common.reflect;

import org.heyframework.common.reflect.mapper.BeanMapper;
import org.heyframework.common.util.AssertUtils;

/**
 * 所有反射类的基类
 * 
 * @author h.l
 *
 */
public abstract class ReflectBean {

	public BeanMapper getBeanMapper(Object object) {
		AssertUtils.isNull(object, "reflect object is not null.");

		return getIntrospectionData(object.getClass());
	}

	public BeanMapper getBeanMapper(Class<?> beanClass) {
		AssertUtils.isNull(beanClass, "reflect Class is not null.");

		return getIntrospectionData(beanClass);
	}

	protected abstract BeanMapper getIntrospectionData(Class<?> beanClass);

	/**
	 * 获取字段类型
	 * 
	 * @param field
	 * @return
	 */
	protected String getFieldType(String type) {
		if (type.contains(".")) {
			type = type.substring(type.lastIndexOf(".") + 1);
		}
		return type;
	}
}
