import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileLoader {

	// FORMAT OF INFORMATION
	
	/*
<TR>
<TD CLASS="dddefault"><ABBR title = Closed>C</ABBR></TD>
<TD CLASS="dddefault"><A HREF="/pls/PROD/bwckschd.p_disp_detail_sched?term_in=201280&amp;crn_in=73211" onMouseOver="window.status='Detail';  return true" onFocus="window.status='Detail';  return true" onMouseOut="window.status='';  return true"onBlur="window.status='';  return true">73211</A></TD>
<TD CLASS="dddefault">CS</TD>   SUBJECT
<TD CLASS="dddefault">4000</TD> SUBJECT NUMBER
<TD CLASS="dddefault">01</TD>   SECTION
<TD CLASS="dddefault">M</TD>    
<TD CLASS="dddefault">3.000</TD>  CREDITS 
<TD CLASS="dddefault">Social Implications of Computing</TD>  TITLE
<TD CLASS="dddefault">TR</TD>  DAYS
<TD CLASS="dddefault">02:00 pm-03:20 pm</TD>   TIME
<TD CLASS="dddefault">18</TD>    CAPACITY
<TD CLASS="dddefault">18</TD>    ACTUAL
<TD CLASS="dddefault">0</TD>     REMAINING
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">0</TD>
<TD CLASS="dddefault">Leo Finkelstein (<ABBR title= "Primary">P</ABBR>)</TD>  // TEACHER
<TD CLASS="dddefault">08/27-12/15</TD>     
<TD CLASS="dddefault">MC JC 191</TD>    // LOCATION
<TD CLASS="dddefault">Integrated Writing</TD>
<TD CLASS="dddefault">see class details</TD>
</TR>
	 */
	
	
	public static void main(String[] args) {
		new GUI();
		printDarsSection(DARS.CS_3000);
	}
	
	public static void printDarsSection(final DARS.CLASS[] darsClass) {
		try {		
			File file = new File("bwskfcls.P_GetCrse.htm");	
			final BufferedReader br = new BufferedReader(new FileReader(file));
			String input = null;
			M: while ((input = br.readLine()) != null) {			
				if(input.startsWith(VAR.CLASSKEY)) {
					final String Subject = br.readLine().replaceAll("\\<.*?>","");
					final String SubjectNumber = br.readLine().replaceAll("\\<.*?>","");
					for (DARS.CLASS curClass : darsClass) {
						if(Subject.equals(curClass.Subject) && ((curClass.Number.endsWith("*") && SubjectNumber.charAt(0) == curClass.Number.charAt(0)) || curClass.Number.equals(SubjectNumber))) {					
							Course course = new Course(input, Subject, SubjectNumber, br);
							System.out.println(course.toString());
							Table.addCourse(course);
							continue M;
						}
					}
				}
			}
		} catch (final Exception e) { 
			e.printStackTrace();
		}
	}
	
}