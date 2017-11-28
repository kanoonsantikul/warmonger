package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;

public class Hud extends Group {
  private Unit creatingUnit;

  public Hud () {
  }

  public void setCreatingUnit (Unit unit) {
    removeActor(creatingUnit);
    this.creatingUnit = unit;
    addActor(creatingUnit);
  }
}
