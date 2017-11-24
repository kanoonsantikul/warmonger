package com.olan.warmonger;

public class PikeMan extends Unit {
  private static final float MANUAL_OFFSET_X = 6f;
  private static final float MANUAL_OFFSET_Y = 15.0f;
  private static final int HEALTH_POINT = 4;

  public PikeMan (Team team, int row, int column) {
    super(team, row, column);
    setTextures(Assets.pikemanFront, Assets.pikemanBack);
    setTeam(team);
    setOffsetY(MANUAL_OFFSET_Y);
    setHealthPoint(HEALTH_POINT);
  }

  @Override
  public void setTeam(Team team) {
    super.setTeam(team);

    if (team == Team.BLUE) {
      setOffsetX(MANUAL_OFFSET_X);
    } else if (team == Team.RED){
      setOffsetX(-MANUAL_OFFSET_X);
    }
  }
}
