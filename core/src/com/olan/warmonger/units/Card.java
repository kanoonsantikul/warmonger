package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Card extends GameObject {
  private Unit prototype;
  private CardListener listener;

  public Card (TextureRegion cardTexture , Unit prototype) {
    super(cardTexture);
    this.prototype = prototype;

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onCardClicked(Card.this.prototype.clone());
        }
      }
    });
  }

  public void setListener (CardListener listener) {
    this.listener = listener;
  }

  public interface CardListener {
    public void onCardClicked (Unit unit);
  }
}
