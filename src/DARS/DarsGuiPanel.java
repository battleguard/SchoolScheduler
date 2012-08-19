package DARS;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class DarsGuiPanel extends JFrame {
	private static final long serialVersionUID = 5857887917951435365L;
	
	
	public static void main(String[] args) {
		new DarsGuiPanel();
	}
	
	public DarsGuiPanel() {
		super("Dars Report");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		Dars dars = new Dars();
		
		for (MainGroup mainGroup : dars.MainGroups) {
			for (SubGroup subGroup : mainGroup.subGroups) {
				for (SubClassGroup subClassGroup : subGroup.SubClassGroups) {
					for (Course course : subClassGroup.courses) {
						DarsTable.addCourse(course);						
					}
				}
			}							
		}
		DarsTable.setupColumnSpacing();
		DarsTable.table.setFillsViewportHeight(true);
		add(DarsTable.scrollPane);
		
		//setPreferredSize(new Dimension(Table.TABLE_WIDTH, Table.TABLE_HEIGHT));
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width / 2 - 100, screen.height / 2 - 100);
		pack();
		setVisible(true);
	}
}
