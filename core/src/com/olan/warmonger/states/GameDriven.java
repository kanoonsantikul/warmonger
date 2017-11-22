package com.olan.warmonger;

public class GameDriven {
  private Action state;

  public void setState (GameDriven.Action state) {
    if (this.state != null) {
      this.state.exit();
    }
    this.state = state;
    if (this.state != null) {
      this.state.enter();
    }
  }

  public void run () {
    state.run();
  }

  public State getState () {
    return this.state instanceof State ? (State)state : null;
  }

  public interface Action {
    public void enter ();
    public void exit ();
    public void run ();
  }

  public interface State extends Action,
      Unit.UnitListener,
      Building.BuildingListener,
      Tile.TileListener {
  }
}
