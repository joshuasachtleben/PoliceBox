package com.hambonegamestudios.PoliceBox;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       9:52 AM
 * Project:    PoliceBox
 */

import com.badlogic.gdx.Game;
import com.hambonegamestudios.Screens.GameScreen;

public class PBGame extends Game {

    @Override
    public void create() {
        System.out.println("PBGame - create() called");

        setScreen(new GameScreen());
    }
}
