package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Map extends GameObject implements Unit.UnitListener, Tile.TileListener {
  public static final int ROW = 8;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Unit> units = new ArrayList();

  private Unit selectedUnit;
  private boolean isSelectUnit = false;

  private Tile selectedTile;
  private boolean isSelectTile = false;

  public Map () {
    super(Assets.background);
    setOffsetX((World.WIDTH - width) / 2);
    setOffsetY((World.HEIGHT - height) / 2);
    initTiles();
  }

  private void initTiles () {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        tiles[i][j] = new Tile(i, j, this);
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
    units.add(unit);
  }

  public void update () {
    for (int i=0; i<ROW; i++) {
      for (int j=0; j<COLUMN; j++) {
        if (isSelectUnit) {
          if (!isSelectTile) {
            showTileMark(i, j);
          } else {
            if (selectedUnit.canMove(selectedTile.getRow(), selectedTile.getColumn())) {
              selectedUnit.move(selectedTile.getRow(), selectedTile.getColumn());
            }
            isSelectUnit = false;
            isSelectTile = false;
            selectedUnit = null;
            selectedTile = null;
          }
        } else {
          getTile(i, j).setTexture(Assets.tile);
        }
      }
    }
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (isSelectUnit) {
      this.selectedTile = tile;
      this.isSelectTile = true;
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (unit != selectedUnit) {
      this.selectedUnit = unit;
      this.isSelectUnit = true;
    } else {
      this.isSelectUnit = !this.isSelectUnit;
    }
  }

  @Override
  public void act (float delta) {
    update();
  }

  public void showTileMark (int i, int j) {
    getTile(i, j).setTexture(Assets.tileMark);

    if (j == selectedUnit.getColumn()) {
      if ((i <= selectedUnit.getRow() + selectedUnit.getMoveRange())
        && (i > selectedUnit.getRow())) {
          showMoveRage(i, j);
      }
    }
  }

  public void showMoveRage (int i, int j) {
    getTile(i, j).setTexture(Assets.selectionNormal);
  }
}
