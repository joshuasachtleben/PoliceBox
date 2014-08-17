package com.hambonegamestudios.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/20/2014
 * Time:       8:47 PM
 * Project:    PoliceBox
 */
public class AssetLoader {

    public static Texture starTexture;
    public static Texture TARDIStexture;
    public static Texture boundingBoxTexture;
    public static Texture meteoroidTexture;
    public static TextureRegion meteoroid_1, meteoroid_2, meteoroid_3, meteoroid_4, meteoroid_5;
    public static TextureRegion tardis_0, tardis_1, tardis_2, tardis_3, tardis_4, tardis_5, tardis_6, tardis_7, tardis_8, tardis_9, tardis_10, tardis_11, tardis_12;
    //public static Texture
    public static BitmapFont font, shadow;
    public static Animation tardisAnimation;

    public static void load() {

        /*
            Load fonts
         */
        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        /*
            Load Star texture
         */
        starTexture = new Texture(Gdx.files.internal("star.png"));

        /*
            Load Meteoroid texture
         */

        //meteoroidTexture = new Texture(Gdx.files.internal("meteoroid_32x32.png"));
        //meteoroidTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        meteoroidTexture = new Texture(Gdx.files.internal("meteoroids.png"));
        meteoroid_1 = new TextureRegion(meteoroidTexture, 33, 1, 31, 31);
        meteoroid_2 = new TextureRegion(meteoroidTexture, 65, 2, 31, 28);

        /*
            Load Bounding Box texture
         */
        boundingBoxTexture = new Texture(Gdx.files.internal("boundingBox_32x32.png"));
        boundingBoxTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        boundingBoxTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        /*
         * Load TARDIS textures
         */
        TARDIStexture = new Texture(Gdx.files.internal("TARDIS.png"));
        TARDIStexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        tardis_0 = new TextureRegion(TARDIStexture, 9, 3, 14, 26);
        tardis_0.flip(false, true);
        tardis_1 = new TextureRegion(TARDIStexture, 41, 3, 14, 26);
        tardis_1.flip(false, true);
        tardis_2 = new TextureRegion(TARDIStexture, 73, 3, 14, 26);
        tardis_2.flip(false, true);
        tardis_3 = new TextureRegion(TARDIStexture, 105, 3, 14, 26);
        tardis_3.flip(false, true);
        tardis_4 = new TextureRegion(TARDIStexture, 137, 3, 14, 26);
        tardis_4.flip(false, true);
        tardis_5 = new TextureRegion(TARDIStexture, 169, 3, 14, 26);
        tardis_5.flip(false, true);
        tardis_6 = new TextureRegion(TARDIStexture, 201, 3, 14, 26);
        tardis_6.flip(false, true);
        tardis_7 = new TextureRegion(TARDIStexture, 233, 3, 14, 26);
        tardis_7.flip(false, true);
        tardis_8 = new TextureRegion(TARDIStexture, 265, 3, 14, 26);
        tardis_8.flip(false, true);
        tardis_9 = new TextureRegion(TARDIStexture, 297, 3, 14, 26);
        tardis_9.flip(false, true);
        tardis_10 = new TextureRegion(TARDIStexture, 329, 3, 14, 26);
        tardis_10.flip(false, true);
        tardis_11 = new TextureRegion(TARDIStexture, 361, 3, 14, 26);
        tardis_11.flip(false, true);
        tardis_12 = new TextureRegion(TARDIStexture, 393, 3, 14, 26);
        tardis_12.flip(false, true);

        TextureRegion [] tardisSpin = {tardis_0, tardis_1, tardis_2, tardis_3, tardis_4, tardis_5, tardis_6, tardis_7, tardis_8, tardis_9, tardis_10, tardis_11, tardis_12};
        tardisAnimation = new Animation(.12f, tardisSpin);
        tardisAnimation.setPlayMode(Animation.PlayMode.LOOP);

        /* Load explosion textures */


    }

    public static void dispose() {

        TARDIStexture.dispose();
        boundingBoxTexture.dispose();
        meteoroidTexture.dispose();
        font.dispose();
        shadow.dispose();
    }
}
