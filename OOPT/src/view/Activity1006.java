package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultTreeModel;

import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.chooser.IFileChooserService;
import com.horstmann.violet.framework.file.naming.ExtensionFilter;
import com.horstmann.violet.framework.file.naming.FileNamingService;
import com.horstmann.violet.framework.file.persistence.IFileReader;
import com.horstmann.violet.framework.file.persistence.XStreamBasedPersistenceService;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.plugin.IDiagramPlugin;
import com.horstmann.violet.framework.plugin.PluginRegistry;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.edge.DependencyEdge;
import com.horstmann.violet.product.diagram.property.ArrowheadChoiceList;
import com.horstmann.violet.product.diagram.property.BentStyleChoiceList;
import com.horstmann.violet.product.diagram.property.LineStyleChoiceList;
import com.horstmann.violet.product.diagram.sequence.SequenceDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.node.ActorNode;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Model.Graph;
import Model.Requirement;
import Model.UseCase;



public class Activity1006 extends JTabbedPane {
	//private JTable table;
	DefaultTableModel model;
	DefaultTableModel model2;
	DefaultTableModel model3;
	DefaultTableModel model4;
	DefaultTableModel model5;
	   
    private IWorkspace workspace;
    
    private WorkspacePanel wp;
    
	public Activity1006(JTree tree, Requirement req, ArrayList<UseCase> uc, Graph ud, ArrayList<Graph> sd, ArrayList<Graph> id, ArrayList<Graph> std) {
		
		BeanInjector.getInjector().inject(this);
		
		String category[] = {"Primary","Secondary", "Optional"};
		String rank[] = {"High","Medium", "Low"};
		
		JScrollPane scrollPane = new JScrollPane();
		addTab("Define System Boundary", null, scrollPane, null);
		
		JSplitPane splitPane_1 = new JSplitPane();
		JPanel jpanel_1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		JButton button_1 = new JButton("Commit");
		jpanel_1.add(button_1, c);
		jpanel_1.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.disable();
		splitPane_1.setBottomComponent(scrollPane_1);
		splitPane_1.setTopComponent(jpanel_1);
		this.addTab("Identify and Describe Actors", null, splitPane_1, null);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		textPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("computer");
		        	 }
		        	 ((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JLabel lblNewLabel = new JLabel("<html>example(Library Management System)<br>"
	            + "- Librarian : an employee of the library who interacts with the<br>"
				+ "customers(borrowers) and whose work is supported by the system.<br>"
	            + "</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jpanel_1.add(lblNewLabel, c);
		//
		JTabbedPane tabPane = new JTabbedPane();
		JSplitPane splitPane_2 = new JSplitPane();
		JScrollPane panel = new JScrollPane();
		String[] colName= {"AUseCase"};
		Object[][] rowData= {{""}};
		model=new DefaultTableModel(rowData,colName);
		JTable table = new JTable(model);
				
		panel.setViewportView(table);
		table.setRowHeight(45);
		
		table.getColumn("AUseCase").setCellRenderer(new TextAreaRenderer());
	    table.getColumn("AUseCase").setCellEditor(new TextAreaEditor());
	    
		JPanel jpanel_2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_2 = new JButton("+");
		JButton button_3 = new JButton("-");
		JButton button_4 = new JButton("Commit");
		
		jpanel_2.add(button_2);
		jpanel_2.add(button_3);
		jpanel_2.add(button_4);
		jpanel_2.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setBottomComponent(panel);
		splitPane_2.setTopComponent(jpanel_2);
		splitPane_2.disable();
		
		JSplitPane splitPane_3 = new JSplitPane();
		JScrollPane panel2 = new JScrollPane();
		String[] colName2= {"EUseCase"};
		Object[][] rowData2= {{""}};
		model2=new DefaultTableModel(rowData2, colName2);
		JTable table_1 = new JTable(model2);
		
		panel2.setViewportView(table_1);
		table_1.setRowHeight(45);		

		table_1.getColumn("EUseCase").setCellRenderer(new TextAreaRenderer());
	    table_1.getColumn("EUseCase").setCellEditor(new TextAreaEditor());
	    
	    JPanel jpanel_3 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_5 = new JButton("+");
		JButton button_6 = new JButton("-");
		JButton button_7 = new JButton("Commit");
		
		jpanel_3.add(button_5);
		jpanel_3.add(button_6);
		jpanel_3.add(button_7);
		jpanel_3.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setBottomComponent(panel2);
		splitPane_3.setTopComponent(jpanel_3);
		splitPane_3.disable();
		
		
		tabPane.addTab("Use-Cases by Actor-Based", null, splitPane_2, null);
		tabPane.addTab("Use-Cases by Event-Based", null, splitPane_3, null);
		this.addTab("Identify Use-Case", null,tabPane, null);

		JSplitPane splitPane_4 = new JSplitPane();
		JScrollPane panel3 = new JScrollPane();
		String[] colName3= {"Ref", "Function", "Use-Case Number & Name"};
		Object[][] rowData3= {{"","",""}};
		model3=new DefaultTableModel(rowData3, colName3);
		JTable table_2 = new JTable(model3);
		
		panel3.setViewportView(table_2);
		table_2.setRowHeight(45);		

		table_2.getColumn("Use-Case Number & Name").setCellRenderer(new TextAreaRenderer());
	    table_2.getColumn("Use-Case Number & Name").setCellEditor(new TextAreaEditor());
	    
	    JPanel jpanel_4 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_8 = new JButton("Commit");
		
		jpanel_4.add(button_8);
		jpanel_4.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setBottomComponent(panel3);
		splitPane_4.setTopComponent(jpanel_4);
		splitPane_4.disable();
				
		this.addTab("Allocate System Functions into Related Use-Cases", null, splitPane_4, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane)e.getSource();
				
				int tab = TabbedPane.getSelectedIndex();
				
				if(tab == 3) {
					sync(req);
				}
			}
			
		});
		
