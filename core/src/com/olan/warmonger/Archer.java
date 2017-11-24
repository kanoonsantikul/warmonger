package com.olan.warmonger;

public class Archer extends Unit {
  public Archer (Team team, int row, int column) {
    super(team, row, column);
    setTextures(Assets.archerFront, Assets.archerBack);
  }
}
