package com.hambonegamestudios.GameHelpers;

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
                tardis.setVelocity(new Vector2(0, -75));
                break;
            case Input.Keys.A:
                tardis.setVelocity(new Vector2(-75, 0));
                break;
            case Input.Keys.S:
                tardis.setVelocity(new Vector2(0, 75));
                break;
            case Input.Keys.D:
                tardis.setVelocity(new Vector2(75, 0));
                break;
            case Input.Keys.LEFT_BRACKET:
                renderer.setCameraZoom(-.01f);
                break;
            case Input.Keys.RIGHT_BRACKET:
                renderer.setCameraZoom(.01f);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
        return false;
    }
}
