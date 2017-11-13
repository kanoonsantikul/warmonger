package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
  public static TextureAtlas sprite;

  public static AtlasRegion background;
  public static AtlasRegion tile;
  public static AtlasRegion transparentTile;
  public static AtlasRegion unit;
  public static AtlasRegion selectedUnit;

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
    transparentTile = loadTexture("transparent-tile");
    unit = loadTexture("unit");
    selectedUnit = loadTexture("selected-unit");
  }

  public static void dispose () {
    sprite.dispose();
  }
}
