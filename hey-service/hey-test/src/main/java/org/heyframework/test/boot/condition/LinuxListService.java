package org.heyframework.test.boot.condition;

public class LinuxListService implements ListService {

	@Override
	public String showListCmd() {
		return "LS";
	}

}
