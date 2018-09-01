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
import javax.swing.JButton;
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
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.node.ActorNode;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Model.Requirement;



public class Activity1006 extends JTabbedPane {
	//private JTable table;
	DefaultTableModel model;
	DefaultTableModel model2;
	DefaultTableModel model3;
	DefaultTableModel model4;
	
	@InjectedBean
	private FileNamingService fileNamingService;
	@InjectedBean
	private IFileChooserService fileChooserService;
	@InjectedBean
	private DialogFactory dialogFactory;
	@InjectedBean
	private PluginRegistry pluginRegistry;
	@ResourceBundleBean(key = "dialog.open_file_failed.text")
	private String dialogOpenFileErrorMessage;
    @ResourceBundleBean(key = "dialog.open_file_content_incompatibility.text")
    private String dialogOpenFileIncompatibilityMessage;
    
    private XStreamBasedPersistenceService xstreamService = new XStreamBasedPersistenceService();
    
	public Activity1006(JTree tree, Requirement req, ArrayList uc) {
		
		BeanInjector.getInjector().inject(this);
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
	            + ".....<br>"
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
		
		//JScrollPane scrollPane_2 = new JScrollPane();
		//addTab("Allocate System Functions into Related Use-Cases", null, scrollPane_2, null);
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
		JScrollPane scrollPane_3 = new JScrollPane();
		addTab("Categorize Use-Cases", null, scrollPane_3, null);

		//JScrollPane scrollPane_5 = new JScrollPane();
		//addTab("Identify Relationsships between Use-Cases", null, scrollPane_5, null);
		
		JSplitPane splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_14 = new JButton("Save");
		JButton button_15 = new JButton("Open");
		jpanel_6.add(button_14);
		jpanel_6.add(button_15);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        WorkspacePanel wp = workspace.getAWTComponent();
        //addTab("Draw a Use-Case Diagram", null, wp, null);
        button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getGraphFile().save();
			}
		});
		button_15.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent event)
	            {
	            	IFile selectedFile = null;
	                try
	                {
	                    ExtensionFilter[] filters = fileNamingService.getFileFilters();
	                    IFileReader fileOpener = fileChooserService.chooseAndGetFileReader(filters);
	                    if (fileOpener == null)
	                    {
	                        // Action cancelled by user
	                        return;
	                    }
	                    selectedFile = fileOpener.getFileDefinition();
	                    InputStream in = fileOpener.getInputStream();
	                    read(in);
	                    IGraphFile graphFile_tmp = new GraphFile(selectedFile);
	                    if(graphFile_tmp.getGraph().getClass().equals(graphClass)) {
	                    	IWorkspace workspace_tmp = new Workspace(graphFile_tmp);
		                    WorkspacePanel wp_tmp = workspace_tmp.getAWTComponent();
		                    splitPane_6.setBottomComponent(wp_tmp);	
	                    }
	                }
	                catch (StreamException se)
	                {
	                    dialogFactory.showErrorDialog(dialogOpenFileIncompatibilityMessage);
	                }
	                catch (Exception e)
	                {
	                    dialogFactory.showErrorDialog(dialogOpenFileErrorMessage + " : " + e.getMessage());
	                }
	            }
	    });
		if(fileChooserService==null) button_15.setEnabled(false);
		
        splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_6.setBottomComponent(wp);
        splitPane_6.setTopComponent(jpanel_6);
        addTab("Draw a Use-Case Diagram", null, splitPane_6, null);
        
		JTabbedPane tpanel = new JTabbedPane();
		addTab("Describe Use-Cases", null, tpanel, null);
		
		for(int i = 0; i < 5; i++) {
			JScrollPane usecasePane = new JScrollPane();
			tpanel.addTab("Use Case" +  i, null, usecasePane, null);
			JTable table_4 = new JTable();
			table_4.setModel(new DefaultTableModel(
				new Object[][] {
					{"Name", null},
					{"Actor", null},
					{"Description", null}
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
		    table_4.getColumn(" ").setCellEditor(new TextAreaEditor(null, table_4, uc, tpanel));

		}
			
		JScrollPane scrollPane_7 = new JScrollPane();
		addTab("Rank Use-Cases", null, scrollPane_7, null);
		
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
				read();
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
	
	public IGraph read(InputStream in) throws IOException
    {
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
    	XHTMLPersistenceServiceParserGetter kit = new XHTMLPersistenceServiceParserGetter();
        HTMLEditorKit.Parser parser = kit.getParser();
        StringWriter writer = new StringWriter();
        HTMLEditorKit.ParserCallback callback = new XHTMLPersistenceServiceParserCallback(writer);
        parser.parse(reader, callback, true);
        String xmlContent = writer.toString();
        InputStream xmlContentStream = new ByteArrayInputStream(xmlContent.getBytes());
        System.out.println(xmlContent);
        InputStreamReader reader2 = new InputStreamReader(xmlContentStream);
		XStream xStream = new XStream(new DomDriver("UTF-8"));
		xStream = getConfiguredXStream(xStream);
		Object fromXML = xStream.fromXML(reader2);
		IGraph graph2 = (IGraph) fromXML;
		
		Collection<INode> allNodes = graph2.getAllNodes();
		Collection<IEdge> allEdges = graph2.getAllEdges();
		
		
		reader2.close();
		graph2.deserializeSupport();
		for (INode aNode : allNodes) {
			if(aNode.getClass().equals(UseCaseNode.class)) {
				UseCaseNode a = (UseCaseNode)aNode;
				System.out.println(a.getName());
				//System.out.println(a.getTextColor());
			}
			//else if (aNode.getClass().equals(ActorNode.class)) {
				//ActorNode b = (ActorNode)aNode;
				//System.out.println(b.getName());
			//}
			System.out.println(aNode.getId());
			System.out.println(aNode.getLocation());
			System.out.println(aNode.getClass());
		}
		for (IEdge Edge : allEdges) {
			if(Edge.getClass().equals(DependencyEdge.class)) {
				DependencyEdge e = (DependencyEdge)Edge;
				
			}
			System.out.println(Edge.getId());
			System.out.println(Edge.getStartNode());
			System.out.println(Edge.getEndNode());
			System.out.println(Edge.getClass());
		}
        //IGraph graph = this.xstreamService.read(xmlContentStream);
        reader.close();
        xmlContentStream.close();
        reader.close();
        writer.close();
        return graph2;
    }
	private class XHTMLPersistenceServiceParserGetter extends HTMLEditorKit
    {
        public HTMLEditorKit.Parser getParser()
        {
            return super.getParser();
        }
    }

    private class XHTMLPersistenceServiceParserCallback extends HTMLEditorKit.ParserCallback
    {

        private Writer out;

        private boolean inHeader = false;

        public XHTMLPersistenceServiceParserCallback(Writer out)
        {
            this.out = out;
        }

        public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position)
        {
            if (!tag.equals(HTML.Tag.SCRIPT))
            {
                return;
            }
            if (!attributes.containsAttribute(HTML.getAttributeKey("id"), "content"))
            {
                return;
            }
            this.inHeader = true;
        }

        public void handleEndTag(HTML.Tag tag, int position)
        {
            if (tag.equals(HTML.Tag.SCRIPT))
            {
                if (this.inHeader)
                {
                    this.inHeader = false;
                }
            }
            // work around bug in the parser that fails to call flush
            if (tag.equals(HTML.Tag.HTML)) this.flush();
        }
        

        @Override
        public void handleComment(char[] text, int position)
        {
            if (this.inHeader)
            {
                try
                {
                    String xmlContent = new String(text);
                    xmlContent = xmlContent.replace("<![CDATA[", "");
                    xmlContent = xmlContent.replace("]]>", "");
                    out.write(xmlContent);
                    out.flush();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }

        public void flush()
        {
            try
            {
                out.flush();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

    }
    private XStream getConfiguredXStream(XStream xStream) {
		xStream.autodetectAnnotations(true);
		xStream.setMode(XStream.ID_REFERENCES);
		xStream.useAttributeFor(Point2D.Double.class, "x");
		xStream.useAttributeFor(Point2D.Double.class, "y");
		xStream.alias("Point2D.Double", Point2D.Double.class);
		xStream.addImmutableType(ArrowheadChoiceList.class);
        xStream.addImmutableType(LineStyleChoiceList.class);
        xStream.addImmutableType(BentStyleChoiceList.class);
		List<IDiagramPlugin> diagramPlugins = this.pluginRegistry.getDiagramPlugins();
		for (IDiagramPlugin aPlugin : diagramPlugins) {
			Class<? extends IGraph> graphClass = aPlugin.getGraphClass();
			xStream.alias(graphClass.getSimpleName(), graphClass);
			try {
				IGraph aDummyGraph = graphClass.newInstance();
				List<IEdge> edgePrototypes = aDummyGraph.getEdgePrototypes();
				List<INode> nodePrototypes = aDummyGraph.getNodePrototypes();
				for (IEdge anEdgePrototype : edgePrototypes) {
					Class<? extends IEdge> edgeClass = anEdgePrototype.getClass();
					xStream.alias(edgeClass.getSimpleName(), anEdgePrototype.getClass());
				}
				for (INode aNodePrototype : nodePrototypes) {
					Class<? extends INode> nodeClass = aNodePrototype.getClass();
					xStream.alias(nodeClass.getSimpleName(), aNodePrototype.getClass());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return xStream;
	}


}
