package StandAlone.Filter;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Professor extends Filter {
	JTextField professorTextField = new JTextField("", 15);	
	
	public Professor() {
		//professorTextField.setMaximumSize(new Dimension(200, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Professor Name: "));
		add(professorTextField);
	}
	
	

	@Override
	public boolean apply(Course course) {		
		final String professorInput = professorTextField.getText();								
		
		if(!professorInput.isEmpty()) {
			final String[] inputKeywords = professorInput.split(" ");
			final String[] professorKeywords = course.Professor.split(" ");
			m: for (String keyword : inputKeywords) {
				for (String courseTitleKeyword : professorKeywords) {
					if(courseTitleKeyword.toLowerCase().contains(keyword.toLowerCase())) {
						continue m;
					}
				}
				return false;
			}						
		}
		return true;
	}
}
