package com.subzero.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Entity {

	public Bullet(float x, float y, float health, boolean hostile, AssetManager assetManager, boolean shouldPlaySound) {
		super(x, y, health, hostile, assetManager);
		texture = assetManager.get("bullet2.png", Texture.class);
		sprite = new Sprite(texture);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setSize(5, 6);
		if(shouldPlaySound)
			assetManager.get("Laser_Shoot3.wav", Sound.class).play(0.1f);
	}

	public void render(SpriteBatch batch) {
		if (health > 0) {
			sprite.draw(batch);
			y += 2;
			sprite.setX(x);
			sprite.setY(y);
		}
	}

}
