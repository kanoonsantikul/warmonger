package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class UnitFactory extends Group implements Card.CardListener {
  UnitFactoryListener listener;

  public UnitFactory () {
    Card card;

    card = new Card(Assets.cardPikeman, new PikeMan());
    card.setCenter(900, 500);
    card.setListener(this);
    addActor(card);

    card = new Card(Assets.cardCavalry, new Cavalry());
    card.setCenter(1000, 500);
    card.setListener(this);
    addActor(card);

    card = new Card(Assets.cardArcher, new Archer());
    card.setCenter(1100, 500);
    card.setListener(this);
    addActor(card);
  }

  @Override
  public void onCardClicked (Unit unit) {
    if (listener != null) {
      listener.onUnitFactoryClicked(unit);
    }
  }

  public void setListener (UnitFactoryListener listener) {
    this.listener = listener;
  }

  public interface UnitFactoryListener {
    public void onUnitFactoryClicked (Unit unit);
  }
}
