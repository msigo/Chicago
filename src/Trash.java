import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Trash extends JPanel{

    private BufferedImage image;

    public Trash(String path) {
    	//this.addMouseMotionListener(this);

    	try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            // handle exception...
    	   System.out.println("Could not read the Image");
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0; 
        int y = 0; 

        g.drawImage(image, x, y, image.getWidth()/8,image.getHeight()/8, this);
        this.setSize(image.getWidth()/8,image.getHeight()/8);
        
    }

}