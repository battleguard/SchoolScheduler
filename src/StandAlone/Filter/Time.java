package StandAlone.Filter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import StandAlone.Course;

@SuppressWarnings("serial")
public class Time extends Filter {
	
	private static final Integer[] HOURS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private static final Integer[] MINUTES = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
	private static final String[] AM_PM = {"am", "pm"};
	
	private final JComboBox<Integer> startHours = new JComboBox<Integer>(HOURS);
	private final JComboBox<Integer> startMinutes = new JComboBox<Integer>(MINUTES);
	private final JComboBox<String> startAmPm = new JComboBox<String>(AM_PM);
	
	private final JComboBox<Integer> endHours = new JComboBox<Integer>(HOURS);
	private final JComboBox<Integer> endtMinutes = new JComboBox<Integer>(MINUTES);
	private final JComboBox<String> endAmPm = new JComboBox<String>(AM_PM);
	
	public Time() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(createPanel(true));
		add(createPanel(false));
	}
	
	private void setSize(Component... comps) {
		for (Component component : comps) {
			component.setMaximumSize(new Dimension(50, 20));
		}
	}
	
	private JPanel createPanel(boolean start) {
		setSize(startHours, startMinutes, startAmPm, endHours, endtMinutes, endAmPm);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(new JLabel((start ? "Start" : "End") +  " Time: "));
		panel.add(new JLabel("Hour "));
		panel.add(start ? startHours : endHours);
		panel.add(new JLabel("Minute "));
		panel.add(start ? startMinutes : endtMinutes);
		panel.add(new JLabel("am/pm "));
		panel.add(start ? startAmPm : endAmPm);
		return panel;
	}

	@Override
	public boolean apply(Course course) {
		final int startTime = (startHours.getSelectedIndex() % 12) * 60 + startMinutes.getSelectedIndex() * 10 + startAmPm.getSelectedIndex() * 12 * 60;
		final int endTime = (endHours.getSelectedIndex() % 12) * 60 + endtMinutes.getSelectedIndex() * 10 + endAmPm.getSelectedIndex() * 12 * 60;
		

		
		
		if(startTime != 0 || endTime != 0) {
			if(course.Time.length() > 10) {
				String[] data = course.Time.split("-");
				final int dataStartTime = getTime(data[0]);
				final int dataEndTime = getTime(data[1]);
				if(course.CRN == 75122) {
					System.out.println("Start: " + startTime);
					System.out.println("End: " + endTime);
					System.out.println("dataStartTime: " + dataStartTime);
					System.out.println("dataEndTime: " + dataEndTime);
				}
				
				if(startTime != 0 && startTime > dataStartTime) {
					return false;
				}
				if(endTime != 0 && endTime < dataEndTime) {
					return false;
				}
				return true;
			}
			return false;
		}
		return true;
	}
	
	public int getTime(String input) {
		try {
			input = input.trim();
			int time = (Integer.parseInt(input.substring(0, 2)) % 12) * 60;
			time += Integer.parseInt(input.substring(3, 5));
			if(input.endsWith("pm")) {
				time += 12 * 60;
			}
			return time;
		} catch(Exception e) {
		}
		return 0;
	}

}
