package com.hambonegamestudios.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.hambonegamestudios.GameObjects.TARDIS;
import com.hambonegamestudios.GameWorld.GameRenderer;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/25/2014
 * Time:       1:11 PM
 * Project:    PoliceBox
 */
public class InputHandler implements InputProcessor{

    private TARDIS tardis;
    private GameRenderer renderer;

    public InputHandler(TARDIS tardis, GameRenderer renderer) {
        this.tardis = tardis;
        this.renderer = renderer;
    }



    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.W:
                tardis.setMoveUp(true);
                break;
            case Input.Keys.A:
                tardis.setMoveLeft(true);
                break;
            case Input.Keys.S:
                tardis.setMoveDown(true);
                break;
            case Input.Keys.D:
                tardis.setMoveRight(true);
                break;
            case Input.Keys.LEFT_BRACKET:
                renderer.setCameraZoom(-.03f);
                break;
            case Input.Keys.RIGHT_BRACKET:
                renderer.setCameraZoom(.03f);
                break;
            case Input.Keys.F11:
                Gdx.graphics.setDisplayMode(1920, 1080, true);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.W:
                tardis.setMoveUp(false);
                break;
            case Input.Keys.A:
                tardis.setMoveLeft(false);
                break;
            case Input.Keys.S:
                tardis.setMoveDown(false);
                break;
            case Input.Keys.D:
                tardis.setMoveRight(false);
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
        tardis.onClick();
        return false;
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
            renderer.setCameraZoom(-.05f);
            return true;
        } else if(amount == 1) {
            renderer.setCameraZoom(.05f);
            return true;
        } else {
            return false;
        }
    }
}
