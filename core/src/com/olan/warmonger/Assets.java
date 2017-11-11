package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
  public static TextureAtlas sprite;

  public static AtlasRegion background;
  public static AtlasRegion tile;
  public static AtlasRegion unit;

  public static void load () {
    loadTexture();
  }

  public static AtlasRegion loadTexture (String name) {
    return sprite.findRegion(name);
  }

  public static void loadTexture () {
    sprite = new TextureAtlas("warmonger.atlas");

    background = loadTexture("background");
    tile = loadTexture("tile");
    unit = loadTexture("unit");
  }

  public static void dispose () {
    sprite.dispose();
  }
}
