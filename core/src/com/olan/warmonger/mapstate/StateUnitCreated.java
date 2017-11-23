package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class StateUnitCreated implements MapState.State {
  private Map map;
  private Unit unit;
  private Tile tile;

  public StateUnitCreated (Map map, Unit unit) {
    this.map = map;
    this.unit = unit;
  }

  public void enter () {
    map.setCreatedUnit(unit);
    map.addActor(map.getCreatedUnit());
    map.getCreatedUnit().setTouchable(Touchable.disabled);
  }

  public void exit () {
    map.setCreatedUnit(null);
    map.endTurn();
  }

  public void run () {
    if (map.getCreatedUnit() != null) {
      map.getCreatedUnit().setCenter(new Vector2(Gdx.input.getX(),
      Gdx.graphics.getHeight() - Gdx.input.getY()));
    }
  }
}
