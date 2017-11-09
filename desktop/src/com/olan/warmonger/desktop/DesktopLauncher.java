package com.olan.warmonger.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.olan.warmonger.Warmonger;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;

		config.resizable = false;
		//config.fullscreen = true;
		config.vSyncEnabled = true;

		new LwjglApplication(new Warmonger(), config);
	}
}
