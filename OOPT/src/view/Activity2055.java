package view;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
//write unit test code
public class Activity2055 extends JTabbedPane {
	
	public Activity2055() {
		
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Write Unit Test Code", null, scrollPane, null);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

	}

}
