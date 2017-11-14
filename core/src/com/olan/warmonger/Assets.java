package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
  private static Texture backgroundTexture;
  public static TextureAtlas sprite;

  public static TextureRegion background;
  public static AtlasRegion tile;

  public static AtlasRegion pikemanBack;
  public static AtlasRegion tileMark;
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
    backgroundTexture = new Texture("background.png");
    background = new TextureRegion(backgroundTexture);

    tile = loadTexture("tile");
    pikemanBack = loadTexture("pikeman-back");
    tileMark = loadTexture("tile-mark");
    selectionNormal = loadTexture("selection-normal");
    selectionCombat = loadTexture("selection-combat");
    castleBlue = loadTexture("castle-blue");
    castleRed = loadTexture("castle-red");
  }

  public static void dispose () {
    sprite.dispose();
    backgroundTexture.dispose();
  }
}
