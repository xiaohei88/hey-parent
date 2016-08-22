package org.heyframework.common.persistence.mybatis.database;

import org.heyframework.common.util.StringUtils;

public class OracleDataBase implements DataBase {

	@Override
	public String getSequence(String sequence) {
		return StringUtils.format("{}.nextval", sequence);
	}
}
