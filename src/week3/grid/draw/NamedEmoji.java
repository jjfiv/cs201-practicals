package week3.grid.draw;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

/**
 * This class gives simple access to a handful of named emoji.
 * See a <a href="https://jjfiv.github.io/cs201-s2022/mini_emoji.html">list of
 * available emoji</a>.
 * 
 * NOTE: the images are under the Apache license.
 */
public class NamedEmoji extends Drawable {
    /**
     * This hashmap stores the emoji by name as we load them, so we only need to
     * load the images once.
     */
    static ConcurrentHashMap<String, BufferedImage> cachedEmoji = new ConcurrentHashMap<>();
    /**
     * When someone asks for an emoji we don't have, we show the red question-mark.
     */
    static String ERROR_EMOJI = "question";

    /**
     * Load the emoji image or retrieve it from cache.
     * 
     * @param name the name of the emoji image.
     * @return null if we couldn't find anything.
     */
    static BufferedImage loadEmoji(String name) {
        BufferedImage cached = cachedEmoji.get(name);
        if (cached != null) {
            return cached;
        }
        InputStream resource = NamedEmoji.class.getResourceAsStream("/mini-noto/" + name + ".png");
        if (resource == null) {
            System.err.println("No emoji for " + name);
            return null;
        }
        try {
            cached = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
        cachedEmoji.put(name, cached);
        return cached;
    }

    /**
     * The name provided.
     */
    private String name;
    /**
     * The raw image data.
     */
    private BufferedImage image;
    /**
     * How wide the emoji is, in pixels.
     */
    private int width;
    /**
     * How tall the emoji image is, in pixels.
     */
    private int height;

    /**
     * Construct a named emoji from a name string.
     * 
     * @param name - try "turtle", he's super cute.
     */
    public NamedEmoji(String name) {
        this.name = name;
        assert (name != null);
        this.image = loadEmoji(name);
        if (this.image == null) {
            this.image = loadEmoji(ERROR_EMOJI);
        }
        assert (this.image != null);
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
    }

    @Override
    public void draw(Graphics2D g, Rectangle2D destination) {
        double scale = 1.0;
        if (this.width > this.height) {
            scale = (destination.getWidth() * .9) / this.width;
        } else {
            scale = (destination.getHeight() * .9) / this.height;
        }
        int w = (int) (this.width * scale);
        int h = (int) (this.height * scale);
        int dx = (((int) destination.getWidth()) - w) / 2;
        int dy = (((int) destination.getHeight()) - h) / 2;

        g.drawImage(this.image, (int) destination.getX() + dx, (int) destination.getY() + dy,
                w, h, null);
    }

    @Override
    public String toString() {
        return this.name;
    }
}