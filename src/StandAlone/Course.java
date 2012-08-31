package StandAlone;
import java.util.Arrays;
import java.util.Vector;


public class Course {
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
	
	public Course(final String courseInfo) throws Exception{
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
	
	@SuppressWarnings("rawtypes")
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
		s = s.replaceAll("\\s{3}+", "  ");
		return s;
	}
	
}
