package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import DARS.Dars;


public class RateMyProfessor {
	public static String SEARCH_HEADER = "rate my professor wright state university";
	public static String BING_URL_HEADER = "http://www.bing.com/search?q=";
	public static String PROFESSOR_ID_KEY = "tid=";
	public static String RATE_MY_PROF_URL_HEADER = "http://www.ratemyprofessors.com/ShowRatings.jsp?tid=";
	public static String RATE_MY_PROF_KEY = "<li class=\"tTip\" id=\"quality\"";
	public static double[] EMTPY = {0.0, 0.0, 0.0, 0.0};
	
	public static HashMap<String, double[]> professors = new HashMap<String, double[]>();
	
	public static void main(String[] args) {
		double[] rating = getProfessorRatingFromFile("Anisa J. Guy");
		for (int i = 0; i < rating.length; i++) {
			System.out.print(rating[i] + "  ");
		}		
		rating = getProfessorRatingFromFile("Danny L. Miska");
		for (int i = 0; i < rating.length; i++) {
			System.out.print(rating[i] + "  ");
		}
	}
	
	
	private static Vector<Professor> professorList = new Vector<Professor>();
	
	public static void loadFile() {
		try {
			final File readFile = new File("RateMyProfessor.txt");			
			final FileReader readStream = new FileReader(readFile);
			final BufferedReader br = new BufferedReader(readStream);
			
			String input;
			
			while((input = br.readLine()) != null) {
				professorList.add(new Professor(input));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static double[] getProfessorRatingFromFile(final String name) {
		if(professorList.isEmpty()) {
			System.out.println("loading file");
			loadFile();
		}
		for (Professor professor : professorList) {
			if(professor.equals(name)) {
				return professor.ratings;
			}
		}
		return EMTPY;
	}
	
	public static class Professor {
		public final String name;
		public final double[] ratings = new double[4];
		
		public Professor(String fileLine) {
			final String[] data = fileLine.split("  ");
			this.name = data[0];
			for(int i = 0; i < ratings.length; i++) {
				ratings[i] = Double.parseDouble(data[i+1]);
			}
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj.equals(name);
		}
	}
	
	public static double[] getProfessorRating(final String Professor) {
		double[] rating = EMTPY;
		if(professors.containsKey(Professor)) {
			return professors.get(Professor);
		} else {
			final int ID = getID(Professor);
			if(ID != -1) {
				rating = getRating(ID);
			}			
			professors.put(Professor, rating);
			return rating;
		}
	}
	

	
	private static int getID(final String Professor) {
		try {			
			final URL url = new URL((BING_URL_HEADER + SEARCH_HEADER + " " + Professor).replaceAll(" ", "+"));			
			final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String input;
			while ((input = br.readLine()) != null) {								
				if(input.contains(PROFESSOR_ID_KEY)) {					
					final int index = input.indexOf(PROFESSOR_ID_KEY);					
					if(index != -1) {
						input = input.substring(index + 4, input.indexOf('\"', index));
						final int idx = input.indexOf('&');
						if(idx != -1) {
							input = input.substring(0, idx);
						}						
						return Integer.parseInt(input);
					}			
				}
			}
		} catch (final Exception e) { 
			e.printStackTrace();
		}
		return -1;
	}
	
	private static double[] getRating(final int ID) {
		final double[] Ratings = {0.0, 0.0, 0.0, 0.0};
		try {			
			final URL url = new URL(RATE_MY_PROF_URL_HEADER + ID);			
			final BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String input;
			while ((input = br.readLine()) != null) {								
				if(input.startsWith(RATE_MY_PROF_KEY)) {					
					for(int i = 0; i < Ratings.length; i++) {
						final int idx = input.indexOf("<strong>");
						Ratings[i] = Double.parseDouble(input.substring(idx + 8, idx + 11));
						input = br.readLine();
					}					
					return Ratings;
				}
				
			}
		} catch (final Exception e) { 
			e.printStackTrace();
		}
		return EMTPY;
	}
}
