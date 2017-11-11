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

	Map map;

	public World (Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		this.world = this;
		this.viewport = viewport;

		map = new Map();
		Unit unit = new Unit(0, 0);
		map.addUnit(unit);

		addActor(map);
		for (int i=0; i<Map.WIDTH; i++) {
			for (int j=0; j<Map.HEIGHT; j++) {
				addActor(map.getTile(i, j));
			}
		}

		for (Unit u : map.getUnits()) {
			addActor(u);
		}
	}

	public static World instance () {
		return world;
	}

	@Override
	public void draw () {
		super.draw();
  }
}
