package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Activity2067 extends JTabbedPane {

	/**
	 * Create the panel.
	 */
	public Activity2067() {
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Testing Traceability Analysis", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		

	}

}
