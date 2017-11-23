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

    Tile tile;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(true);

        if (map.getUnit(i, j) == null) {
          if ((map.getCurrentTeam() == Team.RED) && (i == Map.ROW - 2)) {
            tile.selectionVisible(true);
          } else if ((map.getCurrentTeam() == Team.BLUE) && (i == 1)) {
            tile.selectionVisible(true);
          }
        }
      }
    }


  }

  public void exit () {
    Tile tile;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        tile = map.getTile(i, j);
        tile.markVisible(false);
        tile.selectionVisible(false);
      }
    }

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
