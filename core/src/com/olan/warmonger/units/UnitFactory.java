package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class UnitFactory extends Group implements Card.CardListener {
  UnitFactoryListener listener;
  Card cardPikeman;
  Card cardCavalry;
  Card cardArcher;

  public UnitFactory () {

    cardPikeman = new Card(Assets.cardPikeman, new PikeMan());
    cardPikeman.setCenter(980, 580);
    cardPikeman.setListener(this);
    addActor(cardPikeman);

    cardCavalry = new Card(Assets.cardCavalry, new Cavalry());
    cardCavalry.setCenter(980, 440);
    cardCavalry.setListener(this);
    addActor(cardCavalry);

    cardArcher = new Card(Assets.cardArcher, new Archer());
    cardArcher.setCenter(980, 300);
    cardArcher.setListener(this);
    addActor(cardArcher);
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
