package Model;


import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.SplashScreen;
import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.dialog.DialogFactoryMode;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.framework.file.chooser.IFileChooserService;
import com.horstmann.violet.framework.file.chooser.JFileChooserService;
import com.horstmann.violet.framework.file.persistence.IFilePersistenceService;
import com.horstmann.violet.framework.file.persistence.XHTMLPersistenceService;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanFactory;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.plugin.PluginLoader;
import com.horstmann.violet.framework.theme.BlueAmbianceTheme;
import com.horstmann.violet.framework.theme.ClassicMetalTheme;
import com.horstmann.violet.framework.theme.DarkAmbianceTheme;
import com.horstmann.violet.framework.theme.ITheme;
import com.horstmann.violet.framework.theme.ThemeManager;
import com.horstmann.violet.framework.userpreferences.DefaultUserPreferencesDao;
import com.horstmann.violet.framework.userpreferences.IUserPreferencesDao;
import com.horstmann.violet.framework.userpreferences.UserPreferencesService;
import com.horstmann.violet.framework.util.VersionChecker;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

import javax.swing.*;

/**
 * A program for editing UML diagrams.
 */
public class UMLEditorApplication
{
    /**
     * Default constructor
     * 
     * @param filesToOpen
     */
    public UMLEditorApplication(String[] filesToOpen, JTabbedPane jp)
    {
        initBeanFactory();
        BeanInjector.getInjector().inject(this);
        createDefaultWorkspace(filesToOpen, jp);
    }
    
    private static void initBeanFactory() {
        IUserPreferencesDao userPreferencesDao = new DefaultUserPreferencesDao();
        BeanFactory.getFactory().register(IUserPreferencesDao.class, userPreferencesDao);

        ThemeManager themeManager = new ThemeManager();
        ITheme theme1 = new ClassicMetalTheme();
        ITheme theme2 = new BlueAmbianceTheme();
        ITheme theme3 = new DarkAmbianceTheme();
        List<ITheme> themeList = new ArrayList<ITheme>();
        themeList.add(theme1);
        themeList.add(theme2);
        themeList.add(theme3);
        themeManager.setInstalledThemes(themeList);
        themeManager.applyPreferedTheme();
        BeanFactory.getFactory().register(ThemeManager.class, themeManager);
        themeManager.applyPreferedTheme();

        DialogFactory dialogFactory = new DialogFactory(DialogFactoryMode.INTERNAL);
        BeanFactory.getFactory().register(DialogFactory.class, dialogFactory);
        
        IFilePersistenceService filePersistenceService = new XHTMLPersistenceService();
        BeanFactory.getFactory().register(IFilePersistenceService.class, filePersistenceService);
        
        IFileChooserService fileChooserService = new JFileChooserService();
        BeanFactory.getFactory().register(IFileChooserService.class, fileChooserService);
    }



    /**
     * Creates workspace when application works as a standalone one. It contains :<br>
     * + plugins loading + GUI theme management + a spash screen<br>
     * + jvm checking<br>
     * + command line args<br>
     * + last workspace restore<br>
     */
    
    private void createDefaultWorkspace(String[] filesToOpen, JTabbedPane jp)
    {
        installPlugins();
        //SplashScreen splashScreen = new SplashScreen();
        //splashScreen.setVisible(true);
        this.versionChecker.checkJavaVersion();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //SplashScreen.displayOverEditor(mainFrame, 1000);
        List<IFile> fullList = new ArrayList<IFile>();
        List<IFile> lastSessionFiles = this.userPreferencesService.getOpenedFilesDuringLastSession();
        fullList.addAll(lastSessionFiles);
        
        
        for (String aFileToOpen : filesToOpen)
        {
            try
            {
                LocalFile localFile = new LocalFile(new File(aFileToOpen));
                fullList.add(localFile);
            }
            catch (IOException e)
            {
                // There's nothing to do. We're starting the program
                // Some logs should be nive
                e.printStackTrace();
            }
        }
        // Open files
        for (IFile aFile : lastSessionFiles)
        {
            try
            {
                IGraphFile graphFile = new GraphFile(aFile);
                IWorkspace workspace = new Workspace(graphFile);
                WorkspacePanel wp = workspace.getAWTComponent();
                jp.addTab("test", null, wp, null);
                break;
                //mainFrame.addWorkspace(workspace);
            }
            catch (Exception e)
            {
                System.err.println("Unable to open file " + aFile.getFilename() + "from location " + aFile.getDirectory());
                userPreferencesService.removeOpenedFile(aFile);
                System.err.println("Removed from user preferences!");
            }
        }
        IFile activeFile = this.userPreferencesService.getActiveDiagramFile();
        //mainFrame.setActiveWorkspace(activeFile);
        //mainFrame.setVisible(true);
        //splashScreen.setVisible(false);
        //splashScreen.dispose();
    }

    /**
     * Install plugins
     */
    private void installPlugins()
    {

        this.pluginLoader.installPlugins();
    }


    
    @InjectedBean
    private VersionChecker versionChecker;

    @InjectedBean
    private PluginLoader pluginLoader;

    @InjectedBean
    private UserPreferencesService userPreferencesService;


}