package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
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

import Model.SystemOperation;
//define system sequence diagram
public class Activity2035 extends JTabbedPane {
	
	@InjectedBean
	private FileNamingService fileNamingService;
	@InjectedBean
	private IFileChooserService fileChooserService;
	@InjectedBean
	private DialogFactory dialogFactory;
	@ResourceBundleBean(key = "dialog.open_file_failed.text")
	private String dialogOpenFileErrorMessage;
    @ResourceBundleBean(key = "dialog.open_file_content_incompatibility.text")
    private String dialogOpenFileIncompatibilityMessage;
    
    private IWorkspace workspace;
    private WorkspacePanel wp;
    
	public Activity2035(ArrayList<SystemOperation> op) {
		
		BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new SequenceDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        workspace = new Workspace(graphFile);
        wp = workspace.getAWTComponent();
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_save = new JButton("Save");
		JButton button_open = new JButton("Open");
		JButton button_commit = new JButton("Commit");
		tpanel_dd.add(button_save);
		tpanel_dd.add(button_open);
		tpanel_dd.add(button_commit);
		JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBottomComponent(wp);
        splitPane.setTopComponent(tpanel_dd);
		
        button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getGraphFile().save();
			}
		});
		button_open.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent event)
	            {
	            	IFile selectedFile = null;
	                try
	                {
	                    ExtensionFilter[] filters = fileNamingService.getFileFilters();
	                    IFileReader fileOpener = fileChooserService.chooseAndGetFileReader(filters);
	                    if (fileOpener == null)
	                    {
	                        // Action cancelled by user
	                        return;
	                    }
	                    selectedFile = fileOpener.getFileDefinition();
	                    IGraphFile graphFile_tmp = new GraphFile(selectedFile);
	                    if(graphFile_tmp.getGraph().getClass().equals(graphClass)) {
	                    	workspace = new Workspace(graphFile_tmp);
		                    wp = workspace.getAWTComponent();
		                    splitPane.setBottomComponent(wp);	
	                    }
	                    //if diffrent show dialog
	                }
	                catch (StreamException se)
	                {
	                    dialogFactory.showErrorDialog(dialogOpenFileIncompatibilityMessage);
	                }
	                catch (Exception e)
	                {
	                    dialogFactory.showErrorDialog(dialogOpenFileErrorMessage + " : " + e.getMessage());
	                }
	            }
	    });
		if(fileChooserService==null) button_open.setEnabled(false);
		button_commit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Collection<IEdge> allEdges = workspace.getGraphFile().getGraph().getAllEdges();
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
				}
				ArrayList<SystemOperation> tmp_list = new ArrayList();
				for(SystemOperation tmp : op) {
					for(IEdge aEdge : allEdges) {
						if(tmp.getId().equals(aEdge.getId())) {
							tmp_list.add(tmp);
						}
					}
				}
				op.clear();
				op.addAll(tmp_list);
			}
			
		});
		
        addTab("Define System Sequence Diagrams", null, splitPane, null);
	}

}
