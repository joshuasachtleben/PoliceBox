package com.hambonegamestudios.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/20/2014
 * Time:       8:47 PM
 * Project:    PoliceBox
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion tardis_0, tardis_1, tardis_2, tardis_3, tardis_4, tardis_5, tardis_6, tardis_7, tardis_8, tardis_9, tardis_10, tardis_11, tardis_12;
    public static Animation tardisAnimation;

    public static void load() {
        texture = new Texture(Gdx.files.internal("TARDIS.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        tardis_0 = new TextureRegion(texture, 0, 0, 32, 32);
        tardis_0.flip(false, true);
        tardis_1 = new TextureRegion(texture, 32, 0, 32, 32);
        tardis_1.flip(false, true);
        tardis_2 = new TextureRegion(texture, 64, 0, 32, 32);
        tardis_2.flip(false, true);
        tardis_3 = new TextureRegion(texture, 96, 0, 32, 32);
        tardis_3.flip(false, true);
        tardis_4 = new TextureRegion(texture, 128, 0, 32, 32);
        tardis_4.flip(false, true);
        tardis_5 = new TextureRegion(texture, 160, 0, 32, 32);
        tardis_5.flip(false, true);
        tardis_6 = new TextureRegion(texture, 192, 0, 32, 32);
        tardis_6.flip(false, true);
        tardis_7 = new TextureRegion(texture, 224, 0, 32, 32);
        tardis_7.flip(false, true);
        tardis_8 = new TextureRegion(texture, 256, 0, 32, 32);
        tardis_8.flip(false, true);
        tardis_9 = new TextureRegion(texture, 288, 0, 32, 32);
        tardis_9.flip(false, true);
        tardis_10 = new TextureRegion(texture, 320, 0, 32, 32);
        tardis_10.flip(false, true);
        tardis_11 = new TextureRegion(texture, 352, 0, 32, 32);
        tardis_11.flip(false, true);
        tardis_12 = new TextureRegion(texture, 384, 0, 32, 32);
        tardis_12.flip(false, true);

        TextureRegion [] tardisSpin = {tardis_0, tardis_1, tardis_2, tardis_3, tardis_4, tardis_5, tardis_6, tardis_7, tardis_8, tardis_9, tardis_10, tardis_11, tardis_12};
        tardisAnimation = new Animation(.12f, tardisSpin);
        tardisAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public static void dispose() {
        texture.dispose();
    }
}
