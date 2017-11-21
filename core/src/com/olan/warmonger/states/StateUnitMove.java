package com.olan.warmonger;

public class StateUnitMove implements GameDriven.State {
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
  }

  public void run () {
    if (!unit.isMovingTo(tile)) {
      map.setState(new StateIdle(map));
    }
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {

  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {

  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {

  }
}
