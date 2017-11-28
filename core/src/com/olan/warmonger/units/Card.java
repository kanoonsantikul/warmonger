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
    moveRangeText = new Text(Assets.worldFont);
    costText = new Text(Assets.worldFont);

    healthText.setText("Health: " + prototype.getHealthPoint());
    attackPointText.setText("Attack: " + prototype.getAttackPoint());
    attackRangeText.setText("Attack Range: " + prototype.getAttackRange());
    moveRangeText.setText("Move Range: " + prototype.getMoveRange());
    costText.setText(prototype.getCost() + "");
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(Assets.cost,
        getCenterX() - Assets.cost.getRegionWidth() / 2,
        getY() - Assets.cost.getRegionHeight() + 10);

    healthText.draw(batch);
    attackPointText.draw(batch);
    attackRangeText.draw(batch);
    moveRangeText.draw(batch);
    costText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    float x = getX() + getWidth() + 10;
    float y = getY() + getHeight() - 25;
    healthText.setPosition(x, y);
    y = y - attackPointText.getHeight() - 8;
    attackPointText.setPosition(x, y);
    y = y - attackRangeText.getHeight() - 8;
    attackRangeText.setPosition(x, y);
    y = y - moveRangeText.getHeight() - 8;
    moveRangeText.setPosition(x, y);

    costText.setCenter(
        getCenterX() + 10,
        getY() - Assets.cost.getRegionHeight() / 2 + 10);
  }

  public void setListener (CardListener listener) {
    this.listener = listener;
  }

  public interface CardListener {
    public void onCardClicked (Unit unit);
  }
}
