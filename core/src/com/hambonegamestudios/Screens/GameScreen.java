package com.hambonegamestudios.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.hambonegamestudios.GameHelpers.InputHandler;
import com.hambonegamestudios.GameWorld.GameRenderer;
import com.hambonegamestudios.GameWorld.GameWorld;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       10:10 AM
 * Project:    PoliceBox
 */
public class GameScreen implements Screen{

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);

        // Attaching InputHandler
        Gdx.input.setInputProcessor(new InputHandler(world.getTardis(), renderer));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(34 / 255.0f, 34 / 255.0f, 34 / 255.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        runTime += delta;
        // Update game objects in the world
        world.update(delta);
        // Render game objects that have been updated
        renderer.render(runTime);
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
