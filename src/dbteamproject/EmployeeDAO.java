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
	
	public ArrayList<String> department() {
		ArrayList<String> department = new ArrayList<String>();
		department.add("all");
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		Connection con = null;       //�뿰寃�
        PreparedStatement ps = null; //紐낅졊
        ResultSet rs = null;         //寃곌낵
        

        try {
           
			con = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql = "select * from DEPARTMENT";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
            	String Dname = rs.getString(1);
            	department.add(Dname);
            	        
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return department;
		
	}
	
public Vector getList(ArrayList<String> attributes, ArrayList<String> tables){
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	       
        Vector data = new Vector();  
       
        Connection con = null;       
        PreparedStatement ps = null; 
        ResultSet rs = null;        
       
        try{
        	
        	
			con = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String all ="select * from DEPARTMENT d, EMPLOYEE e left outer join EMPLOYEE s on s.ssn = e.super_ssn  where e.dno = d.dnumber";
			String department="select * from DEPARTMENT d, EMPLOYEE e left outer join EMPLOYEE s on s.ssn = e.super_ssn  where e.dno = d.dnumber and d.dname = ?";
			String sql;

			
			if (tables.get(0) == null) {
				sql = all;
			}else {
				sql = department;
			}
			ps = con.prepareStatement(sql);
			if(sql == department) {
				ps.setString(1, tables.get(0));
			}
			rs=ps.executeQuery();
            
           
           
            while(rs.next()){
            	Vector row = new Vector();
            	for (int i = 0; i< attributes.size(); i++) {
            		if (attributes.get(i) == "NAME") {
            			String fnameString;
            			fnameString=rs.getString("e.fname");
            			String minitString;
            			minitString=rs.getString("e.minit");
            			String lnameString;
            			lnameString=rs.getString("e.lname");
            			String name = fnameString + ", " + minitString +", "+lnameString;
            			row.add(name);
            		}else if(attributes.get(i) == "SUPERVISOR"){
            			String fnameString;
            			fnameString=rs.getString("s.fname");
            			String minitString;
            			minitString=rs.getString("s.minit");
            			String lnameString;
            			lnameString=rs.getString("s.lname");
            			String name = fnameString + ", " + minitString +", "+lnameString;
            			row.add(name);
            		}else {
            			String d=rs.getString(attributes.get(i));
                		row.add(d);
            			
            		}
            		  
            		}
            	data.add(row);
            	
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
	public Vector getMemberList(){
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	       
        Vector data = new Vector(); 
       
        Connection con = null;      
        PreparedStatement ps = null; 
        ResultSet rs = null;         
       
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
				
				String name = fnameString + ", " + minitString +", "+lnameString;
				String sname = superFname + ", " + superMinit +", "+superLname;
               
                Vector row = new Vector();
                row.add(name);
                row.add(ssnString);
                row.add(bdateString);
                row.add(addressString);
                row.add(sexString);
                row.add(salaryString);
                row.add(sname);
                row.add(dnoString);
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
	public void Delete(ArrayList<String> ssn) {
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

       
        Connection con = null;  
        try {
        	
        	con = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        	System.out.println(ssn.size());
        	for(int i = 0; i < ssn.size(); i++) {
        		String del="delete from EMPLOYEE where ssn = ?";
  	    	  PreparedStatement p2=con.prepareStatement(del);
  	          p2.clearParameters();
  	          p2.setString(1, ssn.get(i));
  	          int delr=p2.executeUpdate();
  		      if( delr == 0 ){
  		          System.out.println("error");
  		      }else{
  		          System.out.println("delete");
  		      }
                }

	    	  
	    	  
        } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}
public void Update(ArrayList<String> ssn, String Salary) {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //JDBC �뱶�씪�씠踰� �뿰寃�
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

       
        Connection con = null;  
        try {
        	con = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        	System.out.println(ssn.size());
        	for(int i = 0; i < ssn.size(); i++) {
        		String update="update EMPLOYEE set salary = ? where ssn = ?";
                PreparedStatement p3=con.prepareStatement(update);
                p3.clearParameters();
                p3.setString(1, Salary);
                p3.setString(2, ssn.get(i));
                int up=p3.executeUpdate();
                if( up > 0 ){
                    System.out.println("update");
                    System.out.println(i);
                }else{
                    System.out.println("error");
                }

				
			}
            

 
        } catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}


}
