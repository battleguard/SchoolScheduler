package StandAlone;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Vector;


public class RateMyProfessor {
	private static final double[] EMTPY = {0.0, 0.0, 0.0, 0.0};		
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
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static double[] getProfessorRatingFromFile(final String name) {
		if(professorList.isEmpty()) {			
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
}
