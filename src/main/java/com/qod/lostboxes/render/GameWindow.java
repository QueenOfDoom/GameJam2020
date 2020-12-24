package com.qod.lostboxes.render;

import com.qod.lostboxes.log.Logger;
import com.qod.lostboxes.render.input.KeyInput;
import com.qod.lostboxes.render.thread.EventLoop;
import com.qod.lostboxes.world.Level;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    JFrame frame;
    Logger frameLogger;
    public static int WIDTH, HEIGHT;

    Level level;

    public GameWindow() {
        frameLogger = new Logger(System.out);
        frameLogger.enableEnhanced(0);

        Dimension display = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int) (display.getWidth()*0.8);
        HEIGHT = (int) (display.getHeight()*0.8);

        level = new Level(32);

        frame = new JFrame("Lost Boxes");
        frame.addKeyListener(new KeyInput());
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        level.paintLevel(frame.getContentPane().getGraphics());

        frameLogger.info("Created Frame.");

        new EventLoop(this);
    }

    public void repaint() {
        level.paintLevel(frame.getContentPane().getGraphics());
    }

    public Logger getLogger() {
        return frameLogger;
    }

    public JFrame getFrame() {
        return frame;
    }
}
