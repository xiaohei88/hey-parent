package org.heyframework.common.mail;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSenderImpl extends JavaMailSenderImpl {
	private String mailTemplatePath;

	public String getMailTemplatePath() {
		return mailTemplatePath;
	}

	public void setMailTemplatePath(String mailTemplatePath) {
		this.mailTemplatePath = mailTemplatePath;
	}
}
