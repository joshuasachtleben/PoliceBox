package com.hambonegamestudios.PoliceBox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hambonegamestudios.PoliceBox.PBGame;
import org.lwjgl.input.Keyboard;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Police Box";
        config.width = 900;
        config.height = config.width / 16* 9;
        new LwjglApplication(new PBGame(), config);
        Keyboard.enableRepeatEvents(true);
	}
}
