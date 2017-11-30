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
    World.instance().getHud().renderUnitHealths(map.getUnits());
    World.instance().getHud().renderLoots(map.getTiles());
  }

  public void run () {
    World.instance().getHud().renderUnitHealths(map.getUnits());
    if (!actor.isMovingTo(targetTile)) {
      if (targetTile.getResource() != 0){
        targetTile.lootMarkVisible(true);
      }

      int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
      if (remainHealth <= 0) {
        targetTile = map.getTile(target.getRow(), target.getColumn());
        targetTile.lootMarkVisible(false);
        map.getUnits().remove(target);
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
