package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map extends GameObject {
  public static final int WIDTH = 6;
  public static final int HEIGHT = 10;

  Tile[][] tiles = new Tile[WIDTH][HEIGHT];

  public Map () {
    super(new TextureRegion(new Texture("images/background.png"), 0, 0, 600, 1000));
    initTiles();
  }

  private void initTiles () {
    for (int i=0; i<WIDTH; i++) {
      for (int j=0; j<HEIGHT; j++) {
        tiles[i][j] = new Tile(i, j);
      }
    }
  }

  public Tile getTile (int row, int column) {
    return tiles[row][column];
  }

  public Tile[][] getTiles () {
    return tiles;
  }

}
