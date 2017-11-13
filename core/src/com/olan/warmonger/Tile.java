package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile extends GameObject {
  private int row;
  private int column;

  public Tile (int row, int column) {
    setRow(row);
    setColumn(column);
    setTexture(Assets.transparentTile);
  }

  public void setRow (int row) {
    this.row = row;
  }

  public void setColumn (int column) {
    this.column = column;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }

  public void indexToXY () {
    setX(row * getWidth());
    setY(column * getHeight());
  }

  @Override
  public void act (float delta) {
    indexToXY();
  }
}
