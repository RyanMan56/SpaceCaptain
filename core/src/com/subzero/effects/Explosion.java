package com.subzero.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
	private Animation animation;
	private float elapsedTime = 0;
	private TextureRegion textureRegion;
	private TextureRegion[] animationTextures;
	private Sound sound;
	private boolean exploded = false; 
	
	public Explosion(AssetManager assetManager){
		textureRegion = new TextureRegion(assetManager.get("explosion-6.png", Texture.class));
		animationTextures = textureRegion.split(48, 48)[0];
		animation = new Animation(1/15f, animationTextures);
		sound = assetManager.get("Explosion2.wav", Sound.class);
	}
	
	public void render(SpriteBatch batch, float x, float y, float width, float height){
		elapsedTime += Gdx.graphics.getDeltaTime();
		if(!animation.isAnimationFinished(elapsedTime))
			batch.draw(animation.getKeyFrame(elapsedTime, false), x, y, width, height);
		if(!exploded){
			sound.play();
			exploded = true;
			//sound.dispose();
		}
		//System.out.println("OH GOD MY EARS");
	}
}
