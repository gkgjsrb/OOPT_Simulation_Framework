package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeModel;

import Model.Datainfo;
import Model.NonFuncReq;
import Model.Requirement;
import Model.SystemTestCase;
import Model.UseCase;

public class Activity2038 extends JTabbedPane {
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBox2 = new JComboBox<String>();
	DefaultTableModel model;
	DefaultTableModel model2;

	public Activity2038(JTree tree, Requirement req, ArrayList<UseCase> uc, Datainfo data) {

		String[] colName = { "Test No.", "Test Item", "Description", "Use Case", "System Function" };
		String[] colName2 = { "Category", "Test Case" };

		Object[][] rowData = { { null, null, null, null, null } };
		Object[][] rowData2 = { { null, null } };

		model = new DefaultTableModel(rowData, colName);
		model2 = new DefaultTableModel(rowData2, colName2);

		JTable table = new JTable(model);
		JTable table2 = new JTable(model2);
		
		table.setCellSelectionEnabled(false);
		table2.setCellSelectionEnabled(false);
		
		table.setRowHeight(70);
		table2.setRowHeight(70);

		table.getColumn("Test No.").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Test No.").setCellEditor(new TextAreaEditor());

		// table.getColumn("Function").setCellRenderer(new TextAreaRenderer());
		// table.getColumn("Function").setCellEditor(new TextAreaEditor());
		for (int i = 0; i < req.get_length(); i++) {
			comboBox.addItem(req.getuNumber(i) + ". " + req.getuName(i));
			comboBox2.addItem(req.getRef(i));
		}

		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table.getColumnModel().getColumn(3).setCellEditor(Comboeditor);
		TableCellEditor Comboeditor2 = new DefaultCellEditor(comboBox2);
		table.getColumnModel().getColumn(4).setCellEditor(Comboeditor2);

		table.getColumn("Test Item").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Test Item").setCellEditor(new TextAreaEditor());

		table.getColumn("Description").setCellRenderer(new TextAreaRenderer());
		table.getColumn("Description").setCellEditor(new TextAreaEditor());

		// table.getColumn("Use Case").setCellRenderer(new TextAreaRenderer());
		// table.getColumn("Use Case").setCellEditor(new TextAreaEditor());
		// table.getColumn("System Function").setCellRenderer(new
		// TextAreaRenderer());
		// table.getColumn("System Function").setCellEditor(new
		// TextAreaEditor());

		table2.getColumn("Category").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Category").setCellEditor(new TextAreaEditor());

		table2.getColumn("Test Case").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Test Case").setCellEditor(new TextAreaEditor());

		JScrollPane panel = new JScrollPane(table);
		JScrollPane panel2 = new JScrollPane(table2);
		JSplitPane splitPane = new JSplitPane();
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel jpanel_1 = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		JButton button = new JButton("+");
		JButton button_1 = new JButton("-");
		JButton button_2 = new JButton("Commit");

		JButton button_3 = new JButton("+");
		JButton button_4 = new JButton("-");
		JButton button_5 = new JButton("Commit");
		jpanel.add(button);
		jpanel.add(button_1);
		jpanel.add(button_2);

		jpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

		jpanel_1.add(button_3);
		jpanel_1.add(button_4);
		jpanel_1.add(button_5);

		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(panel);
		splitPane.setTopComponent(jpanel);
		splitPane.disable();

		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setBottomComponent(panel2);
		splitPane_1.setTopComponent(jpanel_1);
		splitPane_1.disable();

		this.addTab("Functional Requirement Test Case", null, splitPane, null);
		this.addTab("Non Functional Requirement Test Case", null, splitPane_1, null);

		JPopupMenu popupMenu = new JPopupMenu();
		JPopupMenu popupMenu2 = new JPopupMenu();
		addPopup(table, popupMenu);
		addPopup(table2, popupMenu2);
		addPopup(panel, popupMenu);
		addPopup(panel2, popupMenu2);

		JMenuItem mntmNewMenuItem = new JMenuItem("add row");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { null, null, null, null, null };
				model.addRow(add);
			}
		});
		popupMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("del row");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row != -1) {
					model.removeRow(row);
				}
			}
		});
		popupMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("add row");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add2 = { null, null };
				model2.addRow(add2);
			}
		});
		popupMenu2.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("del row");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row2 = table2.getSelectedRow();
				if (row2 != -1) {
					model2.removeRow(row2);
				}
			}
		});
		popupMenu2.add(mntmNewMenuItem_3);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { "", "", "", "", "" };
				model.addRow(add);
			}
		});

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row != -1) {
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 7) {
						node.setIconName("floppyDrive");
					}
				}

				table.editingStopped(changeEvent);
				data.syncSystemTestCase();
				for (int i = 0; i < model.getRowCount(); i++) {
					SystemTestCase r = new SystemTestCase();
					r.setNumber((String) model.getValueAt(i, 0));
					r.setName((String) model.getValueAt(i, 1));
					r.setDescription((String) model.getValueAt(i, 2));
					r.setUsecase((String) model.getValueAt(i, 3));
					r.setSystemFunction((String) model.getValueAt(i, 4));
					data.setSystemTestCase(i, r);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
			}
		});

		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add = { "", "" };
				model2.addRow(add);
			}
		});

		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table2.getSelectedRow();
				if (row != -1) {
					model2.removeRow(row);
					table2.editingCanceled(changeEvent);
				}
			}
		});

		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node = (IconNode) tree.getLastSelectedPathComponent();
				if (node.getParent().equals(node.getRoot().getChildAt(1).getChildAt(0))) {
					int index = node.getParent().getIndex(node);
					if (index == 7) {
						node.setIconName("floppyDrive");
					}
				}

				table2.editingStopped(changeEvent);
				data.syncNonReq("R");
				for (int i = 0; i < model2.getRowCount(); i++) {
					NonFuncReq r = new NonFuncReq();
					r.setCategory((String) model2.getValueAt(i, 0));
					r.setTestcase((String) model2.getValueAt(i, 1));
					r.setType("R");
					data.setNonReq(i, r);
				}
				((DefaultTreeModel) tree.getModel()).nodeChanged(node);
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

	// public void save(Datainfo data, Requirement req) {
	// for (int i = 0; i < req.get_length(); i++) {
	// data.setReq(i, req);
	// }
	//
	// }

	public void open(ArrayList<SystemTestCase> req, ArrayList<NonFuncReq> nreq) {

		model.setRowCount(0);
		model2.setRowCount(0);

		for (SystemTestCase r : req) {
			Object[] add = { r.getNumber(), r.getName(), r.getDescription(), r.getUsecase(), r.getSystemFunction() };
			model.addRow(add);
		}
		for (NonFuncReq r : nreq) {
			Object[] add2 = { r.getCategory(), r.getTestcase() };
			model2.addRow(add2);
		}
		if (req.size() == 0) {
			Object[] add = { "", "", "", "", "" };
			model.addRow(add);
		}
		if (nreq.size() == 0) {
			Object[] add2 = { "", "" };
			model2.addRow(add2);
		}

	}

	public void syncComboBox(Requirement req) {
		comboBox.removeAllItems();
		comboBox2.removeAllItems();
		for (int i = 0; i < req.get_length(); i++) {
			comboBox.addItem(req.getuNumber(i) + ". " + req.getuName(i));
			comboBox2.addItem(req.getRef(i));
		}
	}
}
