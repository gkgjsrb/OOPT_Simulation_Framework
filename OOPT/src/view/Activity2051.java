package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.MethodDescription;
import Model.UseCase;

public class Activity2051 extends JTabbedPane {
	private JTable table;
	private JTabbedPane panel;
	
	public Activity2051(JTree tree, ArrayList<MethodDescription> md, Datainfo data) {
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
		
		addTab("Implement Class & Method Definitions", null, splitPane, null);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(1))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 0) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				data.syncMethod();
				int c = 0;
				for(int i = 0; i < panel.getTabCount(); i++) {
					JTabbedPane tp = (JTabbedPane) panel.getComponentAt(i);
					for(int j = 0; j < tp.getTabCount(); j++) {
						JScrollPane cp = (JScrollPane) tp.getComponentAt(j);
						
						JViewport v = cp.getViewport();
						JTable t = (JTable) v.getView();
						t.editingStopped(changeEvent);
						tp.setTitleAt(j, (String)t.getValueAt(1, 1));
						
						md.get(c).setType((String)t.getValueAt(0, 1));
						md.get(c).setName((String)t.getValueAt(1, 1));
						md.get(c).setPurpose((String)t.getValueAt(2, 1));
						md.get(c).setOverview((String)t.getValueAt(3, 1));
						md.get(c).setCross((String)t.getValueAt(4, 1));
						md.get(c).setInput((String)t.getValueAt(5, 1));
						md.get(c).setOutput((String)t.getValueAt(6, 1));
						md.get(c).setAbstract((String)t.getValueAt(7, 1));
						md.get(c).setException((String)t.getValueAt(8, 1));
						data.setMethod(md.get(c));
						c++;
					}
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		
		
	}
	public void save(Datainfo data, ArrayList<MethodDescription> md) {
		
		data.syncMethod();
		for(MethodDescription tmp : md) {
			data.setMethod(tmp);
		}
	}
	public void open(ArrayList<MethodDescription> md) {
		panel.removeAll();
		if(md.size()==0) {
			
		}
		else {
			JTabbedPane classPane = null;
			for(MethodDescription tmp : md) {
				JScrollPane mdPane = new JScrollPane();
				if(tmp.getType().equals("Class")) {
					classPane = new JTabbedPane();
					panel.addTab(tmp.getName(), null, classPane, null);
					
				}
				
				classPane.addTab(tmp.getName(), null, mdPane, null);
				table = new JTable();
				table.setCellSelectionEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Type", tmp.getType()},
						{"Name", tmp.getName()},
						{"Purpose", tmp.getPurpose()},
						{"Overview(Class)", tmp.getOverview()},
						{"Cross Reference", tmp.getCross()},
						{"Input(Method)", tmp.getInput()},
						{"Output(Method)", tmp.getOutput()},
						{"Abstract operation(Method)", tmp.getAbstract()},
						{"Exceptional Courses of Events", tmp.getException()},
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
				mdPane.setViewportView(table);
				table.setRowHeight(45);
			
				table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
				table.getColumn(" ").setCellEditor(new TextAreaEditor(table, panel, md));				
			}
		}
	}
	public void syncMethod(ArrayList<MethodDescription> md) {
		panel.removeAll();
		if(md.size()==0) {
			
		}
		else {
			JTabbedPane classPane = null;
			for(MethodDescription tmp : md) {
				JScrollPane mdPane = new JScrollPane();
				if(tmp.getType().equals("Class")) {
					classPane = new JTabbedPane();
					panel.addTab(tmp.getName(), null, classPane, null);
					
				}
				
				classPane.addTab(tmp.getName(), null, mdPane, null);
				table = new JTable();
				table.setCellSelectionEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Type", tmp.getType()},
						{"Name", tmp.getName()},
						{"Purpose", tmp.getPurpose()},
						{"Overview(Class)", tmp.getOverview()},
						{"Cross Reference", tmp.getCross()},
						{"Input(Method)", tmp.getInput()},
						{"Output(Method)", tmp.getOutput()},
						{"Abstract operation(Method)", tmp.getAbstract()},
						{"Exceptional Courses of Events", tmp.getException()},
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
				mdPane.setViewportView(table);
				table.setRowHeight(45);
			
				table.getColumn(" ").setCellRenderer(new TextAreaRenderer());
				table.getColumn(" ").setCellEditor(new TextAreaEditor(table, panel, md));				
			}
		}
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
