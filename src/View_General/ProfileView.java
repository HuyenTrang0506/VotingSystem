package View_General;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientController.UserController;
import Models.User;
import Tool.HashFunction;

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

import javax.swing.JPasswordField;

public class ProfileView extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textEmail;
	private JTextField textPhone;
	
	private User userSession;
	
	public ProfileView(User userSession) {
		this.userSession= userSession;
		setBounds(100, 100, 675, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// mau
		Color green = new Color(49, 135, 45);

		JButton btnSignIn = new JButton("Update");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User(userSession.getId(), userSession.getUsername(), new HashFunction().hash(passwordField.getText()),textEmail.getText(), textPhone.getText(),userSession.getDecentralize(),userSession.getIsActive());
				if(new UserController().update(u)!=0) {
					JOptionPane.showMessageDialog(null, "Update successful");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error");

				}
				
				
				
			}
		});
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(203, 493, 107, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setBackground(new Color(51, 204, 204));
		
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(142, 161, 127, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1.setBounds(142, 242, 107, 38);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.setBounds(268, 29, 140, 51);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(142, 277, 387, 33);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		lblNewLabel_1_2.setForeground(Color.GRAY);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(142, 320, 127, 38);
		contentPane.add(lblNewLabel_1_2);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textEmail.setColumns(10);
		textEmail.setBounds(142, 358, 387, 31);
		contentPane.add(textEmail);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPhone.setColumns(10);
		textPhone.setBounds(142, 429, 387, 31);
		contentPane.add(textPhone);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Phone: ");
		lblNewLabel_1_2_1.setForeground(Color.GRAY);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(142, 394, 127, 38);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setForeground(Color.GRAY);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(142, 90, 127, 38);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2 = new JLabel("<-Back");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
				
			}
		});
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(25, 10, 93, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblID = new JLabel("");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID.setBounds(142, 127, 387, 38);
		contentPane.add(lblID);
		lblID.setText(userSession.getId()+"");
		textEmail.setText(userSession.getEmail()+"");
		textPhone.setText(userSession.getPhone()+"");
		passwordField.setText(userSession.getUsername()+"");
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setText(userSession.getUsername()+"");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(142, 195, 387, 38);
		contentPane.add(lblUsername);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(51, 204, 204));
		btnCancel.setBounds(341, 493, 107, 44);
		contentPane.add(btnCancel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

	}
}
