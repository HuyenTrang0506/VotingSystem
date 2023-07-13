package Tool;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

	public static void sendEmail(String email, String Otp) {

		String senderEmail = "bankloverboy@gmail.com";
        String senderPassword = "xgdjkhjlmxdrnvqb";

		String recipientEmail = email;

		String smtpHost = "smtp.gmail.com";
		String smtpPort = "587";

		String subject = "Your new password is: ";
		String body = Otp;

		Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			System.out.println("Failed to send email. Error: " + e.getMessage());
		}
	}
	
}