package org.heyframework.common.reflect.mapper;

public class AnnotationBeanMapper extends BeanMapper {

	private String majorKey;

	private String origMajorKey;

	private String sequence;

	public String getMajorKey() {
		return majorKey;
	}

	public void setMajorKey(String majorKey) {
		this.majorKey = majorKey;
	}

	public String getOrigMajorKey() {
		return origMajorKey;
	}

	public void setOrigMajorKey(String origMajorKey) {
		this.origMajorKey = origMajorKey;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
