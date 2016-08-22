package org.heyframework.common.initializing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.heyframework.common.initializing.InitializingLabels.Label;
import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.ListUtils;

/**
 * 加载初始化配置文件信息时候，此类可以提取出相关信息
 * 
 * @author h.l
 *
 */
public final class Configure {

	private Configure() {

	}

	private static Configure configure = new Configure();

	public static Configure getInstance() {
		return configure;
	}

	/** 系统菜单缓存 */
	public final Map<String, String> menus = new HashMap<String, String>();

	/** 系统标签缓存 */
	public final Map<String, List<Label>> labels = new HashMap<String, List<Label>>();

	private static InitializingLabels initLables;

	static {
		InitializingContainer initializingContainer = SpringContext.getInitContainer();
		if (initializingContainer != null) {
			initLables = (InitializingLabels) initializingContainer.getInstance(InitializingLabels.class);
		}
	}

	/**
	 * 得到模块标签
	 * 
	 * @param moduleCode
	 * @return
	 * @throws DocumentException
	 */
	public List<Label> getLabels(String moduleLabel) throws DocumentException {
		List<Label> labels = Configure.getInstance().labels.get(moduleLabel);
		if (ListUtils.isEmpty(labels) && initLables != null) {
			labels = initLables.loadingLabels(moduleLabel);
		}
		return labels;
	}

	public String getLabelXmlPath() {
		if (initLables != null) {
			return initLables.getLabelXmlPath();
		}
		return "";
	}

	public String getReturnLabelPage() {
		if (initLables != null) {
			return initLables.getReturnLabelPage();
		}
		return "";
	}
}
