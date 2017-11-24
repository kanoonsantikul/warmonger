package com.olan.warmonger;

public class PikeMan extends Unit {
  public PikeMan (Team team, int row, int column) {
    super(team, row, column);
    setTextures(Assets.pikemanFront, Assets.pikemanBack);
  }
}
