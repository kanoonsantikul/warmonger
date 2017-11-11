package com.olan.warmonger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends GameObject {
  private int row, collumn;
  private UnitListener listener;

  private int moveRange;
  private int attackRange;

  public Unit (int row, int collumn) {
    this.row = row;
    this.collumn = collumn;

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        listener.onUnitClicked(Unit.this, Unit.this.row, Unit.this.collumn);
      }
    });
  }

  public void addListener (UnitListener listerner) {
      this.listener = listerner;
  }

  public int getRow () {
    return this.row;
  }

  public void setRow (int row) {
    this.row = row;
  }

  public int getcollumn () {
    return this.collumn;
  }

  public void setcollumn (int collumn) {
    this.collumn = collumn;
  }

  public int getMoveRange () {
    return this.moveRange;
  }

  public void setMoveRange (int moveRange) {
    this.moveRange = moveRange;
  }

  public int getAttackRange () {
    return this.attackRange;
  }

  public void setAttackRange (int attackRange) {
    this.attackRange = attackRange;
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int collumn);
  }
}
