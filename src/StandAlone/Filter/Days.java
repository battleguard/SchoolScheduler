package StandAlone.Filter;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Days extends Filter {
	
	JTextField crnTextField = new JTextField("", 5);	
	
	public Days() {
		//crnTextField.setMaximumSize(new Dimension(100, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Days: "));
		add(crnTextField);
	}

	@Override
	public boolean apply(Course course) {
		final String days = crnTextField.getText();		
		if(!days.isEmpty() && !course.Days.toLowerCase().startsWith(days.toLowerCase())) {
			return false;
		}				
		return true;
	}
}
