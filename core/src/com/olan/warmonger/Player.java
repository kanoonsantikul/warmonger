package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject {
  private static int INIT_RESOURCE = 10;

  private Team team;

  private int resources;
  private Text resourceText;
  private TextureRegion resourceTexture;

  public Player (Team team) {
    this.team = team;
    resources = INIT_RESOURCE;

    setX(270);
    if (team == Team.BLUE) {
      resourceTexture = Assets.resourceCountBlue;
      setY(resourceTexture.getRegionHeight());
    } else {
      resourceTexture = Assets.resourceCountRed;
      setY(World.HEIGHT - resourceTexture.getRegionHeight() * 3 / 2);
    }

    resourceText = new Text(Assets.hudFont);
    resourceText.setCenter(
        getX() + resourceTexture.getRegionWidth() / 2,
        getY() + resourceTexture.getRegionHeight() / 2);
    setResources(resources);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    if (team == Team.BLUE) {
      batch.draw(resourceTexture, getX(), getY());
    } else {
      batch.draw(resourceTexture, getX(), getY());
    }
    resourceText.draw(batch);
  }

  public Team getTeam () {
    return this.team;
  }

  public int getResources() {
    return this.resources;
  }

  public void setResources(int resources) {
    this.resources = resources;
    resourceText.setText(resources + "");
  }

  public void setResourceTexture (TextureRegion texture) {
    this.resourceTexture = texture;
  }
}
