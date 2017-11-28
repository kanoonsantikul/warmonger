package com.olan.warmonger;

public class ActionEndTurn implements GameDriven.Action {
  private Map map;
  private Team currentTeam;

  public ActionEndTurn (Map map, Team currentTeam) {
    this.map = map;
    this.currentTeam = currentTeam;
  }

  public void enter () {
    endTurn();
  }

  public void exit () {
  }

  public void run () {
    World.instance().setState(new StateIdle(map));
  }

  public void endTurn () {
    int resourceRate = calculateResourceRate();
    Player currentPlayer;
    if (currentTeam == Team.RED) {
      currentPlayer = World.instance().getRedPlayer();
      World.instance().setCurrentTeam(Team.BLUE);
    } else {
      currentPlayer = World.instance().getBluePlayer();
      World.instance().setCurrentTeam(Team.RED);
    }
    currentPlayer.setResources(currentPlayer.getResources() + resourceRate);
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
