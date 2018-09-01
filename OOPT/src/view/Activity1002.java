package view;

import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
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

import Model.Risk;

public class Activity1002 extends JTabbedPane {

	public Activity1002(JTree tree, Risk risk) {
		DefaultTableModel model;
		DefaultTableModel model2;
		
		String[] colName= {"Name","Probability","Significance","Weight"};
		String[] colName2= {"Name","Plan"};
		
		Object[][] rowData= {{risk.getName(0),risk.getPro(0),risk.getSig(0),risk.getWeight(0)}};
		Object[][] rowData2= {{risk.getName(0), risk.getPlan(0)}};		
		
		model=new DefaultTableModel(rowData,colName) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		model2=new DefaultTableModel(rowData2,colName2);
		
		for(int i=1; i<risk.get_length();i++) {
			Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
			Object[] add2= {risk.getName(i),risk.getPlan(i)};
			model.addRow(add);
			model2.addRow(add2);
		}
		
		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		JTextField tf = new JTextField();
		JTextField tf2 = new JTextField();
		TableCellEditor editor = new DefaultCellEditor(tf);
		TableCellEditor editor2 = new DefaultCellEditor(tf2);
		table.getColumnModel().getColumn(0).setCellEditor(editor);
		table.getColumnModel().getColumn(1).setCellEditor(editor);
		table.getColumnModel().getColumn(2).setCellEditor(editor);
		table.getColumnModel().getColumn(3).setCellEditor(editor);
		table2.getColumnModel().getColumn(0).setCellEditor(editor2);
		table2.getColumnModel().getColumn(1).setCellEditor(editor2);
		table.setCellSelectionEnabled(false);
		table2.setCellSelectionEnabled(false);
		editor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()), table.getSelectedRow(), table.getSelectedColumn());
				if(table.getSelectedColumn()==0) {
					risk.setName((String)table.getValueAt(table.getSelectedRow(), 0),table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==1) {
					risk.setPro(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 1)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				else if(table.getSelectedColumn()==2) {
					risk.setSig(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 2)),table.getSelectedRow());
					risk.setWeight((Integer)risk.getPro(table.getSelectedRow()), (Integer)risk.getSig(table.getSelectedRow()), table.getSelectedRow());
				}
				for(int i=0; i<risk.get_length(); i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length(); i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
				}
			}	
		});
		
		editor2.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				table2.setValueAt(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()), table2.getSelectedRow(), table2.getSelectedColumn());
				if(table2.getSelectedColumn()==0) {
					risk.setName((String)table2.getValueAt(table2.getSelectedRow(), 0), table2.getSelectedRow());
				}
				else if(table2.getSelectedColumn()==1) {
					risk.setPlan((String)table2.getValueAt(table2.getSelectedRow(), 1), table2.getSelectedRow());
				}
				
				for(int i=0; i<risk.get_length() ;i++) {
					model.removeRow(0);
					model2.removeRow(0);
				}
				for(int i=0; i<risk.get_length();i++) {
					Object[] add= {risk.getName(i),risk.getPro(i),risk.getSig(i),risk.getWeight(i)};
					Object[] add2= {risk.getName(i),risk.getPlan(i)};
					model.addRow(add);
					model2.addRow(add2);
				}
			}
		});	
		
		table.setRowHeight(35);
		table2.setRowHeight(70);
	    
	    table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor(risk, table, model, model2));

	    table2.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table2.getColumn("Name").setCellEditor(new TextAreaEditor(table2, risk, model, model2));
	    
	    table2.getColumn("Plan").setCellRenderer(new TextAreaRenderer());
	    table2.getColumn("Plan").setCellEditor(new TextAreaEditor(table2, risk, model, model2));
	  
	    JSplitPane splitPane_2 = new JSplitPane();
		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(panel, popupMenu);

		JPanel jpanel_2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("+");
		JButton button_3 = new JButton("-");
		JButton button_4 = new JButton("Commit");
		jpanel_2.add(button_2);
		jpanel_2.add(button_3);
		jpanel_2.add(button_4);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(panel);
		splitPane_2.setTopComponent(jpanel_2);
		splitPane_2.disable();
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"",1, 1, 1};
				Object[] add2= {"",""};
				risk.add_row();
				model.addRow(add);
				model2.addRow(add2);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					risk.del_row(row);
					model.removeRow(row);
					model2.removeRow(row);
					table.editingCanceled(changeEvent);
					table2.editingCanceled(changeEvent);

				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"",1, 1, 1};
				Object[] add2= {"",""};
				risk.add_row();
				model.addRow(add);
				model2.addRow(add2);
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					risk.del_row(row);
					model.removeRow(row);
					model2.removeRow(row);
					table.editingCanceled(changeEvent);
					table2.editingCanceled(changeEvent);
				}
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 1) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
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
		
		this.addTab("Alternative Solution", null, splitPane, null);
		
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
		        	 if(index == 1) {
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
	            + "- Purchasing such a library managing software, if available<br>"
	            + "- Outsourcing<br>"
	            + "- Other Options<br>"
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
		this.addTab("Project Justification", null, splitPane_1, null);
		
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
		        	 if(index == 1) {
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
	            + "- Cost<br>"
	            + "- Duration<br>"
	            + "- Risk<br>"
	            + "- Effect<br>"
	            + "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(lblNewLabel_1, c);
		
		this.addTab("Risk Management", null, splitPane_2, null);
		
		JSplitPane splitPane_3 = new JSplitPane();
		JPanel jpanel_3 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_5 = new JButton("Commit");
		jpanel_3.add(button_5);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(panel2);
		splitPane_3.setTopComponent(jpanel_3);
		splitPane_3.disable();
		this.addTab("Risk Reduction Plan", null, splitPane_3, null);
		
		JSplitPane splitPane_4 = new JSplitPane();
		JPanel jpanel_4 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_6 = new JButton("Commit");
		jpanel_4.add(button_6, c);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(scrollPane_2);
		splitPane_4.setTopComponent(jpanel_4);
		splitPane_4.disable();
		this.addTab("Analyze business Plan", null, splitPane_4, null);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_2.setViewportView(textPane_2);
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
		        	 if(index == 1) {
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
				+ "- A few generic packages are available, however too expensive<br>"
				+ "- May be able to market the software to other similar-scaled libraries<br>"
				+ "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_4.add(lblNewLabel_2, c);
				
		JSplitPane splitPane_5 = new JSplitPane();
		JPanel jpanel_5 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_7 = new JButton("Commit");
		jpanel_5.add(button_7, c);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(scrollPane_3);
		splitPane_5.setTopComponent(jpanel_5);
		splitPane_5.disable();
		this.addTab("Managerial Issues", null, splitPane_5, null);
		
		JTextPane textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
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
		        	 if(index == 1) {
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
	            + "- The project should be compeleted by June, 2008(Plan to participate in a SW exhibition<br>"
	            + "</html>");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_5.add(lblNewLabel_3, c);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 1) {
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
		        	 if(index == 1) {
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
		        	 if(index == 1) {
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
		        	 if(index == 1) {
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
		        	 if(index == 1) {
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
	
				
}


