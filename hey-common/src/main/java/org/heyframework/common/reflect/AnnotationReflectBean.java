package org.heyframework.common.reflect;

import java.util.HashMap;
import java.util.Map;

import org.heyframework.common.reflect.mapper.AnnotationBeanMapper;
import org.heyframework.common.reflect.mapper.BeanMapper;
import org.heyframework.common.util.ObjectUtils;

/**
 * 反射只有注解的字段
 * 
 * @author h.l
 *
 */
public class AnnotationReflectBean extends SemiAnnotationReflectBean {

	private final Map<Class<?>, AnnotationBeanMapper> annotationTableMapperCache = new HashMap<Class<?>, AnnotationBeanMapper>();

	private static AnnotationReflectBean reflectBean = new AnnotationReflectBean();

	public static ReflectBean getInstance() {
		return reflectBean;
	}

	@Override
	protected BeanMapper getIntrospectionData(Class<?> beanClass) {
		AnnotationBeanMapper annotationTableMapper = annotationTableMapperCache.get(beanClass);
		if (annotationTableMapper == null) {
			annotationTableMapper = fetchIntrospectionData(beanClass);
			if (annotationTableMapper != null) {
				annotationTableMapperCache.put(beanClass, annotationTableMapper);
			}
		}
		AnnotationBeanMapper newAnnotationTableMapper = (AnnotationBeanMapper) ObjectUtils.clone(annotationTableMapper);
		return newAnnotationTableMapper;
	}

	protected boolean isSemi() {
		return false;
	}
}
