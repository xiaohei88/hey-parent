package org.heyframework.common.reflect.mapper;

import java.util.List;

public class BeanMapper {

	private String name;

	private List<FieldMapper> fields;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FieldMapper> getFields() {
		return fields;
	}

	public void setFields(List<FieldMapper> fields) {
		this.fields = fields;
	}
}
