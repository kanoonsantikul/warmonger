package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileObject extends GameObject {
  private int row;
  private int column;

  private float offsetX;
  private float offsetY;

  public TileObject (int row, int column) {
    this(null, row, column);
  }

  public TileObject (TextureRegion texture, int row, int column) {
    super(texture);

    setRow(row);
    setColumn(column);
  }

  public int getRow () {
    return this.row;
  }

  public void setRow (int row) {
    this.row = row;
  }

  public int getColumn () {
    return this.column;
  }

  public void setColumn (int column) {
    this.column = column;
  }

  public float getOffsetX () {
    return this.offsetX;
  }

  public void setOffsetX (float offsetX) {
    this.offsetX = offsetX;
  }

  public float getOffsetY () {
    return this.offsetY;
  }

  public void setOffsetY (float offsetY) {
    this.offsetY = offsetY;
  }

  public void setOnTile (Tile tile) {
    setCenter(tile.getCenterX() + getOffsetX(),
        tile.getCenterY() + getOffsetY());
  }
}
