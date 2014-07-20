package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        // may be able to divide width and height by 2 (or more) to scale objects when drawn
        camera.setToOrtho(true, width, height);
        System.out.println("Orthographic Camera created with dimensions " + width + " x " + height);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render() {
        System.out.println("GameRenderer - render() called");

        /*
            1. Draw a black background to prevent flickering.
         */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
            2. Draw the filled rectangle
         */

        // Tells shapeRenderer to begin drawing filled shapes
        shapeRenderer.begin(ShapeType.Filled);

        // Choose an RGB color with full opacity
        shapeRenderer.setColor(16/255.0f, 35/255.0f, 114/255.0f, 1.0f);

        // Draw the rectangle from myWorld
        shapeRenderer.rect(myWorld.getRectangle().x, myWorld.getRectangle().y, myWorld.getRectangle().width, myWorld.getRectangle().height);

        // Tells shapeRenderer to finish rendering
        shapeRenderer.end();

        /*
            3. Draw the rectangle's outline
         */

        // Tells shapeRenderer to begin drawing the outline shape
        shapeRenderer.begin(ShapeType.Line);

        // Choose an RGB color with full opacity
        shapeRenderer.setColor(255/255.0f, 255/255.0f, 255/255.0f, 1.0f);

        // Draw the rectangle
        shapeRenderer.rect(myWorld.getRectangle().x, myWorld.getRectangle().y, myWorld.getRectangle().width, myWorld.getRectangle().height);

        // Tells shapeRenderer to finish rendering
        shapeRenderer.end();
    }
}
