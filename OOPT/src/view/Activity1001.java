package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultTreeModel;


public class Activity1001 extends JTabbedPane {

	JTextPane MotivationPane;
	
	private Hashtable makeIcons() {
	    Hashtable icons = new Hashtable();
	    icons.put("floppyDrive", MetalIconFactory.getTreeFloppyDriveIcon());
	    icons.put("hardDrive", MetalIconFactory.getTreeHardDriveIcon());
	    icons.put("computer", MetalIconFactory.getTreeComputerIcon());
	    icons.put("c", TextIcons.getIcon("c"));
	    icons.put("java", TextIcons.getIcon("java"));
	    icons.put("html", TextIcons.getIcon("html"));
	    return icons;
	}
	public Activity1001(JTree tree) {
		JSplitPane splitPane = new JSplitPane();

		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("Commit");
		jpanel.add(button);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Motivation", null, splitPane, null);
		
		MotivationPane = new JTextPane();
		scrollPane.setViewportView(MotivationPane);
		MotivationPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(scrollPane);
		splitPane.setTopComponent(jpanel);
		
		JLabel MotivationEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- The Size of title volumes and the number of users for a city library are sharply increasing<br>"
				+ "- Hence, the city wants to develop a 'Library Management System'<br>"
				+ "&ensp;in order to automate most of the library operations<br>"
				+ "- Among the various library operations, they want to automate the<br>" 
				+ "&ensp;most commonly used operations such as loan, reservation, purchase,<br>" 
				+ "&ensp;discarding old books, and simple statistics  </html>");
		MotivationEx.setOpaque(true);
		MotivationEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane.setColumnHeaderView(MotivationEx);
		
		JSplitPane splitPane_1 = new JSplitPane();
		
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("Commit");
		jpanel_1.add(button_1);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		this.addTab("Project Objective", null, splitPane_1, null);

		JTextPane ProjectObjectivePane = new JTextPane();
		scrollPane_1.setViewportView(ProjectObjectivePane);
		
		ProjectObjectivePane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(scrollPane_1);
		splitPane_1.setTopComponent(jpanel_1);
		
		JLabel ProjectObjectiveEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- To develop a computerized library management software , that<br>"
				+ "&ensp;provides typical library operation such as:<br>"
				+ "&ensp;Lend and return books, Reserve books, Maintaining Borrow inforamtion, and Purchasing new books<br>"
				+ "- The new software should be easy to learn and use, and efficient.<br>"
				+ "</html>");
		ProjectObjectiveEx.setOpaque(true);
		ProjectObjectiveEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_1.setColumnHeaderView(ProjectObjectiveEx);
				
		JSplitPane splitPane_2 = new JSplitPane();
		
		JPanel jpanel_2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("Commit");
		jpanel_2.add(button_2);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		this.addTab("Scope", null, splitPane_2, null);

		JTextPane ScopePane = new JTextPane();
		scrollPane_2.setViewportView(ScopePane);
		ScopePane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(scrollPane_2);
		splitPane_2.setTopComponent(jpanel_2);
		JLabel ScopeEx = new JLabel("<html>example(Library Management System)<br>"
				+ "</html>");
		ScopeEx.setOpaque(true);
		ScopeEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_2.setColumnHeaderView(ScopeEx);
		
		JSplitPane splitPane_3 = new JSplitPane();
		
		JPanel jpanel_3 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_3 = new JButton("Commit");
		jpanel_3.add(button_3);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		this.addTab("Functional Requirement", null, splitPane_3, null);

		JTextPane FuncReqPane = new JTextPane();
		scrollPane_3.setViewportView(FuncReqPane);
		FuncReqPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(scrollPane_3);
		splitPane_3.setTopComponent(jpanel_3);
		
		JLabel FuncReqEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- Lend titles.<br>"
				+ "- Return titles.<br>"
				+ "- Reserve titles.<br>"
				+ "- Purchase new titles.<br>"
				+ "- Discard old titles<br>"
				+ "- Maintain borrower information.<br>"
				+ "</html>");
		FuncReqEx.setOpaque(true);
		FuncReqEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_3.setColumnHeaderView(FuncReqEx);
		
		JSplitPane splitPane_4 = new JSplitPane();
		
		JPanel jpanel_4 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_4 = new JButton("Commit");
		jpanel_4.add(button_4);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		this.addTab("Non-Functional Requirement", null, splitPane_4, null);

		JTextPane NonFuncReqPane = new JTextPane();
		scrollPane_4.setViewportView(NonFuncReqPane);
		NonFuncReqPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(scrollPane_4);
		splitPane_4.setTopComponent(jpanel_4);
		
		JLabel NonFuncReqEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- The average response time for front desk operations should be less than 5 seconds<br>"
	            + "- The system should be desgined to expandable and maintainable<br>"
	            + "</html>");
		NonFuncReqEx.setOpaque(true);
		NonFuncReqEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_4.setColumnHeaderView(NonFuncReqEx);
		
		JSplitPane splitPane_5 = new JSplitPane();
		
		JPanel jpanel_5 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_5 = new JButton("Commit");
		jpanel_5.add(button_5);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		this.addTab("Resource Estimation", null, splitPane_5, null);

		JTextPane ResourceEstPane = new JTextPane();
		scrollPane_5.setViewportView(ResourceEstPane);
		ResourceEstPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(scrollPane_5);
		splitPane_5.setTopComponent(jpanel_5);
		
		JLabel ResourceEstEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Human Efforts(Man-Month)<br>"
	            + "- Human Resource<br>"
	            + "- Project Duration<br>"
	            + "- Cost<br>"
	            + "</html>");
		ResourceEstEx.setOpaque(true);
		ResourceEstEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_5.setColumnHeaderView(ResourceEstEx);
		
		JSplitPane splitPane_6 = new JSplitPane();
		
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_6 = new JButton("Commit");
		jpanel_6.add(button_6);
		jpanel_6.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		this.addTab("Other Information", null, splitPane_6, null);

		JTextPane OtherInfoPane = new JTextPane();
		scrollPane_6.setViewportView(OtherInfoPane);
		OtherInfoPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_6.setBottomComponent(scrollPane_6);
		splitPane_6.setTopComponent(jpanel_6);
		
		JLabel OtherInfoEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Future Version:<br>"
	            + "&ensp;Adopt 3-Tier Client/Server Architecture<br>"
	            + "&ensp;Add Web Interface<br>"
	            + "</html>");
		OtherInfoEx.setOpaque(true);
		OtherInfoEx.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_6.setColumnHeaderView(OtherInfoEx);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
	}
	public String getMotivationString() {
		return MotivationPane.getText();
		
	}

}
