package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

public class Hud extends Group {
  private Unit creatingUnit;
  private final ArrayList<Loot> loots = new ArrayList<Loot>();
  private final ArrayList<Health> unitHealths = new ArrayList<Health>();
  private final ArrayList<Health> buildingHealths = new ArrayList<Health>();

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    for (Loot loot : loots) {
      loot.draw(batch, parentAlpha);
    }
    for (Health health : buildingHealths) {
      health.draw(batch, parentAlpha);
    }
    for (Health health : unitHealths) {
      health.draw(batch, parentAlpha);
    }
  }

  public void setCreatingUnit (Unit unit) {
    removeActor(creatingUnit);
    this.creatingUnit = unit;

    if (unit != null) {
      addActor(creatingUnit);
    }
  }

  public void renderLoots (Tile[][] tiles) {
    loots.clear();
    Loot loot;
    for (int i = 0; i < Map.ROW; i++) {
      for (int j = 0; j < Map.COLUMN; j++) {
        if (tiles[i][j].isLootMarkVisible()) {
          loot = new Loot(tiles[i][j].getResource());
          loot.setCenter(
              tiles[i][j].getX() + Tile.WIDTH - 5,
              tiles[i][j].getY() + loot.getHeight());
          loots.add(loot);
        }
      }
    }
  }

  public void renderHealths (ArrayList<? extends TileObject> objects, ArrayList<Health> healths) {
    healths.clear();
    Health health;
    for(TileObject object : objects) {
      health = new Health(object.getHealthPoint());
      health.setCenter(
          object.getCenterX(),
          object.getY());
      healths.add(health);
    }
  }

  public void renderUnitHealths (ArrayList<Unit> units) {
    renderHealths(units, unitHealths);
  }

  public void renderBuildingHealths (ArrayList<Building> buildings) {
    renderHealths(buildings, buildingHealths);
  }
 }
