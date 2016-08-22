package org.heyframework.common.initializing;

import java.util.ArrayList;
import java.util.List;

import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.AssertUtils;
import org.heyframework.common.util.ListUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * 初始化配置文件容器,实现Initialization这个接口
 * 
 * @author h.l
 *
 */
public class InitializingContainer implements InitializingBean {

	/** 加载需要初始化的子类实例 */
	private List<Initializing> instances = new ArrayList<Initializing>();

	@Override
	public void afterPropertiesSet() throws Exception {
		AssertUtils.isNull(SpringContext.getServletContext(), "请先加载littlehey配置文件(classpath:/config/spring/spring.xml).");

		// 初始化加载文件信息
		if (!ListUtils.isEmpty(instances)) {
			for (Initializing instance : instances) {
				instance.initializing();
			}
		}
	}

	/**
	 * 获取某个指定的初始化实例
	 * 
	 * @param object
	 * @return
	 */
	public Initializing getInstance(Class<?> clazz) {
		if (!ListUtils.isEmpty(instances)) {
			for (Initializing intance : instances) {
				if (clazz.isInstance(intance)) {
					return intance;
				}
			}
		}
		return null;
	}

	public List<Initializing> getInstances() {
		return instances;
	}

	public void setInstances(List<Initializing> instances) {
		this.instances = instances;
	}
}
