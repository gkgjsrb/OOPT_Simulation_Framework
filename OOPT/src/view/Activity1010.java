package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.Requirement;
import Model.Schedule;
import Model.StageText;

public class Activity1010 extends JTabbedPane {
	private JTable table_1;
	JTextPane textPane;
	JTextPane textPane_1;
	JTextPane textPane_2;
	JTextPane textPane_3;
	JTextPane textPane_4;
	JTextPane textPane_5;
	JTextPane textPane_6;
	
	DefaultTableModel model;
	DefaultTableModel model2;
	
	
	public Activity1010(JTree tree, Requirement req, Datainfo data) {
		String Category[] = {"EVIDENT","HIDEEN"};
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		model=new DefaultTableModel(rowData,colName);
		JTable table = new JTable(model);
		table.setCellSelectionEnabled(false);
		
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
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor(req, table));

		table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor(req, table));
	    
	    table.getModel().addTableModelListener(new TableModelListener() {
	    	public void tableChanged(TableModelEvent e) {
	    		IconNode node=(IconNode)tree.getLastSelectedPathComponent();
	    		if(node.getParent().equals(node.getRoot().getChildAt(0))){
	    	       	int index = node.getParent().getIndex(node);
	    	       	 if(index == 9) {
	    	      	 	node.setIconName("computer");
	    	       	 }
	    	       	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
	    		}
	    	}
	    });
	    
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
		splitPane_2.disable();

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
					data.syncReq(req.get_length());
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		
		
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
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(scrollPane);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		this.addTab("Project Scope", null, splitPane, null);
		
		textPane = new JTextPane();
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel.add(lblNewLabel, c);
		
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
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(scrollPane_1);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();
		
		this.addTab("Project Objective", null, splitPane_1, null);
		
		textPane_1 = new JTextPane();
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(lblNewLabel_1, c);
		
		this.addTab("Functional Requirements", null, splitPane_2, null);
		
		JSplitPane splitPane_3 = new JSplitPane();
		JPanel jpanel_3 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_5 = new JButton("Commit");
		jpanel_3.add(button_5, c);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(scrollPane_3);
		splitPane_3.setTopComponent(jpanel_3);
		splitPane_3.disable();
		
		this.addTab("Performance Requirement", null, splitPane_3, null);
		
		textPane_2 = new JTextPane();
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_3.add(lblNewLabel_2, c);
		
		JSplitPane splitPane_4 = new JSplitPane();
		JPanel jpanel_4 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_6 = new JButton("Commit");
		jpanel_4.add(button_6, c);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(scrollPane_4);
		splitPane_4.setTopComponent(jpanel_4);
		splitPane_4.disable();
		
		this.addTab("Operation Environment", null, splitPane_4, null);
		
		textPane_3 = new JTextPane();
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_4.add(lblNewLabel_3, c);
		
		JSplitPane splitPane_5 = new JSplitPane();
		JPanel jpanel_5 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_7 = new JButton("Commit");
		jpanel_5.add(button_7, c);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(scrollPane_5);
		splitPane_5.setTopComponent(jpanel_5);
		splitPane_5.disable();
		
		this.addTab("User Interface Requirement", null, splitPane_5, null);
		
		textPane_4 = new JTextPane();
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_5.add(lblNewLabel_4, c);
		
		JSplitPane splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_8 = new JButton("Commit");
		jpanel_6.add(button_8, c);
		jpanel_6.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_6.setBottomComponent(scrollPane_6);
		splitPane_6.setTopComponent(jpanel_6);
		splitPane_6.disable();
		
		this.addTab("Other Requirement", null, splitPane_6, null);
		
		textPane_5 = new JTextPane();
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_6.add(lblNewLabel_5, c);
		
		JSplitPane splitPane_7 = new JSplitPane();
		JPanel jpanel_7 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_9 = new JButton("Commit");
		jpanel_7.add(button_9, c);
		jpanel_7.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		
		JScrollPane scrollPane_7 = new JScrollPane();
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_7.setBottomComponent(scrollPane_7);
		splitPane_7.setTopComponent(jpanel_7);
		splitPane_7.disable();
		
		this.addTab("Resources", null, splitPane_7, null);
		
		textPane_6 = new JTextPane();
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_7.add(lblNewLabel_6, c);
		
		table_1 = new JTable();
		table_1.setModel(model2=new DefaultTableModel(
			new Object[][] {
				{"1001", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1002", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1003", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1004", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1005", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1006", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1007", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1008", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1009", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1010", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2010", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2020", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2031", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2032", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2033", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2034", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2035", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2036", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2037", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2038", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2039", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2041", "","","","","","","","","","","","","","","","","","","","","",""},
				{"1042", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2043", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2044", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2045", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2046", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2051", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2052", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2053", "","","","","","","","","","","","","","","","","","","","","",""},		
				{"2054", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2055", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2061", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2062", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2063", "","","","","","","","","","","","","","","","","","","","","",""},		
				{"2064", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2065", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2066", "","","","","","","","","","","","","","","","","","","","","",""},
				{"2067", "","","","","","","","","","","","","","","","","","","","","",""},
			},
			new String[] {
				"Phase", "W1-1", "W1-2", "W2-1", "W2-2", "W3-1", "W3-2", "W4-1", "W4-2", "W5-1", "W5-2" ,"W6-1", "W6-2", "W7-1", "W7-2", "W8-1", "W8-2", "W9-1", "W9-2", "W10-1", "W10-2"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		TableCellRenderer renderer = new MyTableCellRenderer();
		table_1.setDefaultRenderer(Object.class, renderer);
		table_1.setCellSelectionEnabled(false);
		table_1.addMouseListener(new MouseListener() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==1) {
					if(table_1.getValueAt(table_1.getSelectedRow(), table_1.getSelectedColumn()).equals("")) {
						table_1.setValueAt("1",table_1.getSelectedRow(), table_1.getSelectedColumn());
					}
					else if(table_1.getValueAt(table_1.getSelectedRow(), table_1.getSelectedColumn()).equals("1")){
						table_1.setValueAt("",table_1.getSelectedRow(), table_1.getSelectedColumn());	
					}
					IconNode node=(IconNode)tree.getLastSelectedPathComponent();
					if(node.getParent().equals(node.getRoot().getChildAt(0))){
			        	int index = node.getParent().getIndex(node);
			        	 if(index == 9) {
			        	 	node.setIconName("computer");
			        	 }
			        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		splitPane_8.disable();
		
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
				data.setText(16, textPane.getText());

			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 9) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(17, textPane_1.getText());

			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"","",""};
				req.add_row();
				model.addRow(add);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				int row = table.getSelectedRow();
				if(row!=-1) {
					req.del_row(row);
					data.syncReq(req.get_length());
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
				table.editingStopped(changeEvent);
				for(int i = 0; i < req.get_length(); i++) {
					req.setRef((String)table.getValueAt(i, 0), i);
					req.setName((String)table.getValueAt(i, 1), i);
					req.setCategory((String)table.getValueAt(i, 2), i);
					data.setReq(i, req);
				}
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
				data.setText(18, textPane_2.getText());

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
				data.setText(19, textPane_3.getText());
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
				data.setText(20, textPane_4.getText());
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
				data.setText(21, textPane_5.getText());
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
				data.setText(22, textPane_6.getText());
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
				table_1.editingStopped(changeEvent);
				data.syncSchedule();
				for(int i = 0; i < table_1.getColumnCount(); i++) {
					for(int j = 0; j < table_1.getRowCount(); j++) {
						if(table_1.getValueAt(j, i).equals("1"))
							data.setSchedule(j, i);
					}
				}
				
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
		if(req.get_length()>model.getRowCount()) {
			int count = req.get_length()-model.getRowCount();
			for(int i = 0; i < count; i++) {
				Object[] add = {"", "", ""};
				model.addRow(add);
			}
		}
		else if(req.get_length()<model.getRowCount()) {
			int count = model.getRowCount()-req.get_length();
			for(int i = 0; i < count; i++) {
				model.removeRow(0);
			}
		}
		for(int i = 0; i < req.get_length(); i++) {
			model.setValueAt(req.getRef(i), i, 0);
			model.setValueAt(req.getName(i), i, 1);
			model.setValueAt(req.getCategory(i), i, 2);
		}	
	}
	public void save(Datainfo data) {
		data.setText(16, textPane.getText());
		data.setText(17, textPane_1.getText());
		data.setText(18, textPane_2.getText());
		data.setText(19, textPane_3.getText());
		data.setText(20, textPane_4.getText());
		data.setText(21, textPane_5.getText());
		data.setText(22, textPane_6.getText());
		
		
	}
	public void open(ArrayList<StageText> st, ArrayList<Schedule> schedule) {
		setScope(st);
		setPro(st);
		setPerformance(st);
		setOperation(st);
		setInterface(st);
		setOther(st);
		setResource(st);
		
		for(int i = 0; i < schedule.size(); i++) {
			table_1.setValueAt("1", schedule.get(i).getRow(), schedule.get(i).getColumn());
		}
		
	}
	private void setScope(ArrayList<StageText> st) {
		textPane.setText(st.get(16).getText());
	}
	private void setPro(ArrayList<StageText> st) {
		textPane_1.setText(st.get(17).getText());
	}
	private void setPerformance(ArrayList<StageText> st) {
		textPane_2.setText(st.get(18).getText());
	}
	private void setOperation(ArrayList<StageText> st) {
		textPane_3.setText(st.get(19).getText());
	}
	private void setInterface(ArrayList<StageText> st) {
		textPane_4.setText(st.get(20).getText());
	}
	private void setOther(ArrayList<StageText> st) {
		textPane_5.setText(st.get(21).getText());
	}
	private void setResource(ArrayList<StageText> st) {
		textPane_6.setText(st.get(22).getText());
	}
}
