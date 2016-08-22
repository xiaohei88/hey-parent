package org.heyframework.common.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.heyframework.common.spring.SpringContext;
import org.heyframework.common.template.TemplateConfigurer;
import org.heyframework.common.util.AssertUtils;

import freemarker.template.Template;

public class MailSender {

	/** 邮件发送器 */
	private static final MailSenderImpl mailSender = SpringContext.getJavaMailSender();

	/** 模板配置器 */
	public static final TemplateConfigurer templateConfigurer = SpringContext.getTemplateConfigurer();

	/**
	 * 获取模板
	 * 
	 * @param templeteName
	 * @return
	 */
	public static Template getTemplate(String templeteName) {
		AssertUtils.isNull(templateConfigurer, "模板配置器为空,加载失败");
		return templateConfigurer.getTemplate(templeteName);
	}

	/**
	 * 创建消息
	 * 
	 * @return
	 */
	public static MimeMessage createMimeMessage() {
		if (mailSender != null) {
			return mailSender.createMimeMessage();
		}
		return null;
	}

	/**
	 * 邮件发送
	 * 
	 * @param mimeMessage
	 * @throws MessagingException
	 */
	public static void send(MimeMessage mimeMessage) throws MessagingException {
		mailSender.send(mimeMessage);
	}
}