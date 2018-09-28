package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.StageText;


public class Activity1001 extends JTabbedPane {

	JTextPane MotivationPane;
	JTextPane ProjectObjectivePane;
	JTextPane ScopePane;
	JTextPane FuncReqPane;
	JTextPane NonFuncReqPane;
	JTextPane ResourceEstPane;
	JTextPane OtherInfoPane;
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
	public Activity1001(JTree tree, Datainfo data) {
		JSplitPane splitPane = new JSplitPane();
		
		JPanel jpanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button = new JButton("Commit");
		jpanel.add(button, c);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
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
		splitPane.disable();
		
		JLabel MotivationEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- The Size of title volumes and the number of users for a city library are sharply increasing<br>"
				+ "- Hence, the city wants to develop a 'Library Management System'<br>"
				+ "&ensp;in order to automate most of the library operations<br>"
				+ "- Among the various library operations, they want to automate the<br>" 
				+ "&ensp;most commonly used operations such as loan, reservation, purchase,<br>" 
				+ "&ensp;discarding old books, and simple statistics  </html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel.add(MotivationEx, c);
				
		JSplitPane splitPane_1 = new JSplitPane();
		
		JPanel jpanel_1 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_1 = new JButton("Commit");
		jpanel_1.add(button_1, c);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		this.addTab("Project Objective", null, splitPane_1, null);

		ProjectObjectivePane = new JTextPane();
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
		splitPane_1.disable();
		
		JLabel ProjectObjectiveEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- To develop a computerized library management software , that<br>"
				+ "&ensp;provides typical library operation such as:<br>"
				+ "&ensp;Lend and return books, Reserve books, Maintaining Borrow inforamtion, and Purchasing new books<br>"
				+ "- The new software should be easy to learn and use, and efficient.<br>"
				+ "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(ProjectObjectiveEx, c);
				
		JSplitPane splitPane_2 = new JSplitPane();
		
		JPanel jpanel_2 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_2 = new JButton("Commit");
		jpanel_2.add(button_2, c);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		this.addTab("Scope", null, splitPane_2, null);

		ScopePane = new JTextPane();
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
		splitPane_2.disable();
		
		JLabel ScopeEx = new JLabel("<html>example(Library Management System)<br>"
				+ "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_2.add(ScopeEx, c);
		
		JSplitPane splitPane_3 = new JSplitPane();
		
		JPanel jpanel_3 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_3 = new JButton("Commit");
		jpanel_3.add(button_3, c);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		this.addTab("Functional Requirement", null, splitPane_3, null);

		FuncReqPane = new JTextPane();
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
		splitPane_3.disable();
		
		JLabel FuncReqEx = new JLabel("<html>example(Library Management System)<br>"
				+ "- Lend titles.<br>"
				+ "- Return titles.<br>"
				+ "- Reserve titles.<br>"
				+ "- Purchase new titles.<br>"
				+ "- Discard old titles<br>"
				+ "- Maintain borrower information.<br>"
				+ "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_3.add(FuncReqEx, c);
		
		JSplitPane splitPane_4 = new JSplitPane();
		
		JPanel jpanel_4 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_4 = new JButton("Commit");
		jpanel_4.add(button_4, c);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		this.addTab("Non-Functional Requirement", null, splitPane_4, null);

		NonFuncReqPane = new JTextPane();
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
		splitPane_4.disable();
		
		JLabel NonFuncReqEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- The average response time for front desk operations should be less than 5 seconds<br>"
	            + "- The system should be desgined to expandable and maintainable<br>"
	            + "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_4.add(NonFuncReqEx, c);
		
		JSplitPane splitPane_5 = new JSplitPane();
		JPanel jpanel_5 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_5 = new JButton("Commit");
		jpanel_5.add(button_5, c);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		this.addTab("Resource Estimation", null, splitPane_5, null);

		ResourceEstPane = new JTextPane();
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
		splitPane_5.disable();
		
		JLabel ResourceEstEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Human Efforts(Man-Month)<br>"
	            + "- Human Resource<br>"
	            + "- Project Duration<br>"
	            + "- Cost<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_5.add(ResourceEstEx, c);
		
		JSplitPane splitPane_6 = new JSplitPane();
		
		JPanel jpanel_6 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_6 = new JButton("Commit");
		jpanel_6.add(button_6, c);
		jpanel_6.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		this.addTab("Other Information", null, splitPane_6, null);

		OtherInfoPane = new JTextPane();
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
		splitPane_6.disable();
		
		JLabel OtherInfoEx = new JLabel("<html>example(Library Management System)<br>"
	            + "- Future Version:<br>"
	            + "&ensp;Adopt 3-Tier Client/Server Architecture<br>"
	            + "&ensp;Add Web Interface<br>"
	            + "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_6.add(OtherInfoEx, c);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				data.setText(0, MotivationPane.getText());
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
				data.setText(1, ProjectObjectivePane.getText());
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
				data.setText(2, ScopePane.getText());
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
				data.setText(3, FuncReqPane.getText());
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
				data.setText(4, NonFuncReqPane.getText());
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
				data.setText(5, ResourceEstPane.getText());
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
				data.setText(6, OtherInfoPane.getText());
			}
		});
	}
	public void save(Datainfo data) {
		data.setText(0, MotivationPane.getText());
		data.setText(1, ProjectObjectivePane.getText());
		data.setText(2, ScopePane.getText());
		data.setText(3, FuncReqPane.getText());
		data.setText(4, NonFuncReqPane.getText());
		data.setText(5, ResourceEstPane.getText());
		data.setText(6, OtherInfoPane.getText());
	}
	public void open(ArrayList<StageText> st) {
		setMotivationString(st);
		setProjectObjective(st);
		setScope(st);
		setFunc(st);
		setNonFunc(st);
		setResource(st);
		setOther(st);
	}
	private void setMotivationString(ArrayList<StageText> st) {
		MotivationPane.setText(st.get(0).getText());
	}
	private void setProjectObjective(ArrayList<StageText> st) {
		ProjectObjectivePane.setText(st.get(1).getText());
	}
	private void setScope(ArrayList<StageText> st) {
		ScopePane.setText(st.get(2).getText());	
	}
	private void setFunc(ArrayList<StageText> st) {
		FuncReqPane.setText(st.get(3).getText());
	}
	private void setNonFunc(ArrayList<StageText> st) {
		NonFuncReqPane.setText(st.get(4).getText());
	}
	private void setResource(ArrayList<StageText> st) {
		ResourceEstPane.setText(st.get(5).getText());
	}
	private void setOther(ArrayList<StageText> st) {
		OtherInfoPane.setText(st.get(6).getText());
	}

}
