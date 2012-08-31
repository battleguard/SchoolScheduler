package StandAlone.Filter;

import javax.swing.JPanel;

import StandAlone.Course;

@SuppressWarnings("serial")
public abstract class Filter extends JPanel {		
	
	public static int TEXT_FIELD_HEIGHT = 20;
	
	public abstract boolean apply(Course course);
}
