package players;

import graphics.DrawPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;



import static graphics.DrawPanel.PIC_PATH;


public class Ball extends Thread{
    private BufferedImage ball_image;
    private final int IMG_WIDTH;
    private final int IMG_HEIGHT ;
    private final Point ball_points;
    private final UserPlayer userPlayer;
    private final ComputerPlayer comp;
    private int y_vector = 1; //the problem is with the vectors
    private int x_vector = 1;
    private int x_speed = 5;
    private int y_speed = 5;

    private final ImageIcon icon;
    private int userScore = 0;
    private int computerScore = 0;
    private  int compSpeed = 2;
    private DrawPanel dp;
    private boolean game = true;





    public Ball(UserPlayer userPlayer, ComputerPlayer comp, DrawPanel dp)  {
        this.userPlayer = userPlayer;
        this.comp = comp;
        this.dp =dp;
        icon = new ImageIcon("goalPic.png");



        ball_points = new Point(270,270);
        {
            try {
                ball_image = ImageIO.read(new File(PIC_PATH +"\\disk.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        IMG_WIDTH = ball_image.getWidth();
        IMG_HEIGHT = ball_image.getHeight();



    }

    public Point getBallPoints(){
        return ball_points;
    }


    public BufferedImage getBallImage(){
        return this.ball_image;
    }





    @Override
    public void run() {  //ball start moving

        while (game) {

            //goal
            if(ball_points.getX() > 190 && ball_points.getX() < 345 && ( ball_points.getY() > 468 || ball_points.getY() < 71 )) {
                //computer goal
                if (ball_points.getY() > 468)
                    computerScore++;

                    //User goal
                else if (ball_points.getY() < 71)
                    userScore++;

                if (userScore == 3) {
                    JOptionPane.showMessageDialog(null, "Good job !! You win !!", "win", JOptionPane.INFORMATION_MESSAGE);
                    game = false;
                    dp.finish();

                } else if (computerScore == 3) {
                    JOptionPane.showMessageDialog(null, "Not bad Comp win", "win", JOptionPane.INFORMATION_MESSAGE);
                    game = false;
                    dp.finish();
                }
                else {

                    JOptionPane.showMessageDialog(null, "", "Goal", JOptionPane.INFORMATION_MESSAGE, icon);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ball_points.setLocation(270, 270);
                    int[] arr = new int[]{0, -1, 1};
                    int rnd = new Random().nextInt(arr.length);
                    y_vector = arr[rnd];
                    rnd = new Random().nextInt(arr.length);
                    x_vector = arr[rnd];
                    //System.out.println(x_vector+" "+ y_vector);
                    dp.setScore(userScore, computerScore);
                    x_speed = 5;
                    y_speed = 5;
                    //System.out.println("ball: (" +ball_points.getX() +"," + ball_points.getY() +")");
                    //System.out.println("User: (" +userPlayer.getUserPoint().getX() +"," + userPlayer.getUserPoint().getY() +")\n");
                }
            }


            // work on random numbers that changes directions of the ball
            //keeping the ball in the field
            if(ball_points.getX() < 30 ){
                x_vector = 1;
                x_speed = 4;
                y_speed = 5;
            }
            if(ball_points.getX() > 525){
                x_vector = -1;
                x_speed = 5;
                y_speed = 4;
            }

            if(ball_points.getY() > 470 ) {
                y_vector = -1;
                x_speed = 6;
                y_speed = 5;

            }
            if(ball_points.getY() < 70){
                y_vector = 1;
                x_speed = 5;
                y_speed = 6;
            }



            try {
                Thread.sleep(20);

            } catch (InterruptedException ignored) { }



            //strike of user forward
            if (Math.abs(userPlayer.getUserPoint().getX() - ball_points.getX()) < 15 &&
                    Math.abs(userPlayer.getUserPoint().getY() - ball_points.getY()) < 20)
            {

                if(userPlayer.getUserPoint().getY()  >= ball_points.getY())
                    this.y_vector = -1;
                else
                    this.y_vector = 1;

                if(userPlayer.getUserPoint().getX() < ball_points.getX())
                    this.x_vector = 1;
                else
                    this.x_vector = -1;

                y_speed += 1;
                x_speed += 1;

                if(Math.abs(userPlayer.getUserPoint().getX() - ball_points.getX()) < 2)
                    x_speed = 0;

            }



            //strike of computer forward
            if(Math.abs(comp.getCompPoints().getY() - ball_points.getY()) < 20 &&
                    Math.abs(comp.getCompPoints().getX() - ball_points.getX()) < 20)
            {
                if(comp.getCompPoints().getY() >= ball_points.getY())
                    this.y_vector = -1;
                else
                    this.y_vector = 1;

                x_speed += 1;
                y_speed += 1;

                if( Math.abs(comp.getCompPoints().getX() - ball_points.getX()) < 2)
                    x_speed = 0;



            }

            //strike of computer from the side
            if(Math.abs(comp.getCompPoints().getX() - ball_points.getX()) < 10 &&
                    Math.abs(comp.getCompPoints().getY() - ball_points.getY()) < 10)
            {
                //shoot from left
                if(comp.getCompPoints().getX() <= ball_points.getX())
                    this.x_vector = 1;
                else
                    this.x_vector = -1;

                x_speed += 1;
                y_speed += 1;

            }

            //the computer move by ball movement
            if(ball_points.getY() >= comp.getCompPoints().getY() )
            {
                if(ball_points.getX() > comp.getCompPoints().getX())
                    comp.setCompXPoint(compSpeed);

                else if(ball_points.getX() == comp.getCompPoints().getX())
                {
                    comp.setCompYPoint(2);
                }

                else
                    comp.setCompXPoint(-compSpeed);
            }



            //the ball in computer field
            if(ball_points.getY() <= 270 )
            {

                //System.out.println("ball: (" +ball_points.getX() +"," + ball_points.getY() +")");
                if(ball_points.getY() > comp.getCompPoints().getY())
                    comp.setCompYPoint(compSpeed);
                else
                    comp.setCompYPoint(-compSpeed);
            }
            else if(comp.getCompPoints().getY() > 51)
                comp.setCompYPoint(-compSpeed);




            ball_points.setLocation(ball_points.getX() + (x_vector * x_speed), ball_points.getY() + (y_vector * y_speed));
           // System.out.println("Comp: (" +comp.getCompPoints().getX() +"," + comp.getCompPoints().getY() +")");
           // System.out.println("ball: (" +ball_points.getX() +"," + ball_points.getY() +")");
            dp.repaint();






        } //while true

        }

        public void setDifficult(int speed)
        {
            this.compSpeed = speed;
        }

        public void stopGame()
        {
            this.game = false;
        }







}





