package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.horstmann.violet.product.diagram.state.StateDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.thoughtworks.xstream.io.StreamException;
//define state diagram
public class Activity2037 extends JTabbedPane {
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
	public Activity2037() {
		BeanInjector.getInjector().inject(this);
		Class<? extends IGraph> graphClass = new StateDiagramGraph().getClass();
		StateDiagramGraph a = new StateDiagramGraph();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        WorkspacePanel wp = workspace.getAWTComponent();
        
        
        JPanel tpanel_dd = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_save = new JButton("Save");
		JButton button_open = new JButton("Open");
		tpanel_dd.add(button_save);
		tpanel_dd.add(button_open);
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
	                    	IWorkspace workspace_tmp = new Workspace(graphFile_tmp);
		                    WorkspacePanel wp_tmp = workspace_tmp.getAWTComponent();
		                    splitPane.setBottomComponent(wp_tmp);	
	                    }

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
		
		
        addTab("Define Domain Model", null, splitPane, null);

	}

}
