package StandAlone;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import StandAlone.Filter.*;

public class FilterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Filter[] filters = new Filter[9];		
	
	public ActionListener buttonListener = new ActionListener() {				
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Table.filterCourses();
		}
	};	
	
	public FilterPanel() {
		filters[0] = new Credits();
		filters[1] = new CRN();
		filters[2] = new Subject();		
		filters[3] = new CourseNumber();		
		filters[4] = new Title();
		filters[5] = new Professor();	
		filters[6] = new Time();
		filters[7] = new Days();
		filters[8] = new Full();
		
		for (Filter filter : filters) {
			filter.setAlignmentX(0);
		}
		
					
		JPanel leftPanel = new JPanel();						
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
		
		leftPanel.add(filters[0]);
		
		JPanel subPanel = new JPanel();						
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
		subPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
		
		subPanel.add(filters[1]);
		subPanel.add(filters[2]);
		
		leftPanel.add(subPanel);
		
		
		
		for(int i = 3; i < 6; i++) {			
			leftPanel.add(filters[i]);
		}
		
		JPanel rightPanel = new JPanel();		
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));		
		rightPanel.add(filters[6]);
		rightPanel.add(filters[7]);
		rightPanel.add(filters[8]);
		JButton applyButton = new JButton("Apply Filters");
		applyButton.setMaximumSize(new Dimension(350, 20));
		rightPanel.add(applyButton);		
		applyButton.addActionListener(buttonListener);
				
		setLayout(new GridLayout(1, 2, 20, 0));
		add(leftPanel);
		add(rightPanel);
		setMaximumSize(new Dimension(700, 200));
	}
	
	public static final boolean apply(Course course) {
		for (Filter filter : filters) {
			if(!filter.apply(course)) {
				return false;
			}
		}
		return true;
	}
}
