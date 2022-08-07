package hockey_game;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is for the first window that provide the background picture
 */

public class DrawPictures extends JPanel {
    private BufferedImage img = null;
    public static final String PIC_PATH = "C:\\Users\\omerh\\IdeaProjects\\iceHockey";
    private String pic = "\\hockeypic.jpg";




    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try
        {

                img = ImageIO.read(new File(PIC_PATH + pic));
                Dimension size = this.getSize();
                gr.drawImage(img, 0, 0, size.width, size.height, this);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }


}
