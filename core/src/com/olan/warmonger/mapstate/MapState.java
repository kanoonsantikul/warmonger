package com.olan.warmonger;

public class MapState {
  State state;

  public void set (State state) {
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

  public boolean is (Class object) {
    return state.getClass() == object;
  }

  public interface State {
    public void enter ();
    public void exit ();
    public void run ();
  }
}
