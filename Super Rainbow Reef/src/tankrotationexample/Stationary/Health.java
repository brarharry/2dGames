package tankrotationexample.Stationary;

import tankrotationexample.Movable.Katch;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health extends Wall {

    public Health(int x, int y, BufferedImage img) {
        super(x, y, img);
    }

    @Override
    public void checkCollision() {

    }

    public void setState(int x){
        state = x;
        if(Katch.getLives() != 3){
            Katch.setLives(Katch.getLives()+1);
        }
    }

    @Override
    public void drawImage(Graphics g){
        if (state == 1) {
            super.drawImage(g);
        }
        else {
            this.hitBox.setLocation(0,0);
        }
    }
}
