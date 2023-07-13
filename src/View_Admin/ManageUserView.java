package View_Admin;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Tool.HashFunction;
import ClientController.DateController;
import ClientController.PollController;
import ClientController.UserController;
import ClientController.VoteController;
import ClientController.VotersController;
import DAO.*;
import Models.Poll;
import Models.User;
import Models.Voters;
import Models.Vote;
import View_Login.SignInView;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Date;
import java.text.ParseException;
import java.awt.event.MouseAdapter;
import View_Admin.*;
import View_General.*;

public class ManageUserView extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch;
	private ArrayList<User> listUserActive;
	private ArrayList<User> list;
	private DefaultTableModel model;
	private int getRow;
	private JComboBox comboBox;
	private User userSession;
	private JTextField textFieldFile;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ManageUserView(String s, User userSession) {

		super(s);
		this.userSession = userSession;
		listUserActive = new ArrayList<User>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 708);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.addMouseListener(this);
		list = new UserController().selectAll();
		for (User u : list) {
			if (u.getIsActive().equalsIgnoreCase("active")) {
				listUserActive.add(u);
			}
		}
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Username");
		model.addColumn("Email");
		model.addColumn("Phone");
		model.addColumn("Decentralize");
		String decentral = "";
		for (User p : listUserActive) {
			if (p.getDecentralize() == 1) {
				decentral = "ADMIN";

			} else {
				decentral = "USER";
			}
			model.addRow(new Object[] { p.getId(), p.getUsername(), p.getEmail(), p.getPhone(), decentral });
		}
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder("List Users"));
		scrollPane.setBounds(260, 83, 793, 331);

		contentPane.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(51, 102, 204));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(813, 25, 68, 37);
		contentPane.add(lblNewLabel_1);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldSearch.setBounds(884, 25, 169, 37);
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String ss = textFieldSearch.getText();
				search(ss);
			}
		});
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 239, 667);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 153, 255));
		panel_1.setBounds(0, 0, 239, 185);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblAdmin = new JLabel("Voting System");
		lblAdmin.setBackground(new Color(0, 153, 204));
		lblAdmin.setForeground(new Color(255, 255, 255));
		lblAdmin.setBounds(10, 64, 219, 59);
		panel_1.add(lblAdmin);
		lblAdmin.setFont(new Font("VNI-Truck", Font.BOLD, 29));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlightText);
		panel_2.setBounds(0, 184, 239, 59);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel(" All the voting polls");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (userSession.getDecentralize() == 1) {
					new AllPollingView("Administrator " + userSession.getUsername(), userSession);
				} else {
					new AllPollingView("User " + userSession.getUsername(), userSession);
				}
			}
		});
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setBounds(0, 0, 239, 59);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panel_2.add(lblNewLabel);

		JLabel lblHaventTakenPlace = new JLabel("    Haven't taken");
		lblHaventTakenPlace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (userSession.getDecentralize() == 1) {
					new PollHaven_tTaken("Administrator " + userSession.getUsername(), userSession);
				} else {
					new PollHaven_tTaken("User " + userSession.getUsername(), userSession);
				}

			}
		});
		lblHaventTakenPlace.setForeground(new Color(102, 102, 204));
		lblHaventTakenPlace.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblHaventTakenPlace.setBounds(0, 247, 239, 49);
		panel.add(lblHaventTakenPlace);

		JLabel lblInProgress = new JLabel("    In progress");
		lblInProgress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (userSession.getDecentralize() == 1) {
					new PollInProgress("Administrator " + userSession.getUsername(), userSession);
				} else {
					new PollInProgress("User " + userSession.getUsername(), userSession);
				}
			}
		});
		lblInProgress.setForeground(new Color(102, 102, 204));
		lblInProgress.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblInProgress.setBounds(0, 295, 239, 49);
		panel.add(lblInProgress);

		JLabel lblHaveTakenPlace = new JLabel("    Have taken");
		lblHaveTakenPlace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (userSession.getDecentralize() == 1) {
					new PollHaveTaken("Administrator " + userSession.getUsername(), userSession);
				} else {
					new PollHaveTaken("User " + userSession.getUsername(), userSession);
				}
			}
		});
		lblHaveTakenPlace.setForeground(new Color(102, 102, 204));
		lblHaveTakenPlace.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblHaveTakenPlace.setBounds(0, 342, 239, 49);
		panel.add(lblHaveTakenPlace);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(SystemColor.control);
		panel_2_1.setBounds(0, 413, 239, 59);
		panel.add(panel_2_1);

		JLabel lblUsers = new JLabel(" Users");
		lblUsers.setForeground(new Color(100, 149, 237));
		lblUsers.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsers.setBackground(new Color(204, 255, 255));
		lblUsers.setBounds(0, 0, 239, 59);
		panel_2_1.add(lblUsers);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBackground(new Color(255, 255, 255));
		panel_2_2.setBounds(0, 470, 239, 59);
		panel.add(panel_2_2);

		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String decentral = table.getValueAt(getRow, 4).toString();
				if (decentral.equalsIgnoreCase("ADMIN"))
					JOptionPane.showMessageDialog(null, "Cannot edit admin information");
				else
					new EditUserView(listUserActive, model, getRow);
			}
		});
		btnNewButton_1.setBackground(new Color(102, 204, 204));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(260, 583, 384, 44);
		contentPane.add(btnNewButton_1);

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(51, 51, 153));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.addItem("ID");
		comboBox.addItem("Name");

		comboBox.setBounds(604, 25, 159, 37);
		contentPane.add(comboBox);

		JButton btnNewButton_2 = new JButton("Profile");
		btnNewButton_2.setBackground(new Color(102, 204, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileView(new UserController().selectById(1));
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(260, 26, 105, 37);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_1_1 = new JButton("Add admin");
		btnNewButton_1_1.setBackground(new Color(102, 204, 204));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newAdminId = JOptionPane.showInputDialog("Enter user_id").toString();
				User user1 = new UserController().selectById(Integer.parseInt(newAdminId));
				user1.setDecentralize(1);
				String decentral = "";
				if (user1.getDecentralize() == 1) {
					decentral = "ADMIN";

				} else {
					decentral = "USER";
				}
				if (new UserController().update(user1) != 0) {

					int targetColumnIndex = 0; // chỉ số cột cần tìm
					int rowCanTim;
					for (rowCanTim = 0; rowCanTim < model.getRowCount(); rowCanTim++) {
						String cellValue = model.getValueAt(rowCanTim, targetColumnIndex).toString();
						if (cellValue.equals(newAdminId)) {
							break;
						}
					}

					// update trong list
					listUserActive.set(rowCanTim, user1);

					// update trong model
					model.insertRow(rowCanTim, new Object[] { user1.getId(), user1.getUsername(), user1.getEmail(),
							user1.getPhone(), decentral });
					model.removeRow(rowCanTim + 1);
					model.fireTableDataChanged();

					JOptionPane.showMessageDialog(null, "Success");
				}

				else
					JOptionPane.showMessageDialog(null, "Error");

			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(669, 514, 384, 44);
		contentPane.add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Add new user");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddUserView(listUserActive, model);
			}
		});
		btnNewButton_1_1_1.setBackground(new Color(102, 204, 204));
		btnNewButton_1_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_1.setBounds(260, 514, 384, 44);
		contentPane.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1_2 = new JButton("Delete");
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// lấy ra thông tin user
				int user_id = (Integer) table.getValueAt(getRow, 0);
				User user = new UserController().selectById(user_id);
				if (user.getDecentralize() == 1) {
					JOptionPane.showMessageDialog(null, "Cannot delete admin");
					return;
				}
				// lay ra list poll mà user tham gia
				ArrayList<Poll> listPoll = new PollController().selectByUser_Id(user_id);
				Date now = new DateController().dateNow();
				// check từng poll
				for (Poll p : listPoll) {
					// nếu user ở trong 1 cuộc bỏ phiếu p thì lấy ra
					Voters voter = new Voters(user.getId(), p.getId(), user.getUsername());
					// lấy ra những lá phiếu của cuộc bỏ phiếu(nếu có)
					ArrayList<Vote> listVote = new VoteController().selectByPollId(p.getId());
					if (p.getEndTime().compareTo(now) < 0) {
						// lá phiếu của cuộc bỏ phiếu đã diễn ra được giữ lại, quyền voter cũng được giữ

					} else if (p.getStartTime().compareTo(now) < 0) {
						// chua dien ra =>xóa quyền voter, giảm số người tgia cuộc bỏ phiếu

						if (new VotersController().delete(voter) != 0) {
							int newVoters = p.getMaxVotes() - 1;
							p.setMaxVotes(newVoters);
							if (new PollController().update(p) == 0) {
								JOptionPane.showMessageDialog(null, "Cannot update poll");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Cannot delete voter");
						}

					} else {
						// đang diễn ra=> xóa quyền voter, xóa lá phiếu nếu user đã bỏ phiếu
						if (new VotersController().delete(voter) != 0) {
							// xóa lá phiếu của user
							for (Vote vv : listVote) {
								if (vv.getUser_id() == user.getId()) {
									if (new VoteController().delete(vv) == 0) {
										JOptionPane.showMessageDialog(null, "delete vote failure");
									}
								}
							}
							int newVoters = p.getMaxVotes() - 1;
							p.setMaxVotes(newVoters);
							if (new PollController().update(p) == 0) {
								JOptionPane.showMessageDialog(null, "Cannot update poll");

							}
						} else {
							JOptionPane.showMessageDialog(null, "Cannot delete voter");
						}

					}

				} // xóa hết các liên kết rồi thì xóa user
				user.setIsActive("deActive");
				if (new UserController().update(user) != 0) {
					listUserActive.remove(getRow);
					model.removeRow(getRow);
					JOptionPane.showMessageDialog(null, "Success");
				}

			}
		});
		btnNewButton_1_1_2.setBackground(new Color(102, 204, 204));
		btnNewButton_1_1_2.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_2.setBounds(669, 585, 384, 41);
		contentPane.add(btnNewButton_1_1_2);

		JButton btnNewButton_3 = new JButton("Sort by");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clone = comboBox.getSelectedItem().toString();
				new SortUserView(listUserActive, clone);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBackground(new Color(102, 204, 204));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(495, 26, 99, 37);
		contentPane.add(btnNewButton_3);

		textFieldFile = new JTextField();
		textFieldFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldFile.setColumns(10);
		textFieldFile.setBounds(495, 441, 321, 43);
		contentPane.add(textFieldFile);

		JButton btnNewButtonAddFile = new JButton("Choose file");
		btnNewButtonAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					textFieldFile.setText(path);

				}

			}

		});
		btnNewButtonAddFile.setForeground(Color.WHITE);
		btnNewButtonAddFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButtonAddFile.setBackground(new Color(102, 204, 204));
		btnNewButtonAddFile.setBounds(260, 441, 227, 44);
		contentPane.add(btnNewButtonAddFile);

		JButton btnOk = new JButton("Add user from file");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String path = textFieldFile.getText().toString();
					FileReader reader = new FileReader(path);
					BufferedReader bufferedReader = new BufferedReader(reader);

					String line;
					while ((line = bufferedReader.readLine()) != null) {

						String listInfo[] = line.split(",");// 5 thông tin, 6 là 2, 7 là active
						int id = Integer.parseInt(listInfo[0]);
						String name = listInfo[1];
						String pass = HashFunction.hash(listInfo[2]);
						String email = listInfo[3];
						String phone = listInfo[4];
						int decentralize = 2;
						String active = "active";
						User userRead = new User(id, name, pass, email, phone, decentralize, active);

						if (new UserController().insert(userRead) == 0) {
							JOptionPane.showMessageDialog(null, "Failure");
						} else {
							String decentral = "USER";
							model.addRow(new Object[] { id, name, email, phone, decentral });
							model.fireTableChanged(null);
							listUserActive.add(userRead);
						}

					}
					reader.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnOk.setForeground(Color.WHITE);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOk.setBackground(new Color(102, 204, 204));
		btnOk.setBounds(826, 441, 227, 44);
		contentPane.add(btnOk);
		this.setVisible(true);
	}

	public void search(String Str) {
		DefaultTableModel modelClone = (DefaultTableModel) table.getModel();

		TableRowSorter<DefaultTableModel> defaultTableModel = new TableRowSorter<>(modelClone);
		table.setRowSorter(defaultTableModel);

		defaultTableModel.setRowFilter(RowFilter.regexFilter(Str));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getRow = Integer.valueOf(table.getSelectedRow());
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
