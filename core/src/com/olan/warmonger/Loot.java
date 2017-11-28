package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Loot extends GameObject {
  private int amount;
  private Text amountText;

  public Loot () {
    super(Assets.loot);
    amountText = new Text(Assets.worldFont);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    amountText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    amountText.setCenter(getCenterX() + 7, getCenterY() - 1);
  }

  public int getAmount () {
    return this.amount;
  }

  public void setAmount (int amount) {
    this.amount = amount;
    amountText.setText(amount + "");
  }
}
