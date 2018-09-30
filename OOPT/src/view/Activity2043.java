package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.classes.ClassDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Datainfo;
import Model.Graph;
//refine system architecture
public class Activity2043 extends JTabbedPane {
	IWorkspace workspace;
	WorkspacePanel wp;
	JSplitPane splitPane;
	public Activity2043(Graph sa, Datainfo data) {
		//BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new ClassDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        sa.setGraph(graphFile);
        workspace = new Workspace(sa.getGraph());
        wp = workspace.getAWTComponent();
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_commit = new JButton("Commit");
		tpanel_dd.add(button_commit);
		splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(tpanel_dd);
		
        button_commit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sa.setGraph(workspace.getGraphFile());
        		sa.setName("sa");
				data.syncGraph("sa","");
				data.setGraph("sa", sa);
			}
		});
       
        addTab("Refine System Architecture", null, splitPane, null);
    }
	
	public void save(Datainfo data, Graph sa) {
		data.syncGraph("sa","");
		data.setGraph("sa", sa);
	}
	
	public void open(Graph sa) {
		workspace = new Workspace(sa.getGraph());
		wp = workspace.getAWTComponent();
		splitPane.setBottomComponent(wp);
	}
}
