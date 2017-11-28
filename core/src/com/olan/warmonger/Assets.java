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
  public static AtlasRegion tileMark;
  public static AtlasRegion lootMark;
  public static AtlasRegion selectionCombat;
  public static AtlasRegion selectionNormal;

  public static AtlasRegion pikemanBack;
  public static AtlasRegion pikemanFront;

  public static AtlasRegion cavalryBack;
  public static AtlasRegion cavalryFront;

  public static AtlasRegion archerBack;
  public static AtlasRegion archerFront;

  public static AtlasRegion castleBlue;
  public static AtlasRegion castleRed;

  public static AtlasRegion corn;
  public static AtlasRegion loot;
  public static AtlasRegion hearth;
  public static AtlasRegion resourceCountBlue;
  public static AtlasRegion resourceCountRed;

  public static AtlasRegion cardPikeman;
  public static AtlasRegion cardCavalry;
  public static AtlasRegion cardArcher;

  public static AtlasRegion cardPikemanGray;
  public static AtlasRegion cardCavalryGray;
  public static AtlasRegion cardArcherGray;

  public static BitmapFont worldFont;
  public static BitmapFont hudFont;

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
    tileMark = loadTexture("tile-mark");
    lootMark = loadTexture("loot-mark");
    selectionCombat = loadTexture("selection-combat");
    selectionNormal = loadTexture("selection-normal");

    pikemanBack = loadTexture("pikeman-back");
    pikemanFront = loadTexture("pikeman-front");

    cavalryBack = loadTexture("cavalry-back");
    cavalryFront = loadTexture("cavalry-front");

    archerBack = loadTexture("archer-back");
    archerFront = loadTexture("archer-front");

    castleBlue = loadTexture("castle-blue");
    castleRed = loadTexture("castle-red");

    cardPikeman = loadTexture("card-pikeman");
    cardCavalry = loadTexture("card-cavalry");
    cardArcher = loadTexture("card-archer");

    cardPikemanGray = loadTexture("card-pikeman-bw");
    cardCavalryGray = loadTexture("card-cavalry-bw");
    cardArcherGray = loadTexture("card-archer-bw");

    corn = loadTexture("corn");
    loot = loadTexture("loot");
    hearth = loadTexture("hearth");
    resourceCountBlue = loadTexture("resource-count-blue");
    resourceCountRed = loadTexture("resource-count-red");
  }

  public static void loadFont () {
    worldFont = loadFont("world-font");
    worldFont.setColor(Color.BLACK);

    hudFont = loadFont("hud-font");
  }

  public static void dispose () {
    worldFont.dispose();
    hudFont.dispose();
    sprite.dispose();
    backgroundTexture.dispose();
  }
}
