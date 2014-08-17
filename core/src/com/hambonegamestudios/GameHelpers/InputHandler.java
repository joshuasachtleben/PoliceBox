package com.hambonegamestudios.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.hambonegamestudios.GameObjects.Meteoroid;
import com.hambonegamestudios.GameObjects.PoliceBox;
import com.hambonegamestudios.GameWorld.GameRenderer;

import java.util.ArrayList;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/25/2014
 * Time:       1:11 PM
 * Project:    PoliceBox
 */
public class InputHandler implements InputProcessor{

    private PoliceBox policeBox;
    private GameRenderer renderer;
    private ArrayList<Meteoroid> meteoroids;

    public InputHandler(PoliceBox policeBox, ArrayList<Meteoroid> meteoroids, GameRenderer renderer) {
        this.policeBox = policeBox;
        this.meteoroids = meteoroids;
        this.renderer = renderer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.W:
                policeBox.setMoveUp(true);
                break;
            case Input.Keys.A:
                policeBox.setMoveLeft(true);
                break;
            case Input.Keys.S:
                policeBox.setMoveDown(true);
                break;
            case Input.Keys.D:
                policeBox.setMoveRight(true);
                break;
            case Input.Keys.LEFT_BRACKET:
                renderer.setCameraZoom(-1f);
                break;
            case Input.Keys.RIGHT_BRACKET:
                renderer.setCameraZoom(1f);
                break;
            case Input.Keys.F11:
                Gdx.graphics.setDisplayMode(1920, 1080, true);
                break;
            case Input.Keys.F4:
                renderer.toggleDebug();
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.W:
                policeBox.setMoveUp(false);
                break;
            case Input.Keys.A:
                policeBox.setMoveLeft(false);
                break;
            case Input.Keys.S:
                policeBox.setMoveDown(false);
                break;
            case Input.Keys.D:
                policeBox.setMoveRight(false);
                break;
            case Input.Keys.F11:
                Gdx.graphics.setDisplayMode(920, 920/16*9, false);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        policeBox.onClick();
        meteoroids.add(new Meteoroid(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount == -1) {
            renderer.setCameraZoom(-1f);
            return true;
        } else if(amount == 1) {
            renderer.setCameraZoom(1f);
            return true;
        } else {
            return false;
        }
    }
}
