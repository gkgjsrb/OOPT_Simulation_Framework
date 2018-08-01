package view;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Requirement;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//use case瑜� trace�빐�꽌 �몴�쓽 媛쒖닔媛� �뒛�뼱�굹寃�
public class Activity2031 extends JTabbedPane {
	private JTable table;

	public Activity2031(Requirement req, ArrayList uc) {
		
		JTabbedPane panel = new JTabbedPane();
		addTab("Define Essential Use Cases", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.addTab("Use Case1", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Use Case", null},
				{"Actor", null},
				{"Purpose", null},
				{"Overview", null},
				{"Type", null},
				{"Cross Reference", null},
				{"Pre-Requistes", null},
				{"Typical Courses of Events", null},
				{"Alternative Courses of Events", null},
				{"Exceptional Courses of Events", null},
			},
			new String[] {
				"", " "
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(45);
		
		table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
	    table.getColumn(" ").setCellEditor(new TextAreaEditor(req, table, uc, panel));
	    
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