		JSplitPane splitPane_5 = new JSplitPane();
		JScrollPane panel4 = new JScrollPane();
		String[] colName4= {"Use-Case Number & Name", "Category"};
		Object[][] rowData4= {{"", ""}};
		model4=new DefaultTableModel(rowData4, colName4);
		JTable table_3 = new JTable(model4);
		JComboBox<String> comboBox = new JComboBox<String>(category);
		TableCellEditor Comboeditor = new DefaultCellEditor(comboBox);
		table_3.getColumnModel().getColumn(1).setCellEditor(Comboeditor);
		panel4.setViewportView(table_3);
		table_3.setRowHeight(45);		

		//table_3.getColumn("Use-Case Number & Name").setCellRenderer(new TextAreaRenderer());
	    //table_3.getColumn("Use-Case Number & Name").setCellEditor(new TextAreaEditor());
	    
	    JPanel jpanel_5 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_9 = new JButton("Commit");
		
		jpanel_5.add(button_9);
		jpanel_5.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setBottomComponent(panel4);
		splitPane_5.setTopComponent(jpanel_5);
		splitPane_5.disable();
				
		this.addTab("Categorize Use-Cases", null, splitPane_5, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane)e.getSource();
				
				int tab = TabbedPane.getSelectedIndex();
				
