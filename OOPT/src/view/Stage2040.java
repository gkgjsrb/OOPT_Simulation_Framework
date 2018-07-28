package view;

import javax.swing.JPanel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Stage2040 extends JPanel {

	/**
	 * Create the panel.
	 */
	Image img = null;

	public Stage2040() {
		
		try{
			File sourceimage = new File("./stage2040.png");
			img = ImageIO.read(sourceimage);
		}
		catch(IOException e){
			System.out.println("Do not exist image file ");
		}
		JLabel lblNewLabel = new JLabel(new ImageIcon(img));
		add(lblNewLabel);

	}

}
