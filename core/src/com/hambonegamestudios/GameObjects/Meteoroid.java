package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        x = random.nextInt(width);
        y = random.nextInt(height);
        rotation = random.nextInt(30);
        meteoroid = new Sprite(AssetLoader.meteoroid_small);
        // Move origin to center of sprite
        meteoroid.setOrigin(meteoroid.getWidth()/2, meteoroid.getHeight()/2);
        // rotate sprite clockwise or anticlockwise
        meteoroid.rotate90(random.nextBoolean());
        // set scale of sprite
        meteoroid.setScale(3, 3);
        System.out.println("Meteoroid - X: " + x + ", Y: " + y);
    }

    public void render(SpriteBatch batch, float delta) {
        meteoroid.rotate(rotation * delta);
        meteoroid.setPosition(x, y);
        meteoroid.draw(batch);
    }
}
