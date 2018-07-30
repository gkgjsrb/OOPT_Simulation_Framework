package view;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Model.Requirement;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Activity1003 extends JTabbedPane {
	DefaultTableModel model;
	
	public Activity1003(Requirement req) {
		String Category[] = {"EVIDENT","HIDDEN"};		
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		
		model=new DefaultTableModel(rowData,colName);
		
		JTable table = new JTable(model);
		
		//CELL 
		JComboBox<String> comboBox = new JComboBox<String>(Category);
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
	    table.getColumn("Ref").setCellEditor(new TextAreaEditor(req,table));

	    table.getColumn("Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Name").setCellEditor(new TextAreaEditor(req,table));
	    
		JScrollPane panel = new JScrollPane(table);

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
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		this.addTab("Requirements", null, panel, null);
		
		JScrollPane ScrollPane = new JScrollPane();
		this.addTab("Operating Environment", null, ScrollPane, null);
		
		JTextPane textPane = new JTextPane();
		ScrollPane.setViewportView(textPane);
		
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
	            + "- Microsoft Windows 7 and 10<br>"
	            + "</html>");
		ScrollPane.setColumnHeaderView(lblNewLabel);
				
		JScrollPane ScrollPane_1 = new JScrollPane();
		this.addTab("Develop Environment", null, ScrollPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		ScrollPane_1.setViewportView(textPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("<html>example(Library Management System)<br>"
	            + "- CPU : Intel<br>"
	            + "- IDE : Eclipse<br>"
	            + "- Language : Java<br>"
	            + "- UML : StarUML<br>"
	            + "</html>");
		ScrollPane_1.setColumnHeaderView(lblNewLabel_1);
		
		JScrollPane ScrollPane_2 = new JScrollPane();
		this.addTab("Interface Requirements", null, ScrollPane_2, null);
		
		JTextPane textPane_2 = new JTextPane();
		ScrollPane_2.setViewportView(textPane_2);
		
		JLabel lblNewLabel_2 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The current version may incorporate a menu-driven approach<br>"
	            + "- Next version incorporates windows metaphor<br>"
	            + "</html>");
		ScrollPane_2.setColumnHeaderView(lblNewLabel_2);
		
		JScrollPane ScrollPane_3 = new JScrollPane();
		this.addTab("Other Requirements", null, ScrollPane_3, null);
		
		JTextPane textPane_3 = new JTextPane();
		ScrollPane_3.setViewportView(textPane_3);
		
		JLabel lblNewLabel_3 = new JLabel("<html>example(Library Management System)<br>"
	            + "- The System must control the system access<br>"
	            + "</html>");
		ScrollPane_3.setColumnHeaderView(lblNewLabel_3);
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
		for(int i = 0; i <= model.getRowCount();i++) {
			model.removeRow(0);
		}
		for(int i = 0; i < req.get_length();i++) {
			Object[] add = {req.getRef(i), req.getName(i), req.getCategory(i)};
			model.addRow(add);
		}
	}
 }
