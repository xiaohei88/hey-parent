package org.heyframework.common.bean;

import org.heyframework.common.reflect.AnnotationReflectBean;
import org.heyframework.common.reflect.mapper.AnnotationBeanMapper;

public class AnnotationSupport {

	public String tableName() {
		return getBeanMapper().getName();
	}

	public String majorKey() {
		return getBeanMapper().getMajorKey();
	}

	public String origMajorKey() {
		return getBeanMapper().getOrigMajorKey();
	}

	protected AnnotationBeanMapper getBeanMapper() {
		return (AnnotationBeanMapper) AnnotationReflectBean.getInstance().getBeanMapper(this.getClass());
	}
}
