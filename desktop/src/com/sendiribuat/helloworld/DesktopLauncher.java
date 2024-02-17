package com.sendiribuat.helloworld;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.sendiribuat.helloworld.helloworld;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//		config.setForegroundFPS(60);
		config.setTitle(helloworld.TITLE);
		config.setWindowedMode(helloworld.WIDTH, helloworld.HEIGHT);

		new Lwjgl3Application(new helloworld(), config);
	}
}
