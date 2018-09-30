package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import Model.Requirement;
import Model.StageText;
import Model.UseCase;
//define domain model
public class Activity2033 extends JTabbedPane {
	
	private IWorkspace workspace;
    private WorkspacePanel wp;
    JSplitPane splitPane;
    
	public Activity2033(Graph dm, Datainfo data) {
		//BeanInjector.getInjector().inject(this);
		
		Class<? extends IGraph> graphClass = new ClassDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        dm.setGraph(graphFile);
        workspace = new Workspace(dm.getGraph());
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
        		dm.setGraph(workspace.getGraphFile());		
        		dm.setName("domain");
				data.syncGraph("dm","");
				data.setGraph("dm", dm);
        	}
		});
		
		
        addTab("Define Domain Model", null, splitPane, null);
    }
	
	public void save(Datainfo data, Graph dm) {
		data.syncGraph("dm","");
		data.setGraph("dm", dm);
	}
	
	public void open(Graph dm) {
		workspace = new Workspace(dm.getGraph());
		wp = workspace.getAWTComponent();
		splitPane.setBottomComponent(wp);
	}
}
