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

	public World (Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		this.world = this;
		this.viewport = viewport;

		background = new GameObject(Assets.background);
		addActor(background);

		map = new Map();
		addActor(map);

		for (int i = 1; i < 4; i++) {
			map.addUnit(new Unit(Team.RED, Map.ROW - 2, i));
			map.addUnit(new Unit(Team.BLUE, 1, i));
		}
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
