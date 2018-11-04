package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//implements reports
public class Activity2053 extends JTabbedPane {

	public Activity2053() {
		JPanel jpanel = new JPanel();
		jpanel.add(new JLabel("Implement Reports"));
        addTab("Implement Reports", null, jpanel, null);
	}

}
