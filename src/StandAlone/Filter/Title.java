package StandAlone.Filter;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Title extends Filter {
	JTextField titleTextField = new JTextField("", 15);	
	
	public Title() {
		//titleTextField.setMaximumSize(new Dimension(200, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Title Contains: "));
		add(titleTextField);
	}
	
	

	@Override
	public boolean apply(Course course) {		
		final String subject = titleTextField.getText();											
		
		if(!subject.isEmpty()) {
			final String[] titleWords = subject.split(" ");
			final String[] courseTitleWords = course.Title.split(" ");
			m: for (String keyword : titleWords) {
				for (String courseTitleKeyword : courseTitleWords) {
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
