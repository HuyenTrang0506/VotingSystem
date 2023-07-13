package View_Login;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientController.UserController;
import DAO.UserDAO;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JPasswordField;

public class SignUpView extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField name;
	private JPasswordField passwordField;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textFieldID;

	public SignUpView() {

		setBounds(100, 100, 675, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton btnSignIn = new JButton("Sign up");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textFieldID.getText());
				int flag = 0;
				// nếu tồn tại rồi nhưng bị deActive=>update, flag = 1
				// tồn tại rồi và active, flag =2
				// chưa tổn tại, flag = 0
				String username = name.getText();

				String password = new HashFunction().hash(passwordField.getText());
				String email = textEmail.getText();
				String phone = textPhone.getText();
				int decentralize = 2;
				for (User k : new UserController().selectAll()) {
					// nếu tồn tại rồi nhưng bị deActive=>update
					if (k.getId() == id && k.getIsActive().equalsIgnoreCase("deActive")) {

						flag = 1;
						break;
					}
					// nếu tồn tại rồi và ko bị deActive
					else if (k.getId() == id && k.getIsActive().equalsIgnoreCase("active")) {

						flag = 2;
						break;
					}

				}

				if (flag == 0) {

					User user = new User(id, username, password, email, phone, decentralize, "active");
					if (new UserController().insert(user) != 0) {
						JOptionPane.showMessageDialog(null, "Success!");
						dispose();
						new SignInView();

					} else {
						JOptionPane.showMessageDialog(null, "Failure!");
					}
				} else if (flag == 1) {
					User user = new User(id, username, password, email, phone, decentralize, "active");
					if (new UserController().update(user) != 0) {
						JOptionPane.showMessageDialog(null, "Success!");
						dispose();
						new SignInView();
					}
				} else if (flag == 2) {
					JOptionPane.showMessageDialog(null, "ID existed");
				}

			}
		});
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(271, 492, 107, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setBackground(new Color(51, 204, 204));

		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setBounds(142, 167, 127, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		name = new JTextField();
		name.setBounds(142, 199, 387, 31);
		contentPane.add(name);
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(Color.GRAY);
		lblNewLabel_1_1.setBounds(142, 242, 107, 38);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Sign Up");
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

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldID.setColumns(10);
		textFieldID.setBounds(142, 126, 387, 31);
		contentPane.add(textFieldID);

		JLabel lblNewLabel_2 = new JLabel("<-Back");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new SignInView();

			}

		});
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(25, 10, 93, 38);
		contentPane.add(lblNewLabel_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}
	// hàm mã hóa md5

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
