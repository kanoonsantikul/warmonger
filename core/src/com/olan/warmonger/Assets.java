package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
  private static Texture backgroundTexture;
  public static TextureAtlas sprite;

  public static TextureRegion background;
  public static AtlasRegion tile;

  public static AtlasRegion pikemanBack;
  public static AtlasRegion pikemanFront;
  public static AtlasRegion tileMark;
  public static AtlasRegion selectionNormal;
  public static AtlasRegion selectionCombat;
  public static AtlasRegion castleBlue;
  public static AtlasRegion castleRed;
  public static AtlasRegion corn;

  public static BitmapFont worldFont;

  public static void load () {
    loadTexture();
    loadFont();
  }

  public static AtlasRegion loadTexture (String name) {
    return sprite.findRegion(name);
  }

  public static BitmapFont loadFont (String name) {
    return new BitmapFont(
        Gdx.files.internal(name + ".fnt"),
        Gdx.files.internal(name + ".png"),
        false);
  }

  public static void loadTexture () {
    sprite = new TextureAtlas("warmonger.atlas");
    backgroundTexture = new Texture("background.png");
    background = new TextureRegion(backgroundTexture);

    tile = loadTexture("tile");
    pikemanBack = loadTexture("pikeman-back");
    pikemanFront = loadTexture("pikeman-front");
    tileMark = loadTexture("tile-mark");
    selectionNormal = loadTexture("selection-normal");
    selectionCombat = loadTexture("selection-combat");
    castleBlue = loadTexture("castle-blue");
    castleRed = loadTexture("castle-red");
    corn = loadTexture("corn");
  }

  public static void loadFont () {
    worldFont = loadFont("world-font");
    worldFont.setColor(Color.BLACK);
  }

  public static void dispose () {
    worldFont.dispose();
    sprite.dispose();
    backgroundTexture.dispose();
  }
}
