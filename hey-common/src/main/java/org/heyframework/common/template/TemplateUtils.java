package org.heyframework.common.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.heyframework.common.util.FileUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class TemplateUtils {

	/**
	 * 格式化模板返回字符串
	 * 
	 * @param template
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String processTemplateIntoString(Template template, Object model) throws IOException,
			TemplateException {
		StringWriter result = new StringWriter();
		template.process(model, result);
		return result.toString();
	}

	/**
	 * 格式化模板生成文件
	 * 
	 * @param template
	 * @param file
	 * @param model
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void processTemplateIntoFile(Template template, File file, Object model) throws IOException,
			TemplateException {
		if (!file.exists()) {
			FileUtils.createFile(file);
		}
		Writer out = new OutputStreamWriter(new FileOutputStream(file));
		template.process(model, out);
		out.flush();
		out.close();
	}
}
