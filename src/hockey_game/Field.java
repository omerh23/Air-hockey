package hockey_game;

import players.UserPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Field extends JDialog  {

    public Field(Hockeyframe frame) throws IOException {
       super(frame,"game",true);
        UserPlayer player = new UserPlayer(frame);
        player.setNickName(frame.getNickName());
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 4    , size.height / 12);


        this.add(player);

        this.setSize(600,600);
        this.setVisible(true);




    }



}
