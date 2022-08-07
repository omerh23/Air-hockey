package players;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragImage extends MouseMotionAdapter {
    private UserPlayer userp;
    private Ball ball;
    private ComputerPlayer comp;
    private boolean start = true;

    public DragImage(UserPlayer up, Ball ball, ComputerPlayer comp){
        this.userp = up;
        this.ball = ball;
        this.comp = comp;

    }



    public void mouseDragged(MouseEvent evt){

        Point currentPoint = evt.getPoint();

        if(start)
        {
            ball.start();
            start = false;
        }




        if(currentPoint.getY() <= 270 || currentPoint.getY() >= 490 || currentPoint.getX() <= 30 || currentPoint.getX() >= 545)
            userp.getUserPoint().translate(0, 0);



        else {
            userp.getUserPoint().translate((int) (currentPoint.getX() - userp.getPreviousPoint().getX()), (int) (currentPoint.getY() - userp.getPreviousPoint().getY()));
            userp.setPreviousPoint(currentPoint);
        }


       // System.out.println("x:"+(int)userp.getUserPoint().getX() + " y:"+(int)userp.getUserPoint().getY());
        userp.repaint();
    }
}
