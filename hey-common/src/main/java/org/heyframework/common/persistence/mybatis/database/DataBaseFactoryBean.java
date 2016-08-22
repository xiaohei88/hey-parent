package org.heyframework.common.persistence.mybatis.database;

import org.springframework.beans.factory.InitializingBean;

public class DataBaseFactoryBean implements InitializingBean {

	private Class<?> dataBase;

	public Class<?> getDataBase() {
		return dataBase;
	}

	public void setDataBase(Class<?> dataBase) {
		this.dataBase = dataBase;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
