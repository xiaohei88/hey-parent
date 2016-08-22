package org.heyframework.common.mail;

import java.util.Map;

import org.heyframework.common.util.ReflectUtils;

/**
 * 所有模板的基类
 * 
 * @author h.l
 *
 */
public abstract class MailTemplate {

	public String templateFtl;

	protected Map<String, Object> toMap() {
		return ReflectUtils.describe(this);
	}

	public String getTemplateFtl() {
		return templateFtl;
	}

	public void setTemplateFtl(String templateFtl) {
		this.templateFtl = templateFtl;
	}
}
