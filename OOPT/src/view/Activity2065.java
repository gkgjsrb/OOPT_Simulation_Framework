package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Activity2065 extends JTabbedPane {

	/**
	 * Create the panel.
	 */
	public Activity2065() {
		JScrollPane scrollPane = new JScrollPane();
		this.addTab("Acceptance Test Environment", null, scrollPane, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		String []header = {"Test Case Number", "Test Name","Description", "Result"};
		String [][]contents = {
				{null, null, null, null}
		};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		
		JTable table = new JTable(model);
		
		table.setRowHeight(70);

		table.getColumn("Test Name").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Test Name").setCellEditor(new TextAreaEditor());
	    
		table.getColumn("Description").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Description").setCellEditor(new TextAreaEditor());
	    	    
		JScrollPane scrollPane_1 = new JScrollPane(table);

		this.addTab("Acceptance Test Result", null,scrollPane_1, null);


		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(scrollPane_1, popupMenu);

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
