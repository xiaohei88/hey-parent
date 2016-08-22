package org.heyframework.common.persistence.mybatis;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import org.heyframework.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisSqlTemplate<T extends MyBatisAnnotationSupport> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public String insert(T obj) {
		log.debug("Loading SQLTemplate methiod insert..");

		BEGIN();
		INSERT_INTO(obj.tableName());
		VALUES(obj.columns(), obj.values());
		return SQL();
	}

	public String delete(T obj) {
		log.debug("Loading SQLTemplate methiod delete..");

		String majorKey = obj.majorKey();
		String origMajorKey = obj.origMajorKey();
		BEGIN();
		DELETE_FROM(obj.tableName());
		WHERE(StringUtils.format("{} = #{}", majorKey, origMajorKey));
		return SQL();
	}

	public String update(T obj) {
		log.debug("Loading SQLTemplate methiod update..");

		String majorKey = obj.majorKey();
		String origMajorKey = obj.origMajorKey();
		BEGIN();
		UPDATE(obj.tableName());
		SET(obj.sets());
		WHERE(StringUtils.format("{} = #{}", majorKey, origMajorKey));
		return SQL();
	}
}
