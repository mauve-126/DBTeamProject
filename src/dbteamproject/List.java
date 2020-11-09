package dbteamproject;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dbteamproject.List.MyDefaultTableCellRenderer;


public class List extends JFrame{
	
	static Vector v;  
    static Vector cols;
	public static JLabel lbSelected;
	public static JFrame mainFrame = new JFrame();
	public static ArrayList<String> selected_ssn = new ArrayList<String>();
	public static ArrayList<String> selectedname = new ArrayList<String>();
	public static ArrayList<Integer> selected_row = new ArrayList<Integer>();

	
	private static Connection con;
	
	private static JComboBox cmbDepartment = new JComboBox();
	private static JCheckBox chkBoxName = new JCheckBox("NAME", true);
	private static JCheckBox chkBoxSsn = new JCheckBox("SSN", true);
	private static JCheckBox chkBoxBdate = new JCheckBox("BDATE", true);
	private static JCheckBox chkBoxAddress = new JCheckBox("ADDRESS", true);
	private static JCheckBox chkBoxSex = new JCheckBox("SEX", true);
	private static JCheckBox chkBoxSalary = new JCheckBox("SALARY", true);
	private static JCheckBox chkBoxSuperName = new JCheckBox("SUPERVISOR", true);
	private static JCheckBox chkBoxDname = new JCheckBox("DNAME", true);
	private static JButton btnSearch = new JButton("검색");
	
	private static DefaultTableModel defaultTableModel = new DefaultTableModel();
	private static JTable table = new JTable(defaultTableModel);
	private static JScrollPane sPane = new JScrollPane(table);
	
	static class MyDefaultTableCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox chkBox = new JCheckBox();
			chkBox.setSelected(isSelected);
			chkBox.setHorizontalAlignment(JCheckBox.CENTER);
			if(chkBox.isSelected()) {
				String selectedssn = (((Vector) v.get(row)).get(1)).toString();
				String selected_name = (((Vector) v.get(row)).get(0)).toString();
				if(selected_ssn.contains(selectedssn)) {
					selected_ssn.remove(selected_name);
				} else {
					selected_ssn.add(selectedssn);
					selectedname.add(selected_name);
				}
				lbSelected.setText("select : "+selectedname.toString());
				System.out.println("selected_ssn :: " + selected_ssn.toString());
			}
			return chkBox;
		}

	}
	
	
	public static void main(String[] args) throws SQLException {
		
		//메인 프레임 생성
		mainFrame.setTitle("Informational Retrieval System");
		mainFrame.setSize(1200, 380);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 상단 패널 생성
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 1200, 40);
		EmployeeDAO Dao = new EmployeeDAO();
		ArrayList<String> arrDepartment = new ArrayList<String>();
		arrDepartment = Dao.department(); 
		
		cmbDepartment = new JComboBox(arrDepartment.toArray(new String[arrDepartment.size()]));
		
		topPanel.add(cmbDepartment);
		topPanel.add(chkBoxName);
		topPanel.add(chkBoxSsn);
		topPanel.add(chkBoxBdate);
		topPanel.add(chkBoxAddress);
		topPanel.add(chkBoxSex);
		topPanel.add(chkBoxSalary);
		topPanel.add(chkBoxSuperName);
		topPanel.add(chkBoxDname);
		topPanel.add(btnSearch);
		
		mainFrame.add(topPanel);
		
		// 하단 패널 생성
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(0, 260, 1200, 100);
		
		
		lbSelected = new JLabel("Selected Employee : ");
		lbSelected.setFont(new Font("Dialog", Font.BOLD, 14));
		JLabel lbSalary = new JLabel("new salary : ");
		lbSalary.setFont(new Font("Dialog", Font.BOLD, 12));
		JTextField tfSalary = new JTextField();
		JButton btnUpdate = new JButton("UPDATE");
		JButton btnDelete = new JButton("DELETE");
		

		bottomPanel.add(lbSelected);
		bottomPanel.add(lbSalary);
		bottomPanel.add(tfSalary);
		bottomPanel.add(btnUpdate);
		bottomPanel.add(btnDelete);

		lbSelected.setBounds(5, 25, 1200, 20);
		lbSalary.setBounds(5, 50, 100, 20);
		tfSalary.setBounds(90, 50, 100, 20);
		btnUpdate.setBounds(200, 50, 100, 20);
		btnDelete.setBounds(1050, 50, 100, 20);
		
		mainFrame.add(bottomPanel);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				mainFrame.remove(sPane);
				Dao.Delete(selected_ssn);
				ShowTable();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) { 
					if(!tfSalary.getText().isEmpty()) {
						mainFrame.remove(sPane);
						Dao.Update(selected_ssn, tfSalary.getText());
						ShowTable();
					}
			}
		});
				
		// 중앙 데이터뷰 생성		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				mainFrame.remove(sPane);
				ShowTable();
			}
			
		});		
		
		mainFrame.setVisible(true);
	
	}
	public static void ShowTable() {
		
		lbSelected.setText("select : ");
		selectedname.clear();
		selected_ssn.clear();
		ArrayList<String> tableHeader = new ArrayList<String>();
		
		if(chkBoxName.isSelected()) {
			tableHeader.add(chkBoxName.getText());
		}
		if(chkBoxSsn.isSelected()) {
			tableHeader.add(chkBoxSsn.getText());
		}
		if(chkBoxBdate.isSelected()) {
			tableHeader.add(chkBoxBdate.getText());	
		}
		if(chkBoxAddress.isSelected()) {
			tableHeader.add(chkBoxAddress.getText());		
		}
		if(chkBoxSex.isSelected()) {
			tableHeader.add(chkBoxSex.getText());		
		}
		if(chkBoxSalary.isSelected()) {
			tableHeader.add(chkBoxSalary.getText());	
		}
		if(chkBoxSuperName.isSelected()) {
			tableHeader.add(chkBoxSuperName.getText());		
		}
		if(chkBoxDname.isSelected()) {
			tableHeader.add(chkBoxDname.getText());		
		}
		
		Vector col = new Vector();
		ArrayList<String> tables = new ArrayList<String>();
		if(cmbDepartment.getSelectedIndex() != 0) {
			tables.add(cmbDepartment.getSelectedItem().toString());
		}else {
			tables.add(null);
		}
		for (int i = 0; i<tableHeader.size(); i++) {
  		  String head = tableHeader.get(i);
      	  col.add(head);
      	  }
		col.add("select");
		
		EmployeeDAO dao = new EmployeeDAO();
		v = dao.getList(tableHeader,tables);
		
		defaultTableModel = new DefaultTableModel(v, col);
	     table = new JTable(defaultTableModel);
	     sPane = new JScrollPane(table);
	     sPane.setBounds(0, 50, 1200, 200);
		
	     System.out.println("v="+v);
	     
	     DefaultTableCellRenderer renderer = new MyDefaultTableCellRenderer();
	     table.getColumn("select").setCellRenderer(renderer);
	     table.getColumn("select").setPreferredWidth(15);
	     mainFrame.add(sPane);
	     
		
	}

}
