package StandAlone;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//setPreferredSize(new Dimension(screen.width, screen.height)); 
		setLocation(100, 100);		
		
		add(new Table());
		add(new FilterPanel());
		pack();
		setVisible(true);	
	}
}
