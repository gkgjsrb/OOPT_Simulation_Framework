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
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class Activity1002 extends JTabbedPane {

	public Activity1002() {
		DefaultTableModel model;
		DefaultTableModel model2;
		
		String[] colName= {"Name","Probability","Significance","Weight"};
		String[] colName2= {"Name","Plan"};
		
		Object[][] rowData= {{null,null,null,null}};
		Object[][] rowData2= {{null,null}};		
		
		model=new DefaultTableModel(rowData,colName);
		model2=new DefaultTableModel(rowData2,colName2);
		
		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);

		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null,null,null};
				Object[] add2= {null,null};
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
					model.removeRow(row);
					model2.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JTextPane textPane = new JTextPane();
		this.addTab("Alternative Solution", null, textPane, null);
		
		JTextPane textPane_1 = new JTextPane();
		this.addTab("Project Justification", null, textPane_1, null);

		this.addTab("Risk Management", null, panel, null);
		this.addTab("Risk Reduction Plan", null, panel2, null);
		
		JTextPane textPane_2 = new JTextPane();
		this.addTab("Analyze business Plan", null, textPane_2, null);
		
		JTextPane textPane_3 = new JTextPane();
		this.addTab("Managerial Issues", null, textPane_3, null);
		

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


