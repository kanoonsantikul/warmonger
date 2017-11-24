package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Castle extends TileObject {
  private static final float MANUAL_OFFSET_Y = 15.0f;

  private CastleListener listener;

  private int healthPoint = 10;
  private Text healthText;

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

    healthText = new Text(Assets.worldFont);
    setHealthPoint(healthPoint);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(Assets.hearth,
        getX() + getWidth() - Assets.hearth.getRegionWidth() / 2,
        getY() - Assets.hearth.getRegionHeight() / 2);
    healthText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    healthText.setCenter(getX() + getWidth(), getY());
  }

  public void addListener (CastleListener listener) {
      this.listener = listener;
  }

  public int getHealthPoint () {
    return this.healthPoint;
  }

  public void setHealthPoint (int healthPoint) {
    this.healthPoint = healthPoint;
    healthText.setText(healthPoint + "");
  }

  public interface CastleListener {
    public void onCastleClicked (Castle castle, int row, int column);
  }
}
