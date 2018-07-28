package view;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
//traceability
public class Activity2039 extends JTabbedPane {


	public Activity2039() {
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Traceability Analysis", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
	}

}
