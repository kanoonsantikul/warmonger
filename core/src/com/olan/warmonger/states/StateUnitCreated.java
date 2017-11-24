package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class StateUnitCreated implements GameDriven.State {
  private Map map;
  private Unit unit;
  private Tile tile;

  public StateUnitCreated (Map map, Unit unit) {
    this.map = map;
    this.unit = unit;
  }

  public void enter () {
    Tile tile;
    int availableTileCount = 0;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(true);

        if (map.getUnit(i, j) == null) {
          if ((map.getCurrentTeam() == Team.RED) && (i == Map.ROW - 2)) {
            tile.selectionVisible(true);
            availableTileCount++;
          } else if ((map.getCurrentTeam() == Team.BLUE) && (i == 1)) {
            tile.selectionVisible(true);
            availableTileCount++;
          }
        }
      }
    }

    if (availableTileCount != 0) {
      map.setCreatedUnit(unit);
      map.addActor(map.getCreatedUnit());
      map.getCreatedUnit().setTouchable(Touchable.disabled);
    } else {
      map.setState(new StateIdle(map));
    }
  }

  public void exit () {
    Tile tile;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(false);
        tile.selectionVisible(false);
      }
    }

    map.setCreatedUnit(null);
  }

  public void run () {
    if (map.getCreatedUnit() != null) {
      map.getCreatedUnit().setCenter(new Vector2(Gdx.input.getX(),
      Gdx.graphics.getHeight() - Gdx.input.getY()));
    }
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (tile.isSelectionVisible()) {
      map.getCreatedUnit().setOnTile(tile);
      map.addUnit(map.getCreatedUnit());
      map.getCreatedUnit().setTouchable(Touchable.enabled);
      map.setState(new ActionEndTurn(map, map.getCurrentTeam()));
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {

  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {

  }

  @Override
  public void onUnitFactoryClicked (Unit unit) {

  }
}
