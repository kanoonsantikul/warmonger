package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Warmonger extends Game {
	private SpriteBatch batch;

	GameScreen gameScreen;

	@Override
	public void create () {
		Gdx.graphics.setContinuousRendering(false);
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
		batch.dispose();
	}
}
