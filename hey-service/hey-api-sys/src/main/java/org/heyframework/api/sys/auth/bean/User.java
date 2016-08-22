package org.heyframework.api.sys.auth.bean;

import java.io.Serializable;

import org.heyframework.common.persistence.mybatis.base.MyBatisBaseBean;

public class User extends MyBatisBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939333614969335833L;

	private String name;
	private String passwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}
