package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Castle extends TileObject {
  private static final float MANUAL_OFFSET_Y = 15.0f;

  private int healthPoint = 10;

  public Castle (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    if (team == Team.RED) {
      setTexture(Assets.castleRed);
    } else {
      setTexture(Assets.castleBlue);
    }
    setOffsetY(MANUAL_OFFSET_Y);
  }

  public int getHealthPoint () {
    return this.healthPoint;
  }

  public void setHealthPoint (int healthPoint) {
    this.healthPoint = healthPoint;
  }
}
