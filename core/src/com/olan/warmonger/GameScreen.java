package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends ScreenAdapter {
	private SpriteBatch batch;

	private FitViewport worldViewport;
	private World world;
	private InputMultiplexer plexInput;

	public GameScreen (SpriteBatch batch) {
		this.batch = batch;

		Assets.load();

		plexInput = new InputMultiplexer();
		Gdx.input.setInputProcessor(plexInput);

		worldViewport = new FitViewport(World.WIDTH, World.HEIGHT);
		initWorld();
		GLProfiler.enable();
	}

	public void initWorld () {
		if (world != null) {
			this.plexInput.removeProcessor(world);
		}
		world = new World(worldViewport, batch);
		this.plexInput.addProcessor(world);
	}

	@Override
	public void render (float delta) {
		GLProfiler.reset();

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (world.isEnd()) {
			initWorld();
		}

		world.act();
		world.draw();
	}

	@Override
	public void resize (int width, int height) {
		worldViewport.update(width, height);
	}

	@Override
	public void dispose () {
		world.dispose();
		Assets.dispose();
	}
}
