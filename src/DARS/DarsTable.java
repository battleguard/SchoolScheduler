package DARS;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DarsTable {
	@SuppressWarnings("rawtypes")
	private static final Vector<Vector> COURSE_DATA = new Vector<Vector>();
	private static final String[] COLUMN_HEADERS = {"Subject", "Number"};
	private static final Vector<String> COLUMN_NAMES = new Vector<String>(Arrays.asList(COLUMN_HEADERS));
	public static final JTable table = new JTable(COURSE_DATA, COLUMN_NAMES);
	public static final JScrollPane scrollPane = new JScrollPane(table);
	
	public static final int TABLE_WIDTH = 1200;
	public static final int TABLE_HEIGHT = 600;
	
	public static final void addCourse(Course course) {
		COURSE_DATA.add(course.getRowData());
		table.repaint();
		scrollPane.revalidate();
	}
	
	public static final void setupColumnSpacing() {
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
	}
	
}
