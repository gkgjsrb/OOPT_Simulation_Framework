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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.Risk;
import Model.StageText;

public class Activity1007 extends JTabbedPane {
	DefaultTableModel model;
	//ArrayList<String> concept;
	
	public Activity1007(JTree tree, Datainfo data) {
		
		//DefaultTableCellRenderer CellRenderer = new DefaultTableCellRenderer();
		//CellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		String[] colName= {"Concepts"};
		Object[][] rowData= {{null}};
		
		model=new DefaultTableModel(rowData,colName);
		
		JTable table = new JTable(model);
		//TableColumnModel colmodel = table.getColumnModel();
		
		//for(int i=0; i<colmodel.getColumnCount(); i++) {
		//	colmodel.getColumn(i).setCellRenderer(CellRenderer);
		//}
		table.setRowHeight(35);

		table.getColumn("Concepts").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("Concepts").setCellEditor(new TextAreaEditor());

	    JSplitPane splitPane = new JSplitPane();
		JScrollPane panel = new JScrollPane(table);
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");
		
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.setBorder(BorderFactory.createEmptyBorder(0 , 0, 5, 5));
		
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(panel, popupMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {null};
				model.addRow(add);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					data.syncConcept(model.getRowCount());
					table.editingCanceled(changeEvent);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);
		this.addTab("Define Business Concept Model", null, splitPane, null);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {""};
				model.addRow(add);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					data.syncConcept(model.getRowCount());
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 6) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				table.editingStopped(changeEvent);
				for(int i = 0; i < model.getRowCount(); i++) {
					String text = (String)model.getValueAt(i, 0);
					data.setConcept(i, text);
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
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
	
	public void save(Datainfo data) {
		
		for(int i = 0; i < model.getRowCount(); i++) {
			String text = (String)model.getValueAt(i, 0);
			data.setConcept(i, text);
		}
	}
	public void open(ArrayList<String> concept) {
		model.setRowCount(0);
				
		for(String s : concept) {
			Object[] add= {s};
			model.addRow(add);
		}
	}

}
