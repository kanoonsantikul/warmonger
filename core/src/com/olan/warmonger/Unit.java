package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends GameObject {
  private int row;
  private int column;
  private UnitListener listener;

  private int moveRange = 2;
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

  public int getColumn () {
    return this.column;
  }

  public void setColumn (int column) {
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

  public void setAttackRange (int attackRange) {
    this.attackRange = attackRange;
  }

  @Override
  public void act (float delta) {
    indexToXY();
  }

  public void indexToXY () {
    setX((column * Tile.WIDTH) + ((Tile.WIDTH - getWidth()) / 2));
    setY((row * Tile.HEIGHT) + ((Tile.HEIGHT - getHeight()) / 2));
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
