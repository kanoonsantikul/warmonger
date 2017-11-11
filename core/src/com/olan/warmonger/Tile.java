package com.olan.warmonger;

public class Tile extends GameObject {
  private int row;
  private int column;

  public Tile (int row, int column) {
    setRow(row);
    setColumn(column);
  }

  public void setRow (int row) {
    this.row = row;
  }

  public void setColumn (int column) {
    this.column = column;
  }

  public int getRow () {
    return row;
  }

  public int getColumn () {
    return column;
  }
}
