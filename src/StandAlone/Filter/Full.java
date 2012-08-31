package StandAlone.Filter;

import javax.swing.JCheckBox;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Full extends Filter {
	
	JCheckBox fullCheckBox = new JCheckBox("Hide Full Courses");	
	
	public Full() {
		fullCheckBox.setAlignmentX(RIGHT_ALIGNMENT);
		add(fullCheckBox);
	}

	@Override
	public boolean apply(Course course) {
		if(course.isFull() && fullCheckBox.isSelected()) {
			return false;
		}
		return true;
	}
}
