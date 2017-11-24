package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class UnitFactory extends GameObject {
  private Class unitClass;
  UnitFactoryListener listener;

  public UnitFactory (Class unitClass, float x, float y) {
    this.unitClass = unitClass;

    if (unitClass == PikeMan.class) {
      setTexture(Assets.pikemanFront);
    } else if (unitClass == Cavalry.class) {
      setTexture(Assets.cavalryFront);
    } else if (unitClass == Archer.class) {
      setTexture(Assets.archerFront);
    }

    setPosition(x, y);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          Unit unit = null;
          if (getOuter().getUnitClass() == PikeMan.class) {
            unit = new PikeMan(Team.RED, 0, 0);
          } else if (getOuter().getUnitClass() == Cavalry.class) {
            unit = new Cavalry(Team.RED, 0, 0);
          } else if (getOuter().getUnitClass() == Archer.class) {
            unit = new Archer(Team.RED, 0, 0);
          }

          listener.onUnitFactoryClicked(unit);
        }
      }
    });
  }

  public void addListener (UnitFactoryListener listener) {
    this.listener = listener;
  }

  public UnitFactory getOuter() {
    return UnitFactory.this;
  }

  public Class getUnitClass () {
    return this.unitClass;
  }

  public interface UnitFactoryListener {
    public void onUnitFactoryClicked (Unit unit);
  }
}
