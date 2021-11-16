package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SystemController;
import model.Date;
import model.Employee;
import model.Equipment;
import model.Request;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Choice;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class NewRequestForm extends JFrame {
	
	private SystemController controller;

	private JPanel contentPane;
	private JTextField textField_4;
	private static JComboBox comboBox;
	private static JComboBox comboBox_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private static int cusId;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					NewRequestForm frame = new NewRequestForm();
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
public NewRequestForm(SystemController c) {
		
		controller = c;
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NEW REQUEST");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(178, 29, 135, 33);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(104, 310, 283, 2);
		contentPane.add(separator);
		
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		JFormattedTextField txtDate = new JFormattedTextField(df);
		txtDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDate.setText("");
			}
		});
		txtDate.setForeground(new Color(128, 128, 0));
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDate.setText("Request Date");
		txtDate.setColumns(10);
		txtDate.setBounds(33, 221, 199, 39);
		contentPane.add(txtDate);
		
		txtDate.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
		      {
		        JOptionPane.showMessageDialog(null, "Please Enter Valid Date");
		        e.consume();
		      }
		    }
		  });
		
		
		comboBox = new JComboBox(); //populated by database
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sel = (String) comboBox.getSelectedItem();
				System.out.println("sel in actionlistener"+ sel);
				
//				comboBox_1
				controller.getAllEquipmentofEqType(sel);
			}
		});
	
		comboBox.setForeground(new Color(128, 128, 0));
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(33, 142, 199, 39);
		contentPane.add(comboBox);
		
		
		
		comboBox_1 = new JComboBox();
		comboBox_1.setForeground(new Color(128, 128, 0));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(259, 142, 199, 39);
		contentPane.add(comboBox_1);
		
		
		
		JButton btnNewButton = new JButton("Make a Request");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if clicked call create request func
				String equipName=(String) comboBox_1.getSelectedItem();
				int eid=controller.getEquipid(equipName);
			
				int day=Integer.parseInt(txtDate.getText().substring(3,4));
				int month=Integer.parseInt(txtDate.getText().substring(0,1));
				int year=Integer.parseInt(txtDate.getText().substring(6,9));

				Request newRequest=new Request(cusId,eid,new Date(day,month,year));
				controller.addNewRequest(newRequest);;
				setVisible(false); //show frame that request was successful
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(128, 128, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(142, 348, 199, 45);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText("CUS ID: "+cusId);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(33, 80, 126, 24);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(259, 80, 135, 24);
		contentPane.add(lblNewLabel_2);
		
		setVisible(false);
		
	}

	public static void grabId(int id) {
		cusId=id;
	}

	public static void populateEqTypeList(String type) {
		comboBox.addItem(type);
	}
	
	public static void populateEqNamesList(String name) {
		
		comboBox_1.addItem(name);
	}
	
	public static String geteqTypeSel() {
		String selection = (String) comboBox.getSelectedItem();
		
		System.out.println(selection);
		return selection;
		
	}
//	
//	public void retrieveEqidFromEname() {
//		
//	}
}
