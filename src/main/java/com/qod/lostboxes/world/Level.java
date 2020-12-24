package com.qod.lostboxes.world;

import com.qod.lostboxes.log.Logger;
import com.qod.lostboxes.math.mat.Matrix3f;
import com.qod.lostboxes.math.mat.Matrix4f;
import com.qod.lostboxes.math.vec.Vector3f;
import com.qod.lostboxes.math.vec.Vector4f;
import com.qod.lostboxes.render.GameWindow;
import com.qod.lostboxes.util.TimeUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {

    public static double scalar = 2.15;

    // In order to prevent long calculations
    // save image here.
    BufferedImage staticImage;

    BufferedImage dbImage;
    Graphics dbGraphics;

    Matrix3f projectionMatrixA, projectionMatrixB;
    Matrix4f translationMatrix;

    ArrayList<Tile> tiles;
    Logger levelLogger;
    int tileWidth = 128;

    public Level(int tileWidth) {
        // Logging
        levelLogger = new Logger(System.out);
        levelLogger.enableEnhanced(0);

        // Tiles
        tiles = new ArrayList<>();
        this.tileWidth = tileWidth;

        // Setting up Projection Matrix
        projectionMatrixA = (Matrix3f) new Matrix3f()
                .rotationZ(Math.toRadians(150));
        projectionMatrixB = (Matrix3f) new Matrix3f()
                .rotationZ(Math.toRadians(30));

        // Setting up Translation Matrix
        translationMatrix = (Matrix4f) new Matrix4f().translation(
                0.45f* GameWindow.WIDTH, 0.1f * GameWindow.HEIGHT, 0f);


        addTiles();
    }

    private void addTiles() {
        for(int i = 0; i < 64; i++)
            tiles.add(new Tile(this, "Earth-Tile", "images/earth.png", new Vector3f(i >> 3, i%8, 0)));
    }

    public void paintLevel(Graphics g) {
        g.clearRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
        Vector4f origin = (Vector4f) new Vector4f(0,0,0,1).transform(translationMatrix);
        Vector3f pxv = (Vector3f) new Vector3f(1, 0, 0).transform(projectionMatrixA).scale(
                2.15*getTileSize()
        );
        Vector3f pyv = (Vector3f) new Vector3f(1, 0, 0).transform(projectionMatrixB).scale(
                2.15*getTileSize()
        );

        for(Tile tile:tiles) {
            Vector3f tileVector = (Vector3f) origin.toVector3()
                    .add(pxv.scale(tile.getPosition().getX()))
                    .add(pyv.scale(tile.getPosition().getY()));
            g.drawLine((int) origin.getI(), (int) origin.getJ(), (int) tileVector.getX(), (int) tileVector.getY());
            g.drawImage(tile.getOriginal(), (int) tileVector.getX(), (int) tileVector.getY(), null);
        }
    }

    public Logger getLogger() {
        return levelLogger;
    }

    public int getTileSize() {
        return tileWidth;
    }
}
