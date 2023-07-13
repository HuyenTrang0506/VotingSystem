package View_General;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Models.Poll;

public class StaticOverTimeView extends JFrame implements ActionListener {
	ArrayList<Poll> listPoll;
	JPanel p, p1, p2;
	JScrollPane spN;
	JTable tbNhap;
	DefaultTableModel dataNhap, dataXuat;
	JTextField time1, time2;
	JButton ok, x, rf;

	public StaticOverTimeView( ArrayList<Poll> list) {
		
		listPoll = list;
		dataNhap = new DefaultTableModel();
		dataNhap.addColumn("ID");
		dataNhap.addColumn("Title");
		dataNhap.addColumn("Description");
		dataNhap.addColumn("Start_time");
		dataNhap.addColumn("End_time");
		tbNhap = new JTable();
		tbNhap.setModel(dataNhap);

	
		spN = new JScrollPane(tbNhap);
		spN.setBounds(30, 0, 670, 198);

		p = new JPanel();
		p.setBackground(new Color(248, 248, 255));
		p.setBounds(0, 48, 736, 198);
		p.setLayout(null);
		p.add(spN);

		p1 = new JPanel();
		p1.setBounds(0, 0, 736, 48);
		p1.setBackground(new Color(248, 248, 255));
		p1.setLayout(null);
		JLabel ltitle = new JLabel("Static Over Time (by start time)");
		ltitle.setForeground(new Color(176, 196, 222));
		ltitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ltitle.setBounds(224, 10, 319, 28);
		p1.add(ltitle);

		p2 = new JPanel();
		p2.setBounds(0, 218, 736, 173);
		p2.setBackground(new Color(248, 248, 255));
		JLabel l1 = new JLabel("From");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l1.setForeground(new Color(51, 102, 204));
		l1.setBounds(33, 44, 53, 41);
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l2 = new JLabel("To");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		l2.setForeground(new Color(102, 102, 255));
		l2.setBounds(361, 44, 63, 41);
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		time1 = new JTextField();
		time1.setBounds(96, 44, 229, 41);
		time2 = new JTextField();
		time2.setBounds(446, 44, 229, 41);
		ok = new JButton("OK");
		ok.setBounds(290, 123, 146, 37);
		ok.setForeground(new Color(255, 255, 255));
		ok.setBackground(new Color(102, 204, 204));
		ok.addActionListener(this);
		x = new JButton("Cancel");
		x.setBounds(446, 123, 158, 37);
		x.setBackground(new Color(102, 204, 204));
		x.setForeground(new Color(255, 0, 0));
		x.addActionListener(this);
		rf = new JButton("Refresh");
		rf.setBounds(125, 123, 146, 37);
		rf.setForeground(new Color(255, 255, 255));
		rf.setBackground(new Color(102, 204, 204));
		rf.addActionListener(this);
		p2.setLayout(null);
		p2.add(l1);
		p2.add(time1);
		p2.add(l2);
		p2.add(time2);
		p2.add(ok);
		p2.add(rf);
		p2.add(x);
		getContentPane().setLayout(null);

		getContentPane().add(p1);
		getContentPane().add(p);
		getContentPane().add(p2);
		setLocation(400, 200);
		setSize(750, 428);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {

			
			Date timeBegin = null, timeEnd = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// định dạng đoạn text theo đúng định dạng ngày tháng
				timeBegin = dateFormat.parse(time1.getText());
				timeEnd = dateFormat.parse(time2.getText());
			} catch (ParseException o) {
				o.printStackTrace();
			}
		
			// Đảm bảo ng dùng nhập tgian bắt đầu nhỏ hơn tgian kết thúc
			if (timeBegin.before(timeEnd)) {
				// hàm sắp xếp theo thời gian
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
				
				for (Poll p : listPoll) {
					{
						Date date=null;
						try {
							date = p.getStartTime();
							if (timeBegin.before(date) && date.before(timeEnd)) {
								dataNhap.addRow(new Object[] { p.getId(), p.getTitle(),p.getDescription(), p.getStartTime(),p.getEndTime() });
								
							}
						} catch (Exception o) {
							o.printStackTrace();
						}
					}
				}tbNhap.setModel(dataNhap);
				
				
				JOptionPane.showMessageDialog(rootPane, "Static successful!");
			} else
				JOptionPane.showMessageDialog(rootPane, "Error! End time cannot less than start time");
		} else if (e.getActionCommand().equals("Refresh")) {
			this.dispose();
			new StaticOverTimeView( listPoll);
		} else if (e.getActionCommand().equals("Cancel"))
			this.dispose();
	}
}
