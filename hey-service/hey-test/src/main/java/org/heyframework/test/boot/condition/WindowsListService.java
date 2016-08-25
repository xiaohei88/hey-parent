package org.heyframework.test.boot.condition;

public class WindowsListService implements ListService {

	@Override
	public String showListCmd() {
		return "DIR";
	}

}
