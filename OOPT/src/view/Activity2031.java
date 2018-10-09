package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

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

import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.Workspace;

import Model.Datainfo;
import Model.Graph;
import Model.Requirement;
import Model.StageText;
import Model.UseCase;
//
public class Activity2031 extends JTabbedPane {
	private JTable table;
	JTabbedPane panel;
	public Activity2031(JTree tree, ArrayList<UseCase> uc, Datainfo data) {
		JSplitPane splitPane = new JSplitPane();
		panel = new JTabbedPane();
		
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
        splitPane.setTopComponent(jpanel);
        splitPane.disable();
		addTab("Define Essential Use Cases", null, splitPane, null);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
					
				for(int i = 0; i < panel.getTabCount(); i++) {
					JScrollPane cp = (JScrollPane) panel.getComponentAt(i);
					
					JViewport v = cp.getViewport();
					JTable t = (JTable) v.getView();
					t.editingStopped(changeEvent);
					panel.setTitleAt(i, (String)t.getValueAt(0, 1));
					
					uc.get(i).setName((String)t.getValueAt(0, 1));
					uc.get(i).setActor((String)t.getValueAt(1, 1));
					uc.get(i).setPurpose((String)t.getValueAt(2, 1));
					uc.get(i).setOverview((String)t.getValueAt(3, 1));
					uc.get(i).setType((String)t.getValueAt(4, 1));
					uc.get(i).setCross((String)t.getValueAt(5, 1));
					uc.get(i).setPreRequistes((String)t.getValueAt(6, 1));
					uc.get(i).setTypical((String)t.getValueAt(7, 1));
					uc.get(i).setAlternative((String)t.getValueAt(8, 1));
					uc.get(i).setExceptional((String)t.getValueAt(9, 1));
					
					
					data.setUsecase(i, uc.get(i));
					
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
			
			
		});
	}

	public void save(Datainfo data, ArrayList<UseCase> uc) {
		
		data.syncUsecase();
		for(UseCase tmp : uc) {
			data.setUsecase(uc.indexOf(tmp), tmp);
		}
	}
	public void open(ArrayList<UseCase> uc) {
		
		panel.removeAll();
		if(uc.size()==0) {
			
		}
		else {
			for(UseCase tmp : uc) {
				JScrollPane usecasePane = new JScrollPane();
				panel.addTab(tmp.getName(), null, usecasePane, null);
				table = new JTable();
				table.setCellSelectionEnabled(false);
				
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Use Case", tmp.getName()},
						{"Actor", tmp.getActor()},
						{"Purpose", tmp.getPurpose()},
						{"Overview", tmp.getOverview()},
						{"Type", tmp.getType()},
						{"Cross Reference", tmp.getCross()},
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
	public void syncUseCase(ArrayList<UseCase> uc) {
		panel.removeAll();
		if(uc.size()==0) {
			
		}
		else {
			for(UseCase tmp : uc) {
				JScrollPane usecasePane = new JScrollPane();
				panel.addTab(tmp.getName(), null, usecasePane, null);
				table = new JTable();
				table.setCellSelectionEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Use Case", tmp.getName()},
						{"Actor", tmp.getActor()},
						{"Purpose", tmp.getPurpose()},
						{"Overview", tmp.getOverview()},
						{"Type", tmp.getType()},
						{"Cross Reference", tmp.getCross()},
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
