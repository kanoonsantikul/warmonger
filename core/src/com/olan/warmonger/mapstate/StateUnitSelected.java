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
      }
    }

    Unit other;
    boolean foundEnemy = false;
    int row = unit.getRow();
    int column = unit.getColumn();
    if (unit.getTeam() == Team.BLUE) {
      for (int i = row; i <= row + unit.getMoveRange(); i++) {
        tile = map.getTile(i, column);
        other = map.getUnit(i, column);
        if (other != null && unit.getTeam() != other.getTeam()) {
          tile.selectionCombatVisible(true);
          foundEnemy = true;
        } else if (!foundEnemy) {
          tile.selectionVisible(true);
        }
      }
    } else {
      for (int i = row; i >= row - unit.getMoveRange(); i--) {
        tile = map.getTile(i, column);
        other = map.getUnit(i, column);
        if (other != null && unit.getTeam() != other.getTeam()) {
          tile.selectionCombatVisible(true);
          foundEnemy = true;
        } else if (!foundEnemy) {
          tile.selectionVisible(true);
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
