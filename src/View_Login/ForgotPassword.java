package View_Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientController.SendMailControl;
import ClientController.UserController;
import Models.User;
import Tool.SendMail;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your email");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(68, 48, 228, 33);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(68, 98, 311, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send new pass");
		btnNewButton.setBackground(new Color(102, 204, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textField.getText();
				User user = new UserController().selectByEmail(email);
				if(user!=null) {
					String newPass = generateOTP();
					String hashPass = hash(newPass);
					new SendMailControl().sendEmail(email,newPass);
					user.setPassword(hashPass);
					new UserController().update(user);
					JOptionPane.showMessageDialog(null, "Please check your email for new password ");
					dispose();
				
				}else {
					JOptionPane.showMessageDialog(null, "Can not find your account");
				}
				
				
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(128, 169, 168, 45);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("<-Back");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 0, 93, 38);
		contentPane.add(lblNewLabel_1);
	}
	public static String hash(String input) {
        try {
            // Create a MessageDigest object for MD5 hashing
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add the input string to the digest
            md.update(input.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert the bytes to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

	
	private String generateOTP() {
		String captchaString = "qwertyuiopasdfghjklzxcvbnm1234567890";
		String captcha = "";
		for (int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * captchaString.length());
			captcha = captcha + captchaString.charAt(index);
		}
		return captcha;
	}
	public static void main(String[] args) {
		
	}
}
