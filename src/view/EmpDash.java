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
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;

public class EmpDash extends JPanel {

	private NewRequestForm newReq;
	private SystemController controller;
	private static JTable generalRequesttable;
	private JTable table_1;
	private JTable table_2;

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
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		generalRequesttable = new JTable();
		scrollPane.setViewportView(generalRequesttable);
		
		Panel panel_1 = new Panel();
		tabbedPane.addTab("Scheduled", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Past Transactions", null, panel_2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
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
		
		setVisible(true);
	}

	public static void populateCustomerRequestTable(String data[][]) {

	//JTable variable name.additemstorow[i](customerName, equipType, equipName, reqDate, quotation, confirmed);
	String columns[] = { "Name", "Equipment Type","Equipment Name","Qoutation","Requested Date","Confirmed" };
    DefaultTableModel model = new DefaultTableModel(data,columns);

	 	generalRequesttable.setModel(model);	 	 
	 	generalRequesttable.setShowGrid(true);
	 	generalRequesttable.setShowVerticalLines(true);
	 	

	
	}
	
}
