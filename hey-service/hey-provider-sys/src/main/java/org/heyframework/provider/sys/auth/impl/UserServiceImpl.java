package org.heyframework.provider.sys.auth.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.collections.MapUtils;
import org.heyframework.api.sys.auth.bean.User;
import org.heyframework.api.sys.auth.service.OrgService;
import org.heyframework.api.sys.auth.service.UserService;
import org.heyframework.common.cache.CacheStringRedisTemplate;
import org.heyframework.provider.sys.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private CacheStringRedisTemplate redisTemplate;

	@Autowired
	private OrgService orgService;

	@Override
	public void add() {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("name", "0027006717");
		map.put("passwd", "123456");
		redisTemplate.hMSet("user", map);
	}

	@Override
	public User get() {
		System.out.println("incr=======>" + redisTemplate.incr("test"));

		Map<String, String> map = redisTemplate.hGetAll("user");
		User u = new User();
		if (!MapUtils.isEmpty(map)) {
			u.setName(map.get("name"));
			u.setPasswd(map.get("passwd"));
		}
		return u;
	}

	@Override
	public void add(final User u) {
		jmsTemplate.send("adduser.queue", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(u.getName() + "&" + u.getPasswd() + "@queue");
			}
		});
	}

	@Override
	public void update() {
		userMapper.updateB();
		orgService.update();
	}
}
