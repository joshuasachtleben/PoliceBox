package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.hambonegamestudios.GameObjects.TARDIS;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:26 PM
 * Project:    PoliceBox
 */
public class GameWorld {

    private int width = Gdx.graphics.getWidth();
    private int height = Gdx.graphics.getHeight();
    private TARDIS tardis;

    public GameWorld() {
        tardis = new TARDIS(0, 0, 32, 32);
    }

    public void update(float delta) {
        System.out.println("GameWorld - update() called");
        tardis.update(delta, width, height);
    }

    public TARDIS getTardis(){
        return tardis;
    }
}
