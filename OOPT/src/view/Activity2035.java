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

import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.chooser.IFileChooserService;
import com.horstmann.violet.framework.file.naming.ExtensionFilter;
import com.horstmann.violet.framework.file.naming.FileNamingService;
import com.horstmann.violet.framework.file.persistence.IFileReader;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.sequence.SequenceDiagramGraph;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.node.ActivationBarNode;
import com.horstmann.violet.product.diagram.sequence.node.LifelineNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.thoughtworks.xstream.io.StreamException;

import Model.Graph;
import Model.SystemOperation;
import Model.UseCase;
//define system sequence diagram
public class Activity2035 extends JTabbedPane {
	
  
    private IWorkspace workspace;
    private WorkspacePanel wp;  
    private JComboBox<String> combo;
    
	public Activity2035(ArrayList<SystemOperation> op, ArrayList<Graph> sd) {
		
		BeanInjector.getInjector().inject(this);
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
				for(Graph tmp_Graph : sd) {
					Collection<IEdge> allEdges = tmp_Graph.getGraph().getGraph().getAllEdges();
					for(IEdge aEdge : allEdges) {
						int exist=0;
						if(aEdge.getClass().equals(SynchronousCallEdge.class)) {
							SynchronousCallEdge a =(SynchronousCallEdge)aEdge;
							for(SystemOperation tmp_Operation : op) {
								if(a.getId().equals(tmp_Operation.getId())) {
									tmp_Operation.setName(a.getCenterLabel().toString());
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
					for(SystemOperation tmp_Operation : op) {
						for(IEdge aEdge : allEdges) {
							if(tmp_Operation.getId().equals(aEdge.getId())) {
								tmp_list.add(tmp_Operation);
							}
						}
					}
				}
				
				op.clear();
				op.addAll(tmp_list);
			}
			
		});
		
        addTab("Define System Sequence Diagrams", null, splitPane, null);
	}
	
	public void syncComboBox(ArrayList<UseCase> uc) {
		combo.removeAllItems();
		for(UseCase tmp : uc) {
			combo.addItem(tmp.getName());
		}
	}

}
