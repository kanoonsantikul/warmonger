package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;

public class Map extends Group {
  public static final int ROW = 9;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Building> buildings = new ArrayList<Building>();
  private ArrayList<Unit> units = new ArrayList<Unit>();

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
    unit.setListener(World.instance());
    unit.setOnTile(tiles[unit.getRow()][unit.getColumn()]);
    units.add(unit);
    addActor(unit);
  }
}
