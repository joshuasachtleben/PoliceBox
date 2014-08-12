package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/20/2014
 * Time:       1:55 PM
 * Project:    PoliceBox
 */
public class TARDIS {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private int health;

    public TARDIS(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);  // starts at the coordinates passed to the constructor
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
        health = 100;
    }

    public void update(float delta, int levelWidth, int levelHeight) {

        //velocity.add(acceleration.cpy().scl(delta));
        //position.add(velocity.cpy().scl(delta));

        if (moveUp) position.y -= 100 * delta;
        if (moveDown) position.y += 100 * delta;
        if (moveLeft) position.x -= 100 * delta;
        if (moveRight) position.x += 100 * delta;


        if (position.x + this.width > levelWidth) {
            position.x = levelWidth - this.width;
        }
        if (position.x <= 0) {
            position.x = 0;
        }
        if (position.y + this.height > levelHeight) {
            position.y = levelHeight - this.height;
        }
        if (position.y <= 0) {
            position.y = 0;
        }
    }

    public void onClick() {
        System.out.println("TARDIS click");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getPosition() {
        //System.out.println("X: " + position.x + " Y: " + position.y);
        return position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public void setMoveUp(boolean x) {
        moveUp = x;
    }

    public void setMoveDown(boolean x) {
        moveDown = x;
    }

    public void setMoveLeft(boolean x) {
        moveLeft = x;
    }

    public void setMoveRight(boolean x) {
        moveRight = x;
    }

    public boolean checkCollision(Meteoroid meteoroid, float delta) {
        if (meteoroid.getPosition().x + meteoroid.getWidth() >= this.getPosition().x && meteoroid.getPosition().x <= this.getPosition().x + this.getWidth() &&
                meteoroid.getPosition().y + meteoroid.getHeight() >= this.getPosition().y && meteoroid.getPosition().y <= this.getPosition().y + this.getHeight()) {
            System.out.println("Collision with the Police Box.");
            return true;
        }
        return false;
    }
}
