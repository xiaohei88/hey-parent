package org.heyframework.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.heyframework.common.grid.DataGrid;
import org.heyframework.common.grid.IDataGrid;
import org.heyframework.common.grid.ITreeGrid;
import org.heyframework.common.grid.TreeGrid;
import org.heyframework.common.initializing.Configure;
import org.heyframework.common.initializing.InitializingLabels.Label;
import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.AssertUtils;
import org.heyframework.common.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("comm")
public class CommonController {

	@Autowired
	private HttpServletRequest request;

	/**
	 * 页面跳转(页面中央处理器)
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("/redirect")
	public String redirect(@RequestParam("url") String url) {
		return url;
	}

	/**
	 * 加载模块下所属的标签
	 * 
	 * @param moduleLabel
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping("/labels")
	public String labels(Model model, @RequestParam("moduleLabel") String moduleLabel) throws DocumentException {
		AssertUtils.isNull(moduleLabel, "加载标签时模块代码不能为空.");

		Configure configure = Configure.getInstance();
		List<Label> labels = configure.getLabels(moduleLabel);
		if (!ListUtils.isEmpty(labels)) {
			model.addAttribute("labels", labels);
			model.addAttribute("moduleLabel", moduleLabel);
			model.addAttribute("params", request.getQueryString());
		} else {
			model.addAttribute("error", "请到" + configure.getLabelXmlPath() + "目录下配置标签.");
		}
		return configure.getReturnLabelPage();
	}

	/**
	 * 解析成树表格
	 * 
	 * @param mm
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/treeGrid")
	public String treeGrid(ModelMap mm, @RequestParam(value = "clazz") String clazz) throws Exception {
		Class<?> cla = Class.forName(clazz);
		ITreeGrid iTreeGrid = (ITreeGrid) SpringContext.getApplicationContext().getBean(cla);
		TreeGrid treeGrid = new TreeGrid();
		iTreeGrid.getTreeGrid(treeGrid);
		mm.addAttribute("treeGrid", treeGrid);
		return "treeGrid";
	}

	/**
	 * 解析成数据表格
	 * 
	 * @param mm
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dataGrid")
	public String dataGrid(ModelMap mm, @RequestParam(value = "clazz") String clazz) throws Exception {
		Class<?> cla = Class.forName(clazz);
		IDataGrid iDataGrid = (IDataGrid) SpringContext.getApplicationContext().getBean(cla);
		DataGrid dataGrid = new DataGrid();
		iDataGrid.getDataGrid(dataGrid);
		mm.addAttribute("dataGrid", dataGrid);
		return "dataGrid";
	}
}
