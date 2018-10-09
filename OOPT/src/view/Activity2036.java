package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.SystemOperation;
import Model.UseCase;

public class Activity2036 extends JTabbedPane {
	private JTable table;
	private JTabbedPane tabbedPane;
	public Activity2036(JTree tree, ArrayList<SystemOperation> op, Datainfo data) {
		JSplitPane splitPane = new JSplitPane();
		tabbedPane = new JTabbedPane();
		
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(tabbedPane);
        splitPane.setTopComponent(jpanel);
        splitPane.disable();
		
		addTab("Define Operation Constracts", null, splitPane, null);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
					
				for(int i = 0; i < tabbedPane.getTabCount(); i++) {
					JScrollPane cp = (JScrollPane) tabbedPane.getComponentAt(i);
					
					JViewport v = cp.getViewport();
					JTable t = (JTable) v.getView();
					t.editingStopped(changeEvent);
					tabbedPane.setTitleAt(i, (String)t.getValueAt(0, 1));
					
					op.get(i).setName((String)t.getValueAt(0, 1));
					op.get(i).setResponsibility((String)t.getValueAt(1, 1));
					op.get(i).setType((String)t.getValueAt(2, 1));
					op.get(i).setCross((String)t.getValueAt(3, 1));
					op.get(i).setNotes((String)t.getValueAt(4, 1));
					op.get(i).setException((String)t.getValueAt(5, 1));
					op.get(i).setOutput((String)t.getValueAt(6, 1));
					op.get(i).setPreconditions((String)t.getValueAt(7, 1));
					op.get(i).setPostconditions((String)t.getValueAt(8, 1));
					
					data.setOp(i, op.get(i));
					
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
			
			
		});
	}
	public void save(Datainfo data, ArrayList<SystemOperation> op) {
		data.syncOp();
		for(SystemOperation tmp : op) {
			data.setOp(op.indexOf(tmp), tmp);
		}
	}
	public void open(ArrayList<SystemOperation> op) {
		tabbedPane.removeAll();
		if(op.size()==0) {
			
		}
		else {
			for(SystemOperation tmp : op) {
				JScrollPane operationPane = new JScrollPane();
				tabbedPane.addTab(tmp.getName(), null, operationPane, null);
				table = new JTable();
				table.setCellSelectionEnabled(false);
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
			    table.getColumn(" ").setCellEditor(new TextAreaEditor(table, op, tabbedPane));
			}
		}
	}
	public void syncOperation(ArrayList<SystemOperation> op) {
		tabbedPane.removeAll();
		for(SystemOperation tmp : op) {
			JScrollPane operationPane = new JScrollPane();
			tabbedPane.addTab(tmp.getName(), null, operationPane, null);
			table = new JTable();
			table.setCellSelectionEnabled(false);
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
		    table.getColumn(" ").setCellEditor(new TextAreaEditor(table, op, tabbedPane));
		}
	}

}
