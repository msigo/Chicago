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

public class Card extends JPanel{

    private BufferedImage image;
    private int runs = 0;
    private String path;

    Card(String path) {
    	this.path = path;
    	try {                
          image = ImageIO.read(new File(System.getProperty("user.dir")+"/Playing_Cards/"+path));
       } catch (IOException ex) {
            // handle exception...
    	   System.out.println("Could not find the path to the Image");
       }
    }
    
    void changeCard(String path){
    	try {   
            image = ImageIO.read(new File(System.getProperty("user.dir")+"/Playing_Cards/"+path));
            this.path = path;
         } catch (IOException ex) {
              // handle exception...
      	   System.out.println("Could not find the path to the Image: "+ path);
         }
    }
    void showCard(boolean choise){
    	this.setVisible(choise);
    }
    
    String getPath(){
    	return this.path;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, image.getWidth()/10,image.getHeight()/10, this);
        this.setSize(image.getWidth()/10,image.getHeight()/10);
        runs++;
        
    }

}