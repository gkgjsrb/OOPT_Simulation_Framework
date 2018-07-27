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
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Activity1003 extends JTabbedPane {
	DefaultTableModel model;
	
	public Activity1003(Requirement req) {
		String Category[] = {"EVIDENT","HIDEEN"};
		String[] colName= {"Ref","Name","Category"};
		Object[][] rowData= {{null,null,null}};
		model=new DefaultTableModel(rowData,colName);

		JTable table = new JTable(model);

		//CELL 
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
		this.addTab("Requirements", null, panel, null);
		
		JTextPane textPane = new JTextPane();
		this.addTab("Operating Environment", null, textPane, null);
		
		JTextPane textPane_1 = new JTextPane();
		this.addTab("Develop Environment", null, textPane_1, null);
		
		JTextPane textPane_2 = new JTextPane();
		this.addTab("Interface Requirements", null, textPane_2, null);
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
		for(int i=0; i<=model.getRowCount();i++) {
			model.removeRow(0);
		}
		for(int i=0; i<req.get_length();i++) {
			Object[] add= {req.getRef(i),req.getName(i),req.getCategory(i)}; 
			model.addRow(add);
		}
	}
}
