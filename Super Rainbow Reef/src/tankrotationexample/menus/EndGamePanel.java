package tankrotationexample.menus;

import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;
import tankrotationexample.Movable.Ball;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EndGamePanel extends JPanel {

    private BufferedImage menuBackgroundWon,menuBackgroundOver;
    private JButton exit;
    private Launcher lf;

    public EndGamePanel(Launcher lf) throws IOException {
        this.lf = lf;
        try {
                menuBackgroundOver = ImageIO.read(this.getClass().getClassLoader().getResource("gameover.png"));
                menuBackgroundWon = ImageIO.read(this.getClass().getClassLoader().getResource("Congratulation.gif"));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        exit = new JButton(new ImageIcon(ImageIO.read(this.getClass().getClassLoader().getResource("Button_quit.gif"))));
        exit.setBounds(345,350,100,32);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));

        this.add(exit);

    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;


        if(Ball.won) {
            g2.drawImage(this.menuBackgroundWon, 0, 0, null);
        }
        else{
            g2.setColor(Color.BLACK);
            g2.fillRect(0,0, GameConstants.END_MENU_SCREEN_WIDTH,GameConstants.END_MENU_SCREEN_HEIGHT);
            g2.drawImage(this.menuBackgroundOver, 390-187, GameConstants.END_MENU_SCREEN_HEIGHT/2-100, null);
        }
    }
}
