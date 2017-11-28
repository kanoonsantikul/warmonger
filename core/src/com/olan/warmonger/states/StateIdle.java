package com.olan.warmonger;

public class StateIdle implements GameDriven.State {
  private Map map;

  public StateIdle (Map map) {
    this.map = map;
  }

  public void enter () {
    World.instance().getHud().setCreatingUnit(null);
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
    if (unit.getTeam() == World.instance().getCurrentTeam()) {
      World.instance().setState(new StateUnitSelected(map, unit));
    }
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {

  }

  @Override
  public void onUnitFactoryClicked (Unit unit) {
    World.instance().setState(new StateUnitCreating(map, unit));
  }
}
