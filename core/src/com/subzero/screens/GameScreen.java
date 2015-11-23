package com.subzero.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.subzero.entities.Bullet;
import com.subzero.entities.Enemy;
import com.subzero.entities.PlayerShip;
import com.subzero.images.ImageProvider;
import com.subzero.spacecaptain.Formations;
import com.subzero.spacecaptain.SpaceCaptain;

public class GameScreen implements Screen {
	SpaceCaptain game;
	SpriteBatch batch;
	ImageProvider imageProvider = new ImageProvider();
	private OrthographicCamera camera;
	private Viewport viewport;
	PlayerShip playerShip;
	Enemy[] enemy = new Enemy[20];
	private ShapeRenderer shapeRenderer;
	Rectangle rects[][] = new Rectangle[10][30];
	Rectangle glowRects[][] = new Rectangle[10][30];
	Random rand;
	int start = 0;
	AssetManager assetManager;
	Formations formations;

	public GameScreen(SpaceCaptain game, AssetManager assetManager) {
		this.game = game;
		this.assetManager = assetManager;
		camera = new OrthographicCamera();
		viewport = new FitViewport(imageProvider.getScreenWidth(), imageProvider.getScreenHeight(), camera);
		camera.setToOrtho(false, imageProvider.getScreenWidth(), imageProvider.getScreenHeight());
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		playerShip = new PlayerShip(60, 5, 100, false, assetManager);
		for (int i = 0; i < enemy.length; i++)
			enemy[i] = new Enemy(-30+(start += 30), imageProvider.getScreenHeight() - 35, 100, true, assetManager);
		formations = new Formations();
		formations.addFormation();
		for(Enemy e : enemy)
			formations.addMember(0, e);

		createStars();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		formations.render(batch);
		for(Enemy e : enemy)
			e.render(batch);
		//for (int i = 0; i < enemy.length; i++)
			//enemy[i].render(batch);
		playerShip.render(batch, camera);
		batch.end();

	}

	public void createStars() {
		rand = new Random();
		for (int i = 0; i < rects.length; i++) { // Make Rectangles here then
													// draw them in render
			for (int j = 0; j < rects[0].length; j++) {
				rects[i][j] = new Rectangle(i * 20 + (rand.nextInt(15) - 15), j * 10 + (rand.nextInt(15) - 15), 1, 1);
				glowRects[i][j] = new Rectangle(rects[i][j].x - 1, rects[i][j].y - 1, 3, 3);
			}

		}
	}

	public void update() {
		starField();
		checkCollisions();
	}

	public void checkCollisions() {
		// System.out.println(enemy.getHealth());
		for (Bullet b : playerShip.getBullets()) {
			for (Enemy e : enemy) {
				if (e.getHealth() > 0)
					if (e.getSprite().getBoundingRectangle().overlaps(b.getSprite().getBoundingRectangle())) {
						if (b.getHealth() > 0) {
							e.setHealth(0);
							b.setHealth(0);
						}
					}
			}
		}
	}

	public void starField() {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 1, 1);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		for (int i = 0; i < rects.length; i++) {
			for (int j = 0; j < rects[0].length; j++) {
				shapeRenderer.setColor(1, 1, 1, 1);
				shapeRenderer.rect(rects[i][j].x, rects[i][j].y, rects[i][j].width, rects[i][j].height);
				shapeRenderer.setColor(1, 1, 1, 0.05f);
				shapeRenderer.rect(glowRects[i][j].x, glowRects[i][j].y, glowRects[i][j].width, glowRects[i][j].height);
				rects[i][j].y -= 0.7f;
				glowRects[i][j].y -= 0.7f;
				if (rects[i][j].y < 0) {
					rects[i][j] = new Rectangle(i * 20 + (rand.nextInt(15) - 15), imageProvider.getScreenHeight() + 20 + (rand.nextInt(15) - 15), 1, 1);
					glowRects[i][j] = new Rectangle(rects[i][j].x - 1, rects[i][j].y - 1, 3, 3);
				}
			}
		}

		shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
