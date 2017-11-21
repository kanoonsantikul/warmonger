package com.olan.warmonger;

public class StateUnitMove implements MapState.State {
  private Map map;
  private Unit unit;
  private Tile tile;

  public StateUnitMove (Map map, Unit unit, Tile tile) {
    this.map = map;
    this.unit = unit;
    this.tile = tile;
  }

  public void enter () {
    map.selectUnit(unit);
  }

  public void exit () {
    map.selectUnit(null);
    map.endTurn();
  }

  public void run () {
    if (!unit.isMovingTo(tile)) {
      map.setState(new StateIdle(map));
    }
  }
}
