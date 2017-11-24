package com.olan.warmonger;

public class Cavalry extends Unit {
  public Cavalry (Team team, int row, int column) {
    super(team, row, column);
    setTextures(Assets.cavalryFront, Assets.cavalryBack);
  }
}
