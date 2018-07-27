package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import Model.Requirement;
import java.awt.Color;

public class Activity1010 extends JTabbedPane {
	DefaultTableModel model;
	DefaultTableModel model2;
	private JTable table_1;
	
	public Activity1010(Requirement req) {
		String Category[] = {"EVIDENT","HIDEEN"};
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		model=new DefaultTableModel(rowData,colName);
		JTable table = new JTable(model);

//		JComboBox<String> comboBox = new JComboBox<>(Category);
		JTextField tf = new JTextField();
//		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		TableCellEditor editor = new DefaultCellEditor(tf);
//		table.getColumnModel().getColumn(2).setCellEditor(Comboeditor);
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
		JScrollPane panel = new JScrollPane(table);

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
		
		JTextPane textPane = new JTextPane();
		addTab("Project Scope", null, textPane, null);
		
		JTextPane textPane_1 = new JTextPane();
		addTab("Project Objective", null, textPane_1, null);
		
		this.addTab("Functional Requirements", null, panel, null);
		
		JTextPane textPane_2 = new JTextPane();
		addTab("Performance Requirement", null, textPane_2, null);
		
		JTextPane textPane_3 = new JTextPane();
		addTab("Operation Environment", null, textPane_3, null);
		
		JTextPane textPane_4 = new JTextPane();
		addTab("User Interface Requriement", null, textPane_4, null);
		
		JTextPane textPane_5 = new JTextPane();
		addTab("Other Requriement", null, textPane_5, null);
		
		JTextPane textPane_6 = new JTextPane();
		addTab("Resources", null, textPane_6, null);
		
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

		JScrollPane ScrollPane = new JScrollPane();
		ScrollPane.setViewportView(table_1);
		addTab("Scheduling",null,ScrollPane,null);

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
