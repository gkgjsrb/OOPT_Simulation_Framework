package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Model.Risk;

public class Activity1002 extends JTabbedPane {

	public Activity1002(Risk risk) {
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

		table.setRowHeight(35);
		table2.setRowHeight(70);
	    
	    table2.getColumn("Plan").setCellRenderer(new TextAreaRenderer());
	    table2.getColumn("Plan").setCellEditor(new TextAreaEditor());
	  
		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(panel, popupMenu);

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
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Alternative Solution", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		this.addTab("Project Justification", null, scrollPane_1, null);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane.setViewportView(textPane_1);
		
		this.addTab("Risk Management", null, panel, null);
		this.addTab("Risk Reduction Plan", null, panel2, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		this.addTab("Analyze business Plan", null, scrollPane_2, null);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane.setViewportView(textPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		this.addTab("Managerial Issues", null, scrollPane_3, null);
		
		JTextPane textPane_3 = new JTextPane();
		scrollPane.setViewportView(textPane_3);
		
		JTextField tf = new JTextField();
		TableCellEditor editor = new DefaultCellEditor(tf);

		table.getColumnModel().getColumn(0).setCellEditor(editor);
		editor.addCellEditorListener(new CellEditorListener() {

			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				for(int i = 0; i < table.getRowCount(); i++) {
				   	table2.setValueAt(table.getValueAt(i, 0), i, 0);
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
	
				
}


