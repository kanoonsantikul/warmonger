package com.olan.warmonger;

public class PikeMan extends Unit {
  private static final float MANUAL_OFFSET_X = 6f;
  private static final float MANUAL_OFFSET_Y = 15.0f;

  private static final int HEALTH_POINT = 4;
  private static final int ATTACK_POINT = 3;
  private static final int ATTACK_RANGE = 1;
  private static final int MOVE_RANGE = 1;
  private static final int COST = 5;

  public PikeMan () {
    setTextures(Assets.pikemanFront, Assets.pikemanBack);

    setHealthPoint(HEALTH_POINT);
    setAttackPoint(ATTACK_POINT);
    setAttackRange(ATTACK_RANGE);
    setMoveRange(MOVE_RANGE);
    setCost(COST);
    setAttackType(Unit.AttackType.MELEE);
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
