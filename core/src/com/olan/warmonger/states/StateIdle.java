package com.olan.warmonger;

public class StateIdle implements GameDriven.State {
  private Map map;

  public StateIdle (Map map) {
    this.map = map;
    map.selectUnit(null);
  }

  public void enter () {

  }

  public void exit () {

  }

  public void run () {

  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {

  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    map.setState(new StateUnitSelected(map, unit));
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {

  }
}
