package com.olan.warmonger;

public class StateUnitCombat implements MapState.State {
  private Map map;
  private Unit actor;
  private Unit target;
  private Tile targetTile;

  public StateUnitCombat (Map map, Unit actor, Unit target) {
    this.map = map;
    this.actor = actor;
    this.target = target;
  }

  public void enter () {
    map.selectUnit(actor);

    if (actor.getTeam() == Team.BLUE) {
      targetTile = map.getTile(target.getRow() - 1, target.getColumn());
    } else {
      targetTile = map.getTile(target.getRow() + 1, target.getColumn());
    }
  }

  public void exit () {
    target.setHealthPoint(target.getHealthPoint() - actor.getAttackPoint());
    map.selectUnit(null);
  }

  public void run () {
    if (!actor.isMovingTo(targetTile)) {
      map.setState(new StateIdle(map));
    }
  }
}
