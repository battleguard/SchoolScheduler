package DARS;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

public class MainGroup {	
	public String Title = "";
	public String Needs = "";
	public Vector<SubGroup> subGroups = new Vector<SubGroup>();
	
	public MainGroup(String input) {		
		System.out.println("creating maingroup");
		addTitle(Dars.format(input));
	}
	
	public void addTitle(String title) {		
		if(!Title.isEmpty()) Title += "\r\n"; 
		Title += title;
	}
	
	public void addNeeds(String needs) {		
		if(!Needs.isEmpty()) Needs += "\r\n";
		this.Needs += needs;
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
