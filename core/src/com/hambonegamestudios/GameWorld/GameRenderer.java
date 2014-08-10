package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.Meteoroid;
import com.hambonegamestudios.GameObjects.Starfield;
import com.hambonegamestudios.GameObjects.TARDIS;

import java.util.ArrayList;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:27 PM
 * Project:    PoliceBox
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera camera;
    private int cameraWidth, cameraHeight;
    private Starfield starfield;
    private TARDIS tardis;
    private int tardisWidth, tardisHeight, tardisPositionX0, tardisPositionX1, tardisPositionY0, tardisPositionY1;
    private int worldLeft, worldRight, worldTop, worldBottom;
    private int cameraLeft, cameraRight, cameraTop, cameraBottom;
    private BitmapFont font;
    private SpriteBatch batch;
    private float cameraZoom;
    private boolean debug = false;

    private ShapeRenderer renderer;

    public GameRenderer(GameWorld world) {
        myWorld = world;
        cameraWidth = Gdx.graphics.getWidth();
        cameraHeight = Gdx.graphics.getHeight();
        font = new BitmapFont(true);
        font.setColor(Color.WHITE);

        camera = new OrthographicCamera();
        cameraZoom = 1.0f;
        // may be able to divide width and height by 2 (or more) to scale objects when drawn
        camera.setToOrtho(true, cameraWidth, cameraHeight);
        System.out.println("Orthographic Camera created with dimensions " + cameraWidth + " x " + cameraHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    public void render(float runTime, float delta) {
        //System.out.println("GameRenderer - render() called");

        // Draw a black background to prevent flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get TARDIS properties
        tardisWidth = tardis.getWidth();
        tardisHeight = tardis.getHeight();
        tardisPositionX0 = (int) tardis.getPosition().x;
        tardisPositionX1 = tardisPositionX0 + tardisWidth;
        tardisPositionY0 = (int) tardis.getPosition().y;
        tardisPositionY1 = tardisPositionY0 + tardisHeight;

        // Get World properties
        worldLeft = 0;
        worldRight = myWorld.getWidth();
        worldTop = 0;
        worldBottom = myWorld.getHeight();

        camera.viewportWidth = cameraWidth;
        camera.viewportHeight = cameraHeight;
        camera.zoom = cameraZoom;

        camera.position.set(tardis.getPosition().x + tardis.getWidth() / 2, tardis.getPosition().y + tardis.getHeight() / 2, 0);

        // Get Camera properties
        cameraLeft = (int) (camera.position.x - (cameraWidth / 2 * camera.zoom));
        cameraRight = (int) (camera.position.x + (cameraWidth / 2 * camera.zoom));
        cameraTop = (int) (camera.position.y - (cameraHeight / 2 * camera.zoom));
        cameraBottom = (int) (camera.position.y + (cameraHeight / 2 * camera.zoom));

        // Set camera properties

        if (cameraLeft <= worldLeft) {
            camera.position.x = worldLeft + ((cameraWidth / 2 * cameraZoom));
        } else if (cameraRight >= worldRight) {
            camera.position.x = worldRight - ((cameraWidth / 2 * cameraZoom));
        }
        if (cameraTop <= worldTop) {
            camera.position.y = worldTop + ((cameraHeight / 2 * cameraZoom));
        } else if (cameraBottom >= worldBottom) {
            camera.position.y = worldBottom - ((cameraHeight / 2 * cameraZoom));
        }


        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Render a starfield
        starfield.render(batch, delta);

        // Render the TARDIS
        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        if(debug) {
            font.drawMultiLine(batch,
                    "TARDIS Location\n" +
                            "X0: " + tardisPositionX0 + "\n" +
                            "X1: " + tardisPositionX1 + "\n" +
                            "Y0: " + tardisPositionY0 + "\n" +
                            "Y1: " + tardisPositionY1 + "\n" +
                            "Camera Position (x, y): " + (int) camera.position.x + "," + (int) camera.position.y + "\n" +
                            "Camera Left: " + cameraLeft + "\n" +
                            "Camera Right: " + cameraRight + "\n" +
                            "Camera Top: " + cameraTop + "\n" +
                            "Camera Bottom: " + cameraBottom + "\n" +
                            "Camera Zoom: " + camera.zoom + " (float), " + (int) camera.zoom + " (int)"
                    ,
                    camera.position.x - (Gdx.graphics.getWidth() / 2 * cameraZoom), camera.position.y - (Gdx.graphics.getHeight() / 2 * cameraZoom));
        }

        // Render the meteoroids
        for (Meteoroid meteoroid : myWorld.getMeteoroids()) {
            meteoroid.render(batch, delta);
        }
        batch.end();

        if (debug) {
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(1, 0, 0, 1);
            renderer.rect(tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
            for (Meteoroid meteoroid : myWorld.getMeteoroids()) {
                renderer.rect(meteoroid.getPosition().x, meteoroid.getPosition().y, meteoroid.getWidth(), meteoroid.getHeight());
            }
            renderer.end();
        }

        // Draw center crosshair for debugging
//        shapeRenderer.begin(ShapeType.Line);
//        shapeRenderer.setColor(1, 0, 0, 1);
//        shapeRenderer.circle(width / 2, height / 2, 10);
//        shapeRenderer.line(width/2, 0, width/2, height);
//        shapeRenderer.line(0, height/2, width, height/2);
//        shapeRenderer.end();
    }

    public void resize(int width, int height) {
        cameraWidth = width;
        cameraHeight = height;
    }

    public void initGameObjects() {
        starfield = new Starfield(myWorld.getWidth(), myWorld.getHeight(), 2000);

        tardis = myWorld.getTardis();
    }

    public void setCameraZoom(float zoomAmount) {
        cameraZoom += zoomAmount * Gdx.graphics.getDeltaTime();
        if (cameraZoom > 1.0f) cameraZoom = 1.0f; //limit to 1.0f zoom max
        if (cameraZoom < .10f) cameraZoom = .10f; //limit to .01f zoom min
    }

    public void toggleDebug() {
        if (debug) {
            debug = false;
        } else {
            debug = true;
        }
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
        AssetLoader.dispose();
    }
}
