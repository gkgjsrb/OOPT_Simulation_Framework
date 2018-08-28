package Model;

import java.io.File;

public class Mkdir {
    private final String autoSaveDirectory = System.getProperty("user.home") + File.separator + "VioletUML";
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
}
