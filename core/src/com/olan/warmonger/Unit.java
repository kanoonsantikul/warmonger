package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends TileObject {
  private static final float MANUAL_OFFSET_X = 15.0f;
  private static final float MANUAL_OFFSET_Y = 5.0f;
  private static final float MOVE_SPEED = 4.5f;

  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange = 1;
  private int attackPoint = 2;
  private int healthPoint = 4;

  public Unit (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    if (team == Team.RED) {
      setTexture(Assets.pikemanFront);
      setOffsetX(-MANUAL_OFFSET_X);
    } else {
      setTexture(Assets.pikemanBack);
      setOffsetX(MANUAL_OFFSET_X);
    }

    setOffsetY(MANUAL_OFFSET_Y);

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

  public int getAttackPoint () {
    return this.attackPoint;
  }

  public void setAttackPoint (int attackPoint) {
    this.attackPoint = attackPoint;
  }

  public int getHealthPoint () {
    return this.healthPoint;
  }

  public void setHealthPoint (int healthPoint) {
    this.healthPoint = healthPoint;
  }

  public boolean canMoveTo (Tile tile) {
    if (getTeam() == Team.RED) {
      return (tile.getRow() < getRow())
          && (getRow() - getMoveRange() <= tile.getRow())
          && (getColumn() == tile.getColumn());
    } else {
      return (tile.getRow() > getRow())
          && (getRow() + getMoveRange() >= tile.getRow())
          && (getColumn() == tile.getColumn());
    }
  }

  public boolean isMovingTo (Tile tile) {
    if (getTeam() == Team.RED) {
      if (getCenterY() - getOffsetY() > tile.getCenterY()) {
        this.moveBy(0.0f, -Unit.MOVE_SPEED);
        return true;
      }
    } else {
      if (getCenterY() - getOffsetY() < tile.getCenterY()) {
        this.moveBy(0.0f, Unit.MOVE_SPEED);
        return true;
      }
    }
    setRow(tile.getRow());
    setColumn(tile.getColumn());
    return false;
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
