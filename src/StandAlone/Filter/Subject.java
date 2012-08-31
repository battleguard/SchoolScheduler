package StandAlone.Filter;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Subject extends Filter {
	
	JTextField subjectTextField = new JTextField("", 3);	
	
	public Subject() {
		//subjectTextField.setMaximumSize(new Dimension(200, TEXT_FIELD_HEIGHT));		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Subject: "));
		add(subjectTextField);
	} 
	
	

	@Override
	public boolean apply(Course course) {		
		final String subject = subjectTextField.getText();				
		
		if(!subject.isEmpty()) {
			return subject.equalsIgnoreCase(course.Subject);			
		}
		return true;
	}
}
