package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.Meteoroid;
import com.hambonegamestudios.GameObjects.TARDIS;

import java.util.ArrayList;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:26 PM
 * Project:    PoliceBox
 */
public class GameWorld {

    private int width = 2048;
    private int height = 2048;
    private TARDIS tardis;
    private ArrayList<Meteoroid> meteoroids;
    private int score;

    public GameWorld() {
        tardis = new TARDIS(width/2, height/2, AssetLoader.tardis_0.getRegionWidth(), AssetLoader.tardis_0.getRegionHeight());
        meteoroids = new ArrayList<Meteoroid>();
        for(int i = 0; i < 200; i++) {
            meteoroids.add(new Meteoroid(this.getWidth(), this.getHeight(), false));
        }
        score = 0;
    }

    public void update(float delta) {
        //System.out.println("GameWorld - update() called");

        tardis.update(delta, width, height);


        for (int i = 0; i < meteoroids.size(); i++) {
            if(meteoroids.get(i).checkBorderCollision()) {
                meteoroids.remove(i);
                meteoroids.add(new Meteoroid(this.getWidth(), this.getHeight(), true));
            }
            if(tardis.checkCollision(meteoroids.get(i), delta)) {
                meteoroids.remove(i);
            }
            meteoroids.get(i).checkMeteoroidCollision(meteoroids, delta);
            meteoroids.get(i).update(delta, width, height);

            //System.out.println("Number of meteoroids: " + meteoroids.size());
        }

        score += 100;
    }

    public TARDIS getTardis(){
        return tardis;
    }

    public ArrayList<Meteoroid> getMeteoroids() {
        return meteoroids;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getScore() { return score; }
}
