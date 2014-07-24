package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.StarBackground;
import com.hambonegamestudios.GameObjects.TARDIS;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:27 PM
 * Project:    PoliceBox
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private int width;
    private int height;
    private TARDIS tardis;
    private SpriteBatch batch;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        // may be able to divide width and height by 2 (or more) to scale objects when drawn
        camera.setToOrtho(true, width / 2, height / 2); // TODO Find a way to do this better.  Match the denominator found in TARDIS.java
        System.out.println("Orthographic Camera created with dimensions " + width + " x " + height);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    public void render(float runTime) {
        System.out.println("GameRenderer - render() called");

        // Draw a black background to prevent flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(tardis.getPosition().x + tardis.getWidth()/2, tardis.getPosition().y + tardis.getHeight()/2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Draw the animated sprite
        batch.begin();

        final int tWidth = AssetLoader.backgroundTexture.getWidth();
        final int tHeight = AssetLoader.backgroundTexture.getHeight();
        final int regionWidth = height;
        final int regionHeight = width;

        for (int i = 0; i < regionWidth / tWidth; i++) {
            for (int k = 0; k < regionHeight / tHeight; k++) {
                batch.draw(AssetLoader.backgroundTexture, i*tWidth, k);
            }
        }

        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        //batch.draw(AssetLoader.backgroundTexture, tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        batch.end();
    }

    public void initGameObjects() {
        tardis = myWorld.getTardis();
    }
}
