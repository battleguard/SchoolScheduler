package StandAlone.Filter;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import StandAlone.Course;

@SuppressWarnings("serial")
public class CRN extends Filter {
	
	JTextField crnTextField = new JTextField("", 5);	
	
	public CRN() {
		//crnTextField.setMaximumSize(new Dimension(100, TEXT_FIELD_HEIGHT));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("CRN: "));
		add(crnTextField);
	}

	@Override
	public boolean apply(Course course) {
		final String CRN = crnTextField.getText();		
		if(!CRN.isEmpty() && !("" + course.CRN).startsWith(CRN)) {
			return false;
		}				
		return true;
	}
}
