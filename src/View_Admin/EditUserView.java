package View_Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClientController.UserController;
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
import java.util.ArrayList;

import javax.swing.JPasswordField;

public class EditUserView extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textEmail;
	private JTextField textPhone;
	private User user;
	
	
	public EditUserView(ArrayList<User> list, DefaultTableModel model,int row) {
		user= list.get(row);	
		
		setBounds(100, 100, 675, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// mau
		Color green = new Color(49, 135, 45);

		JButton btnSignIn = new JButton("Edit");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User u = new User(user.getId(), user.getUsername(), new HashFunction().hash(passwordField.getText()),textEmail.getText(), textPhone.getText(),user.getDecentralize(),user.getIsActive());
				if(new UserController().update(u)!=0) {
					list.set(row, u);
					model.insertRow(row, new Object[] { u.getId(), u.getUsername(), u.getEmail(),u.getPhone(),"USER"});
					model.removeRow(row+1);
					model.fireTableDataChanged();
					JOptionPane.showMessageDialog(null, "Update successful");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error");

				}
				
				
				
			}
		});
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(197, 491, 107, 44);
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

		JLabel lblNewLabel = new JLabel("Edit user info");
		lblNewLabel.setBounds(227, 29, 251, 51);
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
		
		JLabel lblID = new JLabel("");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID.setBounds(142, 127, 387, 38);
		contentPane.add(lblID);
		lblID.setText(user.getId()+"");
		textEmail.setText(user.getEmail()+"");
		textPhone.setText(user.getPhone()+"");
		passwordField.setText(user.getUsername()+"");
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setText(user.getUsername()+"");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(142, 195, 387, 38);
		contentPane.add(lblUsername);
		
		JButton btnSignIn_1 = new JButton("Cancel");
		btnSignIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSignIn_1.setForeground(Color.WHITE);
		btnSignIn_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignIn_1.setBackground(new Color(51, 204, 204));
		btnSignIn_1.setBounds(335, 491, 107, 44);
		contentPane.add(btnSignIn_1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

	}
}
