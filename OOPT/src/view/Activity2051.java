package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Activity2051 extends JTabbedPane {
	private JTable table;

	public Activity2051() {
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Implement Class & Method Definitions", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Type", null},
				{"Name", null},
				{"Purpose", null},
				{"Overview(Class)", null},
				{"Cross Reference", null},
				{"Input(Method)", null},
				{"Output(Method)", null},
				{"Abstract operation(Method)", null},
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
	    table.getColumn(" ").setCellEditor(new TextAreaEditor());
		
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
