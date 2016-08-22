package org.heyframework.common.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.heyframework.common.bean.annotation.Blob;
import org.heyframework.common.bean.annotation.Column;
import org.heyframework.common.bean.annotation.Id;
import org.heyframework.common.bean.annotation.Sequence;
import org.heyframework.common.bean.annotation.Table;
import org.heyframework.common.bean.annotation.Temporal;
import org.heyframework.common.bean.annotation.Transient;
import org.heyframework.common.reflect.mapper.AnnotationBeanMapper;
import org.heyframework.common.reflect.mapper.BeanMapper;
import org.heyframework.common.reflect.mapper.FieldMapper;
import org.heyframework.common.util.ObjectUtils;
import org.heyframework.common.util.ReflectUtils;
import org.heyframework.common.util.StringUtils;

/**
 * 半注解.字段有注解的就取注解的,没有就取字段的名称
 * 
 * @author h.l
 *
 */
public class SemiAnnotationReflectBean extends ReflectBean {

	private final Map<Class<?>, AnnotationBeanMapper> semiAnnotationTableMapperCache = new HashMap<Class<?>, AnnotationBeanMapper>();

	private static SemiAnnotationReflectBean reflectBean = new SemiAnnotationReflectBean();

	public static ReflectBean getInstance() {
		return reflectBean;
	}

	@Override
	protected BeanMapper getIntrospectionData(Class<?> beanClass) {
		AnnotationBeanMapper annotationTableMapper = semiAnnotationTableMapperCache.get(beanClass);
		if (annotationTableMapper == null) {
			annotationTableMapper = fetchIntrospectionData(beanClass);
			if (annotationTableMapper != null) {
				semiAnnotationTableMapperCache.put(beanClass, annotationTableMapper);
			}
		}
		AnnotationBeanMapper newAnnotationTableMapper = (AnnotationBeanMapper) ObjectUtils
				.clone(annotationTableMapper);
		return newAnnotationTableMapper;
	}

	protected AnnotationBeanMapper fetchIntrospectionData(Class<?> beanClass) {
		AnnotationBeanMapper annotationTableMapper = new AnnotationBeanMapper();
		Field[] fileds = beanClass.getDeclaredFields();
		if (fileds != null) {
			List<FieldMapper> fieldMappers = new ArrayList<FieldMapper>(fileds.length);
			Table table = ReflectUtils.getAnnotationValue(beanClass, Table.class);
			if (table != null) {
				annotationTableMapper.setName(table.value());
			}
			for (Field field : fileds) {
				String fieldName = field.getName();
				PropertyDescriptor propertyDescriptor = getPropertyDescriptor(fieldName, beanClass);
				if (propertyDescriptor != null) {
					FieldMapper fieldMapper = setFieldMapper(field, annotationTableMapper);
					if (!StringUtils.isEmpty(fieldMapper.getName())) {
						fieldMappers.add(fieldMapper);
					}
				}
			}
			annotationTableMapper.setFields(fieldMappers);
		}
		return annotationTableMapper;
	}

	private PropertyDescriptor getPropertyDescriptor(String fieldName, Class<?> beanClass) {
		try {
			return new PropertyDescriptor(fieldName, beanClass);
		} catch (IntrospectionException e) {
			return null;
		}
	}

	private FieldMapper setFieldMapper(Field field, AnnotationBeanMapper annotationTableMapper) {
		FieldMapper fieldMapper = new FieldMapper();
		// 如果字段被注解Transient,就无需持久化这个字段
		if (!field.isAnnotationPresent(Transient.class)) {
			String origName = field.getName();
			fieldMapper.setOrigName(origName);
			fieldMapper.setType(getFieldType(field));

			String name = "";
			if (field.isAnnotationPresent(Column.class)) {
				name = ReflectUtils.getAnnotationValue(field, Column.class).value();
				fieldMapper.setName(name);
			} else if (isSemi()) {
				fieldMapper.setName(name);
			}

			if (field.isAnnotationPresent(Id.class)) {
				annotationTableMapper.setMajorKey(name);
				annotationTableMapper.setOrigMajorKey(origName);
				Sequence seq = ReflectUtils.getAnnotationValue(field, Sequence.class);
				if (seq != null) {
					annotationTableMapper.setSequence(seq.value());
				}
			}
		}
		return fieldMapper;
	}

	private String getFieldType(Field field) {
		if (field.isAnnotationPresent(Temporal.class)) {
			return ReflectUtils.getAnnotationValue(field, Temporal.class).value();
		} else if (field.isAnnotationPresent(Blob.class)) {
			return "blob";
		}
		String type = field.getType().getName();
		return getFieldType(type);
	}

	protected boolean isSemi() {
		return true;
	}
}
