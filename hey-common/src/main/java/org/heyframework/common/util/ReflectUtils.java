package org.heyframework.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.heyframework.common.reflect.AnnotationReflectBean;
import org.heyframework.common.reflect.DefaultReflectBean;
import org.heyframework.common.reflect.mapper.BeanMapper;
import org.heyframework.common.reflect.mapper.FieldMapper;

public abstract class ReflectUtils {

	/**
	 * 反射对象的所有的字段和值
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> describe(Object object) {
		try {
			return PropertyUtils.describe(object);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		return null;
	}

	/**
	 * 反射对象的所有注解的字段和值
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> describeAnnotation(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		BeanMapper beanMapper = AnnotationReflectBean.getInstance().getBeanMapper(object);
		if (beanMapper != null) {
			List<FieldMapper> fields = beanMapper.getFields();
			if (!ListUtils.isEmpty(fields)) {
				Map<String, Object> mapValue = describe(object);
				for (FieldMapper field : fields) {
					map.put(field.getName(), mapValue.get(field.getOrigName()));
				}
			}
		}
		return map;
	}

	/**
	 * 得到某个注解的值
	 * 
	 * @param element
	 * @param annotationClass
	 * @return
	 */
	public static <T extends Annotation> T getAnnotationValue(AnnotatedElement element, Class<T> annotationClass) {
		if (element.isAnnotationPresent(annotationClass)) {
			return element.getAnnotation(annotationClass);
		}
		return null;
	}

	/**
	 * 得到某个类所有的信息
	 * 
	 * @param beanClass
	 * @return
	 */
	public static BeanMapper getBean(Class<?> beanClass) {
		return DefaultReflectBean.getInstance().getBeanMapper(beanClass);
	}

	/**
	 * 反射字段值
	 * 
	 * @param object
	 * @return
	 */
	public static List<Object> getFiledValuesForList(Object object) {
		Map<String, Object> map = describe(object);
		if (!MapUtils.isEmpty(map)) {
			List<Object> fieldValues = new ArrayList<Object>(map.size());
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				fieldValues.add(entry.getValue());
			}
			return fieldValues;
		}
		return null;
	}

	/**
	 * 反射字段值
	 * 
	 * @param object
	 * @return
	 */
	public static Object[] getFiledValues(Object object) {
		List<Object> fieldValues = getFiledValuesForList(object);
		if (!ListUtils.isEmpty(fieldValues)) {
			return fieldValues.toArray(new Object[fieldValues.size()]);
		}
		return null;
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(beanClass);
		} catch (IntrospectionException e) {
			return null;
		}
		return beanInfo.getPropertyDescriptors();
	}

	/**
	 * 判断类是否存在
	 * 
	 * @param className
	 * @return
	 */
	public static boolean validateClassEmpty(String className) {
		try {
			Class.forName(className).newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * 验证方法是否存在
	 * 
	 * @param className
	 * @param method
	 * @return
	 */
	public static boolean validateMethodEmpty(String className, String method) {
		try {
			Object object = Class.forName(className).newInstance();
			Class<?> clazz = object.getClass();
			try {
				clazz.getDeclaredMethod(method);
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
				return false;
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
}
