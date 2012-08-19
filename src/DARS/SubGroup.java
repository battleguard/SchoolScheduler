package DARS;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class SubGroup {
	
	private String Title = "";
	private String Summary = "";
	public Vector<SubClassGroup> SubClassGroups = new Vector<SubClassGroup>();
	
	public SubGroup(String input) {	
		System.out.println("creating subgroup");
		if(input.contains("SUB TITLE")) {
			addTitle(Dars.format(input));
		} else {
			addSummary(Dars.format(input));
		}		
	}
	
	public void addTitle(String title) {		
		if(!Title.isEmpty()) Title += "\r\n"; 
		Title += title;
	}
	
	public void addSummary(String summary) {		
		if(!Summary.isEmpty()) Summary += "\r\n";
		Summary += summary;
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
