package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class World extends Stage {
	public static final int WIDTH = Gdx.graphics.getWidth();
	public static final int HEIGHT = Gdx.graphics.getHeight();

	private static World world;
	private Viewport viewport;

	GameObject background;
	Map map;
	Hud hud;

	public World (Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		this.world = this;
		this.viewport = viewport;

		background = new GameObject(Assets.background);
		addActor(background);

		map = new MapBuilder().build();
		addActor(map);

		hud = new Hud();
		addActor(hud);
	}

	public static World instance () {
		return world;
	}

	@Override
	public void	act (float delta) {
		map.act();
	}

	@Override
	public void draw () {
		super.draw();
  }
}
