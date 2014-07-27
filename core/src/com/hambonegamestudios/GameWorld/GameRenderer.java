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

        // Draw the animated sprite
        batch.begin();

//        for (int y = -2; y < myWorld.getHeight() / AssetLoader.backgroundTexture.getHeight() + 2; y++) {
//            for (int x = 0; x < myWorld.getWidth() / AssetLoader.backgroundTexture.getWidth(); x++) {
//                //System.out.println("Tiles: " + x);
//                batch.draw(AssetLoader.backgroundTexture, x * AssetLoader.backgroundTexture.getWidth(), y * AssetLoader.backgroundTexture.getHeight());
//            }
//        }

        /*
            http://www.java-gaming.org/index.php?topic=32157.0
            Rendering the background.  batch.draw takes the following arguments:
            texture: this is the texture that is created in AssetLoader.
            x: Where to start drawing on the x-axis.  We have this as the negative value of half the width of the current camera view.  This will limit the drawing of repeated textures to the left most side of the camera viewing area
            y: Where to start drawing on the y-axis.  We have this as the negative value of half the height of the current camera view.  This will limit the drawing of repeated textures to the top most side of the camera viewing area
            width: How far across to draw the rectangle that is displaying the texture
            height: How far from top to bottom to draw the rectangle that is displaying the texture
            u: starting x-coordinate of texture (left)
            v: starting y-coordinate of texture (bottom)
            u2: ending x-coordinate of texture (right)
            v2: ending y-coordinate of texture (top)
            Note: since this batch.draw() method is using a texture that is set to repeat itself when it reaches the end of the drawing area, this method will allow repeating for the full width and height specified.
                  For example, if you had a texture that was 512x512, and you specified a u,v of (0, 900) and a u2,v2 of (720, 0), and if you didn't have the texture repeat, it would display the 512x512 texture at the top, left, and then just black (or whatever was behind) at the bottom, right.

         */
        tilesWidth = width + cameraWidth / AssetLoader.backgroundTexture.getWidth();
        tilesHeight = height + cameraHeight / AssetLoader.backgroundTexture.getHeight();
        batch.draw(AssetLoader.backgroundTexture, -cameraWidth / 2, -cameraHeight / 2,
                AssetLoader.backgroundTexture.getWidth() * tilesWidth,
                AssetLoader.backgroundTexture.getHeight() * tilesHeight,
                0, tilesHeight,
                tilesWidth, 0
                );

        //AssetLoader.stars.draw(batch);

        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        //batch.draw(AssetLoader.backgroundTexture, tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        batch.end();
    }

    public void initGameObjects() {
        tardis = myWorld.getTardis();
    }

    public float getCameraZoom () {
        return cameraZoom;
    }

    public void setCameraZoom (int zoomAmount) {
        cameraZoom += zoomAmount;
    }
}
