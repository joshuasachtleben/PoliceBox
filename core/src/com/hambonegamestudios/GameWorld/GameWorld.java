package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.Meteoroid;
import com.hambonegamestudios.GameObjects.PoliceBox;

import java.util.ArrayList;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:26 PM
 * Project:    PoliceBox
 */
public class GameWorld {

    private int width = Gdx.graphics.getWidth();//1024;
    private int height = Gdx.graphics.getHeight();//1024;
    private PoliceBox policeBox;
    private ArrayList<Meteoroid> meteoroids;
    private int score;

    private GameState currentState;

    private enum GameState {READY, RUNNING, GAMEOVER};

    public GameWorld() {
        policeBox = new PoliceBox(width/2, height/2, AssetLoader.tardis_0.getRegionWidth(), AssetLoader.tardis_0.getRegionHeight());
        meteoroids = new ArrayList<Meteoroid>();
        for(int i = 0; i < 50; i++) {
            meteoroids.add(new Meteoroid(this.getWidth(), this.getHeight(), false));
        }
        score = 0;
        currentState = GameState.RUNNING;
    }

    public void update(float delta) {
        switch(currentState){
            case READY:
                // do nothing for now
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case GAMEOVER:
                break;
            default:
                break;
        }
    }

    public void updateRunning(float delta) {
        //System.out.println("GameWorld - update() called");

        policeBox.update(delta, width, height);

        for (int i = 0; i < meteoroids.size(); i++) {
            if(meteoroids.get(i).checkBorderCollision()) {
                meteoroids.add(new Meteoroid(this.getWidth(), this.getHeight(), true));
                meteoroids.remove(i);
            }
            if(policeBox.checkCollision(meteoroids.get(i), delta)) {
                meteoroids.remove(i);
                policeBox.setHealth(-10);
                if(policeBox.getHealth() <= 0) currentState = GameState.GAMEOVER;
            }
            meteoroids.get(i).checkMeteoroidCollision(meteoroids, delta);
            meteoroids.get(i).update(delta, width, height);

            //System.out.println("Number of meteoroids: " + meteoroids.size());
        }

        score += 100;
    }

    public PoliceBox getPoliceBox(){
        return policeBox;
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

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState state) {
        currentState = state;
    }
}
