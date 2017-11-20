package com.olan.warmonger;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class Unit extends TileObject {
  private static final float MANUAL_OFFSET_X = 6f;
  private static final float MANUAL_OFFSET_Y = 15.0f;
  private static final float MOVE_SPEED = 5f;

  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange = 1;
  private int attackPoint = 2;
  private int healthPoint = 4;
  private Text healthText;

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

    healthText = new Text(Assets.worldFont);
    setHealthPoint(healthPoint);
  }

  @Override
  public void draw (Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);

    batch.draw(Assets.hearth,
        getX() + getWidth() - Assets.hearth.getRegionWidth() / 2,
        getY() - Assets.hearth.getRegionHeight() / 2);
    healthText.draw(batch);
  }

  @Override
  protected void	positionChanged () {
    healthText.setCenter(getX() + getWidth(), getY());
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
    healthText.setText(healthPoint + "");
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
    setOnTile(tile);
    return false;
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
