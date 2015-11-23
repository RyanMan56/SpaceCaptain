package com.subzero.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.subzero.effects.Explosion;

public class Entity {
	protected float x, y;
	protected float dx = 0.5f, dy = 0.5f;
	protected float speed = 0;
	protected float health;
	protected Texture texture;
	protected boolean hostile;
	protected Sprite sprite;
	protected Explosion explosion;
	protected AssetManager assetManager;
	
	public Entity(float x, float y, float health, boolean hostile, AssetManager assetManager){
		this.x = x;
		this.y = y;
		this.health = health;
		this.hostile = hostile;
		this.assetManager = assetManager;
		explosion = new Explosion(assetManager);
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
		sprite.setX(x);
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
		sprite.setY(y);
	}
	public float getDx() {
		return dx;
	}
	public void setDx(float dx) {
		this.dx = dx;
	}
	public float getDy() {
		return dy;
	}
	public void setDy(float dy) {
		this.dy = dy;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Sprite getSprite(){
		return sprite;
	}
	
	

}
