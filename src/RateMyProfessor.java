import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;


public class RateMyProfessor {
	public static String SEARCH_HEADER = "rate my professor wright state university";
	public static String BING_URL_HEADER = "http://www.bing.com/search?q=";
	public static String PROFESSOR_ID_KEY = "tid=";
	public static String RATE_MY_PROF_URL_HEADER = "http://www.ratemyprofessors.com/ShowRatings.jsp?tid=";
	public static String RATE_MY_PROF_KEY = "<li class=\"tTip\" id=\"quality\"";
	public static double[] EMTPY = {0.0, 0.0, 0.0, 0.0};
	
	public static HashMap<String, double[]> professors = new HashMap<String, double[]>();
	
	public static void main(String[] args) {
		final double[] rating = getProfessorRating("Travis E. Doom");
		for (int i = 0; i < rating.length; i++) {
			System.out.print(rating[i] + "  ");
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
