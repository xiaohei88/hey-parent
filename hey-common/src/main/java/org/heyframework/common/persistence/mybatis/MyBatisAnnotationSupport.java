package org.heyframework.common.persistence.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.heyframework.common.bean.AnnotationSupport;
import org.heyframework.common.persistence.mybatis.database.DataBase;
import org.heyframework.common.persistence.mybatis.database.DataBaseFactoryBean;
import org.heyframework.common.reflect.mapper.AnnotationBeanMapper;
import org.heyframework.common.reflect.mapper.FieldMapper;
import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.AssertUtils;
import org.heyframework.common.util.ListUtils;
import org.heyframework.common.util.ReflectUtils;
import org.heyframework.common.util.StringUtils;
import org.springframework.beans.BeanUtils;

public class MyBatisAnnotationSupport extends AnnotationSupport {

	public String columns() {
		List<FieldMapper> fieldMappers = getBeanMapper().getFields();
		List<String> columns = new ArrayList<String>(fieldMappers.size());
		for (FieldMapper fieldMapper : fieldMappers) {
			columns.add(fieldMapper.getName());
		}
		return ListUtils.toString(columns);
	}

	public String values() {
		AnnotationBeanMapper annotationBeanMapper = getBeanMapper();
		String majorKey = annotationBeanMapper.getMajorKey();
		String sequence = annotationBeanMapper.getSequence();
		AssertUtils.isNull(sequence, "sequence is not null");

		List<FieldMapper> fieldMappers = annotationBeanMapper.getFields();
		List<String> columns = new ArrayList<String>(fieldMappers.size());
		for (FieldMapper fieldMapper : fieldMappers) {
			if (fieldMapper.getName().equals(majorKey)) {
				columns.add(getDataBase().getSequence(sequence));
			} else {
				columns.add(StringUtils.format("#{{}}", fieldMapper.getOrigName()));
			}
		}
		return ListUtils.toString(columns);
	}

	public String sets() {
		// 1、反射出对象里面所有的字段信息
		List<FieldMapper> fieldMappers = getBeanMapper().getFields();
		// 2、反射出对象里面所有的字段值
		Map<String, Object> fieldValueMap = ReflectUtils.describe(this);
		List<String> sets = new ArrayList<String>(fieldMappers.size());
		for (FieldMapper fieldMapper : fieldMappers) {
			Object value = fieldValueMap.get(fieldMapper.getOrigName());
			// 3、value为null,代表没有对这个值进行修改,所以必须的忽略,否则会把冲掉以前的数据
			if (value != null) {
				sets.add(StringUtils.format("{}=#{{}}", fieldMapper.getName(), fieldMapper.getOrigName()));
			}
		}
		return ListUtils.toString(sets);
	}

	private DataBase getDataBase() {
		Class<?> dataBaseClazz = SpringContext.getApplicationContext().getBean(DataBaseFactoryBean.class).getDataBase();
		return (DataBase) BeanUtils.instantiate(dataBaseClazz);
	}
}
