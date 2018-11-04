package view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.edge.IEdge;
import com.horstmann.violet.product.diagram.abstracts.node.INode;
import com.horstmann.violet.product.diagram.classes.ClassDiagramGraph;
import com.horstmann.violet.product.diagram.classes.node.ClassNode;
import com.horstmann.violet.product.diagram.sequence.edge.AsynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.edge.ReturnEdge;
import com.horstmann.violet.product.diagram.sequence.edge.SynchronousCallEdge;
import com.horstmann.violet.product.diagram.sequence.node.ActivationBarNode;
import com.horstmann.violet.product.diagram.sequence.node.CombinedFragmentNode;
import com.horstmann.violet.product.diagram.sequence.node.LifelineNode;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import Model.Edgepair;
import Model.UMLDiagram;

public class Simulation extends JFrame{
	private Workspace w;
	private WorkspacePanel wp;
	private JPanel panel;
	private JLabel label;
	private JSplitPane splitPane;
	private IGraph g;
	private ClassDiagramGraph cld;
	private ArrayList<LifelineNode> lifeNode;
	private ArrayList<ActivationBarNode> activeNode;
	private ArrayList<CombinedFragmentNode> fragmentNode;
	
	private ArrayList<IEdge> allEdges;
	private ArrayList<AsynchronousCallEdge> AsyncEdge;
	private ArrayList<SynchronousCallEdge> syncEdge;
	private ArrayList<ReturnEdge> rtEdge;
	private ArrayList<Edgepair> ep;
	private ArrayList<String> allMethod;
	
