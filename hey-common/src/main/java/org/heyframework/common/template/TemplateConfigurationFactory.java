package org.heyframework.common.template;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.util.FileUtils;
import org.heyframework.common.util.MapUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;

public class TemplateConfigurationFactory {

	protected final Log logger = LogFactory.getLog(getClass());

	/** 模板属性配置 */
	private Properties freemarkerSettings;

	private Map<String, Object> freemarkerVariables;

	/** 编码 */
	private String defaultEncoding;

	/** 模板加载器 */
	private final List<TemplateLoader> templateLoaders = new ArrayList<TemplateLoader>();

	/** 模板路径 */
	private String[] templateLoaderPaths;

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	public void setFreemarkerSettings(Properties settings) {
		this.freemarkerSettings = settings;
	}

	public void setFreemarkerVariables(Map<String, Object> variables) {
		this.freemarkerVariables = variables;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	public void setTemplateLoaderPath(String templateLoaderPath) {
		this.templateLoaderPaths = new String[] { templateLoaderPath };
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public void setTemplateLoaderPaths(String... templateLoaderPaths) {
		this.templateLoaderPaths = templateLoaderPaths;
	}

	protected Configuration createConfiguration() throws IOException, TemplateException {
		Configuration config = newConfiguration();
		Properties props = new Properties();
		if (this.freemarkerSettings != null) {
			props.putAll(this.freemarkerSettings);
		}

		if (!props.isEmpty()) {
			config.setSettings(props);
		}

		if (!MapUtils.isEmpty((this.freemarkerVariables))) {
			config.setAllSharedVariables(new SimpleHash(this.freemarkerVariables, config.getObjectWrapper()));
		}

		if (this.defaultEncoding != null) {
			config.setDefaultEncoding(this.defaultEncoding);
		}

		List<TemplateLoader> templateLoaders = new LinkedList<TemplateLoader>(this.templateLoaders);

		if (this.templateLoaderPaths != null) {
			for (String path : this.templateLoaderPaths) {
				templateLoaders.add(getTemplateLoaderForPath(path));
			}
		}
		postProcessTemplateLoaders(templateLoaders);

		TemplateLoader loader = getAggregateTemplateLoader(templateLoaders);
		if (loader != null) {
			config.setTemplateLoader(loader);
		}

		postProcessConfiguration(config);
		return config;
	}

	protected Configuration newConfiguration() throws IOException, TemplateException {
		return new Configuration();
	}

	protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
		try {
			File file = null;
			if (templateLoaderPath.startsWith(FileUtils.WEB_INFO_URL_PREFIX)) {
				String realPath = SpringContext.getServletContext().getRealPath(templateLoaderPath);
				file = new File(realPath);
			} else if (templateLoaderPath.startsWith(FileUtils.CLASSPATH_URL_PREFIX)) {
				Resource path = new DefaultResourceLoader().getResource(templateLoaderPath);
				file = path.getFile();
			} else {
				file = new File(templateLoaderPath).getAbsoluteFile();
			}
			if (file != null) {
				return new FileTemplateLoader(file);
			}
		} catch (IOException e) {
			return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
		}
		return null;
	}

	protected void postProcessTemplateLoaders(List<TemplateLoader> templateLoaders) {
	}

	protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {
		int loaderCount = templateLoaders.size();
		switch (loaderCount) {
		case 0:
			return null;
		case 1:
			return templateLoaders.get(0);
		default:
			TemplateLoader[] loaders = templateLoaders.toArray(new TemplateLoader[loaderCount]);
			return new MultiTemplateLoader(loaders);
		}
	}

	protected void postProcessConfiguration(Configuration config) throws IOException, TemplateException {
	}

}
