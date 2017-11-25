package com.olan.warmonger;

public class Archer extends Unit {
  private static final float MANUAL_OFFSET_X = -7f;
  private static final float MANUAL_OFFSET_Y = 15.0f;
  private static final int HEALTH_POINT = 2;

  public Archer () {
    setTextures(Assets.archerFront, Assets.archerBack);
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
