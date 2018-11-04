package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.framework.graphics.content.Layout;

public class Oopt extends JPanel{
	Image img = null;
	Image img2 = null;

	public Oopt() {
		
		try{
			String FileName = "." + File.separator + "image" + File.separator + "oopt.png";
			File sourceimage = new File(FileName);
			img = ImageIO.read(sourceimage);
			img2 = img.getScaledInstance(500, 200, Image.SCALE_SMOOTH);

		}
		catch(IOException e){
			System.out.println("Do not exist image file ");
		}
		JLabel lblNewLabel = new JLabel(new ImageIcon(img2));
		
		this.setLayout(new BorderLayout());
		
		add(lblNewLabel);
	}
	
}
