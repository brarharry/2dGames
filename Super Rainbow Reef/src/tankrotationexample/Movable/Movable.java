package tankrotationexample.Movable;

import tankrotationexample.GameConstants;
import tankrotationexample.game.GameObject;

import java.awt.image.BufferedImage;

public abstract class Movable extends GameObject {

    int vx;
    int vy;
    int R;

    public Movable(int x, int y, float angle, BufferedImage img) {
        super(x, y, angle, img);
    }

    public void moveLeft() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }

    public abstract void update();
}
