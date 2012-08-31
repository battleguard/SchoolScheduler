package StandAlone;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;


public class CourseFile {	
	public static final Vector<Course> loadAllCourses() {
		Vector<Course> allCourses = new Vector<Course>();
		String input = null;
		try {
			final File file = new File("CourseInformation.txt");
			final BufferedReader br = new BufferedReader(new FileReader(file));			
			while ((input = br.readLine()) != null) {
				input = input.replaceAll("\\s{3}+", "  ");
				Course course = new Course(input);				
				allCourses.add(course);
			}
		} catch (Exception e) {
			System.out.println(input);
			e.printStackTrace();
		}
		return allCourses;
	}
}
