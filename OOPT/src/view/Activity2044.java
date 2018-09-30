package view;

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
import Model.SystemOperation;
//define interaction diagram
public class Activity2044 extends JTabbedPane {

	private IWorkspace workspace;
    private WorkspacePanel wp;  
    private JComboBox<String> combo;
    
	public Activity2044(ArrayList<Graph> id, Datainfo data) {
		
		//BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        wp = workspace.getAWTComponent();
        
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        combo = new JComboBox<String>();
        JButton button_commit = new JButton("Commit");
		tpanel_dd.add(combo);
		tpanel_dd.add(button_commit);

		JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(tpanel_dd);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = combo.getSelectedIndex();
				String sel = combo.getItemAt(index);
				for(Graph tmp : id) {
					if(tmp.getName().equals(sel)) {
						workspace = new Workspace(tmp.getGraph());
						wp=workspace.getAWTComponent();
						splitPane.setBottomComponent(wp);
					}
				}
			}
		});
		
		button_commit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				data.syncGraph("id", id.get(combo.getSelectedIndex()).getId());
				data.setGraph("id", id.get(combo.getSelectedIndex()));
				
			}
			
		});
		
        addTab("Define Interaction Diagrams", null, splitPane, null);
	}
	
	public void syncComboBox(ArrayList<Graph> id) {
		combo.removeAllItems();
		for(Graph tmp : id) {
			combo.addItem(tmp.getName());
		}
	}
	
	public void save(Datainfo data, ArrayList<Graph> id) {
		
		for(Graph g : id) {
			data.syncGraph("id", g.getId());
			data.setGraph("id", g);
		}
	}
}
