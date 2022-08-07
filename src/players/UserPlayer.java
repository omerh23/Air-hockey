package players;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static graphics.DrawPanel.PIC_PATH;


public class UserPlayer  {

    private BufferedImage image_player;
    private final int IMG_WIDTH ;
    private final int IMG_HEIGHT ;
    private Point userPointImage;
    private Point previousPoint;



    public UserPlayer()  {
        {
            try {
                image_player = ImageIO.read(new File(PIC_PATH + "\\player.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            IMG_WIDTH = image_player.getWidth();
            IMG_HEIGHT = image_player.getHeight();
            userPointImage = new Point(270, 440);


        }



    }



    public void setPreviousPoint(Point p){
         this.previousPoint = p;
    }

    public Point getUserPoint(){

        return userPointImage;
    }

    public Point getPreviousPoint(){

        return this.previousPoint;
    }

    public BufferedImage getPlayerImage(){
        return this.image_player;
    }






}
