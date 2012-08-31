package DARS;

import java.util.Arrays;
import java.util.Vector;

import Main.CourseInfo;

public class Course {
	private final String Subject;
	private final int Number;
	private int EndNumber;
	private final boolean MultiCourse;
	private final boolean Lab;	
	
	public static void main(String[] args) {
		String s = "		 CLASS: EE 4540";
		System.out.println(s);
		s = s.substring(s.indexOf(':') + 1).trim();;
		//s = s.trim();
		System.out.println(s);
	}
	
	/**
	 * Possible Input
	 * Default: CLASS: MTH 4510
	 * Lab:  CLASS: CHM 1210L
	 * MultiClass: CLASS: CEG 4000(X) TO 4999
	 * SpecialCase: OR UH 4000 [*Computers]
	 * @param courseInfo
	 */	
	public Course(String courseInfo) {
		System.out.println("creating course");
		courseInfo = Dars.format(courseInfo);
		System.out.println(courseInfo);
		// special case 'OR UH 4000 [*Computers]' to 'UH 4000'
		courseInfo = courseInfo.replaceAll("\\[.*?]", "").replaceAll("\\(.+?\\)", "");	
		
		final String[] info = courseInfo.split(" ");
		this.Subject = info[0];
				
		this.Lab = info[1].length() > 4;
		this.Number = Integer.parseInt(info[1].substring(0, 4));
		
		if(this.MultiCourse = (info.length > 2)) {
			this.EndNumber = Integer.parseInt(info[3]); 
		}
		System.out.println(toString());
	}
	
	public static String format(String line) {
		System.out.println(line);
		return line.trim().substring(line.indexOf(':') + 1);
	}
	
	public String getCourseNumber() {
		return Number + (Lab ? "L" : "");
	}
	
	@Override public boolean equals(Object obj) {
		if(!obj.getClass().equals(CourseInfo.class)) {
			return false;
		}		
		CourseInfo course = (CourseInfo) obj;
		
		if(course.Subject.equals(Subject)) {
			final boolean hasLab = course.CourseNumber.endsWith("L");
			final int number = Integer.parseInt(course.CourseNumber.substring(0, 4));
			if(MultiCourse) {
				return (number >= Number && number <= EndNumber);				
			} else {
				return hasLab == Lab && number == Number;
			}
		}
		return false;
	};
	
	@SuppressWarnings("rawtypes")
	public Vector getRowData() {
		final Object[] data = {toString()};
		//String number = "" + Number + (Lab ? "L " : " ") + (MultiCourse ? "TO " + EndNumber : "");
		//final Object[] data = {Subject, number};
		return new Vector<>(Arrays.asList(data));
	}
	
	@Override
	public String toString() {
		return Subject + " " + Number + (Lab ? "L " : " ") + (MultiCourse ? "TO " + EndNumber: "") ;
	}
}
