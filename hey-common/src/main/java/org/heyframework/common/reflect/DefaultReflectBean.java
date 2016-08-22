package org.heyframework.common.reflect;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.heyframework.common.reflect.mapper.BeanMapper;
import org.heyframework.common.reflect.mapper.FieldMapper;
import org.heyframework.common.util.ObjectUtils;
import org.heyframework.common.util.ReflectUtils;

/**
 * 反射所有实体类的字段与注解无关
 * 
 * @author h.l
 *
 */
public class DefaultReflectBean extends ReflectBean {

	private final Map<Class<?>, BeanMapper> defaultTableMapperCache = new HashMap<Class<?>, BeanMapper>();

	private static DefaultReflectBean reflectBean = new DefaultReflectBean();

	public static ReflectBean getInstance() {
		return reflectBean;
	}

	@Override
	protected BeanMapper getIntrospectionData(Class<?> beanClass) {
		BeanMapper tableMapper = defaultTableMapperCache.get(beanClass);
		if (tableMapper == null) {
			tableMapper = fetchIntrospectionData(beanClass);
			if (tableMapper != null) {
				defaultTableMapperCache.put(beanClass, tableMapper);
			}
		}
		BeanMapper newTableMapper = (BeanMapper) ObjectUtils.clone(tableMapper);
		return newTableMapper;
	}

	private BeanMapper fetchIntrospectionData(Class<?> beanClass) {
		BeanMapper tableMapper = new BeanMapper();
		tableMapper.setName(getClassName(beanClass));
		PropertyDescriptor[] descriptorses = ReflectUtils.getPropertyDescriptors(beanClass);
		if (descriptorses != null) {
			List<FieldMapper> fieldMappers = new ArrayList<FieldMapper>();
			for (PropertyDescriptor propertyDescriptor : descriptorses) {
				FieldMapper fieldMapper = new FieldMapper();
				String name = propertyDescriptor.getName();
				if (!"class".equals(name)) {
					fieldMapper.setName(name);
					fieldMapper.setOrigName(name);
					fieldMappers.add(fieldMapper);
				}
			}
			tableMapper.setFields(fieldMappers);
		}
		return tableMapper;
	}

	public String getClassName(Class<?> beanClass) {
		String clazz = DefaultReflectBean.class.getName();
		return clazz.substring(clazz.lastIndexOf(".") + 1);
	}
}
