package View_General;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Models.Poll;
import java.awt.Color;


public class SortPollView extends JFrame implements ActionListener {

	JScrollPane sp;
	JPanel p, TieuDe;
	JTable tb;
	DefaultTableModel model;
	ArrayList<Poll> listPoll;
	JButton ok;

	public SortPollView(ArrayList<Poll> list, String name) {
		getContentPane().setBackground(new Color(248, 248, 255));
	
		listPoll = list;
		getContentPane().setLayout(new BorderLayout());
		tb = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Title");
		model.addColumn("Description");
		model.addColumn("Start_time");
		model.addColumn("End_time");
		model.addColumn("Choice");
		model.addColumn("Voters");
		
		if (name == "Title") {
			Collections.sort(listPoll, new Comparator<Poll>() {
				public int compare(Poll h1, Poll h2) {

					return h1.getTitle().compareTo(h2.getTitle());
				}
			});
		} else if (name == "Start_time") {
			Collections.sort(listPoll, new Comparator<Poll>() {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				public int compare(Poll p1, Poll p2) {
					 String ngay1 = dateFormat.format(p1.getStartTime());
				     String ngay2 = dateFormat.format(p2.getStartTime());
					
					try {
						return dateFormat.parse(ngay1).compareTo(dateFormat.parse(ngay2));
					} catch (ParseException e) {
						throw new IllegalArgumentException(e);
					}
				}
			});
		}
//						
		else if (name == "End_time") {
			Collections.sort(listPoll, new Comparator<Poll>() {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				public int compare(Poll p1, Poll p2) {
					 String ngay1 = dateFormat.format(p1.getEndTime());
				     String ngay2 = dateFormat.format(p2.getEndTime());
					
					try {
						return dateFormat.parse(ngay1).compareTo(dateFormat.parse(ngay2));
					} catch (ParseException e) {
						throw new IllegalArgumentException(e);
					}
				}
			});
		}

		for (Poll p : listPoll) {
			model.addRow(new Object[] { p.getId(), p.getTitle(), p.getDescription(),p.getStartTime(),p.getEndTime(),p.getMaxChoices(),p.getMaxVotes() });
		}
		tb.setModel(model);
		sp = new JScrollPane(tb);
		getContentPane().add(sp, BorderLayout.CENTER);

		TieuDe = new JPanel();
		TieuDe.setBackground(new Color(248, 248, 255));
		TieuDe.setLayout(new FlowLayout());
		JLabel ten = new JLabel("Sort by <dynamic>");
		ten.setForeground(new Color(176, 196, 222));
		ten.setFont(new Font("Sitka Text", Font.BOLD, 20));
		TieuDe.add(ten, BorderLayout.CENTER);

		getContentPane().add(TieuDe, BorderLayout.NORTH);
		ok = new JButton("Cancel");
		ok.addActionListener(this);
		p = new JPanel();
		p.setBackground(new Color(248, 248, 255));
		p.add(ok);
		p.setLayout(new FlowLayout());
		getContentPane().add(p, BorderLayout.SOUTH);
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
}
