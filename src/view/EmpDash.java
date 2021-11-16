package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import controller.SystemController;

public class EmpDash extends JPanel {

	private NewRequestForm newReq;
	private SystemController controller;

	/**
	 * Create the panel.
	 */
	public EmpDash(SystemController c) {
		
		controller = c;
		setBounds(0, 90, 668, 463);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		
		newReq = new NewRequestForm(c);
		newReq.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tabbedPane.setBounds(0, 102, 668, 402);
		add(tabbedPane);
		
		Panel panel = new Panel();
		tabbedPane.addTab("Requests", null, panel, null);
		
		Panel panel_1 = new Panel();
		tabbedPane.addTab("Scheduled", null, panel_1, null);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Past Transactions", null, panel_2, null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblNewLabel.setBounds(257, 10, 153, 42);
		add(lblNewLabel);
		
		setVisible(true);
	}


}
