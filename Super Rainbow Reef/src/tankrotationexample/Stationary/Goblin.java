package tankrotationexample.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goblin extends Stationary{

    public Goblin(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    @Override
    public void checkCollision() {

    }

    @Override
    public void drawImage(Graphics g) {
        if (this.state == 1) {
            super.drawImage(g);
        } else {
            this.hitBox.setLocation(0, 0);
        }
    }
}
