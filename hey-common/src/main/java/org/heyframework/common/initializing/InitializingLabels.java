package org.heyframework.common.initializing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.AssertUtils;
import org.heyframework.common.util.FileUtils;

public class InitializingLabels implements Initializing {

	/** 标签配置文件路径 */
	private String labelXmlPath;

	/** 标签返回页面 */
	private String returnLabelPage;

	/** 标签配置文件绝对路径 */
	private String realLabelXmlPath;

	/** 节点属性值 */
	private static final String MODULE_CODE = "code";
	private static final String GROUP_CODE = "code";
	private static final String CODE = "code";
	private static final String NAME = "name";
	private static final String URL = "url";
	private static final String SORT = "sort";

	/** 模块与标签的连接符 */
	private static final String SIGN = "_";

	/**
	 * 初始化加载标签信息
	 * 
	 * @throws DocumentException
	 */
	@Override
	public void initializing() throws FileNotFoundException, DocumentException {
		checkLabelXmlPath();

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(realLabelXmlPath));
		Element root = document.getRootElement();
		parseMoudleLableMapping(root);
	}

	private void checkLabelXmlPath() throws FileNotFoundException {
		AssertUtils.isNull(labelXmlPath, "labelXmlPath is not null.");
		if (!FileUtils.exists(realLabelXmlPath)) {
			throw new FileNotFoundException("labelXmpPath is not exists:" + realLabelXmlPath);
		}
	}

	/**
	 * 加载指定模块标签组标签
	 * 
	 * @param labelXmlPath
	 * @param moduleGroupCode
	 * @return
	 * @throws DocumentException
	 */
	public List<Label> loadingLabels(String moduleGroupCode) throws DocumentException {
		List<Label> labels = new ArrayList<Label>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(realLabelXmlPath));
		Element root = document.getRootElement();
		if (moduleGroupCode.contains(SIGN)) {
			String[] moduleGroupCodes = moduleGroupCode.split(SIGN);
			labels = loadingSpecifyLabels(root, moduleGroupCodes[0], moduleGroupCodes[1]);
		}
		return labels;
	}

	@SuppressWarnings("unchecked")
	private List<Label> loadingSpecifyLabels(Element root, String moduleCode, String groupCode) {
		List<Label> labels = new ArrayList<Label>();
		List<Node> list = root.selectNodes("//module[@code='" + moduleCode + "']/group[@code='" + groupCode
				+ "']/label", "@sort");
		for (Iterator<Node> iterator = list.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			Element el = (Element) node;
			labels.add(putLabel(el));
		}
		return labels;
	}

	/**
	 * 初始化模块信息
	 * 
	 * @param labels
	 * @param root
	 */
	@SuppressWarnings("unchecked")
	private void parseMoudleLableMapping(Element root) {
		List<Element> modules = root.elements();
		for (int i = 0; i < modules.size(); i++) {
			Element moudle = modules.get(i);
			parseGroupLableMapping(moudle);
		}
	}

	/**
	 * 初始化标签组信息
	 * 
	 * @param labels
	 * @param moudle
	 */
	@SuppressWarnings("unchecked")
	private void parseGroupLableMapping(Element moudle) {
		List<Element> groups = moudle.elements();
		for (int i = 0; i < groups.size(); i++) {
			Element group = groups.get(i);
			String module = moudle.attributeValue(MODULE_CODE);
			String label = group.attributeValue(GROUP_CODE);
			Configure.getInstance().labels.put(module + SIGN + label, parseLabel(group));
		}
	}

	/**
	 * 初始化标签信息
	 * 
	 * @param group
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Label> parseLabel(Element group) {
		List<Label> labelList = new ArrayList<Label>();
		List<Element> labels = group.elements();
		for (int i = 0; i < labels.size(); i++) {
			Element ele = labels.get(i);
			labelList.add(putLabel(ele));
		}

		// 标签排序
		Collections.sort(labelList, new Comparator<Label>() {

			public int compare(Label o1, Label o2) {
				return o1.getSort().compareTo(o2.getSort());
			}
		});
		return labelList;
	}

	private Label putLabel(Element ele) {
		Label label = new Label();
		label.setCode(ele.attributeValue(CODE));
		label.setName(ele.attributeValue(NAME));
		label.setUrl(ele.attributeValue(URL));
		label.setSort(ele.attributeValue(SORT));
		return label;
	}

	public String getLabelXmlPath() {
		return labelXmlPath;
	}

	public void setLabelXmlPath(String labelXmlPath) {
		this.labelXmlPath = labelXmlPath;
		if (labelXmlPath.startsWith("classpath:")) {
			labelXmlPath = labelXmlPath.replace("classpath:", "");
			labelXmlPath = "/WEB-INF/classes" + labelXmlPath;
		}
		this.realLabelXmlPath = SpringContext.getServletContext().getRealPath(labelXmlPath);
	}

	public String getReturnLabelPage() {
		return returnLabelPage;
	}

	public void setReturnLabelPage(String returnLabelPage) {
		this.returnLabelPage = returnLabelPage;
	}

	public String getRealLabelXmlPath() {
		return realLabelXmlPath;
	}

	public void setRealLabelXmlPath(String realLabelXmlPath) {
		this.realLabelXmlPath = realLabelXmlPath;
	}

	public static class Label {
		private String code;
		private String name;
		private String url;
		private String sort;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}
	}

}
