package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.ClassDiagramGraph;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Datainfo;
import Model.Graph;
import Model.MethodDescription;
//define design class diagram
public class Activity2045 extends JTabbedPane {
	IWorkspace workspace;
	WorkspacePanel wp;
	JSplitPane splitPane;
   
	public Activity2045(Graph cd, ArrayList<MethodDescription> md, Datainfo data) {
		//BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new ClassDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        cd.setGraph(graphFile);
        workspace = new Workspace(cd.getGraph());
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
				cd.setGraph(workspace.getGraphFile());
				cd.setName("class");
				data.syncGraph("cd","");
				data.setGraph("cd", cd);
				md.clear();
				Collection<INode> node = cd.getGraph().getGraph().getAllNodes();
				for(INode s : node) {
					ClassNode c = (ClassNode) s;
					MethodDescription cn = new MethodDescription();
					cn.setType("Class");
					cn.setName(c.getName().toString());
					md.add(cn);
					StringTokenizer t = new StringTokenizer(c.getMethods().toString(), "|");
					for(int i = 0; t.hasMoreTokens(); i++) {
						//System.out.println(t.nextToken());
						MethodDescription m = new MethodDescription();
						m.setType("Method");
						m.setName(t.nextToken());
						md.add(m);
					}
					
				}
				
			}
		});
       
        addTab("Define Design Class Diagrams", null, splitPane, null);
    }
	
	public void save(Datainfo data, Graph cd) {
		data.syncGraph("cd","");
		data.setGraph("cd", cd);
	}
	
	public void open(Graph cd) {
		workspace = new Workspace(cd.getGraph());
		wp = workspace.getAWTComponent();
		splitPane.setBottomComponent(wp);
	}
}
