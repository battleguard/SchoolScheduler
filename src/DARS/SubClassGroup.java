package DARS;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class SubClassGroup {
	public String Title = "";
	public Vector<Course> courses = new Vector<Course>();
	
	public SubClassGroup(String input) {
		System.out.println("creating subclassgroup");
		addTitle(Dars.format(input));
	}
	
	public void addTitle(String title) {		
		if(!Title.isEmpty()) Title += "\r\n"; 
		Title += title;
	}
	
	public void addCourses(final BufferedReader br) throws IOException {
		do {
			Course course = new Course(Dars.input);
			courses.add(course);
		} while((Dars.input = br.readLine()) != null && Dars.input.contains("CLASS"));
	}
}
