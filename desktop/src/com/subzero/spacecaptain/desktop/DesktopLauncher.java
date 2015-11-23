package com.subzero.spacecaptain.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.subzero.images.ImageProvider;
import com.subzero.spacecaptain.SpaceCaptain;

public class DesktopLauncher {
	static ImageProvider imageProvider = new ImageProvider();
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Captain";
		config.width = 640;//480;//imageProvider.getScreenWidth();
		config.height = 960;//800;//imageProvider.getScreenHeight();
		
		new LwjglApplication(new SpaceCaptain(), config);
	}
}
