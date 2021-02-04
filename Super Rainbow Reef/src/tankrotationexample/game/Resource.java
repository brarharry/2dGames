package tankrotationexample.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Resource {

    private static Map<String, BufferedImage> resources;

    static {
        Resource.resources = new HashMap<>();
        try {
            Resource.resources.put("wallpaper",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Background1.png"))));
            Resource.resources.put("Bullet",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("croppedbullet.png"))));
            Resource.resources.put("Live",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("live.png"))));
            Resource.resources.put("SolidBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Wall.gif"))));//2
            Resource.resources.put("PinkBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Block1.gif"))));//3
            Resource.resources.put("YellowBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Block2.gif"))));//4
            Resource.resources.put("RedBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Block3.gif"))));//5
            Resource.resources.put("GreenBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Block4.gif"))));//6
            Resource.resources.put("Goblin",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("goblin.png"))));//6
            Resource.resources.put("Katch",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Katch.gif"))));
            Resource.resources.put("Ball",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("BALL2.png"))));
            Resource.resources.put("HealthBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("Block_life.gif"))));
            Resource.resources.put("BulletBlock",read(Objects.requireNonNull(RainbowReef.class.getClassLoader().getResource("bigbullet.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map getHashMap(){
        return resources;
    }
}
