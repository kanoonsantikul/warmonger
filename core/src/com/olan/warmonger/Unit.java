package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends TileObject {
  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange;

  public static float manualOffsetX = 5.0f;
  public static float manualOffsetY = 15.0f;

  public Unit (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    if (team == Team.RED) {
      setTexture(Assets.pikemanFront);
      this.manualOffsetX = -manualOffsetX;
    } else {
      setTexture(Assets.pikemanBack);
    }

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
        this.moveBy(0.0f, -World.MOVE_SPEED);
        return true;
      }
    } else {
      if (getCenterY() - getOffsetY() < tile.getCenterY()) {
        this.moveBy(0.0f, World.MOVE_SPEED);
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
