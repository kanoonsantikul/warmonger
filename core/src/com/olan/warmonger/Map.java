package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Map extends GameObject implements Unit.UnitListener, Tile.TileListener {
  public static final int ROW = 10;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Unit> units = new ArrayList();

  public Map () {
    super(Assets.background);
    setOffsetX((World.WIDTH - width) / 2);
    setOffsetY((World.HEIGHT - height) / 2);
    initTiles();
  }

  private void initTiles () {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        tiles[i][j] = new Tile(i, j);
        tiles[i][j].setOffsetX(getOffsetX());
        tiles[i][j].setOffsetY(getOffsetY());
      }
    }
  }

  public Tile getTile (int row, int column) {
    return tiles[row][column];
  }

  public Tile[][] getTiles () {
    return tiles;
  }

  public ArrayList<Unit> getUnits () {
    return units;
  }

  public void addUnit (Unit unit) {
    unit.setOffsetX(getOffsetX());
    unit.setOffsetY(getOffsetY());
    units.add(unit);
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        getTile(i, j).setTexture(Assets.tileMark);
        if (j == column) {
          if ((i <= row + unit.getMoveRange()) && (i > row)) {
            getTile(i, j).setTexture(Assets.selectionNormal);
          }
        }
      }
    }
  }
}
