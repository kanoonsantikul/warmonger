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
    }

    if (actor.getTeam() == Team.BLUE) {
      targetTile = map.getTile(target.getRow() - 1, target.getColumn());
    } else {
      targetTile = map.getTile(target.getRow() + 1, target.getColumn());
    }
  }

  public void exit () {
    int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
    int redBuilding = 0;
    int blueBuilding = 0;
    if (remainHealth <= 0) {
      map.getBuildings().remove(target);
      target.remove();
    } else {
      target.setHealthPoint(remainHealth);
    }

    for (TileObject building : map.getBuildings()) {
      if (building.getTeam() == Team.RED) {
        redBuilding++;
      } else if (building.getTeam() == Team.BLUE) {
        blueBuilding++;
      }
    }

    if (redBuilding == 0 || blueBuilding == 0) {
      World.instance().setEnd(true);
    }
  }

  public void run () {
    if (actor.getAttackType() == Unit.AttackType.MELEE) {
      if (!actor.isMovingTo(targetTile)) {
        World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
      }
    } else {
      World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
    }
  }
}
