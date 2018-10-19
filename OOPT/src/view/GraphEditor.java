package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Collection;

import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.edge.AssociationEdge;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.ArrowheadChoiceList;
import com.horstmann.violet.product.diagram.property.BentStyleChoiceList;
import com.horstmann.violet.product.diagram.usecase.edge.InteractionEdge;
import com.horstmann.violet.workspace.editorpart.IEditorPart;
import com.horstmann.violet.workspace.editorpart.IEditorPartBehaviorManager;
import com.horstmann.violet.workspace.editorpart.IEditorPartSelectionHandler;

public class GraphEditor {

	private IEditorPart editorPart;
    private IGraph graph;
    private IEditorPartSelectionHandler selectionHandler;
    private IEditorPartBehaviorManager behaviorManager;
    
    private INode unprocessedNode = null; 
    private IEdge unprocessedEdge = null;
	
	public GraphEditor(IEditorPart editorPart) {
		this.editorPart = editorPart;
		this.graph = editorPart.getGraph();
        this.selectionHandler = editorPart.getSelectionHandler();
        this.behaviorManager = editorPart.getBehaviorManager();
	}
	public void changeEdge(IEdge edge) {
		AssociationEdge i = (AssociationEdge) edge;
		BentStyleChoiceList t = new BentStyleChoiceList();
		//ArrowheadChoiceList t = new ArrowheadChoiceList();
		t.setSelectedIndex(5);
		//i.setEndArrowheadChoiceList(t);
		i.setBentStyleChoiceList(t);
		
	}
	public void processSelection(Point2D mouseLocation, boolean isResetSelectionFirst)
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
	public void resetSelectedElements()
    {
        this.selectionHandler.clearSelection();
    }
	public boolean isMouseOnNodeOrEdge(Point2D mouseLocation)
    {
        INode node = this.graph.findNode(mouseLocation);
        IEdge edge = this.graph.findEdge(mouseLocation);
        if (node == null && edge == null)
        {
            return false;
        }
        return true;
    }
	public void highlight(MouseEvent event)
    {	
    	 double zoom = editorPart.getZoomFactor();
    	 Point2D mousePoint = new Point2D.Double(event.getX() / zoom, event.getY() / zoom);
    	 boolean isOnNodeOrEdge = isMouseOnNodeOrEdge(mousePoint);
		if (!isOnNodeOrEdge )
        {
			//UseCaseNode node = (UseCaseNode)selectionHandler.getLastSelectedNode();
			
			Collection<IEdge> ea = graph.getAllEdges();
            for(IEdge q : ea) {
            	//if(q.getStartNode().equals(node)) {
            		//node.setBackgroundColor(Color.white);
            		ClassNode startNode = (ClassNode) q.getStartNode();
            		startNode.setBackgroundColor(Color.white);
            		ClassNode endNode = (ClassNode) q.getEndNode();
            		endNode.setBackgroundColor(Color.white);
            	//}
            }
            resetSelectedElements();
            //wp.refreshDisplay();
            return;
        }
        if (isOnNodeOrEdge)
        {
            processSelection(mousePoint, true);
            ClassNode node = (ClassNode) graph.findNode(mousePoint);
            
            Collection<IEdge> ea = graph.getAllEdges();
            for(IEdge q : ea) {
            	if(q.getStartNode().equals(node)) {
            		node.setBackgroundColor(Color.cyan);
            		ClassNode endNode = (ClassNode) q.getEndNode();
            		endNode.setBackgroundColor(Color.GRAY);
            	}
            	else if(q.getEndNode().equals(node)) {
            		node.setBackgroundColor(Color.GRAY);
            		ClassNode startNode = (ClassNode) q.getStartNode();
            		startNode.setBackgroundColor(Color.cyan);
            	}
            }
            return;
        }
		
    	
        
    }
}
