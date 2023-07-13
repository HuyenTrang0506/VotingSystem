package View_Admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ClientController.ChoiceController;
import ClientController.DateController;
import ClientController.PollController;
import ClientController.UserController;
import ClientController.VotersController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DAO.ChoiceDAO;
import DAO.VotersDAO;
import DAO.PollDAO;
import DAO.UserDAO;
import Models.Choice;
import Models.Voters;
import View_General.AllPollingView;
import Models.Poll;
import Models.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;

public class CreatePollingView extends JFrame implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JTable tableChoice, table1Voter;
	private JTextField textTitle;
	private JTextField textDescrip;
	private JTextField textStart;
	private JTextField textEnd;
	private ArrayList<Choice> listChoices;
	private ArrayList<User> listVoters;
	private DefaultTableModel modelChoice, modelVoter;
	private JScrollPane scrollPane;
	private int choicesIndex;
	private int max_voters;
	private int max_choices;
	private User userSession;

	public int getMax_choices() {
		return choicesIndex;
	}

	public void setMax_choices(int max_choices) {
		this.choicesIndex = max_choices;
	}

	public int getMax_voters() {
		return max_voters;
	}

	public void setMax_voters(int max_voters) {
		this.max_voters = max_voters;
	}

	private String decentral;
	private Integer getRowListVoter;
	private Integer getRowListChoice;
	private JTextField textID;


	private void init(User userSession) {
		this.userSession=userSession;
		listChoices = new ArrayList<Choice>();
		listVoters = new ArrayList<User>();
		choicesIndex = 0;
		max_voters = 0;
	}

	public CreatePollingView(User userSession) {

		init(userSession);
		for(User i:new UserController().selectAll()) {
			if(i.getDecentralize()==1) {
				listVoters.add(i);

			}
		}
		
		setBounds(100, 100, 1127, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSignIn = new JButton("Submit");
		btnSignIn.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				boolean flag = true;		
				
				int id = Integer.parseInt(textID.getText());				
				String title = textTitle.getText().toString();
				String descript = textDescrip.getText().toString();		
				max_voters = listVoters.size();
				max_choices= listChoices.size();
				if(max_voters<2||max_choices<2) {
					JOptionPane.showMessageDialog(null, "Number of voters or number of choices cannot less than 2");
					flag=false;
									}
				
				//check id ton tai chưa
						
				for (Poll p : new PollController().selectAll()) {
					if (p.getId() == id) {
						JOptionPane.showMessageDialog(null, "ID existed");
						flag = false;
						break;
					}
				}			
				try {		
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date dateStart = dateFormat.parse(textStart.getText().toString());
					Date dateEnd = dateFormat.parse(textEnd.getText().toString());
					
					// so sanh thoi gian
					Date nowDate = dateFormat.parse(dateFormat.format(new DateController().dateNow()));
					 if (dateStart.compareTo(dateEnd) >= 0) {
						JOptionPane.showMessageDialog(null, "Start time must be less than end time");
					} else if (flag){
						Poll poll = new Poll(id, title, descript, dateStart, dateEnd, max_choices, max_voters);
						// nếu lưu cuộc bỏ phiếu thành công
						if (new PollController().insert(poll) != 0) {
							// lưu xuống bảng voter
							for (User u : listVoters) {
								Voters mem = new Voters(u.getId(), poll.getId(), u.getUsername());
								if (new VotersDAO().insert(mem) == 0) {
									JOptionPane.showMessageDialog(null, "Cannot insert member");
								}
							}
							// lưu xuống bảng choice
							for (Choice c : listChoices) {
								Choice choice = new Choice(poll.getId(), c.getContent());
								if (new ChoiceDAO().insert(choice) == 0) {
									JOptionPane.showMessageDialog(null, "Cannot insert choice");
								}
							}
							
							JOptionPane.showMessageDialog(null, "Create successful");
							

						} else {
							JOptionPane.showMessageDialog(null, "Failure");
						}
					}

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}

			}
		});

		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(416, 635, 107, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setBackground(new Color(51, 153, 204));

		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1.setBounds(154, 113, 72, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Create new voting poll ");
		lblNewLabel.setBounds(366, 10, 419, 51);
		contentPane.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));

		JLabel lblNewLabel_1_1 = new JLabel("Description");
		lblNewLabel_1_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(43, 214, 138, 38);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Start_time");
		lblNewLabel_1_2_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBounds(583, 113, 138, 38);
		contentPane.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("End_time");
		lblNewLabel_1_2_1_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(583, 214, 138, 38);
		contentPane.add(lblNewLabel_1_2_1_1);

		JButton btnNewButton = new JButton("Add ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choicesIndex++;
				String input = JOptionPane.showInputDialog("Input new choice");
				Choice choice = new Choice();
				choice.setContent(input);
				Boolean co = true;
				for (Choice c : listChoices) {
					if (c.getContent().equalsIgnoreCase(input)) {
						co = false;
						break;
					}
				}
				if (co) {
					listChoices.add(choice);
					modelChoice.addRow(new Object[] { choicesIndex, input });
					JOptionPane.showMessageDialog(null, "Success");

				} else {
					JOptionPane.showMessageDialog(null, "Choice existed!");
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(583, 580, 223, 38);
		contentPane.add(btnNewButton);

		JButton btnAddChoice = new JButton("Add ");
		btnAddChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input = JOptionPane.showInputDialog("Enter user_id");

				User user = new UserDAO().selectById(Integer.parseInt(input));

				if (user == null) {
					JOptionPane.showMessageDialog(null, "Id doesn't exist!");
				} else {
					if (user.getDecentralize() == 2) {
						decentral = "USER";
					}
					boolean flag = true;
					for (User u : listVoters) {
						if (u.getId() == Integer.parseInt(input)) {
							JOptionPane.showMessageDialog(null, "User existed!");
							flag = false;
							break;
						}

					}
					if (flag) {

						listVoters.add(user);
						modelVoter.addRow(new Object[] { user.getId(), user.getUsername(), decentral });
						JOptionPane.showMessageDialog(null, "Success");
					}
				}

			}
		});
		btnAddChoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddChoice.setBounds(45, 580, 223, 38);
		contentPane.add(btnAddChoice);

		tableChoice = new JTable();
		tableChoice.addMouseListener(this);
		modelChoice = new DefaultTableModel();
		modelChoice.addColumn("Id");
		modelChoice.addColumn("Content");
		choicesIndex = 0;
		for (Choice c : listChoices) {
			modelChoice.addRow(new Object[] { choicesIndex, c.getContent() });
			choicesIndex++;
		}
		tableChoice.setModel(modelChoice);

		scrollPane = new JScrollPane(tableChoice);
		scrollPane.setBounds(583, 337, 475, 195);
		scrollPane.setBorder(new TitledBorder("List Choice"));
		contentPane.add(scrollPane);

		table1Voter = new JTable();
		table1Voter.addMouseListener(this);
		modelVoter = new DefaultTableModel();
		modelVoter.addColumn("User_id");
		modelVoter.addColumn("Username");
		modelVoter.addColumn("Decentralize");

		for (User c : listVoters) {
			if (c.getDecentralize() == 1) {
				decentral = "ADMIN";
			}
			modelVoter.addRow(new Object[] { c.getId(), c.getUsername(), decentral });

		}
		table1Voter.setModel(modelVoter);

		scrollPane = new JScrollPane(table1Voter);

		JScrollPane scrollPane_1 = new JScrollPane(table1Voter);

		scrollPane_1.setBounds(48, 337, 475, 195);
		scrollPane_1.setBorder(new TitledBorder("List Voters"));
		contentPane.add(scrollPane_1);

		textTitle = new JTextField();
		textTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textTitle.setBounds(154, 151, 369, 42);
		contentPane.add(textTitle);
		textTitle.setColumns(10);

		textDescrip = new JTextField();
		textDescrip.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textDescrip.setColumns(10);
		textDescrip.setBounds(45, 262, 478, 42);
		contentPane.add(textDescrip);

		textStart = new JTextField();
		textStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textStart.setColumns(10);
		textStart.setBounds(580, 151, 478, 42);
		contentPane.add(textStart);

		textEnd = new JTextField();
		textEnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textEnd.setColumns(10);
		textEnd.setBounds(580, 262, 478, 42);
		contentPane.add(textEnd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int layRow = getRowListVoter;
				// xoa trong list
				listVoters.remove(layRow);

				// xoa trong bang
				modelVoter.removeRow(getRowListVoter);

			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove.setBounds(297, 580, 223, 38);
		contentPane.add(btnRemove);

		JButton btnRemove_1 = new JButton("Remove");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choicesIndex--;
				// xoa trong list
				int rowChoice = getRowListChoice;
				listChoices.remove(rowChoice);
				// xoa trong bang
				modelChoice.removeRow(getRowListChoice);
				// chỉnh sửa id trong bảng (id đếm số cho đẹp mắt chứ ko lưu vào đâu)

				for (int j = getRowListChoice; j < listChoices.size(); j++) {

					// đưa hàng dưới vị trí cần xóa lên hàng trên
					modelChoice.insertRow(j, new Object[] { j + 1, listChoices.get(j).getContent() });
					modelChoice.removeRow(j + 1);

				}

			}
		});
		btnRemove_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemove_1.setBounds(835, 580, 223, 38);
		contentPane.add(btnRemove_1);

		textID = new JTextField();
		textID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textID.setColumns(10);
		textID.setBounds(45, 151, 99, 42);
		contentPane.add(textID);

		JLabel lblNewLabel_1_2 = new JLabel("ID");
		lblNewLabel_1_2.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(43, 113, 72, 38);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("(yyyy-MM-dd HH:mm:ss)");
		lblNewLabel_1_2_1_2.setForeground(Color.GRAY);
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_2_1_2.setBounds(684, 113, 223, 38);
		contentPane.add(lblNewLabel_1_2_1_2);

		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("(yyyy-MM-dd HH:mm:ss)");
		lblNewLabel_1_2_1_2_1.setForeground(Color.GRAY);
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_2_1_2_1.setBounds(684, 214, 223, 38);
		contentPane.add(lblNewLabel_1_2_1_2_1);
		
		JLabel lblGoBack = new JLabel("<-Back ");
		lblGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new AllPollingView("Administrator " +userSession.getUsername(),userSession);
			}
		});
		lblGoBack.setForeground(new Color(102, 51, 255));
		lblGoBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGoBack.setBounds(43, 19, 82, 42);
		contentPane.add(lblGoBack);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AllPollingView("Administrator " +userSession.getUsername(),userSession);
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(51, 153, 204));
		btnCancel.setBounds(566, 635, 107, 44);
		contentPane.add(btnCancel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	public void mouseClicked(MouseEvent e) {
		getRowListVoter = Integer.valueOf(table1Voter.getSelectedRow());
		getRowListChoice = Integer.valueOf(tableChoice.getSelectedRow());

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
