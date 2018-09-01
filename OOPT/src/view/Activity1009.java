package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import Model.Requirement;

public class Activity1009 extends JTabbedPane {
	
	JComboBox<String> comboBox = new JComboBox<String>();
	
	public Activity1009(JTree tree, Requirement req) {
		DefaultTableModel model;
		DefaultTableModel model2;

		String[] colName= {"Ref","Function","Test Case"};
		String[] colName2= {"Category","Test Case"};
	
		Object[][] rowData= {{"","",""}};
		Object[][] rowData2= {{"",""}};
	
		model=new DefaultTableModel(rowData,colName);
		model2=new DefaultTableModel(rowData2,colName2);
		
		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		
		table.setRowHeight(70);
		table2.setRowHeight(70);

		table.getColumn("Ref").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor());


		table.getColumn("Test Case").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Test Case").setCellEditor(new TextAreaEditor());

		table2.getColumn("Category").setCellRenderer(new TextAreaRenderer());
	    table2.getColumn("Category").setCellEditor(new TextAreaEditor());

	    table2.getColumn("Test Case").setCellRenderer(new TextAreaRenderer());
	    table2.getColumn("Test Case").setCellEditor(new TextAreaEditor());
	    
		for(int i=0;i<req.get_length();i++) {
			comboBox.addItem(req.getName(i));
		}
		
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table.getColumnModel().getColumn(1).setCellEditor(Comboeditor);
				
		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);	
		JSplitPane splitPane = new JSplitPane();
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");
		
		JButton button_3 = new JButton("+");
		JButton button_4 = new JButton("-");
		JButton button_5 = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		jpanel_1.add(button_3);
		jpanel_1.add(button_4);
		jpanel_1.add(button_5);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(panel2);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();
		
		JPopupMenu popupMenu = new JPopupMenu();		
		JPopupMenu popupMenu2 = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(table2,popupMenu2);
		addPopup(panel, popupMenu);
		addPopup(panel2, popupMenu2);

		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {" "," "," "};
				model.addRow(add);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("add row");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add2= {null,null};
				model2.addRow(add2);
			}
		});
		popupMenu2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("del row");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row2 = table2.getSelectedRow();
				if(row2!=-1) {
					model2.removeRow(row2);
					table2.editingCanceled(changeEvent);
				}
			}
		});
		popupMenu2.add(mntmNewMenuItem_3);
		
		this.addTab("Functional Requirement Test Case", null, splitPane, null);
		this.addTab("Non Functional Requirement Test Case", null, splitPane_1, null);

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
		        	 if(index == 8) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {"",""};
				req.add_row();
				model2.addRow(add);
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					req.del_row(row);
					model2.removeRow(row);
					table2.editingCanceled(changeEvent);
				}
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 8) {
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

	public void syncComboBox(ArrayList array) {
		comboBox.removeAllItems();
		for(int i=0; i<array.size(); i++) {
			comboBox.addItem((String) array.get(i));
		}
	}
/*
	public void addComboItem(String add) {
		comboBox.addItem(add);
	}
	public void removeComboItem(String remove) {
		comboBox.removeItem(remove);
	}
*/
}