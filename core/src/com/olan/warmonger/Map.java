package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;

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

  private Unit selectedEnemy;
  private boolean isSelectEnemy = false;

  private Team currentTeam = Team.RED;

  Player redPlayer = new Player(Team.RED);
  Player bluePlayer = new Player(Team.BLUE);


  public Map () {
    offsetX = (World.WIDTH - width) / 2;
    offsetY = (World.HEIGHT - height) / 2;
    initTiles();
    initResources();
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

  private void initResources () {
    Tile tile;
    (tile = findEmptyTile()).setResource(3);
    tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(3);
    for (int i = 0; i < 2; i++) {
      (tile = findEmptyTile()).setResource(2);
      tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(2);
    }
    for (int i = 0; i < 4; i++) {
      (tile = findEmptyTile()).setResource(1);
      tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(1);
    }
  }

  private Tile findEmptyTile () {
    Tile tile;
    do {
      int row = MathUtils.random(2, (ROW / 2) - 1);
      int column = MathUtils.random(0, COLUMN - 1);
      tile = tiles[row][column];
    } while(tile == null || tile.getResource() != 0);
    return tile;
  }

  private void initCastles () {
    Castle castle;
    for (int column = 0; column < 5; column++) {
      castle = new Castle(Team.BLUE, 0, column);
      castle.setOnTile(tiles[0][column]);
      castles.add(castle);
      addActor(castle);

      castle = new Castle(Team.RED, ROW - 1, column);
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
        selectedTile = tile;
        isSelectTile = true;
      }

      if (isSelectEnemy) {
        selectedEnemy = null;
        isSelectEnemy = false;
      }
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (!isUnitMoving) {
      if (isSelectUnit) {
        if (selectedUnit.getTeam() == unit.getTeam()) {
          selectedUnit = unit;
          isSelectUnit = true;
        } else {
          selectedEnemy = unit;
          isSelectEnemy = true;
        }
      } else {
        if (currentTeam == unit.getTeam()) {
          selectedUnit = unit;
          isSelectUnit = true;
        }
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

          if (currentTeam == Team.RED) {
            currentTeam = Team.BLUE;
          } else {
            currentTeam = Team.RED;
          }
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

      if (selectedUnit.getTeam() == Team.BLUE) {
        if ((i <= selectedUnit.getRow() + selectedUnit.getMoveRange())
        && (i > selectedUnit.getRow())) {
          showMoveRage(i, j);
        }
      } else {
        if ((i >= selectedUnit.getRow() - selectedUnit.getMoveRange())
        && (i < selectedUnit.getRow())) {
          showMoveRage(i, j);
        }
      }

    }
  }

  public void showMoveRage (int i, int j) {
    getTile(i, j).setTexture(Assets.selectionNormal);
  }
}
