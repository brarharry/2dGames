/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.game;


import tankrotationexample.GameConstants;
import tankrotationexample.Launcher;
import tankrotationexample.Movable.*;
import tankrotationexample.Stationary.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;


import static javax.imageio.ImageIO.read;

/**
 *
 * @author anthony-pc
 */
public class RainbowReef extends JPanel implements Runnable {

    private BufferedImage world;
    private BufferedImage background;
    private Launcher lf;
    public static long tick = 0;
    public static ArrayList<GameObject> gameObjs;
    public RainbowReef(Launcher lf){
        this.lf = lf;
    }

    @Override
    public void run(){
       try {
           this.gameInitialize();
           while (true) {
                this.tick++;
                this.gameObjs.forEach(obj -> obj.update());
                this.repaint();   // redraw game
                Thread.sleep(1000 / 144); //sleep for a few milliseconds
                /*
                 * simulate an end game event
                 * we will do this with by ending the game when drawn 2000 frames have been drawn
                 */
                if(Ball.dead || Ball.won){
                    this.lf.setFrame("end");
                    return;
                }
            }
       } catch (InterruptedException ignored) {
           System.out.println(ignored);
       }
    }


    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.GAME_SCREEN_WIDTH,
                                       GameConstants.GAME_SCREEN_HEIGHT,
                                       BufferedImage.TYPE_3BYTE_BGR);

        gameObjs = new ArrayList<>();
        try {
            background = (BufferedImage)Resource.getHashMap().get("wallpaper");
            /*
             * note class loaders read files from the out folder (build folder in Netbeans) and not the
             * current working directory.
             */
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResourceAsStream("map/map1")));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if (row == null) {
                throw new IOException("nothing here");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);
            for (int curRow = 0; curRow < numRows; curRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol = 0; curCol< numCols; curCol++){
                    switch (mapInfo[curCol]) {
                        case "2" -> {
                            UnbreakableWall sowall = new UnbreakableWall(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("SolidBlock"));
                            gameObjs.add(sowall);
                        }
                        case "3" -> {
                            BreakableWall pwall = new BreakableWall(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("PinkBlock"));
                            gameObjs.add(pwall);
                        }
                        case "4" -> {
                            BreakableWall ywall = new BreakableWall(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("YellowBlock"));
                            gameObjs.add(ywall);
                        }
                        case "5" -> {
                            BreakableWall rwall = new BreakableWall(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("RedBlock"));
                            gameObjs.add(rwall);
                        }
                        case "6" -> {
                            BreakableWall gwall = new BreakableWall(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("GreenBlock"));
                            gameObjs.add(gwall);
                        }
                        case "7" -> {
                            Health health = new Health(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("HealthBlock"));
                            gameObjs.add(health);
                        }
                        case "8" -> {
                            BulletBlock bigbullet = new BulletBlock(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("BulletBlock"));
                            gameObjs.add(bigbullet);
                        }
                        case "9" -> {
                            Goblin bl = new Goblin(curCol * 20, curRow * 20, (BufferedImage) Resource.getHashMap().get("Goblin"));
                            gameObjs.add(bl);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        Katch t1 = new Katch(290, 440, 0, 0, 0, (BufferedImage) Resource.getHashMap().get("Katch"));
        KatchControl tc1 = new KatchControl(t1, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE);
        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc1);
        this.gameObjs.add(t1);

    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.drawImage(this.background,0,0,null);
        this.gameObjs.forEach(obj -> obj.drawImage(buffer));
        g2.drawImage(world,0,0,null);
    }


}
