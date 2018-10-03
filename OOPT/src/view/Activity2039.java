package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.product.diagram.usecase.edge.InteractionEdge;
import com.horstmann.violet.product.diagram.usecase.node.UseCaseNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.horstmann.violet.workspace.editorpart.IEditorPart;
import com.horstmann.violet.workspace.editorpart.IEditorPartBehaviorManager;
import com.horstmann.violet.workspace.editorpart.IEditorPartSelectionHandler;

import Model.Graph;
import Model.Requirement;
import Model.SystemOperation;
import Model.UseCase;
//traceability
public class Activity2039 extends JTabbedPane {

	private IEditorPart editorPart;

    private IGraph graph;

    private IEditorPartSelectionHandler selectionHandler;
    
    private IEditorPartBehaviorManager behaviorManager;
    
    
    private WorkspacePanel wp;
    private IWorkspace workspace;
    private boolean isDragGesture = false;
    
    private INode unprocessedNode = null; 

    private IEdge unprocessedEdge = null; 
	
	
	public Activity2039(Requirement req, ArrayList<UseCase> uc, ArrayList<SystemOperation> op, ArrayList<Graph> sd) {
		
		
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("commit");
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.add(button_3);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        
        this.editorPart = workspace.getEditorPart();
        this.graph = editorPart.getGraph();
        this.selectionHandler = editorPart.getSelectionHandler();
        this.behaviorManager = editorPart.getBehaviorManager();
        
        wp = workspace.getAWTComponent();
        wp.getScrollableSideBar().setVisible(false);
        wp.getScrollableEditorPart().getViewport().getView().removeMouseListener(wp.getScrollableEditorPart().getViewport().getView().getMouseListeners()[0]);
        button_3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Collection<INode> allNodes = workspace.getGraphFile().getGraph().getAllNodes();
				Collection<IEdge> allEdges = workspace.getGraphFile().getGraph().getAllEdges();
				for(INode in : allNodes) {
					workspace.getGraphFile().getGraph().removeNode(in);
				}
				for(IEdge ie : allEdges) {
					workspace.getGraphFile().getGraph().removeEdge(ie);
				}
				int i=1;
				ArrayList<UseCaseNode> req_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> uc_Node = new ArrayList<UseCaseNode>();
				ArrayList<UseCaseNode> op_Node = new ArrayList<UseCaseNode>();
				ArrayList<String> req_name = new ArrayList<String>(req.getAllName());
		        ArrayList<String> uName = new ArrayList<String>(req.getAlluName());
		        for(String tmp_name : uName) {
		        	System.out.println(tmp_name);
		        }
				for(String tmp_req : req_name) {
		        	UseCaseNode tmp_reqnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_req);
		        	tmp_reqnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(10.0, i*10.0);
		        	req_Node.add(tmp_reqnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_reqnode, tmp_xy);
		        	i=i+5;
		        }
		        i=1;
				for(UseCase tmp_uc : uc) {
		        	UseCaseNode tmp_ucnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_uc.getName());
		        	tmp_ucnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(200.0, i*10.0);
		        	uc_Node.add(tmp_ucnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_ucnode, tmp_xy);
		        	i=i+5;
		        }
		        i=1;
		        for(SystemOperation tmp_op : op) {
		        	UseCaseNode tmp_opnode = new UseCaseNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_op.getName());
		        	tmp_opnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(380.0, i*10.0);
		        	op_Node.add(tmp_opnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_opnode, tmp_xy);
		        	i=i+5;
		        }
		        for(UseCaseNode tmp_node : req_Node) {
		        	for(int index=0; index<req_name.size(); index++) {
		        		if(req_name.get(index).equals(tmp_node.getName().toString())){
		        			String related_uc = req.getuName(index);
		        			for(UseCaseNode uc_nd : uc_Node) {
		        				if(uc_nd.getName().toString().equals(related_uc)) {
		        					InteractionEdge ie_tmp = new InteractionEdge();
		        					workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocation(), uc_nd, uc_nd.getLocation(), null);
		        				}
		        			}
		        		}
		        	}	
		        }
		        for(UseCaseNode uc_node : uc_Node) {
		        	for(Graph tmp_graph : sd) {
		        		if(uc_node.getName().toString().equals(tmp_graph.getName())) {
		        			Collection<IEdge> tmp_allEdges = tmp_graph.getGraph().getGraph().getAllEdges();
		        			for(IEdge aEdge : tmp_allEdges) {
		        				if(aEdge.getClass().equals(SynchronousCallEdge.class)) {
		        					SynchronousCallEdge a =(SynchronousCallEdge)aEdge;
		        					for(UseCaseNode op_node : op_Node) {
		        						if(a.getCenterLabel().toString().equals(op_node.getName().toString())) {
		        							InteractionEdge ie_tmp = new InteractionEdge();
				        					workspace.getGraphFile().getGraph().connect(ie_tmp, uc_node, uc_node.getLocation(), op_node, op_node.getLocation(), null);
		        						}
		        					}
		        				}
		        				else if(aEdge.getClass().equals(AsynchronousCallEdge.class)) {
		        					AsynchronousCallEdge a =(AsynchronousCallEdge)aEdge;
		        					for(UseCaseNode op_node : op_Node) {
		        						if(a.getCenterLabel().toString().equals(op_node.getName().toString())) {
		        							InteractionEdge ie_tmp = new InteractionEdge();
				        					workspace.getGraphFile().getGraph().connect(ie_tmp, uc_node, uc_node.getLocation(), op_node, op_node.getLocation(), null);
		        						}
		        					}
		        				}
		        			}
		        		}
		        	}
		        }
		        wp.refreshDisplay();
			}
			
        });
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
