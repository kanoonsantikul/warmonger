package com.olan.warmonger;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Unit extends TileObject {
  private static final float MOVE_SPEED = 5f;

  private UnitListener listener;

  private int moveRange = 2;
  private int attackRange = 1;
  private int attackPoint = 2;

  private TextureRegion textureFront;
  private TextureRegion textureBack;

  public Unit (Team team, int row, int column) {
    super(row, column);
    setTeam(team);

    addListener(new ClickListener () {
      public void clicked (InputEvent event, float x, float y) {
        if (listener != null) {
          listener.onUnitClicked(Unit.this, Unit.this.getRow(), Unit.this.getColumn());
        }
      }
    });
  }

  public void setListener (UnitListener listener) {
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
      setTexture(textureBack);
    } else if (team == Team.RED){
      setTexture(textureFront);
    }
  }

  public void setTextures (TextureRegion front, TextureRegion back) {
    this.textureFront = front;
    this.textureBack = back;
  }

  public Unit clone () {
    return new Unit(null, 0, 0);
  }

  public interface UnitListener {
    public void onUnitClicked (Unit unit, int row, int column);
  }
}
