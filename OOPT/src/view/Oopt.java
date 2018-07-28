package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Oopt extends JPanel{
	Image img = null;
	public Oopt() {
		try{
			File sourceimage = new File("./oopt.png");
			img = ImageIO.read(sourceimage);
		}
		catch(IOException e){
			System.out.println("Do not exist image file ");
		}
		JLabel lblNewLabel = new JLabel(new ImageIcon(img));
		add(lblNewLabel);
	}
}
