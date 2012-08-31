package StandAlone.Filter;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Credits extends Filter {
	
	JTextField minTextField = new JTextField("", 3);
	JTextField maxTextField = new JTextField("", 3);
	
	public Credits() {
		//minTextField.setMaximumSize(new Dimension(50, TEXT_FIELD_HEIGHT));
		//maxTextField.setMaximumSize(new Dimension(50, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		add(new JLabel("Credit Range: "));
		add(minTextField);
		add(new JLabel(" hours to "));
		add(maxTextField);
		add(new JLabel(" hours"));
	}

	@Override
	public boolean apply(Course course) {
		final String minText = minTextField.getText();
		final String maxText = maxTextField.getText();
		
		if(!minText.isEmpty()) {
			try {				
				final double min = Double.parseDouble(minText);
				if(maxText.isEmpty()) {
					return course.Credits == min;
				} else {
					final double max = Double.parseDouble(maxText);
					return course.Credits >= min && course.Credits <= max;
				}
			} catch (Exception NumberFormatException) {
				System.out.println("Please Type in only numbers");
			}
			return false;
		}
		return true;
	}

}
