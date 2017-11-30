package com.olan.warmonger;

public class ActionBuildingCombat implements GameDriven.Action {
  private Map map;
  private Unit actor;
  private Building target;
  private Tile targetTile;

  public ActionBuildingCombat (Map map, Unit actor, Building target) {
    this.map = map;
    this.actor = actor;
    this.target = target;
  }

  public void enter () {
    Tile previousTile = map.getTile(actor.getRow(), actor.getColumn());
    if (previousTile.isLootMarkVisible()) {
      previousTile.lootMarkVisible(false);
      World.instance().getHud().renderLoots(map.getTiles());
    }

    if (actor.getAttackType() == Unit.AttackType.MELEE) {
      if (actor.getTeam() == Team.BLUE) {
        targetTile = map.getTile(target.getRow() - 1, target.getColumn());
      } else {
        targetTile = map.getTile(target.getRow() + 1, target.getColumn());
      }
    } else {
      targetTile = map.getTile(actor.getRow(), actor.getColumn());
    }
  }

  public void exit () {
    World.instance().getHud().renderUnitHealths(map.getUnits());
    World.instance().getHud().renderBuildingHealths(map.getBuildings());

    int redBuilding = 0;
    int blueBuilding = 0;
    for (TileObject building : map.getBuildings()) {
      if (building.getTeam() == Team.RED) {
        redBuilding++;
      } else if (building.getTeam() == Team.BLUE) {
        blueBuilding++;
      }
    }

    if (redBuilding == 2 || blueBuilding == 2) {
      World.instance().setEnd(true);
    }
  }

  public void run () {
    World.instance().getHud().renderUnitHealths(map.getUnits());
    if (!actor.isMovingTo(targetTile)) {
      int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
      if (remainHealth <= 0) {
        targetTile = map.getTile(target.getRow(), target.getColumn());
        map.getBuildings().remove(target);
        target.remove();

        if (actor.getAttackType() == Unit.AttackType.MELEE) {
          World.instance().setState(new ActionUnitMove(map, actor, targetTile));
        } else {
          World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
        }
      } else {
        target.setHealthPoint(remainHealth);
        World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
      }
    }
  }
}
