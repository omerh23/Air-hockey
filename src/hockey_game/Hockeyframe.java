package hockey_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Hockeyframe extends JFrame implements ActionListener {

    private  JTextField nameFeild;
    private final JButton startButton;
    private JButton levelButton;
    private int levelChoosen;
    private String nickname;

    public Hockeyframe() {
        super("Air Hockey");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.levelChoosen = 1;
        nameFeild = new JTextField();
        nameFeild.setText("username");
        startButton = new JButton("Start");
        startButton.setBackground(Color.WHITE);
        this.startButton.addActionListener(this);
        levelButton = new JButton("difficulty level");
        levelButton.setBackground(Color.white);
        this.levelButton.addActionListener(this);
        JLabel label = new JLabel("Select nickname:");
        DrawPictures panel = new DrawPictures(); //create first window
        panel.setPreferredSize(new Dimension(300,300));
        panel.add(label);
        panel.setLayout(new GridLayout(4,1,30,30));
        panel.add(nameFeild);
        panel.add(levelButton);
        panel.add(startButton);
        this.add(panel);




        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        this.setLocation(size.width / 3 , size.height / 4);
        this.pack();
        this.setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == levelButton) {
            JDialog levelDialog = new JDialog(this, "Level", true);
            JButton easyButton = new JButton("Easy");
            JButton normalButton = new JButton("Normal");
            JButton hardButton = new JButton("Hard");
            easyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    levelChoosen = 1;
                    levelDialog.dispose();
                }
            });

            normalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    levelChoosen = 2;
                    levelDialog.dispose();
                }
            });

            hardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    levelChoosen = 3;
                    levelDialog.dispose();
                }
            });

            JPanel levelButtonPanel = new JPanel();
            levelButtonPanel.setLayout(new FlowLayout());
            levelButtonPanel.add(easyButton);
            levelButtonPanel.add(normalButton);
            levelButtonPanel.add(hardButton);
            levelDialog.add(levelButtonPanel);
            Toolkit toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            levelDialog.setLocation(size.width/2 - getWidth() / 2 , size.height / 2 - getHeight() / 2);
            levelDialog.pack();
            levelDialog.setVisible(true);
            this.setVisible(true);
        }
        if(e.getSource() == startButton){

            try {
                nickname = nameFeild.getText();
                new Field(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public String getNickName(){
        return this.nickname;
    }



    public static void main(String[] args) throws IOException {
        new Hockeyframe();
    }
}
