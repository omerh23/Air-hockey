package players;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static hockey_game.DrawPictures.PIC_PATH;


public class ComputerPlayer extends Thread{

    private BufferedImage comp_image;
    private final int IMG_WIDTH;
    private final int IMG_HEIGHT ;
    private Point comp_points;

    public ComputerPlayer()
    {
        comp_points = new Point(270,60);
        {
            try {
                comp_image = ImageIO.read(new File(PIC_PATH +"\\comp.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        IMG_WIDTH = comp_image.getWidth();
        IMG_HEIGHT = comp_image.getHeight();

    }

    public BufferedImage computerImage(){
        return this.comp_image;
    }

    public Point getCompPoints(){
        return this.comp_points;
    }

    public void setCompXPoint(int x)
    {
        this.comp_points.x += x ;
    }

    public void setCompYPoint(int y)
    {
        this.comp_points.y += y ;
    }






    @Override
    public void run()
    {


    }
}