	public Simulation(UMLDiagram graph, UMLDiagram cd) {
		setTitle("Simulation");
		setBounds(100, 100, 1300, 617);
		setLocationRelativeTo(null);
		
		setLocation((int)getLocation().getX()+10, (int)getLocation().getY()+10);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
		splitPane = new JSplitPane();
		//splitPane.disable();
		
		add(splitPane);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		if(graph != null) {
			w = new Workspace(graph.getGraph());
			wp = w.getAWTComponent();
			w.getEditorPart().changeZoom(-3);
			splitPane.setLeftComponent(wp.getScrollableEditorPart());
		}
		
		
		
		splitPane.setRightComponent(panel);
		
		splitPane.setDividerLocation(1000);
		setVisible(true);
		
		allMethod = new ArrayList<String>();
		this.cld=(ClassDiagramGraph)cd.getGraph().getGraph();
		
		Collection<INode> allClass = cld.getAllNodes();
		
		for(INode tmp : allClass) {
			if(tmp.getClass().equals(ClassNode.class)) {
				ClassNode c = (ClassNode) tmp;
				System.out.println(c.getMethods().toString());
				StringTokenizer t = new StringTokenizer(c.getMethods().toString(), "|");
				for (int j = 0;t.hasMoreTokens(); j++) {
					String tk = t.nextToken();
					allMethod.add(tk);
				}
			}
		}
				
		g = w.getGraphFile().getGraph();
		Collection<INode> allNodes = g.getAllNodes();
		lifeNode = new ArrayList<>();
		activeNode = new ArrayList<>();
		fragmentNode = new ArrayList<>();
		for(INode n : allNodes) {
			if(n.getClass().equals(LifelineNode.class)) {
				lifeNode.add((LifelineNode) n);
			}
			else if(n.getClass().equals(ActivationBarNode.class)) {
				activeNode.add((ActivationBarNode) n);
			}
			else if(n.getClass().equals(CombinedFragmentNode.class)) {
				fragmentNode.add((CombinedFragmentNode) n);
			}
		}
		
		Collections.sort(lifeNode, new Comparator<LifelineNode>() {

			@Override
			public int compare(LifelineNode o1, LifelineNode o2) {
				// TODO Auto-generated method stub
				return o1.getLocation().getX() > o2.getLocation().getX() ? 1 : (o1.getLocation().getX() < o2.getLocation().getX() ? -1 : 0);
			}
			
		});
		for(LifelineNode b : lifeNode) {
			System.out.println(b.getType() + " " + b.getId() + " " + b.getLocationOnGraph()+ " " + b.getLocation());
		}
		Collections.sort(activeNode, new Comparator<ActivationBarNode>() {

			@Override
			public int compare(ActivationBarNode o1, ActivationBarNode o2) {
				// TODO Auto-generated method stub
				double x1 = o1.getLocationOnGraph().getX();
				double x2 = o2.getLocationOnGraph().getX();
				double y1 = o1.getLocationOnGraph().getY();
				double y2 = o2.getLocationOnGraph().getY();
				int ret = 0;
				if(x1 < x2) {
					ret =  -1;
				}
				if(x1 == x2) {
					if(y1 < y2) {
						ret = -1;
					}
					else if( y1 == y2 ) {
						ret = 0;
					}
					else if(y1 > y2) {
						ret = 1;
					}
				}
				if(x1 > x2) {
					ret = 1;
				}
				return ret;
			}
			
		});
//		for(ActivationBarNode a : activeNode) {
//			System.out.println("parent : " + a.getParent().getId() +" "+ a.getLocationOnGraph().getX() + " " + a.getLocationOnGraph().getY());
//		}
		Collection<IEdge> Edges = g.getAllEdges();
		allEdges = new ArrayList<>();
		allEdges.addAll(Edges);
		Collections.sort(allEdges, new Comparator<IEdge>() {

			@Override
			public int compare(IEdge o1, IEdge o2) {
				// TODO Auto-generated method stub
				return o1.getStartLocationOnGraph().getY() > o2.getStartLocationOnGraph().getY() ? 1 : (o1.getStartLocationOnGraph().getY() < o2.getStartLocationOnGraph().getY() ? -1 : 0);
			}
			
		});
//		for(IEdge e : allEdges) {
//			if(e.getClass().equals(SynchronousCallEdge.class)) {
//				SynchronousCallEdge s = (SynchronousCallEdge) e;
//				System.out.println("start : " +s.getStartNode().getParent().getId() + " end : "
//						+ s.getEndNode().getParent().getId() + " " + s.getCenterLabel() + " " + s.getStartLocationOnGraph().getY());
//			}
//			else if(e.getClass().equals(AsynchronousCallEdge.class)) {
//				AsynchronousCallEdge s = (AsynchronousCallEdge) e;
//				System.out.println("start : " +s.getStartNode().getParent().getId() + " end : "
//						+ s.getEndNode().getParent().getId()+ " " + s.getCenterLabel() + " " + s.getStartLocationOnGraph().getY());
//			}
//			else if(e.getClass().equals(ReturnEdge.class)) {
//				ReturnEdge s = (ReturnEdge) e;
//				System.out.println("start : " +s.getStartNode().getParent().getId() + " end : "
//						+ s.getEndNode().getParent().getId()+ " " + s.getCenterLabel() + " " + s.getStartLocationOnGraph().getY());
//			}
//		}
		ep= new ArrayList<Edgepair>();
		syncEdge = new ArrayList<SynchronousCallEdge>();
		AsyncEdge = new ArrayList<AsynchronousCallEdge>();
		rtEdge = new ArrayList<ReturnEdge>();
		for(IEdge e : allEdges) {
			if(e.getClass().equals(SynchronousCallEdge.class)) {				
				SynchronousCallEdge s = (SynchronousCallEdge) e;
				ep.add(new Edgepair(s));
				syncEdge.add(s);
			}
			else if(e.getClass().equals(AsynchronousCallEdge.class)) {
				AsynchronousCallEdge s = (AsynchronousCallEdge) e;
				AsyncEdge.add(s);
			}
			else if(e.getClass().equals(ReturnEdge.class)) {
				ReturnEdge s = (ReturnEdge) e;
				for(Edgepair tmp : ep) {
					if(tmp.getSync().getStartNode().equals(s.getEndNode()) && tmp.getSync().getEndNode().equals(s.getStartNode()) ) {
						tmp.setRt(s);
						break;
					}
				}
				rtEdge.add(s);
			}
		}
		
		int i = 1;
		
		for(IEdge e : allEdges) {
			label = new JLabel();
			//label.setAlignmentX(Component.CENTER_ALIGNMENT);
			if(e.getClass().equals(SynchronousCallEdge.class)) {
				
				SynchronousCallEdge s = (SynchronousCallEdge) e;
				for(Edgepair tmp : ep) {
					if(tmp.getSync().equals(s)) {
						if(tmp.getRt()==null) {
							label.setText(i + " : " + s.getCenterLabel() + "(Sync with nothing)");
							label.setForeground(Color.RED);
						}
						else {
							label.setText(i + " : " + s.getCenterLabel() + " (Sync)");
						}
						
					}
				}
				
			}
			else if(e.getClass().equals(AsynchronousCallEdge.class)) {
				AsynchronousCallEdge s = (AsynchronousCallEdge) e;
				label.setText(i + " : " + s.getCenterLabel());
			}
			else if(e.getClass().equals(ReturnEdge.class)) {
				int exist=0;
				ReturnEdge s = (ReturnEdge) e;
				for(Edgepair tmp : ep) {
					if(tmp.getRt() == null) {
						
					}
					else if(tmp.getRt().equals(s)) {
						label.setText(i + " : " + s.getCenterLabel()+"(Sync with " + tmp.getSync().getCenterLabel() + ")");
						exist=1;
					}
				}
				if(exist==0) {
					label.setText(i + " : " + s.getCenterLabel());
				}
				
			}
			i++;
			panel.add(label);
			panel.revalidate();
			panel.repaint();
		}
		
		for(CombinedFragmentNode c : fragmentNode) {
			Point2D leftTop = c.getLocationOnGraph();
			Point2D leftBottom = new Point2D.Double();
			leftBottom.setLocation(leftTop.getX(), leftTop.getY()+c.getBounds().getHeight());
			Point2D rightTop = new Point2D.Double();
			rightTop.setLocation(leftTop.getX() + c.getBounds().getWidth(), leftTop.getY());
			Point2D rightBottom = new Point2D.Double();
			rightBottom.setLocation(leftTop.getX() + c.getBounds().getWidth(), leftTop.getY() + c.getBounds().getHeight());
			
			System.out.println(leftTop + " " + leftBottom + " " +rightTop + " " + rightBottom);
		}
	}
	
