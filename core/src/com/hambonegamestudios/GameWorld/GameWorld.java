package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:26 PM
 * Project:    PoliceBox
 */
public class GameWorld {

    private Rectangle rectangle = new Rectangle(0, 0, 25, 50);
    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private int xVel = 1, yVel = 1;

    public void update(float delta) {
        System.out.println("GameWorld - update() called");
        if(rectangle.x + rectangle.getWidth() > width) {
            xVel = -3;
        } else if (rectangle.x < 0) {
            xVel = 3;
        } else if (rectangle.y + rectangle.getHeight() > height) {
            yVel = -3;
        } else if (rectangle.y < 0) {
            yVel = 3;
        }

        rectangle.x += xVel;
        rectangle.y += yVel;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
