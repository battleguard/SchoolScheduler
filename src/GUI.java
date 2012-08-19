import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new GUI();
		Table.showCourses(new DARS.CLASS("CS", "3000*"), new DARS.CLASS("CEG", "3000*"), new DARS.CLASS("EE", "4000*"));
	}
	
	public GUI() {
		super("Class Lookup");
		Table.setupColumnSpacing();
		
		Table.table.setFillsViewportHeight(true);
		add(Table.scrollPane);
		
		setPreferredSize(new Dimension(Table.TABLE_WIDTH, Table.TABLE_HEIGHT));
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width / 2 - Table.TABLE_WIDTH / 2, screen.height / 2 - Table.TABLE_HEIGHT / 2);
		pack();
		setVisible(true);
	}
	
}
