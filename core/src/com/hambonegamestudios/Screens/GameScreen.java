package com.hambonegamestudios.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       10:10 AM
 * Project:    PoliceBox
 */
public class GameScreen implements Screen{
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34/255.0f, 34/255.0f, 34/255.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resize() called");
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show() called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide() called");
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause() called");
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume() called");
    }

    @Override
    public void dispose() {
        System.out.println("GameScreen - dispose() called");
    }
}
