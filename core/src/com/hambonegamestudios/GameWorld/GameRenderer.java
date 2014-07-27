package com.hambonegamestudios.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hambonegamestudios.GameHelpers.AssetLoader;
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
    private int cameraWidth, cameraHeight;
    private TARDIS tardis;
    private int tardisWidth, tardisHeight, tardisPositionX0, tardisPositionX1, tardisPositionY0, tardisPositionY1;
    private BitmapFont font;
    private SpriteBatch batch;
    private float cameraZoom;

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

        initGameObjects();
    }

    public void render(float runTime) {
        //System.out.println("GameRenderer - render() called");

        // Draw a black background to prevent flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set camera properties
        camera.position.set(tardis.getPosition().x + tardis.getWidth() / 2, tardis.getPosition().y + tardis.getHeight() / 2, 0);
        camera.viewportWidth = cameraWidth;
        camera.viewportHeight = cameraHeight;
        camera.zoom = cameraZoom;
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Draw background tiles
        for (int y = -1; y < myWorld.getHeight() / AssetLoader.boundingBoxTexture.getHeight() + 1; y ++) {
            for (int x = -1; x < myWorld.getWidth() / AssetLoader.boundingBoxTexture.getWidth() + 1; x++) {
                batch.draw(AssetLoader.boundingBoxTexture, x * AssetLoader.boundingBoxTexture.getWidth(), y * AssetLoader.boundingBoxTexture.getHeight(), 0, 0, AssetLoader.boundingBoxTexture.getWidth(), AssetLoader.boundingBoxTexture.getHeight());
            }
        }

        // Get TARDIS properties
        tardisWidth = tardis.getWidth();
        tardisHeight = tardis.getHeight();
        tardisPositionX0 = (int)tardis.getPosition().x;
        tardisPositionX1 = tardisPositionX0 + tardisWidth;
        tardisPositionY0 = (int)tardis.getPosition().y;
        tardisPositionY1 = tardisPositionY0 + tardisHeight;

        // Render the TARDIS
        batch.draw(AssetLoader.tardisAnimation.getKeyFrame(runTime), tardis.getPosition().x, tardis.getPosition().y, tardis.getWidth(), tardis.getHeight());
        font.drawMultiLine(batch,
                "TARDIS Location\n" +
                "X0: " + tardisPositionX0 + "\n" +
                "X1: " + tardisPositionX1 + "\n" +
                "Y0: " + tardisPositionY0 + "\n" +
                "Y1: " + tardisPositionY1,
                camera.position.x - (cameraWidth / 2), camera.position.y - (cameraHeight / 2));
        batch.end();

        // Draw center crosshair for debugging
//        shapeRenderer.begin(ShapeType.Line);
//        shapeRenderer.setColor(1, 0, 0, 1);
//        shapeRenderer.circle(width / 2, height / 2, 10);
//        shapeRenderer.line(width/2, 0, width/2, height);
//        shapeRenderer.line(0, height/2, width, height/2);
//        shapeRenderer.end();
    }

    public void initGameObjects() {
        tardis = myWorld.getTardis();
    }

    public void setCameraZoom(float zoomAmount) {
        if(cameraZoom + zoomAmount > .10f && cameraZoom + zoomAmount < 1.0f) {
            cameraZoom += zoomAmount;
        }
    }

}
