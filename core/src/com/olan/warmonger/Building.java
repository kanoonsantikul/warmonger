package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Building extends TileObject {
  private static final float MANUAL_OFFSET_Y = 15.0f;
  private static final int HEALTH_POINT = 10;

  private BuildingListener listener;

  public Building (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    if (team == Team.RED) {
      setTexture(Assets.castleRed);
    } else {
      setTexture(Assets.castleBlue);
    }
    setOffsetY(MANUAL_OFFSET_Y);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onBuildingClicked(Building.this, Building.this.getRow(), Building.this.getColumn());
        }
      }
    });

    setHealthPoint(HEALTH_POINT);
  }

  public void setListener (BuildingListener listerner) {
      this.listener = listerner;
  }

  public interface BuildingListener {
    public void onBuildingClicked (Building castle, int row, int column);
  }
}
