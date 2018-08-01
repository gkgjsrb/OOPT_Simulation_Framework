package view;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


public class Activity1001 extends JTabbedPane {
	JTextPane MotivationPane;
	
	public Activity1001() {
		
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Motivation", null, scrollPane, null);
		
		MotivationPane = new JTextPane();
		scrollPane.setViewportView(MotivationPane);
		
		JLabel MotivationEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- The Size of title volumes and the number of users for a city library are sharply increasing<br>"
				+ "- Hence, the city wants to develop a 'Library Management System'<br>"
				+ "&ensp;in order to automate most of the library operations<br>"
				+ "- Among the various library operations, they want to automate the<br>" 
				+ "&ensp;most commonly used operations such as loan, reservation, purchase,<br>" 
				+ "&ensp;discarding old books, and simple statistics  </html>");
		scrollPane.setColumnHeaderView(MotivationEx);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		this.addTab("Project Objective", null, scrollPane_1, null);

		JTextPane ProjectObjectivePane = new JTextPane();
		scrollPane_1.setViewportView(ProjectObjectivePane);
		
		JLabel ProjectObjectiveEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- To develop a computerized library management software , that<br>"
				+ "&ensp;provides typical library operation such as:<br>"
				+ "&ensp;Lend and return books, Reserve books, Maintaining Borrow inforamtion, and Purchasing new books<br>"
				+ "- The new software should be easy to learn and use, and efficient.<br>"
				+ "</html>");
		scrollPane_1.setColumnHeaderView(ProjectObjectiveEx);
				
		JScrollPane scrollPane_2 = new JScrollPane();
		this.addTab("Scope", null, scrollPane_2, null);

		JTextPane ScopePane = new JTextPane();
		scrollPane_2.setViewportView(ScopePane);
		
		JLabel ScopeEx = new JLabel("<html>example(Library Management System)<br>"
				+ "</html>");
		scrollPane_2.setColumnHeaderView(ScopeEx);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		this.addTab("Functional Requirement", null, scrollPane_3, null);

		JTextPane FuncReqPane = new JTextPane();
		scrollPane_3.setViewportView(FuncReqPane);
		
		JLabel FuncReqEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- Lend titles.<br>"
				+ "- Return titles.<br>"
				+ "- Reserve titles.<br>"
				+ "- Purchase new titles.<br>"
				+ "- Discard old titles<br>"
				+ "- Maintain borrower information.<br>"
				+ "</html>");
		scrollPane_3.setColumnHeaderView(FuncReqEx);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		this.addTab("Non-Functional Requirement", null, scrollPane_4, null);

		JTextPane NonFuncReqPane = new JTextPane();
		scrollPane_4.setViewportView(NonFuncReqPane);
		
		JLabel NonFuncReqEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- The average response time for front desk operations should be less than 5 seconds<br>"
	            + "- The system should be desgined to expandable and maintainable<br>"
	            + "</html>");
		scrollPane_4.setColumnHeaderView(NonFuncReqEx);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		this.addTab("Resource Estimation", null, scrollPane_5, null);

		JTextPane ResourceEstPane = new JTextPane();
		scrollPane_5.setViewportView(ResourceEstPane);
		
		JLabel ResourceEstEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Human Efforts(Man-Month)<br>"
	            + "- Human Resource<br>"
	            + "- Project Duration<br>"
	            + "- Cost<br>"
	            + "</html>");
		scrollPane_5.setColumnHeaderView(ResourceEstEx);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		this.addTab("Other Information", null, scrollPane_6, null);

		JTextPane OtherInfoPane = new JTextPane();
		scrollPane_6.setViewportView(OtherInfoPane);
		
		JLabel OtherInfoEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Future Version:<br>"
	            + "&ensp;Adopt 3-Tier Client/Server Architecture<br>"
	            + "&ensp;Add Web Interface<br>"
	            + "</html>");
		scrollPane_6.setColumnHeaderView(OtherInfoEx);
	}
	
	public String getMotivationString() {
		return MotivationPane.getText();
		
	}

}
