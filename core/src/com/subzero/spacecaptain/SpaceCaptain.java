package com.subzero.spacecaptain;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.subzero.screens.GameScreen;
import com.subzero.screens.MainMenuScreen;

public class SpaceCaptain extends Game {
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	AssetManager assetManager;
	private boolean loaded;

	@Override
	public void create() {
		assetManager = new AssetManager();
		assetManager.load("Ship1.png", Texture.class);
		assetManager.load("Ship2.png", Texture.class);
		assetManager.load("bullet.png", Texture.class);
		assetManager.load("bullet2.png", Texture.class);
		assetManager.load("explosion-1.png", Texture.class);
		assetManager.load("explosion-2.png", Texture.class);
		assetManager.load("explosion-4.png", Texture.class);
		assetManager.load("explosion-6.png", Texture.class);
		assetManager.load("Laser_Shoot3.wav", Sound.class);
		assetManager.load("Explosion2.wav", Sound.class);
		// gameScreen = new GameScreen(this);
		// setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();
		if (!loaded) {
			if (assetManager.update()) {
				gameScreen = new GameScreen(this, assetManager);
				setScreen(gameScreen);
				loaded = true;
			}
		}
	}

	// public void resize(int width, int height){
	// viewport.update(imageProvider.getScreenWidth(),
	// imageProvider.getScreenHeight(), true);
	// }
}