				if(tab == 4) {
					if(model4.getRowCount()<model3.getRowCount()) {
						int count = model3.getRowCount() - model4.getRowCount();
						for(int i = 0; i < count; i++) {
							Object[] add = {"", ""};
							model4.addRow(add);
						}
					}
					else if(model4.getRowCount()>model3.getRowCount()) {
						int count = model4.getRowCount()-model3.getRowCount();
						for(int i = 0; i < count; i++) {
							model4.removeRow(0);
							
						}
					}
					
					for(int i = 0; i < model4.getRowCount(); i++) {
						model4.setValueAt(model3.getValueAt(i, 2), i, 0);
					}
					
				}
			}
			
		});
		
		JSplitPane splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_12 = new JButton("Commit");
		jpanel_6.add(button_12);

		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        
        workspace = new Workspace(graphFile);
        wp = workspace.getAWTComponent();
		
        splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_6.setBottomComponent(wp);
        splitPane_6.setTopComponent(jpanel_6);
        addTab("Draw a Use-Case Diagram", null, splitPane_6, null);
        
		JTabbedPane tpanel = new JTabbedPane();
		addTab("Describe Use-Cases", null, tpanel, null);
		
		
		JSplitPane splitPane_7 = new JSplitPane();
		JScrollPane panel5 = new JScrollPane();
		String[] colName5= {"Use-Case Number & Name", "Rank"};
		Object[][] rowData5= {{"", ""}};
		model5=new DefaultTableModel(rowData5, colName5);
		JTable table_5 = new JTable(model5);
		JComboBox<String> comboBox2 = new JComboBox<String>(rank);
		TableCellEditor Comboeditor2 = new DefaultCellEditor(comboBox2);
		table_5.getColumnModel().getColumn(1).setCellEditor(Comboeditor2);
		
		panel5.setViewportView(table_5);
		table_5.setRowHeight(45);		

		//table_3.getColumn("Use-Case Number & Name").setCellRenderer(new TextAreaRenderer());
	    //table_3.getColumn("Use-Case Number & Name").setCellEditor(new TextAreaEditor());
	    
	    JPanel jpanel_7 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_13 = new JButton("Commit");
		
		jpanel_7.add(button_13);
		jpanel_7.setBorder(BorderFactory.createEmptyBorder(0 , 0, 0, 5));
		
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_7.setBottomComponent(panel5);
		splitPane_7.setTopComponent(jpanel_7);
		splitPane_7.disable();
				
		this.addTab("Rank Use-Cases", null, splitPane_7, null);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JTabbedPane TabbedPane = (JTabbedPane)e.getSource();
				
				int tab = TabbedPane.getSelectedIndex();
				
				if(tab == 7) {
					if(model5.getRowCount()<model3.getRowCount()) {
						int count = model3.getRowCount() - model5.getRowCount();
						for(int i = 0; i < count; i++) {
							Object[] add = {"", ""};
							model5.addRow(add);
						}
					}
					else if(model5.getRowCount()>model3.getRowCount()) {
						int count = model5.getRowCount()-model3.getRowCount();
						for(int i = 0; i < count; i++) {
							model5.removeRow(0);
							
						}
					}
					
					for(int i = 0; i < model5.getRowCount(); i++) {
						model5.setValueAt(model3.getValueAt(i, 2), i, 0);
					}
					
				}
			}
			
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {""};
				model.addRow(add);
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model.removeRow(row);
					table.editingCanceled(changeEvent);
				}
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] add= {""};
				model2.addRow(add);
			}
		});
		
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if(row!=-1) {
					model2.removeRow(row);
					table_1.editingCanceled(changeEvent);
				}
			}
		});
		
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IconNode node=(IconNode)tree.getLastSelectedPathComponent();
				if(node.getParent().equals(node.getRoot().getChildAt(0))){
		        	int index = node.getParent().getIndex(node);
		        	 if(index == 5) {
		        	 	node.setIconName("floppyDrive");
		        	 }
				}
				((DefaultTreeModel)tree.getModel()).nodeChanged(node);
			}
		});
		ud.setGraph(workspace.getGraphFile());
		 button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ud.setGraph(workspace.getGraphFile());
				Collection<INode> allNodes = ud.getGraph().getGraph().getAllNodes();
				Collection<IEdge> allEdges = ud.getGraph().getGraph().getAllEdges();
				tpanel.removeAll();
				for (INode aNode : allNodes) {
					int exist=0;
					if(aNode.getClass().equals(UseCaseNode.class)) {
						UseCaseNode a = (UseCaseNode)aNode;

						for(UseCase tmp : uc) {
							if(a.getId().equals(tmp.getId())) {
								tmp.setName(a.getName().toString());
								exist=1;
							}
						}
						if(exist==0) {
							UseCase usec = new UseCase();
							usec.setName(a.getName().toString());
							usec.setId(a.getId());
							uc.add(usec);
						}
					}
				}
				ArrayList<UseCase> tmp_list = new ArrayList<UseCase>();
				for(UseCase tmp : uc) {
					for(INode aNode : allNodes) {
						if(tmp.getId().equals(aNode.getId())){
							tmp_list.add(tmp);
						}
					}
				} // clone?
				uc.clear();
				uc.addAll(tmp_list);
				
				for(UseCase tmp : uc) {
					Graph sdtmp = new Graph(tmp.getName());
					Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
			        IGraphFile graphFile = new GraphFile(graphClass);
					sdtmp.setGraph(graphFile);
					sd.add(sdtmp);
				}
				for(UseCase tmp : uc) {
					Graph idtmp = new Graph(tmp.getName());
					Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
			        IGraphFile graphFile = new GraphFile(graphClass);
					idtmp.setGraph(graphFile);
					id.add(idtmp);
				}
				
				for(UseCase tmp : uc) {
					Graph stdtmp = new Graph(tmp.getName());
					Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
			        IGraphFile graphFile = new GraphFile(graphClass);
					stdtmp.setGraph(graphFile);
					std.add(stdtmp);
				}
						
				for(UseCase tmp : uc) {
					JScrollPane usecasePane = new JScrollPane();
					tpanel.addTab(tmp.getName(), null, usecasePane, null);
					JTable table_4 = new JTable();
					table_4.setModel(new DefaultTableModel(
						new Object[][] {
							{"Name", tmp.getName()},
							{"Actor", tmp.getActor()},
							{"Description", tmp.getDes()}
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
					table_4.getColumnModel().getColumn(0).setResizable(false);
					usecasePane.setViewportView(table_4);
					table_4.setRowHeight(45);
						
					table_4.getColumn(" ").setCellRenderer(new TextAreaRenderer());
					table_4.getColumn(" ").setCellEditor(new TextAreaEditor(table_4, uc, tpanel, workspace));
					}
				}
		 });
		 button_13.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					IconNode node=(IconNode)tree.getLastSelectedPathComponent();
					if(node.getParent().equals(node.getRoot().getChildAt(0))){
			        	int index = node.getParent().getIndex(node);
			        	 if(index == 5) {
			        	 	node.setIconName("floppyDrive");
			        	 }
					}
					((DefaultTreeModel)tree.getModel()).nodeChanged(node);
				}
		});
	}

	public void sync(Requirement req) {
		if(req.get_length()>model3.getRowCount()) {
			int count = req.get_length()-model3.getRowCount();
			for(int i = 0; i < count; i++) {
				Object[] add = {"", "",""};
				model3.addRow(add);
			}
		}
		else if(req.get_length()<model3.getRowCount()) {
			int count = model3.getRowCount()-req.get_length();
			for(int i = 0; i < count; i++) {
				model3.removeRow(0);
				
			}
		}
		for(int i = 0; i < req.get_length(); i++) {
			model3.setValueAt(req.getRef(i), i, 0);
			model3.setValueAt(req.getName(i), i, 1);
		}	
	}
	

}
