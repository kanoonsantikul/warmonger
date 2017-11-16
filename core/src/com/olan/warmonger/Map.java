package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Map extends Group implements Unit.UnitListener, Tile.TileListener {
  public static final int ROW = 10;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private float offsetX;
  private float offsetY;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Castle> castles = new ArrayList<Castle>();
  private ArrayList<Unit> units = new ArrayList<Unit>();

  private Unit selectedUnit;
  private boolean isSelectUnit = false;
  private boolean isUnitMoving = false;

  private Tile selectedTile;
  private boolean isSelectTile = false;

  public Map () {
    offsetX = (World.WIDTH - width) / 2;
    offsetY = (World.HEIGHT - height) / 2;
    initTiles();
    initCastles();
  }

  private void initTiles () {
    Tile tile;
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        tile = new Tile(i, j);
        tile.addListener(this);
        tile.setPosition(
            offsetX + Tile.WIDTH * j,
            offsetY + Tile.HEIGHT * i);
        tiles[i][j] = tile;
        addActor(tile);
      }
    }
  }

  private void initCastles () {
    Castle castle;
    for (int column = 0; column < 5; column++) {
      castle = new Castle(0, column);
      castle.setOnTile(tiles[0][column]);
      castles.add(castle);
      addActor(castle);

      castle = new Castle(ROW - 1, column);
      castle.setTexture(Assets.castleRed);
      castle.setOnTile(tiles[ROW - 1][column]);
      castles.add(castle);
      addActor(castle);
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
    unit.addListener(this);
    unit.setOnTile(tiles[unit.getRow()][unit.getColumn()]);
    units.add(unit);
    addActor(unit);
  }

  public float getOffsetX () {
    return this.offsetX;
  }

  public float getOffsetY () {
    return this.offsetY;
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (!isUnitMoving) {
      if (isSelectUnit) {
        this.selectedTile = tile;
        this.isSelectTile = true;
      }
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (!isUnitMoving) {
      if (selectedUnit == unit) {
        this.isSelectUnit = !this.isSelectUnit;
      } else {
        this.selectedUnit = unit;
        this.isSelectUnit = true;
      }
    }
  }

  public void act () {
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        if (!isSelectUnit) {
          getTile(i, j).setTexture(Assets.tile);
        }
        if (isSelectUnit && !isSelectTile) {
          showTileMark(i, j);
        }
      }
    }

    if (isSelectUnit && isSelectTile) {
      if (selectedUnit.canMoveTo(selectedTile)) {
        if (selectedUnit.isMovingTo(selectedTile)) {
          isUnitMoving = true;
        } else {
          isUnitMoving = false;
          selectedUnit.setOnTile(selectedTile);
        }

      } else {
        isSelectUnit = false;
        isSelectTile = false;
        selectedUnit = null;
        selectedTile = null;
      }
    }
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
