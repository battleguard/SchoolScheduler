package DARS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class Dars {
	
	public static void main(String[] args) {
		Dars dars = new Dars();
		System.out.println(dars.MainGroups.size());
	}
	
	public Vector<MainGroup> MainGroups = new Vector<MainGroup>();
	public static String input;
	
	public Dars() {
		try {
			final File readFile = new File("Table.txt");			
			final FileReader readStream = new FileReader(readFile);
			final BufferedReader br = new BufferedReader(readStream);
			
			Dars.input = br.readLine();
			while(Dars.input != null) {
				MainGroup mainGroup = new MainGroup(Dars.input);
				while((Dars.input = br.readLine()) != null && (Dars.input.contains("MAIN TITLE") || Dars.input.contains("MAIN NEEDS"))) {
					if(Dars.input.contains("MAIN TITLE")) {
						mainGroup.addTitle(format(input));
					} else {
						mainGroup.addNeeds(format(input));
					}
				}
				mainGroup.addSubGroups(br);
				MainGroups.add(mainGroup);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String format(String line) {
		System.out.println(line);
		//return line.trim().substring(line.indexOf(':') + 1);
		return line.substring(line.indexOf(':') + 1).trim();
	}
	
}
