package tankrotationexample.Movable;



import tankrotationexample.GameConstants;
import tankrotationexample.game.*;
import tankrotationexample.Stationary.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author anthony-pc
 */
public class Katch extends Movable {

    public int cx;
    public int cy;

    private int bulletCount = 0;
    private int bulletLimit = 80;
    private boolean LeftPressed;
    private boolean RightPressed;
    private boolean ballHit = false;
    private boolean shootPressed;
    private ArrayList<Bullet> bulls;
    private static int lives = 3;
    private Stack<Lives> live;
    private Ball ball;

    public Katch(int x, int y, int vx, int vy, float angle, BufferedImage img) {
        super(x,y,angle,img);
        this.vx = vx;
        this.vy = vy;
        this.R = 2;
        this.bulls = new ArrayList<Bullet>();
        this.live = new Stack<>();
        createBall();
        createLives();
    }

    private void createBall(){
        ball = new Ball(this.getX()+15,this.getY()-25,0, (BufferedImage) Resource.getHashMap().get("Ball"));
        RainbowReef.gameObjs.add(ball);
    }

    private void createLives() {
        for (int i=0; i < lives; i++){
            if(live.isEmpty()){
                live.push(new Lives(20,GameConstants.GAME_SCREEN_HEIGHT-52, (BufferedImage) Resource.getHashMap().get("Live")));
            }
            else {
                live.push(new Lives(live.peek().getX()+18, GameConstants.GAME_SCREEN_HEIGHT-52, (BufferedImage) Resource.getHashMap().get("Live")));
            }
        }

    }

    public static int getLives(){
        return lives;
    }

    public static void setLives(int x){
        lives = x;
    }

    void toggleRightPressed() {
        this.LeftPressed = true;
    }

    void toggleLeftPressed() {
        this.RightPressed = true;
    }

    void toggleShootPressed() {
        this.shootPressed = true;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleShootPressed() {
        this.shootPressed = false;
    }

    public void update() {

        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }

        if (this.shootPressed && RainbowReef.tick % 30 == 0) {
            //System.out.println("shoot pressed");
            this.ballHit = true;
            //this.balls.get(lives-1).update();
            if(BulletBlock.collisionDetected && bulletCount<20) {
                    Bullet bull = new Bullet(x + 15, y + 15, 270, (BufferedImage) Resource.getHashMap().get("Bullet"));
                    this.bulls.add(bull);
            }
            bulletCount++;
            bulletLimit-=4;

        }
        this.bulls.forEach(bulls -> bulls.update());
        checkCollision();
        checkBall();
        checkLives();
    }

    private void checkBall(){
        if(!ballHit) {
            this.ball.x = x + 15;
            this.ball.y = y - 25;
        }
    }

    public void checkLives(){
        if(this.ball.getHitBox().getY() > GameConstants.GAME_SCREEN_HEIGHT) {
            lives--;
            if (lives == 0) {
                Ball.dead = true;
            }
            this.ball.setX(this.getX()+15);
            this.ball.setY(this.getY()-25);
            this.ballHit = false;
        }

    }

    public boolean isRightPressed(){
        return this.LeftPressed;
    }

    public boolean isLeftPressed(){
        return this.RightPressed;
    }

    private void moveRight() {//left
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));//
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        this.hitBox.setLocation(x, y);
    }

    public void moveLeft() {//right
        super.moveLeft();
        cx = x - GameConstants.GAME_SCREEN_WIDTH / 4;
        cy = y - GameConstants.GAME_SCREEN_HEIGHT / 4;
        this.hitBox.setLocation(x, y);
        //checkScreen();
    }

    public void checkCollision() {

        for (int i = 0; i < RainbowReef.gameObjs.size(); i++) {

            if (RainbowReef.gameObjs.get(i) instanceof UnbreakableWall) {
                if (this.getHitBox().intersects(((UnbreakableWall) RainbowReef.gameObjs.get(i)).getHitBox()))  {
                    int newX = this.getX();
                    int newY = this.getY();
                    if (this.RightPressed){
                        newX += this.vx;
                        newY += this.vy;
                    }
                    else if(this.LeftPressed){
                        newX -= this.vx;
                        newY -= this.vy;
                    }
                    this.setX(newX);
                    this.setY(newY);
                }

            }
        }
    }

    public void drawImage(Graphics g) {
        super.drawImage(g);
        this.bulls.forEach(bullet -> bullet.drawImage(g));
        for(int i = 0; i < lives;i++){
            live.get(i).drawImage(g);
        }
        if(BulletBlock.collisionDetected) {
            g.setColor(Color.BLUE);
            g.fillRect(GameConstants.GAME_SCREEN_WIDTH - 120, GameConstants.GAME_SCREEN_HEIGHT - 45, bulletLimit, 5);
            g.drawRect(GameConstants.GAME_SCREEN_WIDTH - 120, GameConstants.GAME_SCREEN_HEIGHT - 45, bulletLimit, 5);
        }
    }

    public boolean isBallHit(){
        return this.ballHit;
    }

}
