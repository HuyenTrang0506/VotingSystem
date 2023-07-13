package View_Login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientController.UserController;
import DAO.UserDAO;
import Models.User;
import Tool.HashFunction;
import View_General.AllPollingView;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JPasswordField;
import Tool.HashFunction;

public class SignInView extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField passwordField;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User userSelect) {
		this.user = userSelect;
	}

	public static void main(String[] args) {
		new SignInView();
	}

	public SignInView() {
	
		setBounds(100, 100, 786, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(240, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = name.getText();
				new HashFunction();
				String password = HashFunction.hash(passwordField.getText());
				user = new UserController().selectByNameAndPass(username, password);
				
				if (user != null) {		
					if(user.getIsActive().equalsIgnoreCase("active")) {
						if (user.getDecentralize() == 1) {
							
							new AllPollingView("Administrator " +user.getUsername(),user);
							
						} else if (user.getDecentralize() == 2) {
							new AllPollingView("User " +user.getUsername(),user);
						}dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Sign in failed!");
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Sign in failed!");
				}

			}
		});
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(310, 299, 107, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setBackground(new Color(51, 204, 204));

		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(22, 91, 18));
		lblNewLabel_1.setBounds(131, 131, 127, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		name = new JTextField();
		name.setBounds(289, 135, 308, 31);
		contentPane.add(name);
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(new Color(22, 91, 18));
		lblNewLabel_1_1.setBounds(131, 200, 127, 38);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setBounds(289, 42, 140, 51);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JLabel lblNewLabel_2 = new JLabel("If you don't have account->");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewLabel_2.setBounds(289, 393, 251, 31);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Register now!");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new SignUpView();

			}
		});

		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(539, 393, 133, 31);
		contentPane.add(lblNewLabel_3);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(289, 200, 308, 33);
		contentPane.add(passwordField);

		JLabel lblNewLabel_4 = new JLabel("Forgot password?");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgotPassword f = new ForgotPassword();
				f.setVisible(true);
				

			}
		});
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(433, 243, 164, 31);
		contentPane.add(lblNewLabel_4);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ("Refresh".equals(e.getActionCommand())) {
			this.dispose();
			new SignInView();

		}

	}
}
