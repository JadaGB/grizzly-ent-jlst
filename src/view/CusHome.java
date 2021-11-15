package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;

import controller.SystemController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CusHome extends JPanel {
	
	private NewRequestForm newReq;
	
	private SystemController controller;

	/**
	 * Create the panel.
	 */
	public CusHome(SystemController c) {
		
		controller = c;
		
		setBackground(Color.WHITE);
		setBounds(0, 90, 668, 414);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hello, [First Name]");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 10, 231, 46);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("INVOICE BALANCE: $20,000");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(52, 66, 564, 73);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		add(btnNewButton);
		
		JTextPane txtpnNewMessage = new JTextPane();
		txtpnNewMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnNewMessage.setText("Display New Messages by chronological order");
		txtpnNewMessage.setBounds(52, 182, 564, 222);
		add(txtpnNewMessage);
		
		newReq = new NewRequestForm(c);
		newReq.setLocationRelativeTo(null);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newReq.setVisible(true);
				
				controller.getAllEquipmentTypes();

//				String sel = geteqTypeSelection();
//				if (sel != null) {
//					controller.getAllEquipmentofEqType(sel);
//				}
				
				
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(((new ImageIcon(CusDash.class.getResource("/image/plus sign transparent.jpg"))).getImage()).getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
		btnNewButton_1.setBounds(504, 10, 85, 32);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setOpaque(true);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Updates");
		lblNewLabel_1.setForeground(new Color(128, 128, 0));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		lblNewLabel_1.setBounds(52, 149, 110, 28);
		add(lblNewLabel_1);

	}
	
	public static String geteqTypeSelection() {
		String eqTypeSel =NewRequestForm.geteqTypeSel();
		
		return eqTypeSel;
		
	}
}
