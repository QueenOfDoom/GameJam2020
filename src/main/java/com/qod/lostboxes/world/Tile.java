package com.qod.lostboxes.world;

import com.qod.lostboxes.App;
import com.qod.lostboxes.math.vec.Vector2f;
import com.qod.lostboxes.math.vec.Vector3f;
import com.qod.lostboxes.util.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    Vector3f position;
    BufferedImage original;
    int[] pixels;

    Level root;
    String name;

    public Tile(Level root, String name, String path, Vector3f position) {
        this.root = root;
        this.name = name;
        this.original = load(path);
        this.position = position;

        scale(root.getTileSize());
        root.getLogger().debug("Loaded Pixels Array with: " + pixels.length);
    }

    private BufferedImage load(String localPath) {
        Image image = null;

        // Load Build Image
        String path = Objects.requireNonNull(ClassLoader.getSystemResource(localPath)).toString();
        path = path.substring(path.indexOf("images/"));
        image = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(path));
        if(image != null && image.getWidth(null) > 0) {
            root.getLogger().info("[BUILD] Loaded Image from " + localPath +
                    "; [W]: " + image.getWidth(null) + " [H]: " + image.getHeight(null));
        } else {
            File file = new File(Objects.requireNonNull(App.class.getClassLoader().getResource(localPath)).toString().substring(5));
            try {
                image = ImageIO.read(file);
                root.getLogger().info("[DEV] Loaded Image from " + localPath);
            } catch (IOException ioException) {
                root.getLogger().error("Failed to load Image!");
            }
        }
        return ImageUtils.toBufferedImage(image);
    }

    private void scale(int tileSize) {
        BufferedImage scaled = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        g.drawImage(original, 0, 0, tileSize, tileSize, null);
        g.dispose();
        pixels = new int[tileSize*tileSize];
        scaled.getRGB(0, 0, tileSize, tileSize, pixels, 0, tileSize);
    }

    public BufferedImage getOriginal() {
        return original;
    }

    /**
     * Obtain Pixel using 2D coordinates relative to the Center.
     * @param coordinates Coordinates relative to Tile Center to the Point
     * @return Color at the Point
     */
    public int getPixel(Vector2f coordinates) {
        int ts = root.getTileSize();
        if(2*Math.abs(coordinates.getX()) < ts && 2*Math.abs(coordinates.getY()) < ts) {
            return pixels[(int) (coordinates.getX()*coordinates.getY()) + ts*ts/2];
        } else {
            return -1;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public int[] getPixels() {
        return pixels;
    }
}
