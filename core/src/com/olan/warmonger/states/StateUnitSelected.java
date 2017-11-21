package com.olan.warmonger;

public class StateUnitSelected implements GameDriven.State {
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

    boolean foundEnemy = false;
    int row;
    int column = unit.getColumn();
    TileObject other;

    for (int i = 0; i <= unit.getMoveRange(); i++) {
      row = unit.getRow();
      if (unit.getTeam() == Team.BLUE) {
        row += i;
      } else {
        row -= i;
      }
      if (row >= Map.ROW || row < 0) {
        continue;
      }

      tile = map.getTile(row, column);
      other = map.getUnit(row, column);
      if (other == null) {
        other = map.getBuilding(row, column);
      }

      if (other != null) {
        if (unit.getTeam() != other.getTeam()) {
          tile.selectionCombatVisible(true);
          foundEnemy = true;
        }
      } else if (!foundEnemy) {
        tile.selectionVisible(true);
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

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (tile.isSelectionVisible()) {
      map.setState(new StateUnitMove(map, map.getSelectedUnit(), tile));
    } else {
      map.setState(new StateIdle(map));
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (unit.getTeam() == map.getSelectedUnit().getTeam()) {
      map.setState(new StateUnitSelected(map, unit));
    } else {
      Tile tile = map.getTile(unit.getRow(), unit.getColumn());
      if (tile.isSelectionVisible()) {
        map.setState(new StateUnitCombat(map, map.getSelectedUnit(), unit));
      }
    }
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {
    if (building.getTeam() == map.getSelectedUnit().getTeam()) {
      map.setState(new StateIdle(map));
    } else {
      map.setState(new StateBuildingCombat(map, map.getSelectedUnit(), building));
    }
  }
}
