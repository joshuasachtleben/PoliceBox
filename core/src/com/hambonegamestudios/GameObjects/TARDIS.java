package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

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

    private Rectangle rectangle;

    public TARDIS(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
        position = new Vector2(x, y);  // starts at the coordinates passed to the constructor
        velocity = new Vector2(300, 300);  // starts with a velocity of 3
        acceleration = new Vector2(0, 460); // starts with positive y-axis acceleration to make it fall to the bottom of the screen
    }

    public void update(float delta, int width, int height) {

        if(position.x + this.width > width) {
            velocity.x = -300.0f;
        } else if (position.x < 0) {
            velocity.x = 300.0f;
        } else if (position.y + this.height > height) {
            velocity.y = -300.0f;
        } else if (position.y < 0) {
            velocity.y = 300.0f;
        }

        position.add(velocity.cpy().scl(delta));
    }

    public void onClick() {
        velocity.y = -140; // moves the TARDIS up
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getVelocity () {
        return velocity;
    }

    public Vector2 getPosition() {
        System.out.println("X: " + position.x + " Y: " + position.y);
        return position;
    }
}
