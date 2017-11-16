package com.olan.warmonger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Text {
  private final Vector2 position = new Vector2();

  private BitmapFont font;
  private String defaultText;

  private Vector2 anchorPoint;
  private GlyphLayout textLayout;

  public Text (BitmapFont font, String defaultText) {
    anchorPoint = new Vector2();

    this.font = font;
    this.defaultText = defaultText;
    reset();
  }

  public Text (BitmapFont font) {
    this(font, "");
  }

  public void reset () {
    setText(defaultText);
  }

  public void setText (String text) {
    if (textLayout == null) {
      textLayout = new GlyphLayout();
    }
    textLayout.setText(font, text);
    calculatePosition();
  }

  public void draw (Batch batch) {
    font.draw(batch, textLayout, position.x, position.y);
  }

  public void setCenter (float x, float y) {
    anchorPoint.set(x, y);
    calculatePosition();
  }

  public void calculatePosition () {
    position.set(anchorPoint.x - textLayout.width / 2,
        anchorPoint.y + textLayout.height / 2);
  }

  public float getWidth () {
    return textLayout.width;
  }

  public float getHeight () {
    return textLayout.height;
  }

  public void setColor (Color color) {
    font.setColor(color);
  }
}
