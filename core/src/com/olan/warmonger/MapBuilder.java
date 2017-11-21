package com.olan.warmonger;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class MapBuilder {
  private Map map;
  private float offsetX;
  private float offsetY;

  public Map build () {
    map = new Map();
    offsetX = (World.WIDTH - map.getWidth()) / 2;
    offsetY = (World.HEIGHT - map.getHeight()) / 2;

    initTiles(map.getTiles());
    initResources(map.getTiles());
    initBuildings(map.getTiles(), map.getBuildings());

    return map;
  }

  private void initTiles (Tile[][] tiles) {
    Tile tile;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = new Tile(i, j);
        tile.addListener(map);
        tile.setPosition(
            offsetX + Tile.WIDTH * j,
            offsetY + Tile.HEIGHT * i);
        tiles[i][j] = tile;
        map.addActor(tile);
      }
    }
  }

  private void initResources (Tile[][] tiles) {
    Tile tile;

    (tile = findEmptyTile(tiles)).setResource(3);
    tiles[Map.ROW - tile.getRow() - 1][Map.COLUMN - tile.getColumn() - 1].setResource(3);

    for (int i = 0; i < 2; i++) {
      (tile = findEmptyTile(tiles)).setResource(2);
      tiles[Map.ROW - tile.getRow() - 1][Map.COLUMN - tile.getColumn() - 1].setResource(2);
    }

    for (int i = 0; i < 4; i++) {
      (tile = findEmptyTile(tiles)).setResource(1);
      tiles[Map.ROW - tile.getRow() - 1][Map.COLUMN - tile.getColumn() - 1].setResource(1);
    }
  }

  private Tile findEmptyTile (Tile[][] tiles) {
    Tile tile;
    do {
      int row = MathUtils.random(2, (Map.ROW / 2) - 1);
      int column = MathUtils.random(0, Map.COLUMN - 1);
      tile = tiles[row][column];
    } while(tile == null || tile.getResource() != 0);
    return tile;
  }

  private void initBuildings (Tile[][] tiles, ArrayList<Building> buildings) {
    Building building;
    for (int column = 0; column < Map.COLUMN; column++) {
      building = new Building(Team.BLUE, 0, column);
      building.addListener(map);
      building.setOnTile(tiles[0][column]);
      buildings.add(building);
      map.addActor(building);

      building = new Building(Team.RED, Map.ROW - 1, column);
      building.addListener(map);
      building.setOnTile(tiles[Map.ROW - 1][column]);
      buildings.add(building);
      map.addActor(building);
    }
  }
}