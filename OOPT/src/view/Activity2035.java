package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.sequence.SequenceDiagramGraph;
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.ReturnEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Datainfo;
import Model.Graph;
import Model.Requirement;
import Model.SystemOperation;
import Model.UseCase;

//define system sequence diagram
public class Activity2035 extends JTabbedPane {
	
    private IWorkspace workspace;
    private WorkspacePanel wp;
    private JComboBox<String> combo;
    
	public Activity2035(ArrayList<SystemOperation> op, ArrayList<Graph> sd, Datainfo data) {
		
		//BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        wp = workspace.getAWTComponent();
        
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		JButton button_commit = new JButton("Commit");
		combo = new JComboBox<String>();
		tpanel_dd.add(combo);
		tpanel_dd.add(button_commit);
		
		JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(tpanel_dd);
		
        combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = combo.getSelectedIndex();
				String sel = combo.getItemAt(index);
				for(Graph tmp : sd) {
					if(tmp.getName().equals(sel)) {
						workspace = new Workspace(tmp.getGraph());
						wp=workspace.getAWTComponent();
						splitPane.setBottomComponent(wp);
					}
				}
			}
        	
        });
		button_commit.addActionListener(new ActionListener() {

			ArrayList<SystemOperation> tmp_list = new ArrayList<SystemOperation>();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				data.syncGraph("sd", sd.get(combo.getSelectedIndex()).getId());
				data.setGraph("sd", sd.get(combo.getSelectedIndex()));
				for(Graph tmp_Graph : sd) {
					Collection<IEdge> allEdges = tmp_Graph.getGraph().getGraph().getAllEdges();
					for(IEdge aEdge : allEdges) {
						int exist=0;
						if(aEdge.getClass().equals(SynchronousCallEdge.class)) {
							SynchronousCallEdge a =(SynchronousCallEdge)aEdge;
							for(SystemOperation tmp : op) {
								if(a.getId().equals(tmp.getId())) {
									tmp.setName(a.getCenterLabel().toString());							
									exist=1;
								}
							}
							if(exist==0) {
								SystemOperation opc = new SystemOperation();
								opc.setName(a.getCenterLabel().toString());
								opc.setId(a.getId());
								op.add(opc);
							}
						}
						else if(aEdge.getClass().equals(AsynchronousCallEdge.class)) {
							AsynchronousCallEdge a =(AsynchronousCallEdge)aEdge;
							for(SystemOperation tmp : op) {
								if(a.getId().equals(tmp.getId())) {
									tmp.setName(a.getCenterLabel().toString());
									exist=1;
								}
							}
							if(exist==0) {
								SystemOperation opc = new SystemOperation();
								opc.setName(a.getCenterLabel().toString());
								opc.setId(a.getId());
								op.add(opc);
							}
						}
						else if(aEdge.getClass().equals(ReturnEdge.class)) {
							ReturnEdge a =(ReturnEdge)aEdge;
							for(SystemOperation tmp : op) {
								if(a.getId().equals(tmp.getId())) {
									tmp.setName(a.getCenterLabel().toString());
									exist=1;
								}
							}
							if(exist==0) {
								SystemOperation opc = new SystemOperation();
								opc.setName(a.getCenterLabel().toString());
								opc.setId(a.getId());
								op.add(opc);
							}
						}
					}
					for(SystemOperation tmp : op) {
						for(IEdge aEdge : allEdges) {
							if(tmp.getId().equals(aEdge.getId())) {
								tmp_list.add(tmp);
							}
						}
					}
				}
				op.clear();
				op.addAll(tmp_list);
				tmp_list.clear();
			}
			
		});
		
        addTab("Define System Sequence Diagrams", null, splitPane, null);
	}
	
	public void syncComboBox(ArrayList<Graph> sd) {
		if(sd.size() > 0) {
			combo.removeAllItems();
			for(Graph g : sd) {
				combo.addItem(g.getName());
			}
		}
		
	}
	
	public void save(Datainfo data, ArrayList<Graph> sd) {
		
		for(Graph g : sd) {
			data.syncGraph("sd", g.getId());
			data.setGraph("sd", g);
		}
	}
}
