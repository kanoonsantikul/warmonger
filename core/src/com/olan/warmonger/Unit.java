package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends TileObject {
  private static final float MANUAL_OFFSET_X = 6f;
  private static final float MANUAL_OFFSET_Y = 15.0f;
  private static final float MOVE_SPEED = 5f;
  private static final int HEALTH_POINT = 4;

  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange = 1;
  private int attackPoint = 2;

  public Unit (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    setOffsetY(MANUAL_OFFSET_Y);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onUnitClicked(Unit.this, Unit.this.getRow(), Unit.this.getColumn());
        }
      }
    });

    setHealthPoint(HEALTH_POINT);
  }

  public void addListener (UnitListener listener) {
      this.listener = listener;
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
    setOnTile(tile);

    return false;
  }

  @Override
  public void setTeam (Team team) {
    super.setTeam(team);

    if (team == Team.BLUE) {
      setTexture(Assets.pikemanBack);
      setOffsetX(MANUAL_OFFSET_X);
    } else {
      setTexture(Assets.pikemanFront);
      setOffsetX(-MANUAL_OFFSET_X);
    }
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
