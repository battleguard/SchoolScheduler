package DARS;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainGroup {
	
	// PANEL FORMAT
	// JLABEL FIRST LINE OF TITLE
	// JTEXT AREA THAT INCLUDES REST OF TITLE AND NEEDS
	   // SUB GROUP JLABELS
	   // SUBCLASS GROUP JLABELS
	   // JTABELS FOR EACH SUBCLASS GROUP
	
	
	public String Title = "";
	public String Info = "";
	public Vector<SubGroup> subGroups = new Vector<SubGroup>();
	
	private JButton button = new JButton("Testing");
	private JPanel panel = new JPanel();
	
	public MainGroup(String input) {		
		System.out.println("creating maingroup");
		addTitle(Dars.format(input));
	}
	
	public void addTitle(String title) {		
		if(Title.isEmpty()) Title = title;
	}		
	
	public void addNeeds(String needs) {		
		Info += needs + "\r\n";
	}
	
	public JPanel createPanel() {	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
		button = new JButton(Title);
		button.setMaximumSize(new Dimension(500, 30));
		panel.add(button);		
		return panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public JButton getButton() {
		return button;
	}

	public void setEnabled(boolean enable) {
		if(enable) {
			for (SubGroup group : subGroups) {
				panel.add(group.getPanel());
			}
		} else {
			panel.removeAll();
			panel.add(button);
		}		
		button.setEnabled(!enable);
		panel.repaint();
		panel.revalidate();
	}
	
	public void addSubGroups(final BufferedReader br) throws IOException {
		do {
			SubGroup subGroup = new SubGroup(Dars.input);
			while((Dars.input = br.readLine()) != null && (Dars.input.contains("SUB TITLE") || Dars.input.contains("SUB SUMMARY"))) {
				if(Dars.input.contains("SUB TITLE")) {
					subGroup.addTitle(Dars.format(Dars.input));
				} else {
					subGroup.addSummary(Dars.format(Dars.input));
				}
			}
			subGroup.addSubClassGroups(br);
			subGroups.add(subGroup);
		} while(Dars.input != null && (Dars.input.contains("SUB TITLE") || Dars.input.contains("SUB SUMMARY")));
	}
	
}
