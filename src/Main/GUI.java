package Main;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DARS.DarsGuiPanel;



public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new GUI();
		//Table.showAllCourses();
	}
	
	public GUI() {
		super("Class Lookup");
				
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));		
		
		add(new DarsGuiPanel());
		
		Table.setupColumnSpacing();				
		Table.table.setFillsViewportHeight(true);		
		
		//JPanel tablePanel = new JPanel();
		//tablePanel.add(Table.scrollPane);
		//tablePanel.setPreferredSize(new Dimension(Table.TABLE_WIDTH, Table.TABLE_HEIGHT));
		
		add(Table.scrollPane);
		
		
		
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(screen.width, screen.height));
		//setLocation(50, 50);
		pack();
		setVisible(true);
		
		
	}
	/*
	private Dars dars = new Dars();
	
	public static void main(String[] args) {
		new DarsGuiPanel();
	}
	
	public ActionListener BUTTON_LISTENER = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (MainGroup group : dars.MainGroups) {
				group.setEnabled(e.getSource().equals(group.getButton()));
			}	
		}
	};
	
	public DarsGuiPanel() {
		super("Dars Report");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		for (MainGroup group : dars.MainGroups) {
			getContentPane().add(group.createPanel());
			group.getButton().addActionListener(BUTTON_LISTENER);
		}				

		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(500, screen.height - 100));
		//setLocation(screen.width / 2 - 100, screen.height / 2 - 200);
		setLocation(50, 50);
		pack();
		setVisible(true);
	}
	*/
	
}
