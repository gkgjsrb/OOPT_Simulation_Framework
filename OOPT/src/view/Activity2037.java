package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.state.StateDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Graph;
import Model.UseCase;
//define state diagram
public class Activity2037 extends JTabbedPane {

	private IWorkspace workspace;
    private WorkspacePanel wp;  
    private JComboBox<String> combo;
    
	public Activity2037(ArrayList<Graph> std) {
		
		BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new StateDiagramGraph().getClass();
        
		IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        wp = workspace.getAWTComponent();
        
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_commit = new JButton("Save");
		combo = new JComboBox<String>();
		tpanel_dd.add(button_commit);
		tpanel_dd.add(combo);
		
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
				for(Graph tmp : std) {
					if(tmp.getName().equals(sel)) {
						workspace = new Workspace(tmp.getGraph());
						wp=workspace.getAWTComponent();
						splitPane.setBottomComponent(wp);
					}
				}
			}
		});
        
        addTab("Define State Diagrams", null, splitPane, null);
	}

	public void syncComboBox(ArrayList<UseCase> uc) {
		combo.removeAllItems();
		for(UseCase tmp : uc) {
			combo.addItem(tmp.getName());
		}
	}
}
