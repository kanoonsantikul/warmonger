package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Tile extends GameObject {
  private int row;
  private int column;
  private TileListener listener;

  public Tile (int row, int column) {
    setRow(row);
    setColumn(column);
    setTexture(Assets.tileMark);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onTileClicked(Tile.this, Tile.this.row, Tile.this.column);
        }
      }
    });
  }

  public void addListener (TileListener listerner) {
      this.listener = listerner;
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
    setX((row * Assets.blockWidth) + ((Assets.blockWidth - getWidth()) / 2));
    setY((column * Assets.blockWidth) + ((Assets.blockWidth - getHeight()) / 2));
  }

  @Override
  public void act (float delta) {
    indexToXY();
  }

  public interface TileListener {
    public void onTileClicked (Tile tile, int row, int column);
  }
}
