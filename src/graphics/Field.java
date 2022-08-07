package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Field extends JDialog  {

    public Field(Hockeyframe frame, DrawPanel dp) throws IOException {
       super(frame,"game",true);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 4    , size.height / 12);


        this.add(dp);

        this.setSize(600,600);
        this.setVisible(true);




    }



}
