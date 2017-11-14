package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Map extends GameObject implements Unit.UnitListener, Tile.TileListener {
  public static final int WIDTH = 5;
  public static final int HEIGHT = 8;

  private float sizeX = Assets.blockWidth * WIDTH;
  private float sizeY = Assets.blockHeight * HEIGHT;

  private Tile[][] tiles = new Tile[WIDTH][HEIGHT];
  private ArrayList<Unit> units = new ArrayList();

  public Map () {
    super(Assets.background);
    setOffsetX((Gdx.graphics.getWidth() - sizeX) / 2);
    setOffsetY((Gdx.graphics.getHeight() - sizeY + Assets.blockHeight) / 2);
    initTiles();
  }

  private void initTiles () {
    for (int i=0; i<WIDTH; i++) {
      for (int j=0; j<HEIGHT; j++) {
        tiles[i][j] = new Tile(i, j);
        tiles[i][j].setOffsetX(getOffsetX());
        tiles[i][j].setOffsetY(getOffsetY());
        System.out.println(getOffsetY());
      }
    }
  }

  public Tile getTile (int row, int column) {
    return tiles[row][column];
  }

  public Tile[][] getTiles () {
    return tiles;
  }

  public ArrayList<Unit> getUnits () {
    return units;
  }

  public void addUnit (Unit unit) {
    unit.setOffsetX(getOffsetX());
    unit.setOffsetY(getOffsetY());
    units.add(unit);
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    for (int i=0; i<WIDTH; i++) {
      for (int j=0; j<HEIGHT; j++) {
        getTile(i, j).setTexture(Assets.tileMark);
        if (i == row) {
          if ((j <= column + unit.getMoveRange()) && (j > column)) {
            getTile(i, j).setTexture(Assets.selectionNornal);
          }
        }
      }
    }
  }
}
