package view;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//use case瑜� trace�빐�꽌 �몴�쓽 媛쒖닔媛� �뒛�뼱�굹寃�
public class Activity2031 extends JTabbedPane {
	private JTable table_1;

	public Activity2031() {
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Define Essential Use Cases", null, scrollPane, null);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
