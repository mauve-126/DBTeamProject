package dbteamproject;


import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class List extends JFrame implements MouseListener,ActionListener{
	 Vector v;  
	    Vector cols;
	    DefaultTableModel model;
	    JTable jTable;
	    JScrollPane pane;
	    JPanel pbtn;
	    JButton btnInsert;
	       
	   
	    public List(){
	        super("Information Retrieval System");
	        //v=getMemberList();
	        //MemberDAO
	        EmployeeDAO dao = new EmployeeDAO();
	        v = dao.getMemberList();
	        System.out.println("v="+v);
	        cols = getColumn();
	       
	        //public DefaultTableModel()
	        //public DefaultTableModel(int rowCount, int columnCount)
	        //public DefaultTableModel(Vector columnNames, int rowCount)
	        //public DefaultTableModel(Object[] columnNames, int rowCount)
	        //public DefaultTableModel(Vector data,Vector columnNames)
	        //public DefaultTableModel(Object[][] data,Object[] columnNames)
	       
	        model = new DefaultTableModel(v, cols);
	       
	        //JTable()
	        //JTable(int numRows, int numColumns)
	        //JTable(Object[][] rowData, Object[] columnNames)
	        //JTable(TableModel dm)
	        //JTable(TableModel dm, TableColumnModel cm)
	        //JTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
	        //JTable(Vector rowData, Vector columnNames)
	       
	        //jTable = new JTable(v,cols);
	        jTable = new JTable(model);
	        pane = new JScrollPane(jTable);
	        add(pane);
	       
	        pbtn = new JPanel();
	        btnInsert = new JButton("회원가입");
	        pbtn.add(btnInsert);
	        add(pbtn,BorderLayout.NORTH);
	       
	       
	        jTable.addMouseListener(this); //리스너 등록
	        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
	       
	        setSize(1000,500);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }//end 생성자
	   
	   
	    //JTable의 컬럼
	    public Vector getColumn(){
	        Vector col = new Vector();
	        col.add("fname");
	        col.add("minit");
	        col.add("lname");
	        col.add("ssn");
	        col.add("bdate");
	        col.add("address");
	        col.add("sex");
	        col.add("salary");
	        col.add("sup");
	        col.add("er");
	        col.add("visor");
	        col.add("department");
	       
	        return col;
	    }//getColumn
	   
	   
	    //Jtable 내용 갱신 메서드
	    public void jTableRefresh(){
	       
	        EmployeeDAO dao = new EmployeeDAO();
	        DefaultTableModel model= new DefaultTableModel(dao.getMemberList(), getColumn());
	        jTable.setModel(model);    
	       
	    }
	   
	    public static void main(String[] args) {
	        new List();
	    }//main
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        // mouseClicked 만 사용
	        int r = jTable.getSelectedRow();
	        String id = (String) jTable.getValueAt(r, 0);
	        //System.out.println("id="+id);
	        //MemberProc mem = new MemberProc(id,this); //아이디를 인자로 수정창 생성
	               
	    }
	    @Override
	    public void mouseEntered(MouseEvent e) {
	        // TODO Auto-generated method stub
	       
	    }
	    @Override
	    public void mouseExited(MouseEvent e) {
	        // TODO Auto-generated method stub
	       
	    }
	    @Override
	    public void mousePressed(MouseEvent e) {
	        // TODO Auto-generated method stub
	       
	    }
	    @Override
	    public void mouseReleased(MouseEvent e) {
	        // TODO Auto-generated method stub
	       
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        //버튼을 클릭하면
	        if(e.getSource() == btnInsert ){
	            //new MemberProc(this);
	           
	            /*테스트*/
	            //dao = new MemberDAO();           
	            //dao.userSelectAll(model);
	            //model.fireTableDataChanged();
	            //jTable.updateUI();           
	            //jTable.requestFocusInWindow();
	           
	        }
	       
	    }

}
