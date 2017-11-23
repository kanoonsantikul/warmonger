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

  private GameDriven gameDriven;
  private Unit selectedUnit;
  private Team currentTeam;

  private Player redPlayer = new Player(Team.RED);
  private Player bluePlayer = new Player(Team.BLUE);

  private int redResources;
  private int blueResources;

  public Map () {
    currentTeam = Team.BLUE;

    gameDriven = new GameDriven();
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

  public int getBlueResources () {
    return blueResources;
  }

  public void setBlueResources (int resources) {
    this.blueResources = resources;
  }

  public int getRedResources() {
    return redResources;
  }

  public void setRedResources (int resources) {
    this.redResources = resources;
  }

  public Team getCurrentTeam () {
    return this.currentTeam;
  }

  public void setCurrentTeam (Team currentTeam) {
    this.currentTeam = currentTeam;
  }

  public void setState (GameDriven.Action state) {
    gameDriven.setState(state);
  }

  public void act () {
    gameDriven.run();
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (gameDriven.getState() != null) {
      gameDriven.getState().onTileClicked(tile, row, column);
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (gameDriven.getState() != null) {
      gameDriven.getState().onUnitClicked(unit, row, column);
    }
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {
    if (gameDriven.getState() != null) {
      gameDriven.getState().onBuildingClicked(building, row, column);
    }
  }
}
