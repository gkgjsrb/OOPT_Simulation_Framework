package view;

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
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.edge.AssociationEdge;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.property.text.SingleLineText;
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.horstmann.violet.workspace.editorpart.IEditorPart;

import Model.Requirement;
import Model.SystemOperation;
import Model.UMLDiagram;
import Model.UseCase;
//traceability
public class Activity2039 extends JTabbedPane {

	private IEditorPart editorPart;
    private WorkspacePanel wp;
    
    public Activity2039(Requirement req, ArrayList<UseCase> uc, ArrayList<SystemOperation> op, ArrayList<UMLDiagram> sd) {
					
		JSplitPane splitPane = new JSplitPane();
		JPanel jpanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_1 = new JButton("+");
		JButton button_2 = new JButton("-");
		JButton button_3 = new JButton("Refresh");
		jpanel.add(button_1);
		jpanel.add(button_2);
		jpanel.add(button_3);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        
        this.editorPart = workspace.getEditorPart();
        GraphEditor ge = new GraphEditor(editorPart);
        
        wp = workspace.getAWTComponent();
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
            	ge.highlight(event);
            	wp.refreshDisplay();
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
        button_3.addActionListener(new ActionListener() {

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
				ArrayList<ClassNode> req_Node = new ArrayList<>();
				ArrayList<ClassNode> uc_Node = new ArrayList<>();
				ArrayList<ClassNode> op_Node = new ArrayList<>();
				ArrayList<String> req_name = new ArrayList<String>(req.getAllName());
		        ArrayList<String> uName = new ArrayList<String>(req.getAlluName());
		        
				for(String tmp_req : req_name) {
					ClassNode tmp_reqnode = new ClassNode();
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
					ClassNode tmp_ucnode = new ClassNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_uc.getName());
		        	tmp_ucnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(400.0, i*10.0);
		        	uc_Node.add(tmp_ucnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_ucnode, tmp_xy);
		        	i=i+5;
		        }
		        i=1;
		        for(SystemOperation tmp_op : op) {
		        	ClassNode tmp_opnode = new ClassNode();
		        	SingleLineText tmp_name = new SingleLineText();
		        	tmp_name.setText(tmp_op.getName());
		        	tmp_opnode.setName(tmp_name);
		        	Point2D tmp_xy = new Point2D.Double(1200.0, i*10.0);
		        	op_Node.add(tmp_opnode);
		        	workspace.getGraphFile().getGraph().addNode(tmp_opnode, tmp_xy);
		        	i=i+5;
		        }
		        for(ClassNode tmp_node : req_Node) {
		        	for(int index=0; index<req_name.size(); index++) {
		        		if(req_name.get(index).equals(tmp_node.getName().toString())){
		        			String related_uc = req.getuName(index);
		        			for(ClassNode uc_nd : uc_Node) {
		        				if(uc_nd.getName().toString().equals(related_uc)) {
		        					AssociationEdge ie_tmp = new AssociationEdge();
		        					ge.changeEdge(ie_tmp); 
		        					workspace.getGraphFile().getGraph().connect(ie_tmp, tmp_node, tmp_node.getLocationOnGraph(), uc_nd, uc_nd.getLocationOnGraph(), null);
		        				}
		        			}
		        		}
		        	}	
		        }
		        for(ClassNode uc_node : uc_Node) {
		        	for(UMLDiagram tmp_graph : sd) {
		        		if(uc_node.getName().toString().equals(tmp_graph.getName())) {
		        			Collection<IEdge> tmp_allEdges = tmp_graph.getGraph().getGraph().getAllEdges();
		        			for(IEdge aEdge : tmp_allEdges) {
		        				if(aEdge.getClass().equals(SynchronousCallEdge.class)) {
		        					SynchronousCallEdge a =(SynchronousCallEdge)aEdge;
		        					for(ClassNode op_node : op_Node) {
		        						if(a.getCenterLabel().toString().equals(op_node.getName().toString())) {
		        							AssociationEdge ie_tmp = new AssociationEdge();
		        							for(SystemOperation so : op) {
		        								if(so.getName().equals(op_node.getName().toString())) {
		        									SingleLineText tmp_name = new SingleLineText();
		        									tmp_name.setText(so.getOp_name());
		        						        	op_node.setName(tmp_name);
		        								}
		        							}
		        							
		        							ge.changeEdge(ie_tmp);
				        					workspace.getGraphFile().getGraph().connect(ie_tmp, uc_node, uc_node.getLocationOnGraph(), op_node, op_node.getLocationOnGraph(), null);
		        						}
		        					}
		        				}
		        				else if(aEdge.getClass().equals(AsynchronousCallEdge.class)) {
		        					AsynchronousCallEdge a =(AsynchronousCallEdge)aEdge;
		        					for(ClassNode op_node : op_Node) {
		        						if(a.getCenterLabel().toString().equals(op_node.getName().toString())) {
		        							AssociationEdge ie_tmp = new AssociationEdge();
		        							for(SystemOperation so : op) {
		        								if(so.getName().equals(op_node.getName().toString())) {
		        									SingleLineText tmp_name = new SingleLineText();
		        									tmp_name.setText(so.getOp_name());
		        						        	op_node.setName(tmp_name);
		        								}
		        							}
		        							ge.changeEdge(ie_tmp);
				        					workspace.getGraphFile().getGraph().connect(ie_tmp, uc_node, uc_node.getLocationOnGraph(), op_node, op_node.getLocationOnGraph(), null);
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
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(jpanel);
        addTab("Traceability Analysis", null, splitPane, null);
	}
	
}
