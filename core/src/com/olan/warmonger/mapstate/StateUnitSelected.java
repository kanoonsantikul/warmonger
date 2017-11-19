package com.olan.warmonger;

public class StateUnitSelected implements MapState.State {
  private Map map;
  private Unit unit;

  public StateUnitSelected (Map map, Unit unit) {
    this.map = map;
    this.unit = unit;
  }

  public void enter () {
    map.selectUnit(unit);

    Tile tile;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(true);

        if (unit.canMoveTo(tile)) {
          Unit other = map.getUnit(tile.getRow(), tile.getColumn());
          if (other != null && unit.getTeam() != other.getTeam()) {
            tile.selectionCombatVisible(true);
          } else {
            tile.selectionVisible(true);
          }
        }
      }
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

    map.selectUnit(null);
  }

  public void run () {

  }
}
