package com.qod.lostboxes.render.thread;

import com.qod.lostboxes.render.GameWindow;
import com.qod.lostboxes.render.input.KeyInput;
import com.qod.lostboxes.world.Level;

import java.security.Key;
import java.util.Timer;
import java.util.TimerTask;

public class EventLoop extends TimerTask {
    GameWindow window;

    public EventLoop(GameWindow window) {
        this.window = window;
        window.getLogger().info("Registered EventLoop");
        Timer timer = new Timer();
        timer.schedule(this, 0, 10);
    }

    @Override
    public void run() {
        if(KeyInput.pressed['p'] || KeyInput.pressed['P']) {
            window.getLogger().debug("Detected Paint.");
            window.repaint();
        } else if(KeyInput.pressed[' ']) {
            window.getLogger().debug("Detected Increase");
            Level.scalar += 0.01;
            window.repaint();
        } else if(KeyInput.pressed['-']) {
            window.getLogger().debug("Detected Decrease");
            Level.scalar -= 0.01;
            window.repaint();
        }
    }
}
