package com.olan.warmonger;

public class GameDriven {

  public interface Action {
    public void enter ();
    public void exit ();
    public void run ();
  }

  public interface State extends
      Unit.UnitListener,
      Building.BuildingListener,
      Tile.TileListener {
    public void enter ();
    public void exit ();
    public void run ();
  }
}
