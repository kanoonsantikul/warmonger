package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class StateUnitCreating implements GameDriven.State {
  private Map map;
  private Unit createdUnit;
  private Tile tile;

  public StateUnitCreating (Map map, Unit unit) {
    this.map = map;
    this.createdUnit = unit;
  }

  public void enter () {
    Tile tile;
    boolean tileEmpty = false;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(true);

        if (map.getUnit(i, j) == null) {
          if ((map.getCurrentTeam() == Team.RED) && (i == Map.ROW - 2)) {
            tile.selectionVisible(true);
            tileEmpty = true;
          } else if ((map.getCurrentTeam() == Team.BLUE) && (i == 1)) {
            tile.selectionVisible(true);
            tileEmpty = true;
          }
        }
      }
    }

    if (tileEmpty) {
      createdUnit.setTeam(map.getCurrentTeam());
      createdUnit.setTouchable(Touchable.disabled);
      map.addActor(createdUnit);
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
  }

  public void run () {
    createdUnit.setCenter(
        Gdx.input.getX(),
        Gdx.graphics.getHeight() - Gdx.input.getY());
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (tile.isSelectionVisible()) {
      createdUnit.setOnTile(tile);
      map.addUnit(createdUnit);
      createdUnit.setTouchable(Touchable.enabled);

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
