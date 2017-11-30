package com.olan.warmonger;

public class StateUnitSelected implements GameDriven.State {
  private Map map;
  private Unit selectedUnit;

  public StateUnitSelected (Map map, Unit unit) {
    this.map = map;
    this.selectedUnit = unit;
  }

  public void enter () {
    Tile tile;
    boolean foundEnemy = false;
    int row = selectedUnit.getRow();
    int column = selectedUnit.getColumn();
    int direction;
    TileObject other;

    if (selectedUnit.getTeam() == Team.BLUE) {
      direction = 1;
    } else {
      direction = -1;
    }

    for (int i = 0; i <= Map.ROW; i++) {
      row += direction;
      if (row >= Map.ROW || row < 0) {
        continue;
      }

      tile = map.getTile(row, column);
      other = map.getUnit(row, column);
      if (other == null) {
        other = map.getBuilding(row, column);
      }

      if (other != null) {
        if (selectedUnit.getTeam() != other.getTeam()) {
          if (Math.abs(selectedUnit.getRow() - row) <= selectedUnit.getAttackRange()) {
            if (!foundEnemy) {
              tile.selectionCombatVisible(true);
            }
            if (selectedUnit.getAttackType() == Unit.AttackType.MELEE) {
              foundEnemy = true;
            }
          }
        }
      } else if (Math.abs(selectedUnit.getRow() - row) <= selectedUnit.getMoveRange()) {
        if (!foundEnemy) {
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
        tile.selectionVisible(false);
      }
    }
  }

  public void run () {

  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (tile.isSelectionVisible()) {
      World.instance().setState(new ActionUnitMove(map, selectedUnit, tile));
    } else {
      World.instance().setState(new StateIdle(map));
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (unit.getTeam() == selectedUnit.getTeam()) {
      World.instance().setState(new StateUnitSelected(map, unit));
    } else {
      Tile tile = map.getTile(unit.getRow(), unit.getColumn());
      if (tile.isSelectionCombatVisible()) {
        World.instance().setState(new ActionUnitCombat(map, selectedUnit, unit));
      }
    }
  }

  @Override
  public void onBuildingClicked (Building building, int row, int column) {
    Tile tile = map.getTile(row, column);

    if (building.getTeam() == selectedUnit.getTeam()) {
      World.instance().setState(new StateIdle(map));
    } else {
      if (tile.isSelectionCombatVisible()) {
        World.instance().setState(new ActionBuildingCombat(map, selectedUnit, building));
      } else {
        World.instance().setState(new StateIdle(map));
      }
    }
  }

  @Override
  public void onUnitFactoryClicked (Unit unit) {
    World.instance().setState(new StateUnitCreating(map, unit));
  }
}
