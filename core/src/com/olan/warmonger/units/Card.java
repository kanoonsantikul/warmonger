package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Card extends GameObject {
  private CardListener listener;

  private Unit prototype;
  private Text healthText;
  private Text attackPointText;
  private Text attackRangeText;
  private Text attackTypeText;
  private Text moveRangeText;
  private Text costText;

  public Card (TextureRegion cardTexture , Unit prototype) {
    super(cardTexture);
    this.prototype = prototype;

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          Unit unit = null;
          try {
            unit = (Unit)Card.this.prototype.getClass().getDeclaredConstructor().newInstance();
          } catch (Exception e) {
            System.out.println(e.toString());
          }

          if (unit != null) {
            listener.onCardClicked(unit);
          }
        }
      }
    });

    healthText = new Text(Assets.worldFont);
    attackPointText = new Text(Assets.worldFont);
    attackRangeText = new Text(Assets.worldFont);
    attackTypeText = new Text(Assets.worldFont);
    moveRangeText = new Text(Assets.worldFont);
    costText = new Text(Assets.worldFont);

    healthText.setText("Health: " + prototype.getHealthPoint());
    attackPointText.setText("Attack: " + prototype.getAttackPoint());
    attackRangeText.setText("Attack Range: " + prototype.getAttackRange());
    attackTypeText.setText("Type: " + prototype.getAttackType().toString());
    moveRangeText.setText("Move Range: " + prototype.getMoveRange());
    costText.setText(prototype.getCost() + "");
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    healthText.draw(batch);
    attackPointText.draw(batch);
    attackRangeText.draw(batch);
    attackTypeText.draw(batch);
    moveRangeText.draw(batch);
    costText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    float x = getCenterX() - 22;
    float y = getY() + getHeight() - 32;
    healthText.setPosition(x, y);
    y = y - attackPointText.getHeight() - 8;
    attackPointText.setPosition(x, y);
    y = y - attackRangeText.getHeight() - 8;
    attackRangeText.setPosition(x, y);
    y = y - attackTypeText.getHeight() - 8;
    attackTypeText.setPosition(x, y);
    y = y - moveRangeText.getHeight() - 8;
    moveRangeText.setPosition(x, y);

    costText.setCenter(getX() + 55, getY() + 24);
  }

  public void setListener (CardListener listener) {
    this.listener = listener;
  }

  public Unit getPrototype () {
    return this.prototype;
  }

  public interface CardListener {
    public void onCardClicked (Unit unit);
  }
}
