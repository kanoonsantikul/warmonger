package com.olan.warmonger;

public class Archer extends Unit {
  private static final float MANUAL_OFFSET_X = -6f;
  private static final float MANUAL_OFFSET_Y = 15.0f;

  private static final int HEALTH_POINT = 3;
  private static final int ATTACK_POINT = 2;
  private static final int ATTACK_RANGE = 2;
  private static final int MOVE_RANGE = 1;
  private static final int COST = 6;

  public Archer () {
    setTextures(Assets.archerFront, Assets.archerBack);

    setHealthPoint(HEALTH_POINT);
    setAttackPoint(ATTACK_POINT);
    setAttackRange(ATTACK_RANGE);
    setMoveRange(MOVE_RANGE);
    setCost(COST);
    setAttackType(Unit.AttackType.RANGE);
  }

  @Override
  public void setTeam(Team team) {
    super.setTeam(team);

    if (team == Team.BLUE) {
      setOffsetX(MANUAL_OFFSET_X);
    } else if (team == Team.RED){
      setOffsetX(-MANUAL_OFFSET_X);
    }
    setOffsetY(MANUAL_OFFSET_Y);
  }
}
