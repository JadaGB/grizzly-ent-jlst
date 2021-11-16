package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

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
	private static JTable customerRequesttable;
	private static JTable scheduledTable;
	private static JTable pastTransactionsTable;

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
		
		JScrollPane scrollPane = new JScrollPane();
		customerRequesttable = new JTable();
		scrollPane.setViewportView(customerRequesttable);
		
		panel.add(scrollPane);
		
		
		Panel panel_1 = new Panel();
		tabbedPane.addTab("Scheduled", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		scheduledTable = new JTable();
		scrollPane_1.setViewportView(scheduledTable);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Past Transactions", null, panel_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		pastTransactionsTable = new JTable();
		scrollPane_2.setViewportView(pastTransactionsTable);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		lblNewLabel.setBounds(257, 10, 153, 42);
		add(lblNewLabel);
		
		tabbedPane.addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent e) {
		            if(tabbedPane.getSelectedIndex()==0) {
		            	controller.getAllCustRequestsInfo(6);
		            }
		            if(tabbedPane.getSelectedIndex()==1) {
		            	controller.getAllScheduled(6);
		            }
		            if(tabbedPane.getSelectedIndex()==2) {
		            	controller.getAllCustPastTransactions(6);
		            }
		        }
		        
		    });
	
	}
	
//	public static void populateCustomerRequestTable(int index,String customerName,String equipType, String equipName, String reqDate, Float quotation, String confirmed ) {
	public static void populateCustomerRequestTable(String data[][]) {

	//Shaheim - Add parameters to each row in JTable
	//JTable variable name.additemstorow[i](customerName, equipType, equipName, reqDate, quotation, confirmed);
	String columns[] = { "Name", "Equipment Type","Equipment Name","Qoutation","Requested Date","Confirmed" };
    DefaultTableModel model = new DefaultTableModel(data,columns);

	 	 customerRequesttable.setModel(model);	 	 
	 	 customerRequesttable.setShowGrid(true);
	 	 customerRequesttable.setShowVerticalLines(true);
	 	

	
	}
	
	public static void populatescheduledTable(String data[][]) {

		//Shaheim - Add parameters to each row in JTable
		//JTable variable name.additemstorow[i](customerName, equipType, equipName, reqDate, quotation, confirmed);
		String columns[] = { "Name", "Equipment Type","Equipment Name","Qoutation","Requested Date","Confirmed" };
	    DefaultTableModel model = new DefaultTableModel(data,columns);
	    scheduledTable.setModel(model);	 	 
	    scheduledTable.setShowGrid(true);
	    scheduledTable.setShowVerticalLines(true);	
		}

	
	
	public static void populateCustomerTransactionTable(String data[][]) {

	//Shaheim - Add parameters to each row in JTable
	//JTable variable name.additemstorow[i](customerName, equipType, equipName, reqDate, quotation, confirmed);
	String columns[] = { "Name", "Equipment Type","Equipment Name","Qoutation","Requested Date","Confirmed" };
    DefaultTableModel model = new DefaultTableModel(data,columns);

    pastTransactionsTable.setModel(model);	 	 
    pastTransactionsTable.setShowGrid(true);
    pastTransactionsTable.setShowVerticalLines(true);
	 	

	
	}

}
