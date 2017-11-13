package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends GameObject {
  private int row, column;
  private UnitListener listener;

  private int moveRange;
  private int attackRange;

  public Unit (int row, int column) {
    super(Assets.unit);

    this.row = row;
    this.column = column;

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onUnitClicked(Unit.this, Unit.this.row, Unit.this.column);
        }
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

  public int getcolumn () {
    return this.column;
  }

  public void setcolumn (int column) {
    this.column = column;
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

  @Override
  public void act (float delta) {
    indexToXY();
  }

  public void indexToXY () {
    setX(row * getWidth());
    setY(column * getHeight());
  }
  
  public void setAttackRange (int attackRange) {
    this.attackRange = attackRange;
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
