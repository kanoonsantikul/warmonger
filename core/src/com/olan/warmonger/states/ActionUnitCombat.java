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
    if (targetTile.getResource() != 0){
      targetTile.lootMarkVisible(true);
    }

    int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
    if (remainHealth <= 0) {
      map.getUnits().remove(target);
      target.remove();
      map.getTile(target.getRow(), target.getColumn()).lootMarkVisible(false);
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
