package com.subzero.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class PlayerShip extends Entity {
	long lastShot = 0;
	Bullet bullets[] = new Bullet[10];
	int next = 0;

	public PlayerShip(float x, float y, float health, boolean hostile, AssetManager assetManager) {
		super(x, y, health, hostile, assetManager);
		texture = assetManager.get("Ship1.png", Texture.class);
		sprite = new Sprite(texture, 35, 40);
		sprite.setX(x);
		sprite.setY(y);
		lastShot = System.currentTimeMillis();
		for (int i = 0; i < bullets.length; i++)
			bullets[i] = new Bullet(2000, 2000, 1, false, assetManager, false);
	}

	public void move(Camera camera) {
		if (Gdx.input.isKeyPressed(Keys.A))
			speed = -1.5f;
		if (Gdx.input.isKeyPressed(Keys.D))
			speed = 1.5f;
		//if (Gdx.input.isKeyPressed(Keys.W))
			//y++;
		//if (Gdx.input.isKeyPressed(Keys.S))
			//y--;
		
		if(Gdx.input.isTouched()){
			if(camera.unproject(new Vector3(Gdx.input.getX(), 0, 0)).x > x + sprite.getWidth()/2)
				speed = 1.5f;
			if(camera.unproject(new Vector3(Gdx.input.getX(), 0, 0)).x < x + sprite.getWidth()/2)
				speed = -1.5f;
			/*if(Gdx.input.getX() > Gdx.graphics.getWidth()/2)
				x++;
			if(Gdx.input.getX() < Gdx.graphics.getWidth()/2)
				x--;
				*/
		}
		x += speed;
		speed *= dx;
		
		sprite.setX(x);
		sprite.setY(y);
	}

	public void render(SpriteBatch batch, Camera camera) {
		if (health > 0) {
			move(camera);
			sprite.draw(batch);

			updateBullets();

			for (int i = 0; i < bullets.length; i++) {
				bullets[i].render(batch);
			}
		}else{
			float size = sprite.getHeight();
			if(sprite.getWidth() > sprite.getHeight())
				size = sprite.getWidth();
			explosion.render(batch, x, y, size, size);
		}
	}

	public void updateBullets() {
		if (System.currentTimeMillis() >= lastShot + 200) {
			bullets[next] = new Bullet(x + 15, y + texture.getHeight() + 1, 1, false, assetManager, true);
			lastShot = System.currentTimeMillis();
			next++;
			if (next >= bullets.length)
				next = 0;
		}
	}

	public Bullet[] getBullets() {
		return bullets;
	}

}
