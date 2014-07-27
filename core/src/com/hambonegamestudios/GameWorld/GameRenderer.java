package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.StarBackground;
import com.hambonegamestudios.GameObjects.TARDIS;

import java.awt.*;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:27 PM
 * Project:    PoliceBox
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private int width, height;
    private int tilesWidth, tilesHeight;
    private int cameraWidth, cameraHeight;
    private TARDIS tardis;
    private SpriteBatch batch;
    private float cameraZoom;
    public GameRenderer(GameWorld world) {
        myWorld = world;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        cameraWidth = width;
        cameraHeight = height;
        cameraZoom = 1.0f;
        // may be able to divide width and height by 2 (or more) to scale objects when drawn
        camera.setToOrtho(true, 100, 100); // TODO Find a way to do this better.  Match the denominator found in TARDIS.java
        System.out.println("Orthographic Camera created with dimensions " + width + " x " + height);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    public void render(float runTime) {
        //System.out.println("GameRenderer - render() called");

        // Draw a black background to prevent flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set camera properties
        camera.position.set(tardis.getPosition().x + tardis.getWidth()/2, tardis.getPosition().y + tardis.getHeight()/2, 0);
        camera.viewportWidth = cameraWidth;
        camera.viewportHeight = cameraHeight;
        camera.zoom = cameraZoom;
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        /*
            http://www.java-gaming.org/index.php?topic=32157.0
            Rendering the background.  batch.draw takes the following arguments:
            texture: this is the texture that is created in AssetLoader.
            x: Where to start drawing on the x-axis.  We have this as the negative value of half the width of the current camera view.  This will limit the drawing of repeated textures to the left most side of the camera viewing area
            y: Where to start drawing on the y-axis.  We have this as the negative value of half the height of the current camera view.  This will limit the drawing of repeated textures to the top most side of the camera viewing area
            width: How far across to draw the rectangle that is displaying the texture
            height: How far from top to bottom to draw the rectangle that is displaying the texture
            u: starting x-coordinate of texture (left)
            v: starting y-coordinate of texture (top)
            u2: ending x-coordinate of texture (right)
            v2: ending y-coordinate of texture (bottom)
            From http://www.java-gaming.org/index.php?topic=29863.0:

                You need to give SpriteBatch UVs using:
                draw(Texture texture, float x, float y, float width, float height, float u, float v, float u2, float v2)
                The UVs are from 0 to 1, where 0 is the top (v) / left (u) and 1 is the bottom (v) / right (u). You can use values outside this range and it will repeat. Eg, use 0,0,2,1 and it will repeat twice horizontally and once vertically.

                You can also use a TextureRegion to specify the UVs. Also see the TextureRegion scroll method.

         */
        tilesWidth = myWorld.getWidth()  / AssetLoader.backgroundTexture.getWidth();
        tilesHeight = myWorld.getHeight()  / AssetLoader.backgroundTexture.getHeight();
        batch.draw(AssetLoader.backgroundTexture, 0, 0,
                AssetLoader.backgroundTexture.getWidth() * tilesWidth,
                AssetLoader.backgroundTexture.getHeight() * tilesHeight,
                0, tilesWidth,
                tilesHeight, 0
                );

        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());

        batch.end();
    }

    public void initGameObjects() {
        tardis = myWorld.getTardis();
    }

    public void setCameraZoom (float zoomAmount) {
        cameraZoom += zoomAmount;
    }

}
