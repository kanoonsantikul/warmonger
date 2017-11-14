package com.olan.warmonger;

import java.util.ArrayList;

public class Map implements Unit.UnitListener {
  public static final int WIDTH = 6;
  public static final int HEIGHT = 10;

  private Tile[][] tiles = new Tile[WIDTH][HEIGHT];
  private ArrayList<Unit> units = new ArrayList();

  public Map () {
    initTiles();
  }

  private void initTiles () {
    for (int i=0; i<WIDTH; i++) {
      for (int j=0; j<HEIGHT; j++) {
        tiles[i][j] = new Tile(i, j);
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

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
  }
}
