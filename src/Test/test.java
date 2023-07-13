package Test;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClientController.VoteController;
import DAO.ChoiceDAO;
import DAO.PollDAO;
import DAO.UserDAO;
import DAO.VoteDAO;
import DAO.VotersDAO;
import Models.Poll;
import Models.User;
import Models.Vote;

public class test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	Poll p = new PollDAO().selectById(4);
	p.setMaxChoices(4);
	new PollDAO().update(p);
	
	}

	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JOptionPane.showMessageDialog(this, "hi");
		setContentPane(contentPane);
	}

}
