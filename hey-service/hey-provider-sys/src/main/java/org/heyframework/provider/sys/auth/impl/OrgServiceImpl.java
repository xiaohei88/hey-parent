package org.heyframework.provider.sys.auth.impl;

import org.heyframework.api.sys.auth.service.OrgService;
import org.heyframework.provider.sys.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orgService")
@Transactional
public class OrgServiceImpl implements OrgService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void update() {
		userMapper.updateA();
	}

}
