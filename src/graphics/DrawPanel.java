package graphics;



import players.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is for the first window that provide the background picture
 */

public class DrawPanel extends JPanel {
    private BufferedImage img = null;
    public static final String PIC_PATH = "C:\\Users\\omerh\\IdeaProjects\\AirHockey";
    private String pic = "\\hockeypic.jpg";
    private boolean start_game = false;
    private Ball ball;
    private ComputerPlayer comp;
    private UserPlayer player;
    private String nickName;
    private JLabel scoreLabel;
    private int userScore = 0;
    private int computerScore = 0;

    public DrawPanel()
    {
        player = new UserPlayer();
        comp = new ComputerPlayer();  //maybe to create them in DrawPIctures
        ball = new Ball(player,comp,this);

        ClickUserImage clickListener = new ClickUserImage(player);
        this.addMouseListener(clickListener);
        DragImage drag = new DragImage(player, ball,comp,this);
        this.addMouseMotionListener(drag);
    }



    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try
        {

                BufferedImage img = ImageIO.read(new File(PIC_PATH+"\\field.png"));
                Dimension size = this.getSize();
                gr.drawImage(img, 0, 0, size.width, size.height, this);
                gr.drawImage(player.getPlayerImage(), (int) player.getUserPoint().getX(), (int) player.getUserPoint().getY(), 50, 50, this);
                gr.drawImage(comp.computerImage(), (int) comp.getCompPoints().getX(), (int) comp.getCompPoints().getY(), 50, 50, this);
                gr.drawImage(ball.getBallImage(), (int)ball.getBallPoints().getX(), (int) ball.getBallPoints().getY(), 40, 30, this);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    public void setNickName(String nm){
        nickName = nm;
        this.scoreLabel = new JLabel(nm+": " + userScore + "  computer: " + computerScore );
        this.add(scoreLabel);
        this.setVisible(true);
    }

    public void setScore(int userScore , int computerScore ){ //move to Ball
        scoreLabel.setText(nickName+": " + userScore + "  computer: " + computerScore);
        this.setVisible(true);
    }



}
