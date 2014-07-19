package com.hambonegamestudios.PoliceBox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hambonegamestudios.PoliceBox.PBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Police Box";
		new LwjglApplication(new PBGame(), config);

	}
}
