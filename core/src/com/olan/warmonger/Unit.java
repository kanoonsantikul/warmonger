package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends TileObject {
  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange;

  public static float manualOffsetX = 5.0f;
  public static float manualOffsetY = 15.0f;

  public Unit (int row, int column) {
    super(Assets.pikemanBack, row, column);
    setOffsetX(manualOffsetX);
    setOffsetY(manualOffsetY);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onUnitClicked(Unit.this, Unit.this.getRow(), Unit.this.getColumn());
        }
      }
    });
  }

  public void addListener (UnitListener listerner) {
      this.listener = listerner;
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

  public boolean canMove (int row, int column) {
    return (row > getRow())
        && (getRow() + getMoveRange() >= row)
        && (getColumn() == column);
  }

  public void move(int row, int column) {
    setRow(row);
    setColumn(column);
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
