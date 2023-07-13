package View_General;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ClientController.ChoiceController;
import ClientController.PollController;
import ClientController.VoteController;
import DAO.PollDAO;
import DAO.VoteDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Models.Choice;

import Models.Poll;
import Models.User;
import Models.Vote;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.jfree.chart.ChartFactory;
public class ResultView extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JTable table;
	private ArrayList<Choice> listChoices;
	private ArrayList<Vote> listVote;
	private ArrayList<Choice> choiceWin;
	private DefaultTableModel modelChoice;
	private JScrollPane scrollPane;
	private Poll poll;
	private int max_choices;
	private int max_voters;

	public int getMax_choices() {
		return max_choices;
	}

	public void setMax_choices(int max_choices) {
		this.max_choices = max_choices;
	}

	public int getMax_voters() {
		return max_voters;
	}

	public void setMax_voters(int max_voters) {
		this.max_voters = max_voters;
	}

	private String decentral;
	
	private Integer getRowListChoice;
	private int choicesIndex;
	private int total_votes;

	
	private void init(Poll poll) {
		this.poll=poll;
		try {
			listChoices = new ChoiceController().selectByPollID(poll.getId());
			listVote=new VoteController().selectByPollId(poll.getId());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		choiceWin= new ArrayList<Choice>();
		max_choices= poll.getMaxChoices();
		max_voters=poll.getMaxVotes();
		
		
	}
	

	public ResultView(Poll poll) {
	
		init(poll);
		
		setBounds(100, 100, 836, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSignIn = new JButton("Result chart");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tạo dữ liệu cho biểu đồ
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				for(Choice c: listChoices) {
					listVote = new VoteController().selectByPollIdAndChoiceId(poll.getId(), c.getId());
					dataset.setValue(listVote.size(), "Revenue", c.getContent());
				}

				// bar chart
				JFreeChart chart = ChartFactory.createBarChart("Poll results chart", "Result",
						"Votes", dataset, PlotOrientation.VERTICAL, false, false, false);
				CategoryPlot p = chart.getCategoryPlot();
				p.setRangeGridlinePaint(Color.blue);
				ChartFrame frame = new ChartFrame("Bar Chart for revenue", chart);
				frame.setVisible(true);
				frame.setSize(750, 450);
				frame.setLocationRelativeTo(null);

			}
		});
		

		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBounds(80, 669, 199, 44);
		contentPane.add(btnSignIn);
		btnSignIn.setBackground(new Color(51, 153, 204));

		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1.setBounds(125, 113, 72, 38);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Poll result");
		lblNewLabel.setBounds(309, 33, 210, 51);
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
		lblNewLabel_1_2_1.setBounds(488, 113, 138, 38);
		contentPane.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("End_time");
		lblNewLabel_1_2_1_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1.setBounds(488, 214, 138, 38);
		contentPane.add(lblNewLabel_1_2_1_1);

		table = new JTable();
		
		modelChoice = new DefaultTableModel();
		modelChoice.addColumn("Id");
		modelChoice.addColumn("Content");
		modelChoice.addColumn("Votes");
		choicesIndex = 0;
		
		int total_choiceVotes=0;
		 total_votes=0;
		//tìm số phiếu lớn nhất;
		int total_choiceVotesMax = total_choiceVotes;
		for (Choice c : listChoices) {
			
			choicesIndex++;
			listVote = new VoteController().selectByPollIdAndChoiceId(poll.getId(), c.getId());
			total_choiceVotes=listVote.size();
			total_votes+=total_choiceVotes;
			modelChoice.addRow(new Object[] { choicesIndex, c.getContent() ,total_choiceVotes});
			if(total_choiceVotes>total_choiceVotesMax) {
				total_choiceVotesMax=total_choiceVotes;
			}
		}
		for (Choice c : listChoices) {	
			
			listVote = new VoteController().selectByPollIdAndChoiceId(poll.getId(), c.getId());
			total_choiceVotes=listVote.size();	
			
			if(total_choiceVotes==total_choiceVotesMax) {
				choiceWin.add(c);
			}
		}
		table.setModel(modelChoice);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(43, 314, 369, 249);
		scrollPane.setBorder(new TitledBorder("List Choice"));
		contentPane.add(scrollPane);
		


		JLabel lblNewLabel_1_2 = new JLabel("ID");
		lblNewLabel_1_2.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(43, 113, 72, 38);
		contentPane.add(lblNewLabel_1_2);
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateStart = formatter.format(poll.getStartTime());
        String formattedDateEnd = formatter.format(poll.getEndTime());
		
		JLabel lblID = new JLabel("");
		lblID.setForeground(new Color(255, 51, 51));
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblID.setBounds(43, 151, 72, 42);
		contentPane.add(lblID);
		
		JLabel lblTiltle = new JLabel("");
		lblTiltle.setForeground(new Color(255, 51, 0));
		lblTiltle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiltle.setBounds(125, 151, 317, 42);
		contentPane.add(lblTiltle);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setForeground(new Color(255, 51, 51));
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(43, 249, 369, 42);
		contentPane.add(lblDescription);
		
		JLabel lblStart = new JLabel("");
		lblStart.setForeground(new Color(255, 51, 51));
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStart.setBounds(488, 151, 316, 42);
		contentPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("");
		lblEnd.setForeground(new Color(255, 51, 51));
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnd.setBounds(488, 249, 316, 42);
		contentPane.add(lblEnd);
		
		JLabel lblWinnerIs = new JLabel("Winner is: ");
		lblWinnerIs.setForeground(new Color(102, 102, 255));
		lblWinnerIs.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWinnerIs.setBackground(SystemColor.activeCaption);
		lblWinnerIs.setBounds(232, 575, 210, 51);
		contentPane.add(lblWinnerIs);
		
		JLabel lblWinner = new JLabel("Winner");
		if(total_choiceVotesMax==0) {
			lblWinner.setText("No winner");
		}else {
			String s="";
			for(Choice c:choiceWin) {
				if(c.equals(choiceWin.get(choiceWin.size()-1))) {
					s+=c.getContent();
				}else {
					
					s=s+c.getContent()+", ";
				}
			}
			lblWinner.setText(s);
		}
		lblWinner.setForeground(new Color(255, 51, 51));
		lblWinner.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblWinner.setBackground(SystemColor.activeCaption);
		lblWinner.setBounds(441, 575, 269, 51);
		contentPane.add(lblWinner);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBackground(new Color(51, 153, 204));
		btnCancel.setBounds(523, 669, 199, 44);
		contentPane.add(btnCancel);
		lblStart.setText(formattedDateStart);
		lblEnd.setText(formattedDateEnd);
		lblID.setText(poll.getId()+"");
		lblDescription.setText(poll.getDescription());
		lblTiltle.setText(poll.getTitle());
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Number of voters:");
		lblNewLabel_1_2_1_1_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1_1.setBounds(488, 374, 170, 38);
		contentPane.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Valid vote:");
		lblNewLabel_1_2_1_1_1_1.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1_1_1.setBounds(488, 422, 170, 38);
		contentPane.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_3 = new JLabel("Blank vote:");
		lblNewLabel_1_2_1_1_1_3.setForeground(new Color(102, 102, 204));
		lblNewLabel_1_2_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1_1_3.setBounds(488, 470, 138, 38);
		contentPane.add(lblNewLabel_1_2_1_1_1_3);
		
		JLabel lblNumberOfVotes = new JLabel("");
		lblNumberOfVotes.setForeground(new Color(255, 51, 51));
		lblNumberOfVotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumberOfVotes.setBackground(SystemColor.activeCaption);
		lblNumberOfVotes.setBounds(668, 368, 148, 51);
		contentPane.add(lblNumberOfVotes);
		
		JLabel lblValid = new JLabel("");
		lblValid.setForeground(new Color(255, 51, 51));
		lblValid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblValid.setBackground(SystemColor.activeCaption);
		lblValid.setBounds(668, 416, 148, 51);
		contentPane.add(lblValid);
		
		JLabel lblBlankVote = new JLabel("");
		lblBlankVote.setForeground(new Color(255, 51, 51));
		lblBlankVote.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBlankVote.setBackground(SystemColor.activeCaption);
		lblBlankVote.setBounds(668, 464, 148, 51);
		
		lblNumberOfVotes.setText(poll.getMaxVotes()+"");
		lblValid.setText( total_votes+"");
		lblBlankVote.setText((poll.getMaxVotes()-total_votes)+"");
		contentPane.add(lblBlankVote);
		
		JButton btnExportExcelFile = new JButton("Export excel file");
		btnExportExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Workbook workbook = new XSSFWorkbook();
		        // tạo một bảng tính mới
		        Sheet sheet = workbook.createSheet("Kết quả bỏ phiếu");

		        // tạo một mảng chứa các tiêu đề cột
		        String[] headers = {"Id", "Title", "Start Time", "End Time","Number of voters","Valid votes","Blank votes","Winner"};
		        // tạo một hàng chứa các tiêu đề cột
		        Row headerRow = sheet.createRow(0);
		        for (int i = 0; i < headers.length; i++) {
		            Cell cell = headerRow.createCell(i);
		            cell.setCellValue(headers[i]);
		        }
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String formattedDateStart = formatter.format(poll.getStartTime());
		        String formattedDateEnd = formatter.format(poll.getEndTime());
		        // tạo các hàng dữ liệu
		        Object[][] data = {
		            {1, poll.getTitle(),formattedDateStart,formattedDateEnd ,poll.getMaxVotes(),total_votes+"",(poll.getMaxVotes()-total_votes)+"", lblWinner.getText()},
		            
		        };
		        int rowIndex = 1;
		        for (Object[] rowData : data) {
		            Row row = sheet.createRow(rowIndex++);
		            int cellIndex = 0;
		            for (Object field : rowData) {
		                Cell cell = row.createCell(cellIndex++);
		                if (field instanceof String) {
		                    cell.setCellValue((String) field);
		                } else if (field instanceof Integer) {
		                    cell.setCellValue((Integer) field);
		                }
		            }
		        }
		        // ghi workbook vào file
		        try (FileOutputStream outputStream = new FileOutputStream("file"+poll.getId()+".xlsx")) {
		        	JOptionPane.showMessageDialog(null, "Success");
		            workbook.write(outputStream);
		            outputStream.close();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
			}
		});
		btnExportExcelFile.setForeground(Color.WHITE);
		btnExportExcelFile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExportExcelFile.setBackground(new Color(51, 153, 204));
		btnExportExcelFile.setBounds(302, 669, 199, 44);
		contentPane.add(btnExportExcelFile);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		
	
		new ResultView(new PollDAO().selectById(1));
	}
}
