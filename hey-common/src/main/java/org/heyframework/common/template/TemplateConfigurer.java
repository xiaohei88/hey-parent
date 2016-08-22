package org.heyframework.common.template;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.heyframework.common.util.AssertUtils;
import org.heyframework.common.util.FileUtils;
import org.springframework.beans.factory.InitializingBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateConfigurer extends TemplateConfigurationFactory implements InitializingBean {

	public static final String TEMPLATE_NAME = "templateName";

	private Configuration configuration;

	/**
	 * 初始化默认值
	 * 
	 * @param templateLoaderPath
	 */
	public TemplateConfigurer() {
		setDefaultEncoding(FileUtils.UFT8);
		Properties settings = new Properties();
		settings.setProperty("classic_compatible", "true");
		setFreemarkerSettings(settings);
	}

	/**
	 * 加载模板配置
	 */
	public void loadConfiguration() {
		if (configuration == null) {
			try {
				configuration = createConfiguration();
			} catch (IOException e) {
				logger.error("加载模板目录错误", e);
			} catch (TemplateException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 获取模板(某个模板路径下的模板文件)
	 * 
	 * @param templeteName
	 * @return
	 */
	public Template getTemplate(String templeteName) {
		try {
			AssertUtils.isNull(configuration, "configuration 不能为空");
			return configuration.getTemplate(templeteName);
		} catch (IOException e) {
			logger.error("加载模板文件错误", e);
		}
		return null;
	}

	/**
	 * 获取模板(字符串模板)
	 * 
	 * @param templateFtl
	 * @return
	 */
	public Template getTemplateForStr(String templateFtl) {
		return getTemplateForStr(TEMPLATE_NAME, templateFtl);
	}

	/**
	 * 获取模板(字符串模板)
	 * 
	 * @param templeteName
	 * @param templateFtl
	 * @return
	 */
	public Template getTemplateForStr(String templeteName, String templateFtl) {
		AssertUtils.isNull(configuration, "configuration 不能为空");
		Template template = null;
		try {
			template = new Template(templeteName, new StringReader(templateFtl), configuration);
		} catch (IOException e) {
			logger.error("加载模板文件错误", e);
		}
		return template;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		if (configuration == null) {
			loadConfiguration();
		}
	}
}
