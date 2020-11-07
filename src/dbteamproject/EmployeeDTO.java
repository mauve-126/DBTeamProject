package dbteamproject;
import java.util.ArrayList;

public class EmployeeDTO {
	private String Fname;
	private String Minit;
	private String Lname;
	private String Ssn;
	private String Bdate;
	private String Address;
	private String Sex;
	private String Salary;
	private String SuperFname;
	private String SuperMinit;
	private String SuperLname;
	private String Dno;
	public EmployeeDTO(String fname, String minit, String lname, String ssn, String bdate, String address, String sex, String salary,
			String Superfname, String Superminit, String Superlname, String dno) {
		super();
		Fname = fname;
		Minit = minit;
		Lname = lname;
		Ssn = ssn;
		Bdate = bdate;
		Address = address;
		Sex = sex;
		Salary = salary;
		SuperFname = Superfname;
		SuperMinit = Superminit;
		SuperLname = Superlname;
		Dno = dno;
	}
	
	@Override
	public String toString() {
		return "Employee [Fname=" + Fname + ", Minit=" + Minit + ",Lname=" + Lname + ", Ssn=" + Ssn + ", Bdate=" + Bdate + ", Address="
				+ Address + ", Sex=" + Sex + ", Salary=" + Salary + ", SuperFname=" + SuperFname + ", SuperMinit=" + SuperMinit+ ", SuperLname="+SuperLname+ ", Dno=" + Dno + "]";
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getMinit() {
		return Minit;
	}
	public void setMinit(String minit) {
		Minit = minit;
	}
	public String getSsn() {
		return Ssn;
	}
	public void setSsn(String ssn) {
		Ssn = ssn;
	}
	public String getBdate() {
		return Bdate;
	}
	public void setBdate(String bdate) {
		Bdate = bdate;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	public String getSuperFname() {
		return SuperFname;
	}
	public void setSuperFname(String superFname) {
		SuperFname = superFname;
	}
	public String getSuperSuperMinit() {
		return SuperMinit;
	}
	public void setSuperMinit(String superMinit) {
		SuperMinit = superMinit;
	}
	public String getSuperLname() {
		return SuperLname;
	}
	public void setSuperLname(String superLname) {
		SuperLname = superLname;
	}
	public String getDno() {
		return Dno;
	}
	public void setDno(String dno) {
		Dno = dno;
	}

}
