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
	private JTable table_1;

	public Activity2051() {
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Implement Class & Method Definitions", null, scrollPane, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
				"New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table_1);
		
		JButton button = new JButton("New button");
		scrollPane.setColumnHeaderView(button);
		

		
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
