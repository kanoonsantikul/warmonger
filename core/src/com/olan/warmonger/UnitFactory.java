package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class UnitFactory extends GameObject {
  private Class c;

  UnitFactoryListener listener;

  public UnitFactory () {
    this.c = c;

    setTexture(Assets.pikemanFront);

    setPosition(1000f, 600f);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          Unit unit = new Unit(Team.RED, 0, 0);
          listener.onUnitFactoryClicked(unit);
        }
      }
    });
  }

  public void addListener (UnitFactoryListener listener) {
    this.listener = listener;
  }

  public interface UnitFactoryListener {
    public void onUnitFactoryClicked (Unit unit);
  }
}
