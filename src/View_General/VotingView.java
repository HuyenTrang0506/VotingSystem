package View_General;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.desktop.UserSessionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ClientController.ChoiceController;
import ClientController.DateController;
import ClientController.PollController;
import ClientController.UserController;
import ClientController.VoteController;
import DAO.ChoiceDAO;
import DAO.PollDAO;
import DAO.VoteDAO;
import Models.Choice;
import Models.Poll;
import Models.User;
import Models.Vote;

import java.util.Date;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class VotingView extends JFrame implements ActionListener,MouseListener {

	
	private static  User userSession;
	JScrollPane sp;
	JPanel p, TieuDe;
	JTable tb;
	DefaultTableModel model;
	ArrayList<Choice> listChoice;
	JButton ok;
	int getRow;
	
	ArrayList<Vote> listVote;
	Poll poll ;

	public VotingView(Poll poll,User userSession) {
		
		this.poll=poll;
		this.userSession= userSession;
		boolean isVisible=true;
		try {			
			if(userSession.getDecentralize()==2) {
				isVisible=false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		listVote = new ArrayList<Vote>();
		try {
			listVote = new VoteController().selectByPollId(poll.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		getContentPane().setBackground(new Color(248, 248, 255));
	//lỗi chỗ này
		listChoice = new ChoiceController().selectByPollID(poll.getId());
		tb = new JTable();
		tb.addMouseListener(this);
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Content");
		
		int stt=0;

		for (Choice p : listChoice) {
			stt++;
			model.addRow(new Object[] {stt,p.getContent()});
		}
		getContentPane().setLayout(null);
		tb.setModel(model);
		sp = new JScrollPane(tb);
		sp.setBounds(0, 36, 294, 198);
		getContentPane().add(sp);

		TieuDe = new JPanel();
		TieuDe.setBounds(0, 0, 586, 36);
		TieuDe.setBackground(new Color(248, 248, 255));
		TieuDe.setLayout(new FlowLayout());
		JLabel ten = new JLabel("Choose your choice");
		ten.setForeground(new Color(176, 196, 222));
		ten.setFont(new Font("Sitka Text", Font.BOLD, 20));
		TieuDe.add(ten, BorderLayout.CENTER);

		getContentPane().add(TieuDe);
		ok = new JButton("Cancel");
		ok.addActionListener(this);
		p = new JPanel();
		p.setBounds(0, 232, 586, 31);
		p.setBackground(new Color(248, 248, 255));
		p.add(ok);
		p.setLayout(new FlowLayout());
		getContentPane().add(p);
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date now = new DateController().dateNow();
				//ktra cuoc bo phieu ket thuc chua
				if(now.compareTo(poll.getEndTime())<0) {
					//ktra xem user da bo phieu chua
					boolean voted=false;
					if(listVote!=null) {
						for(Vote v:listVote) {
							if(v.getUser_id()==userSession.getId()) {
								JOptionPane.showMessageDialog(null, "You already voted");
								voted=true;
							}
						}
					}
					if(!voted) {
						String content = tb.getValueAt(getRow, 1).toString();
						Choice choice = new ChoiceDAO().selectByPollIdAndContent(poll.getId(), content);
						
						Vote vote = new Vote(poll.getId(), userSession.getId(), choice.getId(), now);
						listVote.add(vote);
						if(new VoteDAO().insert(vote)!=0) {		
							
							JOptionPane.showMessageDialog(null, "Success");
						}
						
						
					}
				}else
					JOptionPane.showMessageDialog(null, "Voting is over!");
				
				
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(102, 204, 204));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(329, 57, 220, 36);
		getContentPane().add(btnNewButton);
		
		JButton btnEndVoting = new JButton("End voting soon");
		btnEndVoting.setVisible(isVisible);
		btnEndVoting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date now = new DateController().dateNow();//thgian hiện tại
					
					poll.setEndTime(now);
					if(new PollController().update(poll)!=0) {
						JOptionPane.showMessageDialog(null, "Voting is over!");
					}
				
				
				
			}
		});
		btnEndVoting.setForeground(Color.WHITE);
		btnEndVoting.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEndVoting.setBackground(new Color(102, 204, 204));
		btnEndVoting.setBounds(329, 103, 220, 36);
		getContentPane().add(btnEndVoting);
		
		JLabel lblNewLabel = new JLabel("Time left");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(304, 183, 155, 25);
		getContentPane().add(lblNewLabel);
		
		
		JLabel lblTimeLeft = new JLabel("thgian con lai");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String endTime = formatter.format(poll.getEndTime());
			Date endDate = formatter.parse(endTime);
			
			Thread th=new Thread(()->{
				boolean isStopped=false;
				try {
					Date nowDate = formatter.parse(formatter.format(new DateController().dateNow()));
					 long diff = endDate.getTime() - nowDate.getTime();
					 if(diff>0) {
						 long diffSeconds = diff / 1000 % 60;
				            long diffMinutes = diff / (60 * 1000) % 60;
				            long diffHours = diff / (60 * 60 * 1000) % 24;
				            long diffDays = diff / (24 * 60 * 60 * 1000);
				         lblTimeLeft.setText( diffDays+" day(s),"+ diffHours+" hr(s)," + diffMinutes+" min(s),"+ diffSeconds+" sec(s)");
					
					 } 
					 while(!isStopped) {
						 Thread.sleep(1000);
						 nowDate = formatter.parse(formatter.format(new DateController().dateNow()));
						  diff = endDate.getTime() - nowDate.getTime();
						
						 long diffSeconds = diff / 1000 % 60;
				            long diffMinutes = diff / (60 * 1000) % 60;
				            long diffHours = diff / (60 * 60 * 1000) % 24;
				            long diffDays = diff / (24 * 60 * 60 * 1000);
				           
				         lblTimeLeft.setText( diffDays+" day(s),"+ diffHours+" hr(s)," + diffMinutes+" min(s),"+ diffSeconds+" sec(s)");
				         if(diff<=0) {
				        	
				        	 isStopped=true;
				        	 JOptionPane.showMessageDialog(null, "Voting is over");
				        
				         }
					 }
					 
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
			th.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		lblTimeLeft.setForeground(Color.GRAY);
		lblTimeLeft.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTimeLeft.setBounds(304, 209, 282, 25);
		getContentPane().add(lblTimeLeft);
		
		
		
		JButton btnViewResult = new JButton("View result");
		btnViewResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cuoc bo phieu phai ket thuc moi xem dc kq
				Date now = new DateController().dateNow();
				if(poll.getEndTime().compareTo(now)<=0) {
					
					new ResultView(poll);					
				}else {
					JOptionPane.showMessageDialog(null, "Voting hasn't taken place");
				}
			}
		});
		btnViewResult.setForeground(Color.WHITE);
		btnViewResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewResult.setBackground(new Color(102, 204, 204));
		btnViewResult.setBounds(329, 150, 220, 36);
		getContentPane().add(btnViewResult);
		setLocation(400, 200);
		setSize(600, 300);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		getRow = Integer.valueOf(tb.getSelectedRow());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		
		new VotingView(new PollController().selectById(10), new UserController().selectById(1));
	}
}
