package tankrotationexample.Movable;

import tankrotationexample.game.GameObject;
import tankrotationexample.game.RainbowReef;

import java.awt.*;
import java.awt.image.BufferedImage;
import tankrotationexample.Stationary.*;

public class Bullet extends Movable {

    boolean bulletHit = false;

    public Bullet(int x, int y, float angle, BufferedImage bulletImage) {
        super(x, y, angle, bulletImage);
        this.R = 7;
        super.hitBox= new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight());
    }

    public Rectangle getBulletHitBox() {
        return this.hitBox.getBounds();
    }

    public void moveLeft(){
        if(!bulletHit) {
            super.moveLeft();
            this.hitBox.setLocation(x, y);
            checkCollision();
        }
    }

    public void checkCollision() {
        for (int i = 0; i < RainbowReef.gameObjs.size(); i++) {
                handleCollision(RainbowReef.gameObjs.get(i));
        }
    }

    public void handleCollision(GameObject ob){
        if(this.getBulletHitBox().intersects(ob.getHitBox())){
            try{
            ((Stationary)ob).setState(0);
            bulletHit = true;
            if(ob instanceof Goblin){
                Ball.won = true;
            }
            }
            catch(ClassCastException ex){
                System.out.println();
            }


        }
    }

    public void update () {
        moveLeft();
    }

    public void drawImage (Graphics g){
        if(!bulletHit) {
            super.drawImage(g);
        }
    }


}

