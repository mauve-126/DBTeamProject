package dbteamproject;

import java.util.Comparator;
import java.util.Vector;

public class EmployeeComparator implements Comparator<Vector<String>  >{
	private int columnIndex = 0;
	public EmployeeComparator(int columnIndex) {this.columnIndex=columnIndex;}
	
	@Override
	public int compare(Vector<String> o1, Vector<String> o2) {
		// TODO Auto-generated method stub
		System.out.println("o1[columnIndex]"+o1.get(columnIndex));
		
		return o1.get(columnIndex).compareTo(o2.get(columnIndex));
	}
}