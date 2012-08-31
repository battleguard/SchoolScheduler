package Main;

public class DARSInfo {
	
	public static CLASS[] WRITING_IN_MAJOR = {new CLASS("CEG", "3320"), new CLASS("CS", "1180"), new CLASS("CS", "1181"), new CLASS("CS", "4000"), new CLASS("CS", "4830")};
	public static CLASS[] ADDITIONAL_CORE_COURSES = {new CLASS("BIO", "1120"), new CLASS("BIO", "1150"), new CLASS("CHM", "1210"), new CLASS("CHM", "1220L"), new CLASS("CHM", "1220"), new CLASS("EES", "2510"), new CLASS("EES", "2550")};
	public static CLASS[] CS_3000 = {new CLASS("CS", "4000*")};
	public static CLASS[] CS_4000 = {new CLASS("CS", "4000*"), new CLASS("CEG", "4000*"), new CLASS("EE", "4000*")};
	public static CLASS[] ENG = {new CLASS("ENG", "1000*")};
	/*
	public static CLASS[] WRITING_INTENSIVE = {new CLASS("EGR", "1010"), new CLASS("AFS", "2000"), new CLASS("CST", "2210"), new CLASS("CST", "2310"), new CLASS("CST", "2320"),
		new CLASS("CST", "2410"), new CLASS("CST", "2420"), new CLASS("CST", "2430"), new CLASS("CST", "2510"), new CLASS("EC", "2500"), new CLASS("EC", "2900"), new CLASS("RST", "2610"), 
		new CLASS("RST", "2620"), new CLASS("RST", "2810"), new CLASS("RST", "2910"), new CLASS("RST", "2920"), new CLASS("URS", "2000")};
		*/
	public static CLASS[] WRITING_INTENSIVE = {new CLASS("CST", "2210"), new CLASS("CST", "2310"), new CLASS("CST", "2320"),
		new CLASS("CST", "2410"), new CLASS("CST", "2420"), new CLASS("CST", "2430"), new CLASS("CST", "2510")};
	
	public static class CLASS {
		public final String Subject;
		public final String Number;		
		public String EndNumber;
		public boolean numberRange = false;
		
		public CLASS(final String Subject, final String Number) {
			this.Subject = Subject;
			this.Number = Number;
		}
		
		public CLASS(final String Subject, final String startNumber, final String endNumber) {
			this(Subject, startNumber);		
			this.numberRange = true;
			this.EndNumber = endNumber;				
		}
	}
	
	public class DarsSection {
		private final String MainTitle;
		private final String MainNeeds;
		
		public DarsSection(String MainTitle, String MainNeeds) {
			this.MainTitle = MainTitle;
			this.MainNeeds = MainNeeds;
		}
		
		public class SubSection {
			private final String SubTitle;
			private final String SubSummary;
			private String[] SubSectionClassTypes;
			
			public SubSection(String SubTitle, String SubSummary) {
				this.SubTitle = SubTitle;
				this.SubSummary = SubSummary;
			}
		}
		
	}
	
	
}
