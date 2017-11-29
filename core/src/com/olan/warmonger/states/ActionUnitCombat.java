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
    if (targetTile.getResource() != 0){
      targetTile.lootMarkVisible(true);
      World.instance().getHud().renderLoots(map.getTiles());
    }

    int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
    if (remainHealth <= 0) {
      map.getTile(target.getRow(), target.getColumn()).lootMarkVisible(false);
      map.getUnits().remove(target);
      target.remove();
      World.instance().getHud().renderLoots(map.getTiles());
    } else {
      target.setHealthPoint(remainHealth);
    }
    World.instance().getHud().renderUnitHealths(map.getUnits());
  }

  public void run () {
    World.instance().getHud().renderUnitHealths(map.getUnits());
    if (!actor.isMovingTo(targetTile)) {
      World.instance().setState(new ActionEndTurn(map, World.instance().getCurrentTeam()));
    }
  }
}
