package com.olan.warmonger;

public class ActionUnitCombat implements GameDriven.Action {
  private Map map;
  private Unit actor;
  private Unit target;
  private Tile targetTile;

  public ActionUnitCombat (Map map, Unit actor, Unit target) {
    this.map = map;
    this.actor = actor;
    this.target = target;
  }

  public void enter () {
    if (actor.getTeam() == Team.BLUE) {
      targetTile = map.getTile(target.getRow() - 1, target.getColumn());
    } else {
      targetTile = map.getTile(target.getRow() + 1, target.getColumn());
    }
  }

  public void exit () {
    int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
    if (remainHealth <= 0) {
      map.getUnits().remove(target);
      target.remove();
    } else {
      target.setHealthPoint(remainHealth);
    }
  }

  public void run () {
    if (!actor.isMovingTo(targetTile)) {
      map.setState(new ActionEndTurn(map, map.getCurrentTeam()));
    }
  }
}
