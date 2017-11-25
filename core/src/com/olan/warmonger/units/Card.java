package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Card extends GameObject {
  private Class unitClass;
  private CardListener listener;

  public Card (TextureRegion cardTexture , Class unitClass) {
    super(cardTexture);
    this.unitClass = unitClass;

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          Unit unit = null;
          try {
            unit = (Unit)Card.this.unitClass.getDeclaredConstructor().newInstance();
          } catch (Exception e) {
            System.out.println(e.toString());
          }

          if (unit != null) {
            listener.onCardClicked(unit);
          }
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
