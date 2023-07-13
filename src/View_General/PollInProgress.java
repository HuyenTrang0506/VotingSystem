package View_General;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ClientController.DateController;
import ClientController.PollController;


import Models.Poll;
import Models.User;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.Date;
import java.text.ParseException;
import java.awt.event.MouseAdapter;
import View_Admin.*;

public class PollInProgress extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch;
	private ArrayList<Poll> listPoll;
	private ArrayList<Poll> listPollInProgress;
	private DefaultTableModel model;
	private int getRow;
	private JComboBox comboBox;
	private  User userSession;
	private boolean isVisible;
/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PollInProgress(String s, User userSession) {
		
		super(s);
		this.userSession=userSession;
		listPollInProgress= new ArrayList<Poll>();
		isVisible = true;
		if(userSession.getDecentralize()==2) {
			isVisible=false;
			listPoll= new PollController().selectByUser_Id(userSession.getId());
		}else {
			listPoll= new PollController().selectAll();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 708);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.addMouseListener(this);
		listPoll= new PollController().selectAll();
		Date now = new DateController().dateNow();
		for(Poll p:listPoll) {
			if (p.getStartTime().compareTo(now) <= 0 && p.getEndTime().compareTo(now) >= 0) {// dang dien ra
				listPollInProgress.add(p);
			}
		}
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Title");
		model.addColumn("Description");
		model.addColumn("Start_time");
		model.addColumn("End_time");
		model.addColumn("Choices");
		model.addColumn("Voters");
		for (Poll p : listPollInProgress) {
			model.addRow(new Object[] { p.getId(), p.getTitle(), p.getDescription(), p.getStartTime(), p.getEndTime(),
					p.getMaxChoices(), p.getMaxVotes() });
		}
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new TitledBorder("List Voting Polls haven't taken"));
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
				String ss=textFieldSearch.getText();
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
		panel_2.setBackground(SystemColor.control);
		panel_2.setBounds(0, 184, 239, 59);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel(" All the voting polls");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if(userSession.getDecentralize()==1) {
					new AllPollingView("Administrator "+userSession.getUsername(), userSession);
				}else {
					new AllPollingView("User "+userSession.getUsername(), userSession);
				}
			}
		});
		lblNewLabel.setForeground(new Color(100, 149, 237));
		lblNewLabel.setBounds(0, 0, 239, 59);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBackground(new Color(0, 191, 255));
		panel_2.add(lblNewLabel);

		JLabel lblHaventTakenPlace = new JLabel("    Haven't taken");
		lblHaventTakenPlace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if(userSession.getDecentralize()==1) {
					new PollHaven_tTaken("Administrator "+userSession.getUsername(), userSession);
				}else {
					new PollHaven_tTaken("User "+userSession.getUsername(), userSession);
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
				if(userSession.getDecentralize()==1) {
					new PollInProgress("Administrator "+userSession.getUsername(), userSession);
				}else {
					new PollInProgress("User "+userSession.getUsername(), userSession);
				}
			}
		});
		lblInProgress.setForeground(new Color(255, 69, 0));
		lblInProgress.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblInProgress.setBounds(0, 295, 239, 49);
		panel.add(lblInProgress);

		JLabel lblHaveTakenPlace = new JLabel("    Have taken");
		lblHaveTakenPlace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if(userSession.getDecentralize()==1) {
					new PollHaveTaken("Administrator "+userSession.getUsername(), userSession);
				}else {
					new PollHaveTaken("User "+userSession.getUsername(), userSession);
				}
			}
		});
		lblHaveTakenPlace.setForeground(new Color(102, 102, 204));
		lblHaveTakenPlace.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblHaveTakenPlace.setBounds(0, 343, 239, 49);
		panel.add(lblHaveTakenPlace);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(255, 255, 255));
		panel_2_1.setBounds(0, 413, 239, 59);
		panel.add(panel_2_1);

		JLabel lblUsers = new JLabel(" Users");
		lblUsers.setVisible(isVisible);
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if(userSession.getDecentralize()==1) {
					new ManageUserView("Administrator "+userSession.getUsername(), userSession);
				}else {
					new ManageUserView("User "+userSession.getUsername(), userSession);
				}
			}
		});
		lblUsers.setForeground(new Color(100, 149, 237));
		lblUsers.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblUsers.setBackground(new Color(204, 255, 255));
		lblUsers.setBounds(0, 0, 239, 59);
		panel_2_1.add(lblUsers);

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(51, 51, 153));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.addItem("Title");
		comboBox.addItem("Start_Time");
		comboBox.addItem("End_Time");
		comboBox.setBounds(604, 25, 159, 37);
		contentPane.add(comboBox);

		JButton btnNewButton_2 = new JButton("Profile");
		btnNewButton_2.setBackground(new Color(102, 204, 204));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProfileView(userSession) ;
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(260, 26, 105, 37);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_1_1 = new JButton("Statistics over time");
		btnNewButton_1_1.setBackground(new Color(102, 204, 204));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StaticOverTimeView(listPollInProgress);
			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(669, 479, 384, 44);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_3 = new JButton("Sort by");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clone=comboBox.getSelectedItem().toString();
				new SortPollView(listPollInProgress,clone);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBackground(new Color(102, 204, 204));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(495, 26, 99, 37);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_1_1_1 = new JButton("Access");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				int poll_id = (Integer) table.getValueAt(getRow, 0);
				Poll poll = new PollController().selectById(poll_id);
				
				// chỉ access cuộc bỏ phiếu đang diễn ra
				Date now = new DateController().dateNow();

				if (poll.getStartTime().compareTo(now) <= 0 && poll.getEndTime().compareTo(now) >= 0 ) {
					
					new VotingView(poll, userSession);
				} else {
					JOptionPane.showMessageDialog(null, "Failure!");

				}
			}
			
		});
		btnNewButton_1_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_1.setBackground(new Color(102, 204, 204));
		btnNewButton_1_1_1.setBounds(260, 479, 384, 44);
		contentPane.add(btnNewButton_1_1_1);
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
