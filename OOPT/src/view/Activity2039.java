package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IColorable;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.edge.InteractionEdge;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.horstmann.violet.workspace.editorpart.IEditorPart;
import com.horstmann.violet.workspace.editorpart.IEditorPartBehaviorManager;
import com.horstmann.violet.workspace.editorpart.IEditorPartSelectionHandler;
//traceability
public class Activity2039 extends JTabbedPane {

	private IEditorPart editorPart;

    private IGraph graph;

    private IEditorPartSelectionHandler selectionHandler;
    
    private IEditorPartBehaviorManager behaviorManager;
    
    
    
    private boolean isDragGesture = false;
    
    private INode unprocessedNode = null; 

    private IEdge unprocessedEdge = null; 
	
	
	public Activity2039() {
		
		
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		jpanel.add(button_1);
		jpanel.add(button_2);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        
        this.editorPart = workspace.getEditorPart();
        this.graph = editorPart.getGraph();
        this.selectionHandler = editorPart.getSelectionHandler();
        this.behaviorManager = editorPart.getBehaviorManager();
        
        
        UseCaseNode n = new UseCaseNode();
        SingleLineText ca = new SingleLineText();
        ca.setText("aaasdfasfasfas");
        n.setName(ca);
        Point2D qq = new Point2D.Double(10.0, 10.0);
        
        UseCaseNode n2 = new UseCaseNode();
        SingleLineText ca2 = new SingleLineText();
        ca2.setText("a2");
        n2.setName(ca2);
        Point2D qq2 = new Point2D.Double(100.0, 100.0);
        
        UseCaseNode n3 = new UseCaseNode();
        SingleLineText ca3 = new SingleLineText();
        ca3.setText("a3");
        n3.setName(ca3);
        Point2D qq3 = new Point2D.Double(300.0, 300.0);
        InteractionEdge ie = new InteractionEdge();
        //ie.setStartNode(n);
       // ie.setEndNode(n3);
        
        workspace.getGraphFile().getGraph().addNode(n, qq);
        workspace.getGraphFile().getGraph().addNode(n2, qq2);
        workspace.getGraphFile().getGraph().addNode(n3, qq3);
        workspace.getGraphFile().getGraph().connect(ie, n, n.getLocation(), n3, n3.getLocation(), null);
        WorkspacePanel wp = workspace.getAWTComponent();
        wp.getScrollableSideBar().setVisible(false);
        wp.getScrollableEditorPart().getViewport().getView().removeMouseListener(wp.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);
        wp.getScrollableEditorPart().getViewport().getView().addMouseListener(new MouseAdapter()
        {
        	
            public void mousePressed(MouseEvent event)
            {
                
            }

            public void mouseReleased(MouseEvent event)
            {
                
            }

            public void mouseClicked(MouseEvent event)
            {	
            	 double zoom = editorPart.getZoomFactor();
            	 Point2D mousePoint = new Point2D.Double(event.getX() / zoom, event.getY() / zoom);
            	 boolean isOnNodeOrEdge = isMouseOnNodeOrEdge(mousePoint);
        		if (!isOnNodeOrEdge )
                {
        			UseCaseNode node = (UseCaseNode)selectionHandler.getLastSelectedNode();
        			
        			Collection<IEdge> ea = graph.getAllEdges();
                    for(IEdge q : ea) {
                    	if(q.getStartNode().equals(node)) {
                    		node.setBackgroundColor(Color.white);
                    		UseCaseNode endNode = (UseCaseNode) q.getEndNode();
                    		endNode.setBackgroundColor(Color.white);
                    	}
                    }
                    resetSelectedElements();
                    wp.refreshDisplay();
                    return;
                }
                if (isOnNodeOrEdge)
                {
                    processSelection(mousePoint, true);
                    UseCaseNode node = (UseCaseNode) graph.findNode(mousePoint);
                    
                    Collection<IEdge> ea = graph.getAllEdges();
                    for(IEdge q : ea) {
                    	if(q.getStartNode().equals(node)) {
                    		node.setBackgroundColor(Color.cyan);
                    		UseCaseNode endNode = (UseCaseNode) q.getEndNode();
                    		endNode.setBackgroundColor(Color.GRAY);
                    	}
                    }
                    return;
                }
        		
            	
                
            }
        });
        
        button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(1);
		    
			}
		});
		
        button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getEditorPart().changeZoom(-1);
		        
			}
		});
        
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(jpanel);
        addTab("Traceability Analysis", null, splitPane, null);
	}
	private void processSelection(Point2D mouseLocation, boolean isResetSelectionFirst)
    {
        INode node = this.graph.findNode(mouseLocation);
        IEdge edge = this.graph.findEdge(mouseLocation);
        if (edge != null)
        {
        	if (this.selectionHandler.isElementAlreadySelected(edge))
        	{
        		// This edge will be removed only on mouse button released
        		// to avoid conflicts with dragging events
        		this.unprocessedEdge = edge;
        	}
        	else
        	{
        		if (isResetSelectionFirst) {
        			resetSelectedElements();
        		}
        		this.selectionHandler.addSelectedElement(edge);
        		if (this.selectionHandler.getSelectedEdges().size() == 1) {
        			this.behaviorManager.fireOnEdgeSelected(edge);
        		}
        	}
        	return;
        }
        if (node != null)
        {
            if (this.selectionHandler.isElementAlreadySelected(node))
            {
                // This node_old will be removed only on mouse button released
                // to avoid conflicts with dragging events
                this.unprocessedNode = node;
            }
            else
            {
                if (isResetSelectionFirst) {
                    resetSelectedElements();
                }
                this.selectionHandler.addSelectedElement(node);
                if (this.selectionHandler.getSelectedNodes().size() == 1) {
                	this.behaviorManager.fireOnNodeSelected(node);
                }
            }
            return;
        }
    }
	private void resetSelectedElements()
    {
        this.selectionHandler.clearSelection();
    }
	private boolean isMouseOnNodeOrEdge(Point2D mouseLocation)
    {
        INode node = this.graph.findNode(mouseLocation);
        IEdge edge = this.graph.findEdge(mouseLocation);
        if (node == null && edge == null)
        {
            return false;
        }
        return true;
    }
}
