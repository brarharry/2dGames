package tankrotationexample.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lives extends Stationary {

    private int liveHealth = 100;

    public Lives(int x, int y,  BufferedImage img) {
        super(x, y, img);
    }

    @Override
    public void checkCollision() {

    }

    @Override
    public void update() {

    }

    public void drawImage(Graphics g){
        if (this.liveHealth != 0) {
            super.drawImage(g);
        }
    }
}
