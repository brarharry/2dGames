package tankrotationexample.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {

    public BreakableWall(int x, int y, BufferedImage img) {
        super(x,y,img);
    }

    @Override
    public void checkCollision() { }

    public void update(){ }

    public void setState(int x){
        state = x;
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
