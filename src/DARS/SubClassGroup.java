package DARS;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JTable;
import Main.Table;

public class SubClassGroup {
	public String Title = "";
	public final Vector<Course> courses = new Vector<Course>();
	
	
	public SubClassGroup(String input) {
		System.out.println("creating subclassgroup");
		addTitle(Dars.format(input));
	}
	
	public void addTitle(String title) {		
		if(!Title.isEmpty()) Title += "\r\n"; 
		Title += title;
	}
	
	public JTable getTable() {
		String[][] CourseList = new String[courses.size()][];
		for (int i = 0; i < CourseList.length; i++) {
			CourseList[i] = new String[1];
			CourseList[i][0] = courses.get(i).toString();
		}
		
		String[] COLUMN_HEADERS = {Title};
		JTable table = new JTable(CourseList, COLUMN_HEADERS);								
		return table;
	}
	
	public ActionListener buttonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Table.showDarsCourses(courses);
		}
	};
	
	public void addCourses(final BufferedReader br) throws IOException {
		do {
			Course course = new Course(Dars.input);
			courses.add(course);			
		} while((Dars.input = br.readLine()) != null && Dars.input.contains("CLASS"));
	}
}
