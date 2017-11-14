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
    unit.setOffsetX(getOffsetX() + Unit.manualOffsetX);
    unit.setOffsetY(getOffsetY() + Unit.manualOffsetY);
    units.add(unit);
  }

  public void update () {
    for (int i=0; i<ROW; i++) {
      for (int j=0; j<COLUMN; j++) {
        if (this.isSelectUnit && this.selectedUnit != null) {
          getTile(i, j).setTexture(Assets.tileMark);
          if (j == selectedUnit.getColumn()) {
            if ((i <= selectedUnit.getRow() + selectedUnit.getMoveRange())
              && (i > selectedUnit.getRow())) {
                getTile(i, j).setTexture(Assets.selectionNormal);
            }
          }
        } else {
          getTile(i, j).setTexture(Assets.tile);
        }
      }
    }
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    this.selectedUnit = null;
    this.isSelectUnit = true;
    update();
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (unit != selectedUnit) {
      this.selectedUnit = unit;
    } else {
      this.isSelectUnit = !this.isSelectUnit;
    }
    update();
  }
}
