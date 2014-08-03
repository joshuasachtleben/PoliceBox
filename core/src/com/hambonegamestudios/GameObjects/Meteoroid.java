package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private float x, y, width, height;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private Sprite meteoroid;
    private ShapeRenderer renderer;

    public Meteoroid(int worldWidth, int worldHeight) {
        Random random = new Random();
        x = random.nextInt(worldWidth);
        y = random.nextInt(worldHeight);
        rotation = random.nextInt(1000);
        meteoroid = new Sprite(AssetLoader.meteoroid_small);
        // set scale of sprite
        meteoroid.setScale(2, 2);
        width = meteoroid.getWidth();
        height = meteoroid.getHeight();
        System.out.println("Meteoroid - X: " + x + ", Y: " + y);

        renderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch, float delta) {

        meteoroid.draw(batch);
    }

    public void update(float delta) {
        meteoroid.setPosition(x, y);
        meteoroid.rotate(rotation * delta);
    }

    public void checkCollision(float x, float y, int width, int height) {
        if (this.x + this.width >= x && x + width >= this.x &&
        this.y + this.height >= y && y + height >= this.y) {
            System.out.println("Meteoroid collision detected.");
        }
    }
}
