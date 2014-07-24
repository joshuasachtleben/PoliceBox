package com.hambonegamestudios.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by: Joshua Sachtleben
 * Date:       7/24/2014
 * Time:       12:34 PM
 * Project:    PoliceBox
 */
public class StarBackground {

    private Pixmap pixmap;
    private Texture texture;
    private Sprite sprite;

    public StarBackground () {
        pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);

    }

    public void create() {
        pixmap.setColor(Color.RED);
        pixmap.fill();

        texture = new Texture(pixmap);
        pixmap.dispose();
        sprite = new Sprite(texture);
    }



}
