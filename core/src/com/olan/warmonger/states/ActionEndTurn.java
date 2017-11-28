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
    Player previousPlayer;
    Player currentPlayer;
    World world = World.instance();
    UnitFactory unitFactory = world.getUnitFactory();
    Card cardPikeman = unitFactory.cardPikeman;
    Card cardCavalry = unitFactory.cardCavalry;
    Card cardArcher = unitFactory.cardArcher;

    if (currentTeam == Team.RED) {
      previousPlayer = world.getRedPlayer();
      world.setCurrentTeam(Team.BLUE);
      currentPlayer = world.getBluePlayer();
    } else {
      previousPlayer = world.getBluePlayer();
      world.setCurrentTeam(Team.RED);
      currentPlayer = world.getRedPlayer();
    }
    previousPlayer.setResources(previousPlayer.getResources() + resourceRate);

    if (cardPikeman.getPrototype().getCost() > currentPlayer.getResources()) {
      cardPikeman.setTexture(Assets.cardPikemanGray);
    } else {
      cardPikeman.setTexture(Assets.cardPikeman);
    }

    if (cardCavalry.getPrototype().getCost() > currentPlayer.getResources()) {
      cardCavalry.setTexture(Assets.cardCavalryGray);
    } else {
      cardCavalry.setTexture(Assets.cardCavalry);
    }

    if (cardArcher.getPrototype().getCost() > currentPlayer.getResources()) {
      cardArcher.setTexture(Assets.cardArcherGray);
    } else {
      cardArcher.setTexture(Assets.cardArcher);
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
