package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hambonegamestudios.GameHelpers.AssetLoader;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

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
    private int spawnPosition;

    public Meteoroid(int worldWidth, int worldHeight, boolean spawn) {
        meteoroid = new Sprite(AssetLoader.meteoroid_small);
        Random random = new Random();
        position = new Vector2();
        velocity = new Vector2();
        velMin = -50;
        velMax = 50;
        velocity.set(random.nextInt(velMax - velMin) + velMin, random.nextInt(velMax - velMin) + velMin);
        x = random.nextInt(worldWidth);
        y = random.nextInt(worldHeight);
        position.x = x;
        position.y = y;
        width = meteoroid.getWidth();
        height = meteoroid.getHeight();
        // get seed for spawn position
        spawnPosition = random.nextInt(4);
        // check to see if we are to spawn a new meteoroid after game is running
        if (spawn) {
            switch (spawnPosition) {
                // spawn from top
                case 0:
                    position.y = -height;
                    velocity.y = Math.abs(velocity.y);
                    break;
                // spawn from bottom
                case 1:
                    position.y = worldHeight;
                    velocity.y = Math.abs(velocity.y) * -1;
                    break;
                // spawn from left
                case 2:
                    position.x = -width;
                    velocity.x = Math.abs(velocity.x);
                    break;
                // spawn from right
                case 3:
                    position.x = worldWidth;
                    velocity.x = Math.abs(velocity.x) * -1;
                    break;
            }

        }
        rotation = random.nextInt(360);

        // set scale of sprite
        meteoroid.setScale(1, 1);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        //System.out.println("Meteoroid - X: " + x + ", Y: " + y);
    }

    public void render(SpriteBatch batch, float delta) {

        meteoroid.draw(batch);
    }

    public void update(float delta, int levelWidth, int levelHeight) {
        meteoroid.rotate(rotation * delta);
        position.add(velocity.cpy().scl(delta));
        meteoroid.setPosition(position.x, position.y);
    }

    public void checkPlayerCollision(float x, float y, int width, int height) {
        if (this.position.x + this.width + (this.velocity.x * Gdx.graphics.getDeltaTime()) >= x && x + width >= this.position.x + (this.velocity.x * Gdx.graphics.getDeltaTime()) &&
                this.position.y + this.height + (this.velocity.y * Gdx.graphics.getDeltaTime()) >= y && y + height >= this.position.y + (this.velocity.y * Gdx.graphics.getDeltaTime())) {
            //System.out.println("Meteoroid collision detected.");
            velocity.x *= -1;
            velocity.y *= -1;
        }
    }

    public void checkMeteoroidCollision(ArrayList<Meteoroid> otherMeteoroids, float delta) {
        for (int i = 0; i < otherMeteoroids.size(); i++) {
//            if (otherMeteoroids.get(i) != this &&
//                    this.position.x + this.width >= otherMeteoroids.get(i).position.x && otherMeteoroids.get(i).position.x + otherMeteoroids.get(i).width >= this.position.x &&
//                    this.position.y + this.height >= otherMeteoroids.get(i).position.y && otherMeteoroids.get(i).position.y + otherMeteoroids.get(i).height >= this.position.y) {
//                this.velocity.x *= -1;
//                this.velocity.y *= -1;
//                otherMeteoroids.get(i).velocity.x *= -1;
//                System.out.println("Meteoroid collided with another meteoroid!");
//            }
            // Check left collision
            if(otherMeteoroids.get(i) != this && this.getPosition().x + this.getWidth() + (this.getVelocity().x * delta) >= otherMeteoroids.get(i).getPosition().x + (otherMeteoroids.get(i).getVelocity().x * delta)) {
                this.setVelocity(this.getVelocity().x * -1, this.getVelocity().y);
                System.out.println("Collision on left side at :" + this.position.x);
            }
        }
    }

    public boolean checkBorderCollision() {
        if (meteoroid.getX() + meteoroid.getWidth() * 2 < 0 || meteoroid.getX() > worldWidth ||
                meteoroid.getY() + meteoroid.getHeight() * 2 < 0 || meteoroid.getY() > worldHeight) {
            return true;
        }
        return false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {g
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(float x, float y) {
        velocity.set(x, y);
    }
}
