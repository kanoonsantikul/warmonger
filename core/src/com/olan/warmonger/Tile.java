package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Tile extends GameObject {
  public static final int WIDTH = Assets.tile.getRegionWidth();
  public static final int HEIGHT = Assets.tile.getRegionHeight();

  private int row;
  private int column;
  private TileListener listener;

  public Tile (int row, int column) {
    super(Assets.tile);

    setRow(row);
    setColumn(column);

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

  public interface TileListener {
    public void onTileClicked (Tile tile, int row, int column);
  }
}
