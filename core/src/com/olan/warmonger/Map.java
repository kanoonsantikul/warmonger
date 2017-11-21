package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

public class Map extends Group implements Unit.UnitListener,
    Tile.TileListener,
    Building.BuildingListener {
  public static final int ROW = 10;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Building> buildings = new ArrayList<Building>();
  private ArrayList<Unit> units = new ArrayList<Unit>();

  private Unit selectedUnit;
  private Team currentTeam;
  private MapState mapState;

  private Player redPlayer = new Player(Team.RED);
  private Player bluePlayer = new Player(Team.BLUE);

  public Map () {
    currentTeam = Team.BLUE;
    mapState = new MapState();
    setState(new StateIdle((this)));
  }

  @Override
  public float getWidth () {
    return this.width;
  }

  @Override
  public float getHeight () {
    return this.height;
  }

  public Tile[][] getTiles () {
    return tiles;
  }

  public Tile getTile (int row, int column) {
    return tiles[row][column];
  }

  public ArrayList<Building> getBuildings () {
    return buildings;
  }

  public Building getBuilding (int row, int column) {
    for (Building building : buildings) {
      if (building.getRow() == row && building.getColumn() == column) {
        return building;
      }
    }
    return null;
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

  public void setState (MapState.State state) {
    mapState.set(state);
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (mapState.is(StateUnitSelected.class)) {
      if (tile.isSelectionVisible()) {
        setState(new StateUnitMove(this, getSelectedUnit(), tile));
      } else {
        setState(new StateIdle(this));
      }
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (mapState.is(StateIdle.class)) {
      setState(new StateUnitSelected(this, unit));
    } else if (mapState.is(StateUnitSelected.class)) {
      if (unit.getTeam() == getSelectedUnit().getTeam()) {
        setState(new StateUnitSelected(this, unit));
      } else if (getTile(unit.getRow(), unit.getColumn()).isSelectionVisible()) {
        setState(new StateUnitCombat(this, getSelectedUnit(), unit));
      }
    }
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {
    if (mapState.is(StateUnitSelected.class)) {
      if (building.getTeam() == getSelectedUnit().getTeam()) {
        setState(new StateIdle(this));
      } else {
        setState(new StateBuildingCombat(this, getSelectedUnit(), building));
      }
    }
  }

  public void act () {
    mapState.run();
  }
}
