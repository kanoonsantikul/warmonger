package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class StateUnitCreating implements GameDriven.State {
  private Map map;
  private Unit creatingUnit;
  private Tile tile;
  Player currentPlayer;

  public StateUnitCreating (Map map, Unit unit) {
    this.map = map;
    this.creatingUnit = unit;
  }

  public void enter () {
    Tile tile;
    boolean tileEmpty = false;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(true);

        if (map.getUnit(i, j) == null) {
          if ((World.instance().getCurrentTeam() == Team.RED) && (i == Map.ROW - 2)) {
            tile.selectionVisible(true);
            tileEmpty = true;
          } else if ((World.instance().getCurrentTeam() == Team.BLUE) && (i == 1)) {
            tile.selectionVisible(true);
            tileEmpty = true;
          }
        }
      }
    }

    currentPlayer = World.instance().getCurrentTeam() == Team.BLUE ?
        World.instance().getBluePlayer() : World.instance().getRedPlayer();

    if (tileEmpty && currentPlayer.getResources() >= creatingUnit.getCost()) {
      creatingUnit.setTeam(currentPlayer.getTeam());
      creatingUnit.setTouchable(Touchable.disabled);
      World.instance().getHud().setCreatingUnit(creatingUnit);
    } else {
      World.instance().setState(new StateIdle(map));
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
    creatingUnit.setCenter(
        Gdx.input.getX(),
        Gdx.graphics.getHeight() - Gdx.input.getY());
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (tile.isSelectionVisible()) {
      creatingUnit.setOnTile(tile);
      creatingUnit.setTouchable(Touchable.enabled);
      currentPlayer.setResources(currentPlayer.getResources() - creatingUnit.getCost());
      map.addUnit(creatingUnit);

      World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
    } else {
      World.instance().setState(new StateIdle(map));
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    World.instance().setState(new StateIdle(map));
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {
    World.instance().setState(new StateIdle(map));
  }

  @Override
  public void onUnitFactoryClicked (Unit unit) {
    if (creatingUnit.getClass() != unit.getClass()) {
      map.removeActor(creatingUnit);
      World.instance().setState(new StateUnitCreating(map, unit));
    }
  }
}