	public boolean isExist(String s) {
		
		for(String tmp : allMethod) {
			if(tmp.indexOf(s)!=-1) {
				return true;
			}
		}
		return false;
	}
	
	public void startSimulation() throws InterruptedException {
		IEdge temp = null;
		for(IEdge e : allEdges) {
			//label.setAlignmentX(Component.CENTER_ALIGNMENT);
			if(e.getClass().equals(SynchronousCallEdge.class)) {
				SynchronousCallEdge s = (SynchronousCallEdge) e;
				if(isExist(s.getCenterLabel().toString())) {
					s.getCenterLabel().setTextColor(Color.BLUE);
					temp = s;
				}
				else if(s.getCenterLabel().toString().indexOf("<<")!=-1 || s.getCenterLabel().toString().indexOf(">>")!=-1 ) {
					
				}
				else {
					s.getCenterLabel().setTextColor(Color.RED);
				}
			}
			else if(e.getClass().equals(AsynchronousCallEdge.class)) {
				AsynchronousCallEdge s = (AsynchronousCallEdge) e;
				if(isExist(s.getCenterLabel().toString())) {
					s.getCenterLabel().setTextColor(Color.BLUE);
					temp = s;
				}
				else if(s.getCenterLabel().toString().indexOf("<<")!=-1 || s.getCenterLabel().toString().indexOf(">>")!=-1 ) {
					
				}
				else {
					s.getCenterLabel().setTextColor(Color.RED);
				}
			}
			else if(e.getClass().equals(ReturnEdge.class)) {
				ReturnEdge s = (ReturnEdge) e;
				s.getCenterLabel().setTextColor(Color.BLUE);
				temp = s;
			}
			wp.refreshDisplay();
			this.revalidate();
			this.repaint();
			Thread.sleep(1000);
			if(temp != null) {
				if(temp.getClass().equals(SynchronousCallEdge.class)) {
					SynchronousCallEdge temps = (SynchronousCallEdge) temp;
					temps.getCenterLabel().setTextColor(Color.BLACK);
				}
				else if(temp.getClass().equals(AsynchronousCallEdge.class)) {
					AsynchronousCallEdge temps = (AsynchronousCallEdge) temp;
					temps.getCenterLabel().setTextColor(Color.BLACK);
				}
				else if(temp.getClass().equals(ReturnEdge.class)){
					ReturnEdge temps = (ReturnEdge) temp;
					temps.getCenterLabel().setTextColor(Color.BLACK);
				}
				
			}
			
			wp.refreshDisplay();
			this.revalidate();
			this.repaint();
		}
	}
	
	public void stopSimulation() {
		
	}
	
	
	
}
