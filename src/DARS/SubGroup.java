package DARS;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class SubGroup {
	
	private String Title = "";
	private String Info = "";
	public Vector<SubClassGroup> SubClassGroups = new Vector<SubClassGroup>();
	
	
	public SubGroup(String input) {	
		System.out.println("creating subgroup");
		if(input.contains("SUB TITLE")) {
			addTitle(Dars.format(input));
		} else {
			addSummary(Dars.format(input));
		}		
	}
	
	public JPanel getPanel() {
		JPanel panel = new JPanel();		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new JLabel(Title));
		if(!Info.isEmpty()) {
			panel.add(new JLabel(Info));
		}
		
		final Font ButtonFont = new Font("Ariel", Font.PLAIN, 10);
		
		for (SubClassGroup group : SubClassGroups) {
			final JTable table = group.getTable();
			
			
			JButton subClassButton = new JButton(group.Title);
			subClassButton.addActionListener(group.buttonListener);
			subClassButton.setFont(ButtonFont);
			subClassButton.setMaximumSize(new Dimension(500, 20));
			panel.add(subClassButton);
			//panel.add(table.getTableHeader());
			panel.add(table);
		}	
		return panel;
	}	
	
	
	public void addTitle(String title) {		
		if(Title.isEmpty()) Title = title;
		else {
			if(!Info.isEmpty()) Info += "\r\n";
			Info += Info;
		}
	}
	
	public void addSummary(String summary) {		
		if(!Info.isEmpty()) Info += "\r\n";
		Info += summary;
	}
	
	public void addSubClassGroups(final BufferedReader br) throws IOException {
		do {
			SubClassGroup subClassGroup = new SubClassGroup(Dars.input);
			while((Dars.input = br.readLine()) != null && Dars.input.contains("SUB SECTION")) {
				subClassGroup.addTitle(Dars.format(Dars.input));
			}
			subClassGroup.addCourses(br);
			SubClassGroups.add(subClassGroup);
		} while((Dars.input != null && Dars.input.contains("SUB SECTION")));
	}
}
