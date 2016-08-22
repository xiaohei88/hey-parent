package org.heyframework.api.sys.auth.service;

import org.heyframework.api.sys.auth.bean.User;

public interface UserService {

	void add();

	User get();

	void add(User user);

	void update();
}
