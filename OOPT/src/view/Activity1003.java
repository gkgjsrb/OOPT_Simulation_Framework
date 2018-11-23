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
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.Requirement;
import Model.Risk;
import Model.StageText;

public class Activity1003 extends JTabbedPane {
	JTextPane textPane;
	JTextPane textPane_1;
	JTextPane textPane_2;
	JTextPane textPane_3;
	JTextPane textPane_4;
	
	DefaultTableModel model;
	
	public Activity1003(JTree tree, Requirement req, Datainfo data) {
		String Category[] = {"EVIDENT","HIDDEN"};		
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		
		model=new DefaultTableModel(rowData,colName);
		
		JTable table = new JTable(model);
		
		//CELL 
		JComboBox<String> comboBox = new JComboBox<String>(Category);
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table.getColumnModel().getColumn(2).setCellEditor(Comboeditor);
		
		table.setRowHeight(70);

		table.getColumn("Ref").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor(req, table));
	    

	    table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor(req, table));
	    table.setCellSelectionEnabled(false);
		JSplitPane splitPane = new JSplitPane();
		JScrollPane panel = new JScrollPane(table);
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel, popupMenu);
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
					table.editingCanceled(changeEvent);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		this.addTab("Requirements", null,splitPane, null);
		
		JSplitPane splitPane_5 = new JSplitPane();
		JPanel jpanel_5 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_7 = new JButton("Commit");
		jpanel_5.add(button_7, c);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane ScrollPane_4 = new JScrollPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.disable();
		splitPane_5.setBottomComponent(ScrollPane_4);
		splitPane_5.setTopComponent(jpanel_5);
		this.addTab("Performance Requirements", null, splitPane_5, null);
		
		textPane_4 = new JTextPane();
		ScrollPane_4.setViewportView(textPane_4);
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
		        	 if(index == 2) {
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
	            + "- The average response time for front desk operations should be less than 5 seconds.<br>"
				+ "- The post-card to notify availability must be printed out immediately after the reserved book becomes available.<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_5.add(lblNewLabel_4, c);

		
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel_1 = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_3 = new JButton("Commit");
		jpanel_1.add(button_3, c);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane ScrollPane = new JScrollPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.disable();
		splitPane_1.setBottomComponent(ScrollPane);
		splitPane_1.setTopComponent(jpanel_1);
		this.addTab("Operating Environment", null, splitPane_1, null);
		
		textPane = new JTextPane();
		ScrollPane.setViewportView(textPane);
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
		        	 if(index == 2) {
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
	            + "- Microsoft Windows 7 and 10<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(lblNewLabel, c);
		
		JSplitPane splitPane_2 = new JSplitPane();
		JPanel jpanel_2 = new JPanel(new GridBagLayout());
		JButton button_4 = new JButton("Commit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		jpanel_2.add(button_4,c);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
 		
		JScrollPane ScrollPane_1 = new JScrollPane();
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(ScrollPane_1);
		splitPane_2.setTopComponent(jpanel_2);
		splitPane_2.disable();
		this.addTab("Develop Environment", null, splitPane_2, null);
		
		textPane_1 = new JTextPane();
		ScrollPane_1.setViewportView(textPane_1);
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
		        	 if(index == 2) {
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
	            + "- CPU : Intel<br>"
	            + "- IDE : Eclipse<br>"
	            + "- Language : Java<br>"
	            + "- UML : StarUML<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_2.add(lblNewLabel_1, c);
		
		JSplitPane splitPane_3 = new JSplitPane();
		JPanel jpanel_3 = new JPanel(new GridBagLayout());
		JButton button_5 = new JButton("Commit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		jpanel_3.add(button_5,c);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		
		JScrollPane ScrollPane_2 = new JScrollPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(ScrollPane_2);
		splitPane_3.setTopComponent(jpanel_3);
		splitPane_3.disable();
		this.addTab("Interface Requirements", null, splitPane_3, null);
		
		textPane_2 = new JTextPane();
		ScrollPane_2.setViewportView(textPane_2);
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
		        	 if(index == 2) {
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
	            + "- The current version may incorporate a menu-driven approach<br>"
	            + "- Next version incorporates windows metaphor<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_3.add(lblNewLabel_2, c);
		
		JSplitPane splitPane_4 = new JSplitPane();
		JPanel jpanel_4 = new JPanel(new GridBagLayout());
		JButton button_6 = new JButton("Commit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		jpanel_4.add(button_6, c);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane ScrollPane_3 = new JScrollPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(ScrollPane_3);
		splitPane_4.setTopComponent(jpanel_4);
		splitPane_4.disable();
		this.addTab("Other Requirements", null, splitPane_4, null);
		
		textPane_3 = new JTextPane();
		ScrollPane_3.setViewportView(textPane_3);
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
		        	 if(index == 2) {
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
	            + "- The System must control the system access<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_4.add(lblNewLabel_3, c);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"","", ""};
				req.add_row();
				model.addRow(add);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
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
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
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
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(11, textPane.getText());
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(12, textPane_1.getText());

			}
		});
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(13, textPane_2.getText());

			}
		});
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(14, textPane_3.getText());

			}
		});
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 2) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				data.setText(23, textPane_4.getText());

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
				Object[] add = {"", "",""};
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
	public void save(Datainfo data, Requirement req) {
		data.setText(11, textPane.getText());
		data.setText(12, textPane_1.getText());
		data.setText(13, textPane_2.getText());
		data.setText(14, textPane_3.getText());
		data.setText(23, textPane_4.getText());
		for(int i = 0; i < req.get_length(); i++) {
			data.setReq(i, req);
		}
	}
	public void open(ArrayList<StageText> st, Requirement req) {
		setOperating(st);
		setDevelop(st);
		setInterface(st);
		setOther(st);
		setPerfor(st);
		model.setRowCount(0);
		for(int i = 0; i < req.get_length(); i++) {
			Object[] add= {req.getRef(i), req.getName(i), req.getCategory(i)};
			model.addRow(add);
		}
	}
	
	private void setOperating(ArrayList<StageText> st) {
		textPane.setText(st.get(11).getText());
	}
	private void setDevelop(ArrayList<StageText> st) {
		textPane_1.setText(st.get(12).getText());
	}
	private void setInterface(ArrayList<StageText> st) {
		textPane_2.setText(st.get(13).getText());
	}
	private void setOther(ArrayList<StageText> st) {
		textPane_3.setText(st.get(14).getText());
	}
	private void setPerfor(ArrayList<StageText> st) {
		textPane_4.setText(st.get(23).getText());
	}
	public void newActivity() {
		textPane.setText("");
		textPane_1.setText("");
		textPane_2.setText("");
		textPane_3.setText("");
		textPane_4.setText("");
		model.setRowCount(0);
		Object[] add= {"", "", ""};
		model.addRow(add);
		
	}
 }
