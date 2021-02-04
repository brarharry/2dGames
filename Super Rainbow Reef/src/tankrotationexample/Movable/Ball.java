package tankrotationexample.Movable;


import tankrotationexample.Stationary.*;

import tankrotationexample.game.GameObject;
import tankrotationexample.game.RainbowReef;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ball extends Movable{

    private int R;
    public static boolean dead,won;
    private float direction = 225;
    private Rectangle topRect,bottomRect,leftRect,rightRect;

    public Ball(int x, int y, float angle, BufferedImage img) {
        super(x, y, angle, img);
        this.R = 2;
    }

    @Override
    public void moveLeft() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(direction)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(direction)));
        x += vx;
        y += vy;
        this.hitBox.setLocation(x, y);
        sides();
    }

    public void sides(){
        topRect = new Rectangle(x+4,y+4,img.getWidth()-10,2);
        bottomRect = new Rectangle(x+5,y+img.getHeight()-4,img.getWidth()-11,2);
        leftRect = new Rectangle(x+1,y+6,2,img.getHeight()-12);
        rightRect = new Rectangle(x+img.getWidth()-6,y+6,2,img.getHeight()-12);
    }

    @Override
    public void update() {
        this.moveLeft();
        checkCollision();
    }

    public void checkCollision(){
        for(int i = 0; i < RainbowReef.gameObjs.size(); i++){
            if(RainbowReef.gameObjs.get(i) instanceof Stationary){
                //checks the right sided collisions
                if(this.rightRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==315){
                    direction = 225;
                   check(RainbowReef.gameObjs.get(i));

                }
                else if(this.rightRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==45){
                    direction = 135;
                    check(RainbowReef.gameObjs.get(i));

                }

                //checks the top sided collisions
                if(this.topRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==225){
                    direction = 135;
                    check(RainbowReef.gameObjs.get(i));

                }
                else if(this.topRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==315){
                    direction = 45;
                    check(RainbowReef.gameObjs.get(i));

                }

                //checks the left sided collisions
                if(this.leftRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==135){
                    direction = 45;
                    check(RainbowReef.gameObjs.get(i));

                }
                else if(this.leftRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==225){
                    direction = 315;
                    check(RainbowReef.gameObjs.get(i));

                }

                //checks the bottom sided collisions
                if(this.bottomRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==45){
                    direction = 315;
                    check(RainbowReef.gameObjs.get(i));

                }
                else if(this.bottomRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && direction==135){
                    direction = 225;
                    check(RainbowReef.gameObjs.get(i));

                }
            }

            //checks collision with the katch
            if(RainbowReef.gameObjs.get(i) instanceof Katch){
                if(this.bottomRect.getBounds().intersects(RainbowReef.gameObjs.get(i).getHitBox()) && ((Katch) RainbowReef.gameObjs.get(i)).isBallHit()){
                    if (direction == 45){
                        if(((Katch) RainbowReef.gameObjs.get(i)).isLeftPressed()){
                            direction = 225;
                        }
                        else {
                            direction = 315;
                        }
                    }
                    else if(direction == 135){
                        if(((Katch) RainbowReef.gameObjs.get(i)).isRightPressed()){
                            direction = 315;
                        }
                        else{
                            direction = 225;
                        }
                    }
                }
            }
        }
    }

    public void check(GameObject ob){
        try{
            if(ob instanceof BreakableWall){
                ((BreakableWall) ob).setState(0);
            }
            else if(ob instanceof Goblin){
                ((Goblin) ob).setState(0);
                won = true;
            }
            else if(ob instanceof Health){
                ((Health) ob).setState(0);
            }
            else if(ob instanceof BulletBlock){
                ((BulletBlock) ob).setState(0);
            }
        }
        catch (ClassCastException ex){
            System.out.println("here");
        }
    }

    @Override
    public void drawImage(Graphics g) {
        super.drawImage(g);
//        g.setColor(Color.RED);
//        g.drawRect(x+4,y+4,img.getWidth()-10,2);
//        g.setColor(Color.RED);
//        g.drawRect(x+img.getWidth()-6,y+6,2,img.getHeight()-12);
//        g.setColor(Color.RED);
//        g.drawRect(x+5,y+img.getHeight()-4,img.getWidth()-11,2);
//        g.setColor(Color.RED);
//        g.drawRect(x+1,y+6,2,img.getHeight()-12);
    }

    public Rectangle getHitBox(){
        return this.hitBox.getBounds();
    }


}
