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
    map.selectUnit(unit);
  }

  public void exit () {
    map.selectUnit(null);
  }

  public void run () {
    if (!unit.isMovingTo(tile)) {
      map.setState(new ActionEndTurn(map, map.getCurrentTeam()));
    }
  }
}
