package Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


public class DarsParser {
	
	public static void main(String[] args) {
		parseRequirmentSections();
	}
	
	public static final void parseRequirmentSections() {
		final String[] SPAN_CLASS_KEYS = {"auditLineType_11_noRequirementTitle", 
										  "auditLineType_15_noRequirementNeedsLine", 
										  "auditLineType_17_noSubrequirementTLine", 
										  "auditLineType_25_noSubrequirementNeedsSummaryLine",
										  "auditLineType_29_noSubrequirementAcceptCourses"};
		final String[] SPAN_CLASS_INFO = {"MAIN TITLE: ",  " MAIN NEEDS: ",  "\tSUB TITLE: ", 
				  							"\t SUB SUMMARY: ", "\t\tSUB COURSES: "};	
		final String EOF_KEY = "TOTAL HOURS REQUIREMENT";		
		
		final File readFile = new File("printerfriendly.htm");
		final File writeFile = new File("Table.txt");
		
		try {			
			writeFile.createNewFile();	// create write file
			final FileWriter outStream = new FileWriter(writeFile); // open filewriter stream
			final BufferedWriter out = new BufferedWriter(outStream);
			
			final FileReader readStream = new FileReader(readFile);
			final BufferedReader br = new BufferedReader(readStream);
			String input;
			while((input = br.readLine()) != null && !input.contains(EOF_KEY)) {				
				for(int i = 0; i < SPAN_CLASS_KEYS.length; i++) {
					if(input.contains(SPAN_CLASS_KEYS[i])) {						
						if(i == 4) {
							// <span class="auditLineType_29_noSubrequirementAcceptCourses">        SELECT FROM: MATHEMATICS: <a href="
							// header info layout >        SELECT FROM: MATHEMATICS: <
							// check for class types header which will always have a ':' in it
							String header = input.substring(input.indexOf('>'), input.indexOf("<a"));
							if(header.contains(":")) {
								printLine("\t\tSUB SECTION TYPES: ", header, out, true);
							}
							// split all classes using html </a> since they are all wrapped in an ahref link
							String[] classes = input.split("</a>");
							// remove sub section class type header because we have already printed it
							classes[0] = classes[0].substring(classes[0].indexOf("<a"));	
							

							
							
							//We need to store previous subject due to how DARS does not always put subject for each class
							String previousSubject = "";
							
							
							for(int j = 0; j < classes.length - 1; j++) {																							
								if(classes[j].contains("(X)")) {
									classes[j+1] = classes[j] + classes[j+1]; // combine both classes because to form XXXX to XXXX
									j++;
								}
								
								classes[j] = formatText(classes[j]);
								
								// split in the middle of class format such as CS 4000. used to detect if in full class format or not
								final int subjectSplitIndex = classes[j].indexOf(' '); 
								
								
								
								if(subjectSplitIndex != -1) {
									// store previous subject incase next class is not in full class format
									previousSubject = classes[j].substring(0, subjectSplitIndex);
									
									// special case for class format such as CS 4, CEG 4, EE 4 etc..
									if(classes[j].length() < 7) {
										// get subject number at end
										final char SubjectNumber = classes[j].charAt(classes[j].length() - 1);
										// rewrite CS 4 to CS 4000 to CS 4999
										classes[j] += "000(X) TO " + SubjectNumber + "999";
									}
																		
									printLine("\t\t CLASS: ", classes[j], out, false);
								} else {
									// class not in full format remove comma and add in stored previous subject
									classes[j] = classes[j].replaceAll(",", "");
									printLine("\t\t CLASS: " + previousSubject + " ", classes[j], out, false);
								}
							}
						} else {
							printLine(SPAN_CLASS_INFO[i], input, out, true);
						}
					}
				}
			}
			out.close();
			br.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static final String[] FORMAT_REMOVAL = {"&amp;", "&gt;", VAR.REGEX};
	
	public static final String formatText(String input) throws Exception {
		input = input.replaceAll("\\s+", " ");
		input = input.replaceAll("\\s{2}+", "");		
		for (String regex : FORMAT_REMOVAL) {
			input = input.replaceAll(regex, "");							
		}
		input = input.trim();
		return input;
	}
	
	public static final void printLine(final String header, String input, final BufferedWriter out, final boolean formatText) throws Exception {
		if(formatText) {
			input = formatText(input);
		}
		System.out.println(header + input);
		out.write(header + input);
		out.newLine();
	}
	
}
