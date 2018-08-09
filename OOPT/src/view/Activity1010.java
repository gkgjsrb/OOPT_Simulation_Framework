package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import Model.Requirement;

public class Activity1010 extends JTabbedPane {
	DefaultTableModel model;
	DefaultTableModel model2;
	private JTable table_1;
	
	public Activity1010(JTree tree, Requirement req) {
		String Category[] = {"EVIDENT","HIDEEN"};
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		model=new DefaultTableModel(rowData,colName);
		JTable table = new JTable(model);

		JComboBox<String> comboBox = new JComboBox<>(Category);
		JTextField tf = new JTextField();
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		TableCellEditor editor = new DefaultCellEditor(tf);
		table.getColumnModel().getColumn(2).setCellEditor(Comboeditor);
		table.getColumnModel().getColumn(0).setCellEditor(editor);
		table.getColumnModel().getColumn(1).setCellEditor(editor);
		editor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				for(int i=0; i<req.get_length();i++) {
					req.setRef((String)table.getValueAt(i, 0), i);
					req.setName((String)table.getValueAt(i, 1), i);
					req.setCategory((String)table.getValueAt(i, 2), i);
				}
			}		
		});
		
		table.setRowHeight(70);

		table.getColumn("Ref").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor());

		table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor());

	    JSplitPane splitPane_2 = new JSplitPane();
		JScrollPane scrollPane_2 = new JScrollPane(table);
		JPanel jpanel_2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("+");
		JButton button_3 = new JButton("-");
		JButton button_4 = new JButton("Commit");
		
		jpanel_2.add(button_2);
		jpanel_2.add(button_3);
		jpanel_2.add(button_4);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(scrollPane_2);
		splitPane_2.setTopComponent(jpanel_2);
		

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				req.add_row();
				Object[] add= {"","",""};
				model.addRow(add);				
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					req.del_row(row);
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		
		
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("Commit");
		jpanel.add(button);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(scrollPane);
		splitPane.setTopComponent(jpanel);
		this.addTab("Project Scope", null, splitPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
	            + "- The library management software automates typical library  <br>"
	            + "&ensp;operations; reservation, lending item, adding, removing, and <br>"
	            + "&ensp;updating the information of title, item, and borrower.<br>"
	            + "</html>");
		scrollPane.setColumnHeaderView(lblNewLabel);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("Commit");
		jpanel_1.add(button_1);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(scrollPane_1);
		splitPane_1.setTopComponent(jpanel_1);
		this.addTab("Project Objective", null, splitPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		textPane_1.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_1 = new JLabel("<html>example(Library Management System)<br>"
	            + "- To develop a computerized library management software, that<br>"
	            + "&ensp;provides typical library operations such as:<br>"
	            + "&ensp;Lend and return books, Reserve books, Maintaining Borrow information, and Purchasing new books<br>"
	            + "- The new software should be easy to learn and use, and efficient<br>"
	            + "</html>");
		scrollPane_1.setColumnHeaderView(lblNewLabel_1);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		
		this.addTab("Functional Requirements", null, splitPane_2, null);
		
		JSplitPane splitPane_3 = new JSplitPane();
		JPanel jpanel_3 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_5 = new JButton("Commit");
		jpanel_3.add(button_5);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(scrollPane_3);
		splitPane_3.setTopComponent(jpanel_3);
		this.addTab("Performance Requirement", null, splitPane_3, null);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_3.setViewportView(textPane_2);
		textPane_2.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_2 = new JLabel("<html>example(Library Management System)<br>"
	            + "- When making reservations, the information of reservation will appear within 5 seconds<br>"
	            + "- When lending items, the content of lending item will appear within 5 seconds<br>"
	            + "- When returning items, the content of returning item will appear within 5 seconds.<br>"
	            + "</html>");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_3.setColumnHeaderView(lblNewLabel_2);
		
		JSplitPane splitPane_4 = new JSplitPane();
		JPanel jpanel_4 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_6 = new JButton("Commit");
		jpanel_4.add(button_6);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(scrollPane_4);
		splitPane_4.setTopComponent(jpanel_4);
		this.addTab("Operation Environment", null, splitPane_4, null);
		
		JTextPane textPane_3 = new JTextPane();
		scrollPane_4.setViewportView(textPane_3);
		textPane_3.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_3 = new JLabel("<html>example(Library Management System)<br>"
	            + "- Microsoft Windows 7 and 10<br>"
	            + "- CPU : Intel<br>"
	            + "- IDE : Eclipse<br>"
	            + "- Language : Java<br>"
	            + "- UML : StarUML<br>"
	            + "</html>");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_4.setColumnHeaderView(lblNewLabel_3);
		
		JSplitPane splitPane_5 = new JSplitPane();
		JPanel jpanel_5 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_7 = new JButton("Commit");
		jpanel_5.add(button_7);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(scrollPane_5);
		splitPane_5.setTopComponent(jpanel_5);
		this.addTab("User Interface Requirement", null, splitPane_5, null);
		
		JTextPane textPane_4 = new JTextPane();
		scrollPane_5.setViewportView(textPane_4);
		textPane_4.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_4 = new JLabel("<html>example(Library Management System)<br>"
	            + "- Menu-driven approach<br>"
	            + "- Should be desgined to upgrading to 'Windows-based' version<br>"
	            + "</html>");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_5.setColumnHeaderView(lblNewLabel_4);
		
		JSplitPane splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_8 = new JButton("Commit");
		jpanel_6.add(button_8);
		jpanel_6.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_6.setBottomComponent(scrollPane_6);
		splitPane_6.setTopComponent(jpanel_6);
		this.addTab("Other Requirement", null, splitPane_6, null);
		
		JTextPane textPane_5 = new JTextPane();
		scrollPane_6.setViewportView(textPane_5);
		textPane_5.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_5 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The Content of database shoudl be maintained reliably<br>"
	            + "- System should control the system access<br>"
	            + "</html>");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_6.setColumnHeaderView(lblNewLabel_5);
		
		JSplitPane splitPane_7 = new JSplitPane();
		JPanel jpanel_7 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_9 = new JButton("Commit");
		jpanel_7.add(button_9);
		jpanel_7.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_7 = new JScrollPane();
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_7.setBottomComponent(scrollPane_7);
		splitPane_7.setTopComponent(jpanel_7);
		this.addTab("Resources", null, splitPane_7, null);
		
		JTextPane textPane_6 = new JTextPane();
		scrollPane_7.setViewportView(textPane_6);
		textPane_6.addKeyListener(new KeyListener() {
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
		        	 if(index == 9) {
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
		JLabel lblNewLabel_6 = new JLabel("<html>example(Library Management System)<br>"
	            + "Man Month : 6 Persons(A Team Leader, A Document Manager, 3~4 Engineers)<br>"
	            + "Period : 5 Days(Around 40 Hours)<br>"
	            + "Hardware : Intel PC<br>"
	            + "Software : Windows 7/10, Java, StarUML<br>"
	            + "</html>");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(Color.LIGHT_GRAY);
		
		scrollPane_7.setColumnHeaderView(lblNewLabel_6);
		
		table_1 = new JTable();
		table_1.setModel(model2=new DefaultTableModel(
			new Object[][] {
				{"1001", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1002", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1003", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1004", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1005", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1006", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1007", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1008", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1009", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1010", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2010", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2020", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2031", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2032", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2033", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2034", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2035", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2036", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2037", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2038", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2039", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2041", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"1042", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2043", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2044", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2045", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2046", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2051", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2052", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2053", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},		
				{"2054", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2055", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2061", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2062", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2063", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},		
				{"2064", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2065", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2066", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
				{"2067", "0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			},
			new String[] {
				"Phase", "W1-1", "W1-2", "W2-1", "W2-2", "W3-1", "W3-2", "W4-1", "W4-2", "W5-1", "W5-2" ,"W6-1", "W6-2", "W7-1", "W7-2", "W8-1", "W8-2", "W9-1", "W9-2", "W10-1", "W10-2"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JSplitPane splitPane_8 = new JSplitPane();
		JPanel jpanel_8 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_10 = new JButton("Commit");
		jpanel_8.add(button_10);
		jpanel_8.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		JScrollPane scrollPane_8 = new JScrollPane();
		splitPane_8.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_8.setBottomComponent(scrollPane_8);
		splitPane_8.setTopComponent(jpanel_8);
		this.addTab("Scheduling", null, splitPane_8, null);
		
		scrollPane_8.setViewportView(table_1);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"",1, 1, 1};
				Object[] add2= {"",""};
				req.add_row();
				model.addRow(add);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int row = table.getSelectedRow();
				if(row!=-1) {
					req.del_row(row);
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
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
		        	 if(index == 9) {
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
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public void syncRequirement(Requirement req) {
		for(int i=0;i<=model.getRowCount();i++) {
			model.removeRow(0);
		}
		for(int i=0;i<req.get_length();i++) {
			Object[] add = {req.getRef(i),req.getName(i),req.getCategory(i)};
			model.addRow(add);
		}
	}
}
