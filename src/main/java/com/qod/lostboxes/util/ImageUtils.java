package com.qod.lostboxes.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    /**
     * Converts Image to BufferedImage
     * @param img image to be converted
     * @return buffered image from {@code img}
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) return (BufferedImage) img;

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bufferedImage;
    }
}
