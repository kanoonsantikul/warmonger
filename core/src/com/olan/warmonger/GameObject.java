package com.olan.warmonger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {
  private TextureRegion texture;

  private float offsetX;
  private float offsetY;

  public GameObject () {
    this(null);
  }

  public GameObject (TextureRegion texture) {
    setTexture(texture);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    draw(batch, parentAlpha, getWidth(), getHeight());
  }

  public void draw (Batch batch, float parentAlpha, float width, float height) {
    if (texture != null && isVisible()) {
      batch.draw(getTexture(), getX(), getY(), width, height);
    }
  }

  public TextureRegion getTexture () {
    return this.texture;
  }

  public void setTexture (TextureRegion texture) {
    this.texture = texture;

    if (texture != null) {
      setWidth(texture.getRegionWidth());
      setHeight(texture.getRegionHeight());
    }
  }

  public Vector2 getCenter () {
    return new Vector2(getCenterX(), getCenterY());
  }

  public float getCenterX () {
    return getX() + getWidth() / 2;
  }

  public float getCenterY () {
    return getY() + getHeight() / 2;
  }

  public void setCenter (float x, float y) {
    setPosition(x - getWidth() / 2, y - getHeight() / 2);
  }

  public void setCenter (Vector2 center) {
    setCenter(center.x, center.y);
  }

  public void setOffsetX (float offset) {
    this.offsetX = offset;
  }

  public float getOffsetX () {
    return this.offsetX;
  }

  public void setOffsetY (float offset) {
    this.offsetY = offset;
  }

  public float getOffsetY () {
    return this.offsetY;
  }
}
