package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/27/2014
 * Time:       7:45 PM
 * Project:    PoliceBox
 */
public class Starfield {

    private ArrayList<Star> stars;
    private int worldWidth, worldHeight;
    private Random rand;

    public Starfield(int width, int height, int starCount) {
        worldWidth = width;
        worldHeight = height;
        rand = new Random();
        stars = new ArrayList<Star>();
        // For each star in the list, create a new star object and place it at a random x,y coordinate based on worldWidth and worldHeight
        for (int x = 0; x < starCount; x++) {
            stars.add(new Star(rand.nextInt(worldWidth), rand.nextInt(worldHeight)));
        }

    }

    public void render(SpriteBatch spriteBatch, float delta){
        for(Star star : stars) {
            //System.out.println(star + "is being drawn at " + x + "," + y);
            star.draw(spriteBatch, delta);
        }
    }
}
