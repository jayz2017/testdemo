package com.demo.activiti.server.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailListener implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String senderAddress = "sender@163.com";
	public static String recipientAddress = "recipient@qq.com";
	public static String senderAccount = "sender@163.com";
	public static String senderPassword = "senderpassword";

	@Override
	public void notify(DelegateTask delegateTask) {
		try {
			Session session = createSession("true", "smtp", "smtp.163.com");
			Message msg = getMimeMessage(session);
			Transport transport = session.getTransport();
			transport.connect(senderAccount, senderPassword);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Session createSession(String auth, String protocol, String address) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.163.com");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		return session;
	}

	public MimeMessage getMimeMessage(Session session) throws AddressException, MessagingException {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(senderAddress));
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
		msg.setSubject("邮件主题", "UTF-8");
		msg.setContent("简单的纯文本邮件！", "text/html;charset=UTF-8");
		msg.setSentDate(new Date());
		return msg;
	}
}
