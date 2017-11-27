package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Card extends GameObject {
  private CardListener listener;

  private Unit prototype;
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

    costText = new Text(Assets.worldFont);
    costText.setText(prototype.getCost() + "");
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(Assets.cost,
        getCenterX() - Assets.cost.getRegionWidth() / 2,
        getY() - Assets.cost.getRegionHeight() + 10);
    costText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    costText.setCenter(getCenterX() + 10, getY() - Assets.cost.getRegionHeight() / 2 + 10);
  }

  public void setListener (CardListener listener) {
    this.listener = listener;
  }

  public interface CardListener {
    public void onCardClicked (Unit unit);
  }
}
