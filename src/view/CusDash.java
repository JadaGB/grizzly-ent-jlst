package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import controller.SystemController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CusDash extends JPanel {

	private NewRequestForm newReq;
	private SystemController controller;

	/**
	 * Create the panel.
	 */
	public CusDash(SystemController c) {
		
		controller = c;
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		
		newReq = new NewRequestForm(c);
		newReq.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("New Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getAllEquipmentTypes();
				newReq.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(((new ImageIcon(CusDash.class.getResource("/image/plus sign transparent.jpg"))).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 59, 148, 33);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		add(btnNewButton);
		
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

	}
	
//	public static void populateGeneralRequestTable() {
//		//JTable.additem(paras);
//	}

}
