package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hambonegamestudios.GameHelpers.AssetLoader;

import java.util.ArrayList;
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
    private int spawnPosition;

    public Meteoroid(int worldWidth, int worldHeight, boolean spawn) {
        Random random = new Random();
        int t = random.nextInt(5);
        switch(t) {
            case 1:
                meteoroid = new Sprite(AssetLoader.meteoroid_1);
                break;
            case 2:
                meteoroid = new Sprite(AssetLoader.meteoroid_2);
                break;
            case 3:
                meteoroid = new Sprite(AssetLoader.meteoroid_3);
                break;
            case 4:
                meteoroid = new Sprite(AssetLoader.meteoroid_4);
                break;
            case 5:
                meteoroid = new Sprite(AssetLoader.meteoroid_5);
                break;
            default:
                meteoroid = new Sprite(AssetLoader.meteoroid_1);
        }
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

    public void checkMeteoroidCollision(ArrayList<Meteoroid> otherMeteoroids, float delta) {
        for (Meteoroid otherMeteoroid : otherMeteoroids) {
//            if (otherMeteoroids.get(i) != this &&
//                    this.position.x + this.width >= otherMeteoroids.get(i).position.x && otherMeteoroids.get(i).position.x + otherMeteoroids.get(i).width >= this.position.x &&
//                    this.position.y + this.height >= otherMeteoroids.get(i).position.y && otherMeteoroids.get(i).position.y + otherMeteoroids.get(i).height >= this.position.y) {
//                this.velocity.x *= -1;
//                this.velocity.y *= -1;
//                otherMeteoroids.get(i).velocity.x *= -1;
//                System.out.println("Meteoroid collided with another meteoroid!");
//            }

            if(otherMeteoroid != this &&
                    // Check left collision
                    otherMeteoroid.getPosition().x + otherMeteoroid.getWidth() + (otherMeteoroid.getVelocity().x * delta) >= this.getPosition().x + (this.getVelocity().x * delta) &&
                    // Check right collision
                    otherMeteoroid.getPosition().x + (otherMeteoroid.getVelocity().x * delta) <= this.getPosition().x + this.getWidth() + (this.getVelocity().x * delta) &&
                    // Check top collision
                    otherMeteoroid.getPosition().y + otherMeteoroid.getHeight() + (otherMeteoroid.getVelocity().y * delta) >= this.getPosition().y + (this.getVelocity().y * delta) &&
                    // Check bottom collision
                    otherMeteoroid.getPosition().y + (otherMeteoroid.getVelocity().y * delta) <= this.getPosition().y + this.getHeight() + (this.getVelocity().y * delta)
                    ) {
//                System.out.println("Collision detected.");

                // Handle collision based on which side experienced the collision
                if(otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth()/ 2) < this.getPosition().x + (this.getWidth() / 2) && //middleX of the other meteoroid is less than middle of this one
                        otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2)  >= this.getPosition().y &&  // middleY of other meteoroid is higher than the top of this meteor
                        otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2)  <= this.getPosition().y + this.getHeight()) { // middleY of other meteoroid is lower than bottom of this meteor
//                    System.out.println("Collision happened on the left.");
                    // Collision happened on the left, so send this meteoroid to the right once it isn't intersecting with the collided meteor.
                    while(this.getPosition().x <= otherMeteoroid.getPosition().x + otherMeteoroid.getWidth()) {
                        this.getPosition().set(this.getPosition().x + 1, this.getPosition().y);
                    }
                    this.setVelocity(this.getVelocity().x * -1, this.getVelocity().y);
                    break;
                } else if (otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth() / 2) > this.getPosition().x + (this.getWidth() / 2) &&
                        otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2)  >= this.getPosition().y &&
                        otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2)  <= this.getPosition().y + this.getHeight()) {
//                    System.out.println("Collision happened on the right.");
                    // Collision happened on the right, so send this meteoroid to the left once it isn't intersecting with the collided meteor.
                    while(this.getPosition().x + this.getWidth() >= otherMeteoroid.getPosition().x) {
                        this.getPosition().set(this.getPosition().x - 1, this.getPosition().y);
                    }
                    this.setVelocity(this.getVelocity().x * -1, this.getVelocity().y);
                    break;
                } else if (otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2) < this.getPosition().y + (this.getHeight() / 2) &&
                        otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth() / 2) >= this.getPosition().x &&
                        otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth() / 2) <= this.getPosition().x + this.getWidth()) {
//                    System.out.println("Collision happened on the top.");
                    // Collision happened on the top, so send this meteoroid down once it isn't intersecting with the collided meteor.
                    while(this.getPosition().y <= otherMeteoroid.getPosition().y + otherMeteoroid.getHeight()) {
                        this.getPosition().set(this.getPosition().x, this.getPosition().y + 1);
                    }
                    this.setVelocity(this.getVelocity().x, this.getVelocity().y * -1);
                    break;
                } else if (otherMeteoroid.getPosition().y + (otherMeteoroid.getHeight() / 2) > this.getPosition().y + (this.getHeight() / 2) &&
                        otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth() / 2) >= this.getPosition().x &&
                        otherMeteoroid.getPosition().x + (otherMeteoroid.getWidth() / 2) <= this.getPosition().x + this.getWidth()) {
//                    System.out.println("Collision happened on the bottom.");
                    // Collision happened on the bottom, so send this meteoroid up once it isn't intersecting with the collided meteor.
                    while(this.getPosition().y + this.getHeight() >= otherMeteoroid.getPosition().y) {
                        this.getPosition().set(this.getPosition().x, this.getPosition().y - 1);
                    }
                    this.setVelocity(this.getVelocity().x, this.getVelocity().y * -1);
                    break;
                }
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

    public float getWidth() {
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
