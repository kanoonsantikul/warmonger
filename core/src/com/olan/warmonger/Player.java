package com.olan.warmonger;

import java.util.ArrayList;

public class Player {
  public static final int RED = 1;
  public static final int BLUE = 2;

  private Team team;

  public Player (Team team) {
    this.team = team;
  }

  public Team getTeam () {
    return this.team;
  }
}
