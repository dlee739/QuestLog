import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



//Source: https://www.edureka.co/community/5470/how-can-we-add-an-image-to-a-jpanel
/***** Custom Image Panel for Displaying Item images *****/
public class ImagePanel extends JPanel{

    private Image image;

    public ImagePanel(String filename) {
       try {                
          image = ImageIO.read(new File(filename));
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    public void setImage(String filename) {
        try {                
            image = ImageIO.read(new File(filename));
         } catch (IOException ex) {
         }
        Dimension size = new Dimension(this.getWidth(), this.getHeight());
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        this.setSize(size);
        this.setOpaque(false);
        }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}