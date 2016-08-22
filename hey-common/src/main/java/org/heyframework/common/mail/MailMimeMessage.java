package org.heyframework.common.mail;

import java.io.IOException;

import javax.activation.FileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.heyframework.common.template.TemplateUtils;
import org.heyframework.common.util.AssertUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailMimeMessage extends MimeMessageHelper {

	private final Log logger = LogFactory.getLog(getClass());

	public MailMimeMessage(MimeMessage mimeMessage) throws MessagingException {
		super(mimeMessage, true, "utf-8");
	}

	public MailMimeMessage(MimeMessage mailMessage, boolean multipart) throws MessagingException {
		super(mailMessage, multipart);
	}

	public MailMimeMessage(MimeMessage mailMessage, boolean multipart, String encoding) throws MessagingException {
		super(mailMessage, multipart, encoding);
	}

	public void addImage(String fileName, InputStreamSource inputStreamSource) throws MessagingException {
		FileTypeMap fileTypeMap = super.getFileTypeMap();
		// image/gif、 image/png 、image/jpeg
		super.addInline("image", inputStreamSource, fileTypeMap.getContentType(fileName));
	}

	public void setText(String text) throws MessagingException {
		super.setText(text);
	}

	public void setTextHtml(String text) throws MessagingException {
		super.setText(text, true);
	}

	public void setTextTemplate(MailTemplate mailTemplate) throws MessagingException {
		AssertUtils.isNull(mailTemplate, "mailTemplate:邮件模板不能为空.");
		Template tpl = MailSender.getTemplate(mailTemplate.getTemplateFtl());
		try {
			setTextHtml(TemplateUtils.processTemplateIntoString(tpl, mailTemplate.toMap()));
		} catch (IOException e) {
			logger.error("读取模板错误", e);
		} catch (TemplateException e) {
			logger.error("解析模板错误", e);
		}
	}
}
