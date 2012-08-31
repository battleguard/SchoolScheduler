package Main;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Vector;


public class CourseInfo {
	public final int CRN;
	public final String Subject;
	public final String CourseNumber;
	public int Section;
	public double Credits;
	public final String Title;
	public final String Days;
	public final String Time;
	public final int Capacity;
	public final int SpotsFilled;
	public final int SpotsRemaining;	
	public final String Professor;
	public final String ClassLocation;
	public double[] rating;
	
	public CourseInfo(final String CRN, final String Subject, final String Number, final BufferedReader br) throws Exception{
		this.CRN = Integer.parseInt(CRN.replaceAll(VAR.REGEX, ""));
		this.Subject = Subject;
		this.CourseNumber = Number;
		try {
			this.Section = Integer.parseInt(br.readLine().replaceAll(VAR.REGEX, ""));
		} catch (Exception e) {
			this.Section = 0;
		}		
		br.readLine(); 					   // CAMPUS
		try {
			this.Credits = Double.parseDouble(br.readLine().replaceAll(VAR.REGEX, ""));
		} catch (Exception e) {
			this.Credits = 0.0;
		}
		this.Title = br.readLine().replaceAll(VAR.REGEX, "").replaceAll(VAR.AND_REGEX, "and").replaceAll("\\s+", " ");
		final String days = br.readLine().replaceAll(VAR.REGEX, "").replaceAll(VAR.SPACE_REGEX, "");		
		this.Days = days.isEmpty() ? "TBA" : days;
		this.Time = br.readLine().replaceAll(VAR.REGEX, "");
		this.Capacity = Integer.parseInt(br.readLine().replaceAll(VAR.REGEX, ""));
		this.SpotsFilled = Integer.parseInt(br.readLine().replaceAll(VAR.REGEX, ""));
		this.SpotsRemaining = Integer.parseInt(br.readLine().replaceAll(VAR.REGEX, ""));
		br.readLine(); 					   // WAIT LIST CAPACITY
		br.readLine(); 					   // WAIT LIST ACTUAL
		br.readLine(); 					   // WAIT LIST REMINING
		br.readLine(); 					   // CROSS LIST CAPACITY
		br.readLine(); 					   // CROSS LIST ACTUAL
		br.readLine(); 					   // CROSS LIST REMAINING
		this.Professor = br.readLine().replaceAll(VAR.REGEX,"").replaceAll(VAR.PAR_REGEX, "");
		br.readLine(); 					   // START DATE
		this.ClassLocation = br.readLine().replaceAll(VAR.REGEX, "");
		this.rating = RateMyProfessor.getProfessorRatingFromFile(Professor);
	}
	
	public CourseInfo(final String courseInfo) throws Exception{
		final String[] info = courseInfo.split("  "); 
		this.CRN = Integer.parseInt(info[1]);
		this.Subject = info[2];
		this.CourseNumber = info[3];
		this.Section = Integer.parseInt(info[4]);
		this.Credits = Double.parseDouble(info[5]);
		this.Title = info[6];
		this.Days = info[7];
		this.Time = info[8];
		this.Capacity = Integer.parseInt(info[9]);
		this.SpotsFilled = Integer.parseInt(info[10]);
		this.SpotsRemaining = Integer.parseInt(info[11]);
		this.Professor = info[12];		
		this.ClassLocation = info[13];
		this.rating = RateMyProfessor.getProfessorRatingFromFile(Professor);
	}
	
	public Vector getrowData() {
		final Object[] data = {CRN, Subject, CourseNumber, Section, Credits, Title, Days, Time, Capacity, SpotsFilled, SpotsRemaining, Professor, ClassLocation, rating[0], rating[1], rating[2], rating[3]};
		return new Vector<>(Arrays.asList(data));
	}
	
	public boolean isFull() {
		return SpotsRemaining <= 0;
	}
		
	@Override
	public String toString() {
		String s = (isFull() ? "FULL" : "OPEN") + "  " + CRN + "  " + Subject + "  " + CourseNumber + "  " + Section + "  " + Credits + "  "
				+ Title + "  " +  Days + "  " + Time + "  " + Capacity + "  " + SpotsFilled 
				+ "  " + SpotsRemaining + "  " + Professor + "  " + ClassLocation;		
		s = s.replaceAll(VAR.EXCESS_SPACE_REGEX, "  ");
		return s;
	}
	
}
