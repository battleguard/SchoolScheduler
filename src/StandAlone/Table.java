package StandAlone;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class Table extends JPanel {
	private static Vector<Course> ALL_COURSES = new Vector<Course>();
	
	@SuppressWarnings("rawtypes")
	private static final Vector<Vector> COURSE_DATA = new Vector<Vector>();
	private static final String[] COLUMN_HEADERS = {"CRN", "Subject", "Number", "Section", "Credits", "Title", "Days", "Time", "Capacity", "Filled", "Open", "Professor", "Location", "Overall", "Helpfulness", "Clarity", "Easiness"};
	private static final Vector<String> COLUMN_NAMES = new Vector<String>(Arrays.asList(COLUMN_HEADERS));
	private static final JTable table = new JTable(COURSE_DATA, COLUMN_NAMES);
	public static final JScrollPane scrollPane = new JScrollPane(table);
	
	public static final int TABLE_WIDTH = 1400;
	public static final int TABLE_HEIGHT = 600;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Table table = new Table();		
		frame.add(table);		
		frame.setLocation(100, 100);		
		frame.pack();
		frame.setVisible(true);	
	}
	
	public Table() {
		ALL_COURSES = CourseFile.loadAllCourses();
		setupColumnSpacing();
		scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		add(scrollPane);
		showAllCourses();
	}
	

	
	public static final void setupColumnSpacing() {
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
			table.getColumnModel().getColumn(i).setPreferredWidth(width);
		}
	}
	
	public static final void showAllCourses() {
		COURSE_DATA.removeAllElements();
		for (Course course : ALL_COURSES) {
			COURSE_DATA.add(course.getrowData());
		}
		table.repaint();
		scrollPane.revalidate();
	}
	
	public static final void filterCourses() {		
		COURSE_DATA.removeAllElements();
		for (Course course : ALL_COURSES) {
			if(FilterPanel.apply(course)) {
				COURSE_DATA.add(course.getrowData());
			}
		}
		table.repaint();
		scrollPane.revalidate();
	}
}
