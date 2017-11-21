package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileObject extends GameObject {
  private int row;
  private int column;

  private float offsetX;
  private float offsetY;

  private Team team;

  private int healthPoint = 4;
  private Text healthText;

  public TileObject (int row, int column) {
    this(null, row, column);
  }

  public TileObject (TextureRegion texture, int row, int column) {
    super(texture);

    setRow(row);
    setColumn(column);
    healthText = new Text(Assets.worldFont);
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

  public int getRow () {
    return this.row;
  }

  public void setRow (int row) {
    this.row = row;
  }

  public int getColumn () {
    return this.column;
  }

  public void setColumn (int column) {
    this.column = column;
  }

  public float getOffsetX () {
    return this.offsetX;
  }

  public void setOffsetX (float offsetX) {
    this.offsetX = offsetX;
  }

  public float getOffsetY () {
    return this.offsetY;
  }

  public void setOffsetY (float offsetY) {
    this.offsetY = offsetY;
  }

  public void setOnTile (Tile tile) {
    setCenter(tile.getCenterX() + getOffsetX(),
        tile.getCenterY() + getOffsetY());
  }

  public Team getTeam () {
    return this.team;
  }

  public void setTeam (Team team) {
    this.team = team;
  }

  public int getHealthPoint () {
    return this.healthPoint;
  }

  public void setHealthPoint (int healthPoint) {
    this.healthPoint = healthPoint;
    healthText.setText(healthPoint + "");
  }
}
