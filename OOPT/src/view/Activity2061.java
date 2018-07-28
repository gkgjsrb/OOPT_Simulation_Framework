package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class Activity2061 extends JTabbedPane {

	/**
	 * Create the panel.
	 */
	public Activity2061() {
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Unit Test Environment", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		Object []header = {"Test Case Number", "Method Name/Description", "Input", "Output", "Result"};
		Object [][]contents = {
				{null, null, null, null, null}
		};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		
		JTable table = new JTable(model);
		table.setRowHeight(70);

	    table.getColumn("Method Name/Description").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Method Name/Description").setCellEditor(new TextAreaEditor());
	    
	    table.getColumn("Input").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Input").setCellEditor(new TextAreaEditor());
	  
	    table.getColumn("Output").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Output").setCellEditor(new TextAreaEditor());
	  
		JScrollPane scrollPane_1 = new JScrollPane(table);

		this.addTab("Unit Test Result", null,scrollPane_1, null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane_1, popupMenu);
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null};
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
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
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
