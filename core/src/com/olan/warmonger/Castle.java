package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Castle extends TileObject {
  private static final float MANUAL_OFFSET_Y = 15.0f;

  private static final int HEALTH_POINT = 2;

  private CastleListener listener;

  public Castle (Team team, int row, int column) {
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
          listener.onCastleClicked(Castle.this, Castle.this.getRow(), Castle.this.getColumn());
        }
      }
    });


    setHealthPoint(HEALTH_POINT);
  }

  public void addListener (CastleListener listener) {
      this.listener = listener;
  }

  public interface CastleListener {
    public void onCastleClicked (Castle castle, int row, int column);
  }
}
