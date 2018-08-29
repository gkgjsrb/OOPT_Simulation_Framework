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
import com.horstmann.violet.product.diagram.usecase.UseCaseDiagramGraph;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;
import com.thoughtworks.xstream.io.StreamException;
//refine use case diagram
public class Activity2032 extends JTabbedPane {
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
    
	public Activity2032() {
		
		BeanInjector.getInjector().inject(this);

		JSplitPane splitPane_6 = new JSplitPane();
		JPanel jpanel_6 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JButton button_14 = new JButton("Save");
		JButton button_15 = new JButton("Open");
		jpanel_6.add(button_14);
		jpanel_6.add(button_15);
		
		Class<? extends IGraph> graphClass = new UseCaseDiagramGraph().getClass();
        IGraphFile graphFile = new GraphFile(graphClass);
        IWorkspace workspace = new Workspace(graphFile);
        WorkspacePanel wp = workspace.getAWTComponent();
        
        button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				workspace.getGraphFile().save();
			}
		});
		button_15.addActionListener(new ActionListener()
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
		                    splitPane_6.setBottomComponent(wp_tmp);	
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
		if(fileChooserService==null) button_15.setEnabled(false);
		
        splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane_6.setBottomComponent(wp);
        splitPane_6.setTopComponent(jpanel_6);
        addTab("Refine Use Case Diagrams", null, splitPane_6, null);
	}

}
