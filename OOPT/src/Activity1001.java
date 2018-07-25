import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

public class Activity1001 extends JTabbedPane {

	public Activity1001() {
		
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Motivation", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		this.addTab("Project Objective", null, scrollPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		this.addTab("Scope", null, scrollPane_2, null);

		JTextPane textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		this.addTab("Functional Requirement", null, scrollPane_3, null);

		JTextPane textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		this.addTab("Non-Functional Requirement", null, scrollPane_4, null);

		JTextPane textPane_4 = new JTextPane();
		scrollPane_4.setViewportView(textPane_4);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		this.addTab("Resource Estimation", null, scrollPane_5, null);

		JTextPane textPane_5 = new JTextPane();
		scrollPane_5.setViewportView(textPane_5);

		JScrollPane scrollPane_6 = new JScrollPane();
		this.addTab("Other Information", null, scrollPane_6, null);

		JTextPane textPane_6 = new JTextPane();
		scrollPane_6.setViewportView(textPane_6);
		
	}

}