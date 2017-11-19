package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Tile extends GameObject {
  public static final int WIDTH = Assets.tile.getRegionWidth();
  public static final int HEIGHT = Assets.tile.getRegionHeight();

  private int row;
  private int column;
  private TileListener listener;

  private boolean markVisible = false;
  private boolean selectionVisible = false;
  private AtlasRegion selectionTexture;

  private int resource;
  private Text resourceText;

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

    resourceText = new Text(Assets.worldFont);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    if (markVisible) {
      batch.draw(Assets.tileMark,
          getCenterX() - Assets.tileMark.getRegionWidth() / 2,
          getCenterY() - Assets.tileMark.getRegionHeight() / 2);
    }

    if (selectionVisible) {
      batch.draw(selectionTexture,
          getCenterX() - selectionTexture.getRegionWidth() / 2,
          getCenterY() - selectionTexture.getRegionHeight() / 2);
    }

    if (getResource() != 0) {
      batch.draw(Assets.corn,
          getCenterX() - Assets.corn.getRegionWidth() / 2,
          getCenterY() - Assets.corn.getRegionHeight() / 2);
      resourceText.draw(batch);
    }
  }

  @Override
  protected void	positionChanged () {
    resourceText.setCenter(getCenterX() + 8, getCenterY() - 12);
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
    return this.row;
  }

  public int getColumn () {
    return this.column;
  }

  public int getResource () {
    return this.resource;
  }

  public void setResource (int resource) {
    this.resource = resource;
    resourceText.setText(resource + "");
  }

  public void markVisible (boolean visible) {
    this.markVisible = visible;
  }

  public void selectionCombatVisible (boolean visible) {
    this.selectionVisible = visible;
    selectionTexture = Assets.selectionCombat;
  }

  public void selectionVisible (boolean visible) {
    this.selectionVisible = visible;
    selectionTexture = Assets.selectionNormal;
  }

  public interface TileListener {
    public void onTileClicked (Tile tile, int row, int column);
  }
}
