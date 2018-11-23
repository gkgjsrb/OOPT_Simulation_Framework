package Model;

import java.io.File;

public class Mkdir {
    private final String autoSaveDirectory = System.getProperty("user.home") + File.separator + "VioletUML";
    private String projectDirectory;
    
    public boolean createVioletDirectory()
	{
		File directory = new File(autoSaveDirectory);
		if (directory.isDirectory())
		{
			return true;
		}
		else
		{
			return directory.mkdir();
		}
	}
    public boolean initalDirectory() {
    	projectDirectory = "." + File.separator + "init";
    	File directory = new File(projectDirectory);
		if (directory.isDirectory())
		{
			File[] innerFiles = directory.listFiles();
			for(int i = 0; i < innerFiles.length; i++) {
				innerFiles[i].delete();
			}
			File diagram = new File(projectDirectory + File.separator + "diagram");
			diagram.mkdir();
			return true;
		}
		else
		{
			File diagram = new File(projectDirectory + File.separator + "diagram");
			return diagram.mkdirs();
		}
    }
    public boolean createProjectDirectory(String name) {
    	projectDirectory = "." + File.separator + name;
    	File directory = new File(projectDirectory);
		if (directory.isDirectory())
		{
			return true;
		}
		else
		{
			File diagram = new File(projectDirectory + File.separator + "diagram");
			
			return diagram.mkdirs();
		}
    }
}
