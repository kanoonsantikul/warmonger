package com.olan.warmonger;

public class ActionEndTurn implements GameDriven.Action {
  private Map map;
  private Team currentTeam;

  public ActionEndTurn (Map map, Team currentTeam) {
    this.map = map;
    this.currentTeam = currentTeam;
  }

  public void enter () {

  }

  public void exit () {
    endTurn();
  }

  public void run () {
    map.setState(new StateIdle(map));
  }

  public void endTurn () {
    int resourceRate = calculateResourceRate();;
    if (currentTeam == Team.RED) {
      map.setRedResources(map.getRedResources() + resourceRate);
      map.setCurrentTeam(Team.BLUE);
    } else {
      map.setBlueResources(map.getBlueResources() + resourceRate);
      map.setCurrentTeam(Team.RED);
    }
  }

  public int calculateResourceRate () {
    int resourceRate = 0;
    for (Unit unit : map.getUnits()) {
      if (unit.getTeam() == currentTeam) {
        resourceRate += map.getTile(unit.getRow(), unit.getColumn()).getResource();
      }
    }
    return resourceRate;
  }
}
