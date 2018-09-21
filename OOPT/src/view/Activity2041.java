package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Requirement;
import Model.UseCase;

public class Activity2041 extends JTabbedPane {
	private JTable table;
	JTabbedPane panel;
	public Activity2041() {
		
		panel = new JTabbedPane();
		addTab("Design Real Use Case", null, panel, null);
	    
	}

	public void syncUseCase(Requirement req,ArrayList<UseCase> uc) {
		panel.removeAll();
		if(uc.size()==0) {
			
		}
		else {
			for(UseCase tmp : uc) {
				JScrollPane usecasePane = new JScrollPane();
				panel.addTab(tmp.getName(), null, usecasePane, null);
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Use Case", tmp.getName()},
						{"Actor", tmp.getActor()},
						{"Purpose", tmp.getPurpose()},
						{"Overview", tmp.getOverview()},
						{"Type", tmp.getType()},
						{"Cross Reference", null},
						{"Pre-Requistes", tmp.getPreRequistes()},
						{"Typical Courses of Events", tmp.getTypical()},
						{"Alternative Courses of Events", tmp.getAlternative()},
						{"Exceptional Courses of Events", tmp.getExceptional()},
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
				usecasePane.setViewportView(table);
				table.setRowHeight(45);
			
				table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
				table.getColumn(" ").setCellEditor(new TextAreaEditor(uc, table, panel));
			}
		}
	}
}
