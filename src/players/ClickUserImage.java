package players;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickUserImage extends MouseAdapter {
    private UserPlayer userp;

    public ClickUserImage(UserPlayer up){
        userp = up;

    }

    public void mousePressed(MouseEvent evt){
        userp.setPreviousPoint(evt.getPoint());

    }

}
