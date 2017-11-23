package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class UnitFactory extends GameObject {
  private Class unitClass;
  UnitFactoryListener listener;

  public UnitFactory (Class unitClass) {
    this.unitClass = unitClass;

    if (unitClass == Unit.class) {
      setTexture(Assets.pikemanFront);
    }

    setPosition(1000f, 600f);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          Unit unit = null;
          if (getOuter().getUnitClass() == Unit.class) {
            unit = new Unit(Team.RED, 0, 0);
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
