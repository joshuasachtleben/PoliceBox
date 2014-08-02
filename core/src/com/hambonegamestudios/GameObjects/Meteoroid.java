package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hambonegamestudios.GameHelpers.AssetLoader;

import java.util.Random;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/31/2014
 * Time:       12:45 PM
 * Project:    PoliceBox
 */
public class Meteoroid {
    private int x, y;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private Sprite meteoroid;

    public Meteoroid(int width, int height) {
        Random random = new Random();
        this.x = random.nextInt(width);
        this.y = random.nextInt(height);
        meteoroid = new Sprite(AssetLoader.meteoroid_small);
        System.out.println("Meteoroid - Width: " + x+ ", Height: " + y);
    }

    public void render(SpriteBatch batch) {
        meteoroid.setPosition(x, y);
        meteoroid.draw(batch);
    }
}
