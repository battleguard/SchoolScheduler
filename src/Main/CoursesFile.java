package Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;


public class CoursesFile {
	
	public static void main(String[] args) {
		//FormatTable2();
		writeFile();
		System.out.println("done");
	}	
	
	public static final void test() {
		String s = "OPEN  79048  WMS  6990  3  3.0  Independent Study   TBA  5  2  3  Kelli D. Zaytoun  TBA";
		System.out.println(s);
		s = s.replaceAll(VAR.EXCESS_SPACE_REGEX, "  ");
		System.out.println(s);
	}
	
	public static final Vector<CourseInfo> loadAllCourses() {
		Vector<CourseInfo> allCourses = new Vector<CourseInfo>();
		String input = null;
		try {
			final File file = new File("CourseInformation.txt");
			final BufferedReader br = new BufferedReader(new FileReader(file));			
			while ((input = br.readLine()) != null) {
				input = input.replaceAll(VAR.EXCESS_SPACE_REGEX, "  ");
				CourseInfo course = new CourseInfo(input);				
				allCourses.add(course);
			}
		} catch (Exception e) {
			System.out.println(input);
			e.printStackTrace();
		}
		return allCourses;
	}
	
	public static final void FormatTable() {
		final File readFile = new File("Table.txt");
		final File writeFile = new File("Table2.txt");
		
		try {			
			writeFile.createNewFile();	// create write file
			final FileWriter outStream = new FileWriter(writeFile); // open filewriter stream
			final BufferedWriter out = new BufferedWriter(outStream);
			
			final FileReader readStream = new FileReader(readFile);
			final BufferedReader br = new BufferedReader(readStream);
			
			String input;
			while((input = br.readLine()) != null) {
				input = input.replaceAll("\\s+", " ").replaceAll(VAR.REGEX, "");
				if(!input.isEmpty()) {
					System.out.println(input);
					out.write(input + "\n");
				}				
			}
			out.close();
			br.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static final void FormatTable2() {
		final File readFile = new File("printerfriendly.htm");
		final File writeFile = new File("Table2.txt");
		
		try {			
			writeFile.createNewFile();	// create write file
			final FileWriter outStream = new FileWriter(writeFile); // open filewriter stream
			final BufferedWriter out = new BufferedWriter(outStream);
			
			final FileReader readStream = new FileReader(readFile);
			final BufferedReader br = new BufferedReader(readStream);
			
			String input;
			while((input = br.readLine()) != null) {
				
				if(input.contains(">  +") || input.contains(">  -") || input.contains("NEEDS") || input.contains(VAR.DARS_TITLE_KEY)) {
					input = input.replaceAll(VAR.WHITE_SPACE_REGEX, " ").replaceAll(VAR.REGEX, "");
					System.out.println(input);
					out.write(input + "\n");
				}
				
				if(input.contains("a href=")) {
					String[] courses = input.split("\">");
					for (int i = 1; i < courses.length; i++) {
						courses[i] = courses[i].substring(0, courses[i].indexOf('<'));
						System.out.println(courses[i]);
						out.write(courses[i] + "\n");
					}
				}			
			}
			out.close();
			br.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static final void writeRateMyProffessorFile() {
		try {
			File writeFile = new File("RateMyProfessor.txt");
			writeFile.createNewFile();
			FileWriter fstream = new FileWriter("RateMyProfessor.txt");
			BufferedWriter out = new BufferedWriter(fstream);					
			Vector<CourseInfo> ALL_COURSES = loadAllCourses();
			
			for (CourseInfo course : ALL_COURSES) {
				final String professor = course.Professor;
				if(!professor.equals("TBA") && !RateMyProfessor.professors.containsKey(professor)) {
					final double[] Rating = RateMyProfessor.getProfessorRating(professor);
					String s = professor + "  " + Rating[0] + "  " + Rating[1] + "  " + Rating[2] + "  " + Rating[3];
					System.out.println(s);
					out.write(s + "\n");
				}
			}			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static final void writeFile() {
		try {
			// create file we are writing to
			File writeFile = new File("CourseInformation.txt");
			writeFile.createNewFile();
			FileWriter fstream = new FileWriter("CourseInformation.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			// setup file we are reading from
			File file = new File("AllCourses.htm");	
			final BufferedReader br = new BufferedReader(new FileReader(file));
			String input = null;			
			while ((input = br.readLine()) != null) {	
				if(input.contains(VAR.CLASSKEY)) {					
					final String Subject = br.readLine().replaceAll("\\<.*?>","");
					final String SubjectNumber = br.readLine().replaceAll("\\<.*?>","");
					CourseInfo course = new CourseInfo(input, Subject, SubjectNumber, br);
					System.out.println(course.toString());
					out.write(course.toString() + "\n");
				}
			}
			out.close();
			br.close();
		} catch (final Exception e) { 
			e.printStackTrace();
		}		
	}
}
