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
  private Team currentTeam;

  private Player redPlayer = new Player(Team.RED);
  private Player bluePlayer = new Player(Team.BLUE);

  private MapState mapState;

  public Map () {
    offsetX = (World.WIDTH - width) / 2;
    offsetY = (World.HEIGHT - height) / 2;

    initTiles();
    initResources();
    initCastles();

    currentTeam = Team.BLUE;

    mapState = new MapState();
    mapState.set(new StateIdle((this)));
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
    for (int column = 0; column < COLUMN; column++) {
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

  public Unit getUnit (int row, int column) {
    for (Unit unit : units) {
      if (unit.getRow() == row && unit.getColumn() == column) {
        return unit;
      }
    }
    return null;
  }

  public void addUnit (Unit unit) {
    unit.addListener(this);
    unit.setOnTile(tiles[unit.getRow()][unit.getColumn()]);
    units.add(unit);
    addActor(unit);
  }

  public Unit getSelectedUnit () {
    return this.selectedUnit;
  }

  public void selectUnit (Unit selectedUnit) {
    this.selectedUnit = selectedUnit;
  }

  public float getOffsetX () {
    return this.offsetX;
  }

  public float getOffsetY () {
    return this.offsetY;
  }

  public void setState (MapState.State state) {
    mapState.set(state);
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (mapState.is(StateUnitSelected.class)) {
      if (getSelectedUnit().canMoveTo(tile)) {
        mapState.set(new StateUnitMove(this, getSelectedUnit(), tile));
      } else {
        mapState.set(new StateIdle(this));
      }
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (mapState.is(StateIdle.class)) {
      mapState.set(new StateUnitSelected(this, unit));
    } else if (mapState.is(StateUnitSelected.class)) {
      if (unit.getTeam() == getSelectedUnit().getTeam()) {
        mapState.set(new StateUnitSelected(this, unit));
      } else {

      }
    }
  }

  public void act () {
    mapState.run();
  }
}
