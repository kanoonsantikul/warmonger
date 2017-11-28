package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Health extends GameObject {
  private int amount;
  private Text amountText;

  public Health (int amount) {
    super(Assets.health);
    amountText = new Text(Assets.worldFont);
    setAmount(amount);
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
