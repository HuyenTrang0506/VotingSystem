package ClientController;

import Tool.SendMail;

public class SendMailControl {
	public void sendEmail(String email, String newPass) {
		new SendMail().sendEmail(email,newPass);
	}
}
