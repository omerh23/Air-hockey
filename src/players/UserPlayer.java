package players;

import hockey_game.Hockeyframe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static hockey_game.DrawPictures.PIC_PATH;


public class UserPlayer extends JPanel {

    private BufferedImage image_player;
    private final int IMG_WIDTH ;
    private final int IMG_HEIGHT ;
    private Point userPointImage;
    private Point previousPoint;
    private JLabel scoreLabel;
    private int userScore = 0;
    private int computerScore = 0;
    private String nickName;
    private Ball ball;
    private ComputerPlayer comp;


    public UserPlayer(Hockeyframe frame)  {
        {
            try {
                image_player = ImageIO.read(new File(PIC_PATH + "\\player.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            IMG_WIDTH = image_player.getWidth();
            IMG_HEIGHT = image_player.getHeight();
            userPointImage = new Point(270, 440);
            ball = new Ball(this, comp = new ComputerPlayer(),frame);
            comp.start();
            ClickUserImage clickListener = new ClickUserImage(this);
            this.addMouseListener(clickListener);
            DragImage drag = new DragImage(this, ball,comp);
            this.addMouseMotionListener(drag);

        }




    }
    public void setNickName(String nm){
        nickName = nm;
        this.scoreLabel = new JLabel(nm+": " + userScore + "  computer: " + userScore );
        this.add(scoreLabel);
        this.setVisible(true);
    }

    public void setScore(int userScore , int computerScore ){
        scoreLabel.setText(nickName+": " + userScore + "  computer: " + computerScore);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);




        try {

            BufferedImage img = ImageIO.read(new File(PIC_PATH+"\\field.png"));
            Dimension size = this.getSize();
            gr.drawImage(img, 0, 0, size.width, size.height, this);
            gr.drawImage(this.image_player, (int) userPointImage.getX(), (int) userPointImage.getY(), 50, 50, this);
            gr.drawImage(comp.computerImage(), (int) comp.getCompPoints().getX(), (int) comp.getCompPoints().getY(), 50, 50, this);
            gr.drawImage(ball.getBallImage(), (int)ball.getBallPoints().getX(), (int) ball.getBallPoints().getY(), 40, 30, this);

        }
        catch (IOException e) {
            e.printStackTrace();
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





}
