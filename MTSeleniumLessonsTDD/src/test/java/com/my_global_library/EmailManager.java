package com.my_global_library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EmailManager {

	final static Logger logger = Logger.getLogger(EmailManager.class);

	private String toAddress = "";
	private String ccAddress = "";
	private String bccAddress = "";

	private void setToAddress(String toEmails) {
		toAddress = toEmails;
	}

	private InternetAddress[] setMultipleEmails(String emailAddresses) {
		String multipleEmails[] = emailAddresses.split(";");
		InternetAddress[] addresses = new InternetAddress[multipleEmails.length];
		try {
			for (int i = 0; i < multipleEmails.length; i++) {
				addresses[i] = new InternetAddress(multipleEmails[i]);
			}
		} catch (AddressException e) {
			logger.error("Adding multiple email addresses error: " + e);
		}
		return addresses;
	}

	public void sendEmail() {
		List<String> attachments = new ArrayList<>();
		String emailMessageBody = "Test email by JavaMail API example."
				+ "<br><br> Regards, <br>Test Automation Team<br>";
		setToAddress("baku.2012.2015@gmail.com;m.abasguli@gmail.com");
		sendEmail("smtp.gmail.com", "587", "qatesters2018@gmail.com", "Aze122072", "Test Automation", emailMessageBody,
				attachments);
	}

	public void sendEmail(List<String> attachments) {
		String emailMessageBody = "This is testing purpose!" + "<br><br> Regards, <br> Test Automation Team <br>";
		setToAddress("baku.2012.2015@gmail.com;m.abasguli@gmail.com");
		sendEmail("smtp.gmail.com", "587", "qatesters2018@gmail.com", "Aze122072", "Test Automation", emailMessageBody,
				attachments);
	}

	public void sendEmail (String host, String port, String emailUserId, String emailUserPassword, String subject,
			String emailBody, List<String> attachments) {

		try {
			// sets SMTP server properties
			Properties prop = new Properties();
			prop.put("mail.smtp.host", host);
			prop.put("smtp.port", port);
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable", true);
			prop.put("mail.user", emailUserId);
			prop.put("mail.password", emailUserPassword);

			logger.info("Step1-> preparing email configuration...");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailUserId, emailUserPassword);
				}
			};
			Session session = Session.getInstance(prop, auth);

			// creates a new email message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailUserId));
			msg.addRecipients(Message.RecipientType.TO, setMultipleEmails(toAddress));

			if (!ccAddress.isEmpty() && ccAddress.equals(null)) {
				msg.addRecipients(Message.RecipientType.CC, setMultipleEmails(ccAddress));
			}
			if (!bccAddress.isEmpty() && bccAddress.equals(null)) {
				msg.addRecipients(Message.RecipientType.BCC, setMultipleEmails(bccAddress));
			}
			msg.setSubject(subject);
			msg.setSentDate(new Date());

			// crearte message part
			MimeBodyPart messageBodypart = new MimeBodyPart();
			messageBodypart.setContent(emailBody, "text/html");

			// create multy-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodypart);

			// adds attachments
			if (attachments.size() > 0 && attachments != null) {
				for (String singlAttachment : attachments) {
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						attachPart.attachFile(singlAttachment);
					} catch (IOException e) {
						logger.error("Attaching file to email failed...");
					}
					multipart.addBodyPart(attachPart);
				}
			}

			logger.info("Step 2 -> Attaching report files and screenshot...");

			// sents multi-part as email's content
			msg.setContent(multipart);

			// sends email
			logger.info("Step 3-> Sending email is progress...");

			Transport.send(msg);

			logger.info("Step 4-> Sending email complete...");

		} catch (Exception e) {
			logger.info("Sending email faild..." + e);
		}

	}

	public static void main (String[] args) {
		// option 1: with attachments

		EmailManager emailSender = new EmailManager();
		List<String> files = new ArrayList<>();
		files.add("C:/Program Files (x86)/Java/Week4WebDriver-class/Week4WebDriver-class/target/images/Screen1");
		files.add("C:/Program Files (x86)/Java/Week4WebDriver-class/Week4WebDriver-class/target/images/Screen2");
		emailSender.sendEmail(files);

		// option2: without attachments
		EmailManager emailSender2 = new EmailManager();
		emailSender2.sendEmail();
	}

}
