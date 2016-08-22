package org.heyframework.provider.sys.auth.mapper;

import java.util.List;
import java.util.Map;

import org.heyframework.api.sys.auth.bean.User;
import org.heyframework.common.persistence.mybatis.base.MyBatisBaseMapper;

public interface UserMapper extends MyBatisBaseMapper<User> {

	List<Map<String, Object>> queryStaff();

	void updateA();

	void updateB();
}
