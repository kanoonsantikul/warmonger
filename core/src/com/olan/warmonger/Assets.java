package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
  public static TextureAtlas sprite;

  public static AtlasRegion background;
  public static AtlasRegion tile;
  public static AtlasRegion tileMark;
  public static AtlasRegion unit;
  public static AtlasRegion selectionNormal;
  public static AtlasRegion selectionCombat;
  public static AtlasRegion castleBlue;
  public static AtlasRegion castleRed;

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
    tileMark = loadTexture("tile-mark");
    unit = loadTexture("unit");
    selectionNormal = loadTexture("selection-normal");
    selectionCombat = loadTexture("selection-combat");
    castleBlue = loadTexture("castle-blue");
    castleRed = loadTexture("castle-red");
  }

  public static void dispose () {
    sprite.dispose();
  }
}
