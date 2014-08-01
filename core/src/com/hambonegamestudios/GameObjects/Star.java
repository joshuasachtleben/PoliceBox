package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hambonegamestudios.GameHelpers.AssetLoader;

import java.util.Random;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/27/2014
 * Time:       7:59 PM
 * Project:    PoliceBox
 */
public class Star {

    private Texture starTexture;
    private int x, y;
    private float alpha, duration;
    private int noFlicker;
    private int color;
    private Random rand;
    private float elapsedTime;
    private boolean alphaIncrease = true;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
        elapsedTime = 0;
        rand = new Random();
        alpha = rand.nextInt(255) / 255.0f;
        duration = rand.nextInt(30);
        noFlicker = rand.nextInt(3); // one out of every x stars will not flicker.  need to set the number in the draw method to be checked upon each iteration
        color = rand.nextInt(30);
    }

    public void draw(SpriteBatch spriteBatch, float delta) {
        /*
            This will increase the elapsedTime value until it reaches the expected duration.
            It will then toggle alphaIncrease to false to begin reducing the time value.
         */
        if(alphaIncrease && noFlicker != 3){
            elapsedTime += delta;
            if(elapsedTime > duration){
                elapsedTime = duration;
                alphaIncrease = false;
            }
        }
        /*
            This will decrease the elapsedTime value until it reaches 0.
            It will then toggle alphaIncrease to true to begin increasing the time value.
         */
        if(!alphaIncrease && noFlicker != 3) {
            elapsedTime -= delta;
            if(elapsedTime <= 0) {
                elapsedTime = 0;
                alphaIncrease = true;
            }
        }
        // This will set the color and alpha of the star textures.  RGB will produce white, but other colors (1,0,0 for red) can be generated.
        if(color == 1) {
            spriteBatch.setColor(120 / 255.0f, 120 / 255.0f, 0, elapsedTime / duration); //alpha is a ratio of time elapsed and duration.  Example: 1 minute elapsed from a 2 minute duration is a 50% alpha ratio
        } else if(color == 2) {
            spriteBatch.setColor(120 / 255.0f, 0, 0, elapsedTime / duration);
        } else if (color == 3) {
            spriteBatch.setColor(0, 0, 120 / 255.0f, elapsedTime / duration);
        } else {
            spriteBatch.setColor(1, 1, 1, elapsedTime / duration);
        }
        spriteBatch.draw(AssetLoader.starTexture, x, y);
        spriteBatch.setColor(1, 1, 1, 1);  //set color back to white with full opacity so other objects drawn won't flicker also
    }
}
