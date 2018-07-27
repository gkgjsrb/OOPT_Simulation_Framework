package view;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Activity2036 extends JTabbedPane {
	private JTable table;

	public Activity2036() {
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Define Operation Constracts", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Name", null},
				{"Responsibilities", null},
				{"Type", null},
				{"Cross Reference", null},
				{"Notes", null},
				{"Exceptions", null},
				{"Output", null},
				{"Pre-conditions", null},
				{"Post-conditions", null},
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
		scrollPane.setColumnHeaderView(table);

	}

}
