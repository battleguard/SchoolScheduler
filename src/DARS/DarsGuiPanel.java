package DARS;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Table;

public class DarsGuiPanel extends JPanel {
	private static final long serialVersionUID = 5857887917951435365L;
	private Dars dars = new Dars();
	
	public ActionListener BUTTON_LISTENER = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (MainGroup group : dars.MainGroups) {
				group.setEnabled(e.getSource().equals(group.getButton()));
			}	
		}
	};
	
	public DarsGuiPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for (MainGroup group : dars.MainGroups) {
			add(group.createPanel());
			group.getButton().addActionListener(BUTTON_LISTENER);
		}				
		//Table.showDarsCourses(dars.MainGroups.get(0).subGroups.get(0).SubClassGroups.get(0).courses);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setPreferredSize(new Dimension(400, screen.height - 100));
		setMaximumSize(new Dimension(400, screen.height - 100));
	}
}
