package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//Implement DB Schema
public class Activity2054 extends JTabbedPane {

	public Activity2054() {
		JPanel jpanel = new JPanel();
		jpanel.add(new JLabel("Implement DB Schema"));
        addTab("Implement DB Schema", null, jpanel, null);
	}

}
