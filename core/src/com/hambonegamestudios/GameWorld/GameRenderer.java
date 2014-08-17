package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hambonegamestudios.GameHelpers.AssetLoader;
import com.hambonegamestudios.GameObjects.Meteoroid;
import com.hambonegamestudios.GameObjects.PoliceBox;
import com.hambonegamestudios.GameObjects.Starfield;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/19/2014
 * Time:       4:27 PM
 * Project:    PoliceBox
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera camera, HUDcamera;
    private int cameraWidth, cameraHeight;
    private Starfield starfield;
    private PoliceBox policeBox;
    private int tardisWidth, tardisHeight, tardisPositionX0, tardisPositionX1, tardisPositionY0, tardisPositionY1;
    private int worldLeft, worldRight, worldTop, worldBottom;
    private int cameraLeft, cameraRight, cameraTop, cameraBottom;
    private BitmapFont font;
    private SpriteBatch batch, HUDbatch;
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
        HUDcamera = new OrthographicCamera();
        cameraZoom = 1.0f;
        // may be able to divide width and height by 2 (or more) to scale objects when drawn
        camera.setToOrtho(true, cameraWidth, cameraHeight);
        HUDcamera.setToOrtho(true, cameraWidth, cameraHeight);
        System.out.println("Orthographic Camera created with dimensions " + cameraWidth + " x " + cameraHeight);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        HUDbatch = new SpriteBatch();
        HUDbatch.setProjectionMatrix(HUDcamera.combined);
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);

        initGameObjects();
    }

    public void render(float runTime, float delta) {
        //System.out.println("GameRenderer - render() called");

        // Draw a black background to prevent flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get World properties
        worldLeft = 0;
        worldRight = myWorld.getWidth();
        worldTop = 0;
        worldBottom = myWorld.getHeight();

        camera.viewportWidth = cameraWidth;
        camera.viewportHeight = cameraHeight;
        camera.zoom = cameraZoom;

        camera.position.set(policeBox.getPosition().x + policeBox.getWidth() / 2, policeBox.getPosition().y + policeBox.getHeight() / 2, 0);

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
        renderer.setProjectionMatrix(camera.combined);

        batch.begin();

        // Render a starfield
        starfield.render(batch, delta);

        // Render the TARDIS
        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), policeBox.getPosition().x, policeBox.getPosition().y, policeBox.getWidth(), policeBox.getHeight());

        // Render the meteoroids
        for (Meteoroid meteoroid : myWorld.getMeteoroids()) {
            meteoroid.render(batch, delta);
        }
        batch.end();

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(policeBox.getPosition().x - (policeBox.getWidth() / 2), policeBox.getPosition().y - 10, policeBox.getWidth() * 2, 3);
        renderer.setColor(0, 1, 0, 1);
        renderer.rect(policeBox.getPosition().x - (policeBox.getWidth() / 2), policeBox.getPosition().y - 10, policeBox.getLifebarWidth(), 3);
        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(0, 0, 0, 1);
        renderer.rect(policeBox.getPosition().x - (policeBox.getWidth() / 2), policeBox.getPosition().y - 10, policeBox.getWidth() * 2, 3);
        renderer.end();

        /* Draw HUD elements */

        HUDbatch.begin();
        font.setColor(1, 1, 1, 1);
        font.drawMultiLine(HUDbatch, "Score: " + Integer.toString(myWorld.getScore()) + "\n" + "Health: " + policeBox.getHealth() + "\n" + "Meteoroids: " + myWorld.getMeteoroids().size(), 0, 0);
        if(myWorld.getCurrentState() == GameWorld.GameState.GAMEOVER) {
            font.draw(HUDbatch, "GAME OVER!", (cameraWidth / 2) - (font.getBounds("GAME OVER").width / 2), (cameraHeight / 2) - (font.getBounds("GAME OVER").height / 2));
        }
        HUDbatch.end();

        if (debug) {
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(1, 0, 0, 1);
            renderer.rect(policeBox.getPosition().x, policeBox.getPosition().y, policeBox.getWidth(), policeBox.getHeight());
            for (Meteoroid meteoroid : myWorld.getMeteoroids()) {
                renderer.rect(meteoroid.getPosition().x, meteoroid.getPosition().y, meteoroid.getWidth(), meteoroid.getHeight());
            }
            renderer.end();
        }
    }

    public void resize(int width, int height) {
        cameraWidth = width;
        cameraHeight = height;
        camera.update();
    }

    public void initGameObjects() {
        starfield = new Starfield(myWorld.getWidth(), myWorld.getHeight(), 2000);

        policeBox = myWorld.getPoliceBox();
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
