package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

public class Activity1005 extends JTabbedPane {

	public Activity1005(JTree tree) {
		
		JPanel jpanel = new JPanel();
		jpanel.add(new JLabel("Implement Prototype"));
		addTab("Implement Prototype", null, jpanel, null);
		
	}

}
