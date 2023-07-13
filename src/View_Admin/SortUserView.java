package View_Admin;
import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Models.User;

import java.awt.Color;


public class SortUserView extends JFrame implements ActionListener {

	JScrollPane sp;
	JPanel p, TieuDe;
	JTable tb;
	DefaultTableModel model;
	ArrayList<User> listPoll;
	JButton ok;

	public SortUserView(ArrayList<User> list, String name) {
		getContentPane().setBackground(new Color(248, 248, 255));
	
		listPoll = list;
		getContentPane().setLayout(new BorderLayout());
		tb = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Id");
		model.addColumn("Username");
		model.addColumn("Email");
		model.addColumn("Phone");
		model.addColumn("Decentralize");
		
		
		if (name == "ID") {
			Collections.sort(listPoll, new Comparator<User>() {
				public int compare(User h1, User h2) {
			        if (h1.getId() < h2.getId()) {
			            return -1;
			        } else if (h1.getId() > h2.getId()) {
			            return 1;
			        } else {
			            return 0;
			        }
			    }
			});
		} else if (name == "Name") {
			Collections.sort(listPoll, new Comparator<User>() {
				public int compare(User h1, User h2) {

					return h1.getUsername().compareTo(h2.getUsername());
				}
			});
		}
//						
		String decen="";

		for (User p : listPoll) {
			if(p.getDecentralize()==1) {
				decen="ADMIN";
			}else {
				decen="USER";
			}
			model.addRow(new Object[] { p.getId(), p.getUsername(), p.getEmail(),p.getPhone(),decen});
		}
		tb.setModel(model);
		sp = new JScrollPane(tb);
		getContentPane().add(sp, BorderLayout.CENTER);

		TieuDe = new JPanel();
		TieuDe.setBackground(new Color(248, 248, 255));
		TieuDe.setLayout(new FlowLayout());
		JLabel ten = new JLabel("Sort by "+name);
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
