package com.olan.warmonger;

public class StateIdle implements MapState.State {
  private Map map;

  public StateIdle (Map map) {
    this.map = map;
    map.selectUnit(null);
  }

  public void enter () {

  }

  public void exit () {

  }

  public void run () {

  }
}
