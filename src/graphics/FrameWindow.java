package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static graphics.DrawPanel.PIC_PATH;

public class FrameWindow extends JPanel {

    private String pic = "\\hockeypic.jpg";



    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(PIC_PATH + pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Dimension size = this.getSize();
        gr.drawImage(img, 0, 0, size.width, size.height, this);


    }
}
