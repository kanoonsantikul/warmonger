package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Castle extends TileObject {
  private int row;
  private int column;

  private int health;

  public static float manualOffsetX = 5.0f;
  public static float manualOffsetY = 15.0f;

  public Castle (int row, int column) {
    super(Assets.castleBlue, row, column);
  }
}
