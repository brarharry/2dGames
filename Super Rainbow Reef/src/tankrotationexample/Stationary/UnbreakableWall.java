package tankrotationexample.Stationary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {

    private int state = 0;

    public UnbreakableWall(int x, int y, BufferedImage img) {
        super(x,y,img);
    }

    @Override
    public void checkCollision() {

    }

    public void setState(int x){
        state = x;
    }

    public void update(){
    }

    public void drawImage(Graphics g){
        if (state == 0) {
            super.drawImage(g);
        }
    }
}
