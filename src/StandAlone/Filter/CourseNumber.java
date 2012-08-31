package StandAlone.Filter;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class CourseNumber extends Filter {
	
	JTextField minTextField = new JTextField("", 3);
	JTextField maxTextField = new JTextField("", 3);
	
	public CourseNumber() {
		//minTextField.setMaximumSize(new Dimension(50, TEXT_FIELD_HEIGHT));
		//maxTextField.setMaximumSize(new Dimension(50, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Course Number Range: "));
		add(minTextField);
		add(new JLabel(" to "));
		add(maxTextField);
	}

	@Override
	public boolean apply(Course course) {		
		final String minText = minTextField.getText();
		String maxText = maxTextField.getText();
		final int courseNumber = Integer.parseInt(course.CourseNumber.substring(0, 4));
		
		if(!minText.isEmpty()) {
			try {				
				final int min = Integer.parseInt(minText.substring(0, 4));
				if(maxText.isEmpty()) {
					return courseNumber == min;
				} else {
					final int max = Integer.parseInt(maxText.substring(0, 4));
					return courseNumber >= min && courseNumber <= max;
				}
			} catch (Exception NumberFormatException) {
				System.out.println("Please Type in only numbers for Course Number Range");
			}
			return false;
		}
		return true;
	}

}
