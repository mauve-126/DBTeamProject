package dbteamproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class EmployeeDAO {
	private static String dburl = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
	private static String dbUser = "root";
	private static String dbpasswd = "tracer85x1005@";
	
	void insert() {
		
	}
	void update() {
		
	}
	void delete() {
		
	}
	public Vector getMemberList(){
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC 드라이버 연결
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	       
        Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
        	
        	ArrayList<String> num = new ArrayList<String>();
			num.add("e.fname");
			num.add("e.minit");
			num.add("e.lname");
			num.add("e.ssn");
			num.add("e.bdate");
			num.add("e.address");
			num.add("e.sex");
			num.add("e.salary");
			num.add("s.fname");
			num.add("s.minit");
			num.add("s.lname");
			num.add("d.dname");//12
           
			con = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql = "select * from DEPARTMENT d, EMPLOYEE e left outer join EMPLOYEE s on s.ssn = e.super_ssn  where e.dno = d.dnumber";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
           
           
            while(rs.next()){
            	String fnameString = rs.getString(num.get(0));
				String minitString = rs.getString(num.get(1));
				String lnameString = rs.getString(num.get(2));
				String ssnString = rs.getString(num.get(3));
				String bdateString = rs.getString(num.get(4));
				String addressString = rs.getString(num.get(5));
				String sexString=rs.getString(num.get(6));
				String salaryString=rs.getString(num.get(7));
				String superFname = rs.getString(num.get(8));
				String superMinit = rs.getString(num.get(9));
				String superLname = rs.getString(num.get(10));
				String dnoString = rs.getString(num.get(11));
               
                Vector row = new Vector();
                row.add(fnameString);
                row.add(minitString);
                row.add(lnameString);
                row.add(ssnString);
                row.add(bdateString);
                row.add(addressString);
                row.add(sexString);
                row.add(salaryString);
                row.add(superFname);
                row.add(superMinit);
                row.add(superLname);
                row.add(dnoString);
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }

	public ArrayList<EmployeeDTO> search(ArrayList<String> attributes, ArrayList<String> tables) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		EmployeeDTO em = null;
		ArrayList<EmployeeDTO> selectedResultArrayList =  new ArrayList<EmployeeDTO>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
//			int wantedArrayListLength = wanted.size();
			String all ="select * from DEPARTMENT d, EMPLOYEE e left outer join EMPLOYEE s on s.ssn = e.super_ssn  where e.dno = d.dnumber";
			String department="select * from DEPARTMENT d, EMPLOYEE e left outer join EMPLOYEE s on s.ssn = e.super_ssn  where e.dno = d.dnumber and d.dname = ?";
			String sql;
//			for(int i=0;i<wantedArrayListLength;i++) {
//				sql += wanted.get(i);
//				if(i!=wantedArrayListLength-1) {
//					sql+= ",";
//				}
//			}
			//원하는 쿼리에 있는 항목들만 읽어옴
			
			if (tables.get(1) == null) {
				sql = all;
			}else {
				sql = department;
			}
			ps = conn.prepareStatement(sql);
			if(sql == department) {
				ps.setString(1, tables.get(1));
			}
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String fnameString;
				if(attributes.get(0) == null) {
					fnameString = null;
				}else {
					fnameString=rs.getString(attributes.get(0));
				}
				String minitString;
				if(attributes.get(1) == null) {
					minitString = null;
				}else {
					minitString=rs.getString(attributes.get(1));
				}
				String lnameString;
				if(attributes.get(2) == null) {
					lnameString = null;
				}else {
					lnameString=rs.getString(attributes.get(2));
				}
				String ssnString;
				if(attributes.get(3) == null) {
					ssnString = null;
				}else {
					ssnString=rs.getString(attributes.get(3));
				}
				String bdateString;
				if(attributes.get(4) == null) {
					bdateString = null;
				}else {
					bdateString=rs.getString(attributes.get(4));
				}
				String addressString;
				if(attributes.get(5) == null) {
					addressString = null;
				}else {
					addressString=rs.getString(attributes.get(5));
				}
				String sexString;
				if(attributes.get(6) == null) {
					sexString = null;
				}else {
					sexString=rs.getString(attributes.get(6));
				}
				String salaryString;
				if(attributes.get(7) == null) {
					salaryString = null;
				}else {
					salaryString=rs.getString(attributes.get(7));
				}
				String superFname;
				if(attributes.get(8) == null) {
					superFname = null;
				}else {
					superFname=rs.getString(attributes.get(8));
				}
				String superMinit;
				if(attributes.get(9) == null) {
					superMinit = null;
				}else {
					superMinit=rs.getString(attributes.get(9));
				}
				String superLname;
				if(attributes.get(10) == null) {
					superLname = null;
				}else {
					superLname=rs.getString(attributes.get(10));
				}
				String dnoString;
				if(attributes.get(11) == null) {
					dnoString = null;
				}else {
					dnoString=rs.getString(attributes.get(11));
				}
				em = new EmployeeDTO(fnameString, minitString, lnameString,ssnString, bdateString, addressString, sexString, salaryString, superFname, superMinit, superLname, dnoString);
				selectedResultArrayList.add(em);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return selectedResultArrayList;
	}


}
