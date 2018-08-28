package view;

import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.state.StateDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
//define state diagram
public class Activity2037 extends JTabbedPane {

	public Activity2037() {
		Class<? extends IGraph> graphClass = new StateDiagramGraph().getClass();
		StateDiagramGraph a = new StateDiagramGraph();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        WorkspacePanel wp = workspace.getAWTComponent();
        addTab("Define Domain Model", null, wp, null);
	}

}
