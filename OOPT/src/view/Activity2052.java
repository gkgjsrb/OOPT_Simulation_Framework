package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//implements windows
public class Activity2052 extends JTabbedPane {

	public Activity2052() {
		JPanel jpanel = new JPanel();
		jpanel.add(new JLabel("Implement Windows"));
        addTab("Implement Windows", null, jpanel, null);
	}

}
