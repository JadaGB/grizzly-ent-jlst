package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemController;

import java.awt.Color;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private static JLabel lbluserType;
	private JLabel lblNewLabel_4;
	private ParentWindow pWindow;
	
	
	
	private SystemController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//SignIn frame = new SignIn();
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
	public SignIn(SystemController c, Login1 loginW) {
		
		controller = c;
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		
		JButton btnNewButton = new JButton("SIGN IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uType = lbluserType.getText();
				String passText = new String(passwordField.getPassword());
				int userID = Integer.parseInt(textField_1.getText());
				NewRequestForm.grabId(userID);
				boolean grantAccess = controller.signin( uType, userID ,  passText);
				
				
				if(grantAccess == true) {
					//open ParentWindow //Access Granted
					pWindow = new ParentWindow(c, uType);
					pWindow.setLocationRelativeTo(null);
					
					setVisible(false);
					pWindow.setVisible(true);
					
					controller.getAllCustRequestsInfo(userID);
					
				}else if(grantAccess == false) {
					setVisible(true);
					pWindow.setVisible(false);
					lblNewLabel_4.setText("Incorrect username or password. Try Again.");
				}
				
				
					
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(189, 183, 107));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(67, 354, 226, 41);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		panel_1.add(btnNewButton);
		
		lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 166, 81, 25);
		panel_1.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.GRAY);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(40, 266, 287, 41);
		panel_1.add(passwordField);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(40, 241, 81, 25);
		panel_1.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.GRAY);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(40, 190, 287, 41);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME BACK!");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(95, 102, 174, 41);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("LOGO");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(157, 51, 45, 25);
		panel_1.add(lblNewLabel_3);
		
		lbluserType = new JLabel("None");
		lbluserType.setBounds(31, 10, 45, 13);
		panel_1.add(lbluserType);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setBounds(40, 143, 253, 13);
		panel_1.add(lblNewLabel_4);
		
		

		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				loginW.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(10, 29, 65, 25);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setOpaque(true);
		panel_1.add(btnNewButton_1);
		
		setVisible(false);
	}
	
	
	public static void getUserType(String uType) {
		
		lbluserType.setText(uType);
		lbluserType.setVisible(false);
		//System.out.println("Testing");
		
		//lbluserType.setVisible(true);
			
	}
}
