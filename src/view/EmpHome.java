package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import controller.SystemController;
import javax.swing.JScrollBar;

public class EmpHome extends JPanel {

private NewRequestForm newReq;
	
	private SystemController controller;

	/**
	 * Create the panel.
	 */
	public EmpHome(SystemController c) {
		
		controller = c;
		
		setBackground(Color.WHITE);
		setBounds(0, 90, 668, 463);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello, [First Name]");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 10, 231, 46);
		add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(54, 80, 562, 101);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("( 0 ) New Requests");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(17, 10, 527, 42);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("( 0 ) New Invoice Upload");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(17, 49, 527, 42);
		panel_2.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Requests");
		btnNewButton.setBounds(54, 206,282, 181);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Equipment");
		btnNewButton_1.setBounds(334, 206, 282, 181);
		add(btnNewButton_1);
		
		newReq = new NewRequestForm(c);
		newReq.setLocationRelativeTo(null);
		
		setVisible(true);

	}
}
