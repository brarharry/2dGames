package tankrotationexample.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletBlock extends Wall{

    public static boolean collisionDetected = false;

    public BulletBlock(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    @Override
    public void checkCollision() {

    }

    public void setState(int x){
        this.state = x;
        collisionDetected = true;
    }

    public void drawImage(Graphics g) {
        if (this.state == 1) {
            super.drawImage(g);
        } else {
            this.hitBox.setLocation(0, 0);
        }
    }
}
