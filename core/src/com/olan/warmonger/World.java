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

  Texture image = new Texture("badlogic.jpg");

	public World (Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		this.world = this;
		this.viewport = viewport;
		addActor(new Unit(0, 0));
	}

	public static World instance () {
		return world;
	}

	@Override
	public void draw () {
		super.draw();
  }
}
