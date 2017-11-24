package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject {
  private Team team;

  private int resources;
  private Text resourceText;
  private TextureRegion resourceTexture;

  public Player (Team team) {
    this.team = team;

    resourceText = new Text(Assets.hudFont);
    if (team == Team.BLUE) {
      resourceTexture = Assets.resourceCountBlue;
      resourceText.setCenter(
          resourceTexture.getRegionWidth() / 2 + 20,
          World.HEIGHT - resourceTexture.getRegionHeight() / 2 - 20);
    } else {
      resourceTexture = Assets.resourceCountRed;
      resourceText.setCenter(
          World.WIDTH - resourceTexture.getRegionWidth() / 2,
          World.HEIGHT - resourceTexture.getRegionHeight() / 2 - 20);
    }
    setResources(resources);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    if (team == Team.BLUE) {
      batch.draw(resourceTexture, 10,
          World.HEIGHT - resourceTexture.getRegionHeight() - 20);
    } else {
      batch.draw(resourceTexture,
          World.WIDTH - resourceTexture.getRegionWidth() - 10,
          World.HEIGHT - resourceTexture.getRegionHeight() - 20);
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
}
