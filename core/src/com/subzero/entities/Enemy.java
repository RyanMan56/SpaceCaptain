package com.subzero.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Entity{

	public Enemy(float x, float y, float health, boolean hostile, AssetManager assetManager) {
		super(x, y, health, hostile, assetManager);
		texture = assetManager.get("Ship2.png", Texture.class);
		sprite = new Sprite(texture, 27, 31);
		sprite.flip(false, true);
		sprite.setX(x);
		sprite.setY(y);
	}
	
	public void move(){
		
	}
	
	public void update(SpriteBatch batch, boolean draw){
		if(health > 0){
			move();
			if(draw)
				render(batch);
		}else{
			float size = sprite.getHeight();
			if(sprite.getWidth() > sprite.getHeight())
				size = sprite.getWidth();
			explosion.render(batch, x, y, size, size);
		}
	}
	
	public void render(SpriteBatch batch){
		if(health > 0)
			sprite.draw(batch);
	}

}
