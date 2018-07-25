package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Activity2038 extends JTabbedPane {

	public Activity2038() {
		DefaultTableModel model;
		DefaultTableModel model2;

		String[] colName= {"Test#","Function","Description","Use Case","System Function"};
		String[] colName2= {"Category","Test Case"};
	
		Object[][] rowData= {{null,null,null,null,null}};
		Object[][] rowData2= {{null,null}};
	
		model=new DefaultTableModel(rowData,colName);
		model2=new DefaultTableModel(rowData2,colName2);
		
		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		
		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);	
		
		JPopupMenu popupMenu = new JPopupMenu();		
		JPopupMenu popupMenu2 = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(table2,popupMenu2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null,null,null};
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
				}
			}
		});
		popupMenu2.add(mntmNewMenuItem_3);
		
		this.addTab("Functional Requirement Test Case", null, panel, null);
		this.addTab("Non Functional Requirement Test Case", null, panel2, null);

		
		
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
