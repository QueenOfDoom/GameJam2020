package com.qod.lostboxes;

import com.qod.lostboxes.log.Logger;
import com.qod.lostboxes.render.GameWindow;

public class App {
    public static final Logger logger = new Logger(System.out);
    public static void main(String[] args) {
        logger.enableEnhanced(0);
        logger.info("Application Launched.");

        new GameWindow();
    }
}
