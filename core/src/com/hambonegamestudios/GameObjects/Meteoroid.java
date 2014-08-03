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
    private float x, y, width, height, worldWidth, worldHeight;
    private int velMin, velMax;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private Sprite meteoroid;

    public Meteoroid(int worldWidth, int worldHeight) {
        Random random = new Random();
        position = new Vector2();
        velocity = new Vector2();
        velMin = -50;
        velMax = 50;
        //velocity.set(random.nextInt(velMax - velMin) + velMin, random.nextInt(velMax - velMin) + velMin);
        x = random.nextInt(worldWidth);
        y = random.nextInt(worldHeight);
        position.x = x;
        position.y = y;
        rotation = random.nextInt(360);
        meteoroid = new Sprite(AssetLoader.meteoroid_small);
        // set scale of sprite
        meteoroid.setScale(2, 2);
        width = meteoroid.getWidth();
        height = meteoroid.getHeight();
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        System.out.println("Meteoroid - X: " + x + ", Y: " + y);
    }

    public void render(SpriteBatch batch, float delta) {

        meteoroid.draw(batch);
    }

    public void update(float delta, int levelWidth, int levelHeight) {
        meteoroid.rotate(rotation * delta);
        position.add(velocity.cpy().scl(delta));
        meteoroid.setPosition(position.x, position.y);
    }

    public void checkCollision(float x, float y, int width, int height) {
        if (this.x + this.width >= x && x + width >= this.x &&
                this.y + this.height >= y && y + height >= this.y) {
            System.out.println("Meteoroid collision detected.");
            velocity.set(100, 100);
        }
    }

    public boolean checkBorderCollision() {
        if (meteoroid.getX() - meteoroid.getWidth() <= 0 || meteoroid.getX() >= worldWidth ||
                meteoroid.getY() - meteoroid.getHeight() <= 0 || meteoroid.getY() >= worldHeight) {
            return true;
        }
        return false;
    }
}
