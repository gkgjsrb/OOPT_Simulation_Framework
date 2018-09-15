package view;

import javax.swing.JTabbedPane;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Requirement;
import Model.SystemOperation;
import Model.UseCase;

public class Activity2036 extends JTabbedPane {
	private JTable table;
	private JTabbedPane tabbedPane;
	public Activity2036() {
		
		tabbedPane = new JTabbedPane();
		addTab("Define Operation Constracts", null, tabbedPane, null);
		
	}
	
	public void syncOperation(ArrayList<SystemOperation> op) {
		tabbedPane.removeAll();
		for(SystemOperation tmp : op) {
			JScrollPane operationPane = new JScrollPane();
			tabbedPane.addTab(tmp.getName(), null, operationPane, null);
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Name", tmp.getName()},
					{"Responsibilities", tmp.getResponsibility()},
					{"Type", tmp.getType()},
					{"Cross Reference", tmp.getCross()},
					{"Notes", tmp.getNotes()},
					{"Exceptions", tmp.getException()},
					{"Output", tmp.getOutput()},
					{"Pre-conditions", tmp.getPreconditions()},
					{"Post-conditions", tmp.getPostconditions()},
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
			operationPane.setViewportView(table);
			table.setRowHeight(45);
			
			table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
		    table.getColumn(" ").setCellEditor(new TextAreaEditor(table,op,tabbedPane));
		}
	}

}
