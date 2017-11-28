package com.olan.warmonger;

public class ActionUnitMove implements GameDriven.Action {
  private Map map;
  private Unit unit;
  private Tile tile;

  public ActionUnitMove (Map map, Unit unit, Tile tile) {
    this.map = map;
    this.unit = unit;
    this.tile = tile;
  }

  public void enter () {
    Tile previousTile = map.getTile(unit.getRow(), unit.getColumn());
    if (previousTile.isLootMarkVisible()) {
      previousTile.lootMarkVisible(false);
      World.instance().getHud().renderLoots(map.getTiles());
    }
  }

  public void exit () {
    if (tile.getResource() != 0) {
      tile.lootMarkVisible(true);
      World.instance().getHud().renderLoots(map.getTiles());
    }
  }

  public void run () {
    if (!unit.isMovingTo(tile)) {
      World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
    }
  }
}
