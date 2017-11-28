package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Hud extends Group {
  private Unit creatingUnit;
  private final ArrayList<Loot> loots = new ArrayList<Loot>();

  public Hud () {

  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    for (Loot loot : loots) {
      loot.draw(batch, parentAlpha);
    }
  }

  public void setCreatingUnit (Unit unit) {
    removeActor(creatingUnit);
    this.creatingUnit = unit;
    addActor(creatingUnit);
  }

  public void renderLoots (Tile[][] tiles) {
    loots.clear();
    Loot loot;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        if (tiles[i][j].isLootMarkVisible()) {
          loot = new Loot();
          loot.setAmount(tiles[i][j].getResource());
          loot.setCenter(
              tiles[i][j].getX() + tiles[i][j].getWidth() - 5,
              tiles[i][j].getY() + loot.getHeight());
          loots.add(loot);
        }
      }
    }
  }
}
