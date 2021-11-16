package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.SystemController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Login1 extends JFrame {// implements ActionListener{

	private JPanel contentPane;
	JButton btnNewButton;
	JButton btnCustomer;
	
	private SignIn signin1;
	
	private SystemController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Login1 frame = new Login1();
					//frame.setLocationRelativeTo(null);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login1(SystemController ec) {
		
		controller = ec;
		
		
		
		setBounds(100, 100, 694, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 0));
		panel.setBounds(0, 0, 319, 456);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(-231, 0, 550, 478);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(SignIn.class.getResource("/image/signin mic transparent.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(320, 0, 360, 456);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		signin1 = new SignIn(ec, this);
		signin1.setBounds(100, 100, 694, 483);
		signin1.setLocationRelativeTo(null);
		signin1.setVisible(false);
		
		
		btnNewButton = new JButton("EMPLOYEE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				signin1.setVisible(true);
				
				controller.login1("employee");
				
				
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(189, 183, 107));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(65, 184, 226, 41);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		panel_1.add(btnNewButton);
		
		
		btnCustomer = new JButton("CUSTOMER");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				signin1.setVisible(true);
				
				controller.login1("customer");
			}
		});
		btnCustomer.setForeground(Color.WHITE);
		btnCustomer.setOpaque(true);
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCustomer.setBorderPainted(false);
		btnCustomer.setBackground(new Color(189, 183, 107));
		btnCustomer.setBounds(65, 278, 226, 41);
		panel_1.add(btnCustomer);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(44, 250, 271, 2);
		panel_1.add(separator);
		
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME BACK!");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(95, 102, 174, 41);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("LOGO");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(157, 51, 45, 25);
		panel_1.add(lblNewLabel_3);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	

	
	
}
