package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemController;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ParentWindow extends JFrame {
	
	private SystemController controller;

	private JPanel contentPane;
	private EmpDash dash1;
	private Home home1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ParentWindow frame = new ParentWindow();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ParentWindow(SystemController c) {
		controller = c;
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(128, 128, 0));
		panel.setBounds(0, 0, 201, 473);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(201, 0, 668, 473);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		panel_1.setVisible(false);
		
		dash1 = new EmpDash(c);
		dash1.setBounds(0, 0, 668, 439);
		
		
		home1 = new Home(c);
		home1.setBounds(0, 0, 668, 439);
		panel_1.add(home1);
		panel_1.setVisible(true);
		
		JButton btnNewButton = new JButton("HOME");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(189, 183, 107));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				
				panel_1.add(home1);
				panel_1.setVisible(true);
				
				//Resets & updates 
				revalidate();
				repaint();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(0, 120, 201, 51);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		panel.add(btnNewButton);
		
		JButton btnDashboard = new JButton("DASHBOARD");
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setBackground(new Color(128, 128, 0));
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.add(dash1);
				panel_1.setVisible(true);
				
				revalidate();
				repaint();
			}
		});
		btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDashboard.setBounds(0, 169, 201, 51);
		btnDashboard.setBorderPainted(false);
		btnDashboard.setOpaque(true);
		panel.add(btnDashboard);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setForeground(Color.WHITE);
		btnProfile.setBackground(new Color(128, 128, 0));
		btnProfile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnProfile.setBounds(0, 217, 201, 51);
		btnProfile.setBorderPainted(false);
		btnProfile.setOpaque(true);
		panel.add(btnProfile);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setBackground(new Color(128, 128, 0));
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogOut.setBounds(0, 266, 201, 51);
		btnLogOut.setBorderPainted(false);
		btnLogOut.setOpaque(true);
		panel.add(btnLogOut);
		
		JLabel lblNewLabel = new JLabel("LOGO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(72, 57, 57, 26);
		panel.add(lblNewLabel);
		
		setVisible(false);
	}
}
