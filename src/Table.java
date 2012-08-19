import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Table {
	private static final Vector<Course> ALL_COURSES = new Vector<Course>();
	private static final Vector<Vector> COURSE_DATA = new Vector<Vector>();
	private static final String[] COLUMN_HEADERS = {"CRN", "Subject", "Number", "Section", "Credits", "Title", "Days", "Time", "Capacity", "Filled", "Open", "Professor", "Location"};
	private static final Vector<String> COLUMN_NAMES = new Vector<String>(Arrays.asList(COLUMN_HEADERS));
	public static final JTable table = new JTable(COURSE_DATA, COLUMN_NAMES);
	public static final JScrollPane scrollPane = new JScrollPane(table);
	
	public static final int TABLE_WIDTH = 1200;
	public static final int TABLE_HEIGHT = 600;
	
	public static final void addCourse(Course course) {
		ALL_COURSES.add(course);
		COURSE_DATA.add(course.getrowData());
		table.repaint();
	}
	
	public static final void setupColumnSpacing() {
		int total = 0;
		for(int i = 0; i < COLUMN_HEADERS.length; i++) {			
			int width = 50;
			switch (i) {
			case 5:
				width = 250;
				break;
			case 7:
				width = 150;
				break;
			case 11:
				width = 150;
				break;
			case 12:
				width = 80;
				break;
			}
			total += width;
			table.getColumnModel().getColumn(i).setPreferredWidth(width);
		}
		System.out.println(total);
	}
	
	public static final void showAllCourses() {
		Vector<Course> allCourse = CoursesFile.loadAllCourses();
		for (Course course : allCourse) {
			COURSE_DATA.add(course.getrowData());
			table.repaint();
			scrollPane.revalidate();
		}
	}
	
	public static final void showCourses(DARS.CLASS... classes) {
		for (DARS.CLASS curClass : classes) {
			System.out.println(curClass.Subject + "  " + curClass.Number);
		}
		
		
		Vector<Course> allCourse = CoursesFile.loadAllCourses();
		for (Course course : allCourse) {			
			for (DARS.CLASS curClass : classes) {
				if(course.Subject.equals(curClass.Subject) && ((curClass.Number.endsWith("*") && curClass.Number.charAt(0) == course.CourseNumber.charAt(0)) || curClass.Number.equals(course.CourseNumber)) ) {
					COURSE_DATA.add(course.getrowData());
					table.repaint();
					scrollPane.revalidate();
				}	
			}			
		}
	}
}
