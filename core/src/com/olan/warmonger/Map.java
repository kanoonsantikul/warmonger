package com.olan.warmonger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Map extends Group implements Unit.UnitListener,
    Tile.TileListener,
    Castle.CastleListener,
    UnitFactory.UnitFactoryListener {
  public static final int ROW = 10;
  public static final int COLUMN = 5;

  private float width = Tile.WIDTH * COLUMN;
  private float height = Tile.HEIGHT * ROW;

  private Tile[][] tiles = new Tile[ROW][COLUMN];
  private ArrayList<Castle> castles = new ArrayList<Castle>();
  private ArrayList<Unit> units = new ArrayList<Unit>();

  private Unit selectedUnit;
  private Team currentTeam;
  private MapState mapState;

  private Player redPlayer = new Player(Team.RED);
  private Player bluePlayer = new Player(Team.BLUE);

  private int redResourceAmount;
  private int blueResourceAmount;
  private int redResourceRate;
  private int blueResourceRate;


  private UnitFactory unitFactory;
  private Unit newUnit;
  private boolean isCreatingUnit;

  public Map () {
    initTiles();
    initResources();
    initCastles();

    currentTeam = Team.BLUE;

    mapState = new MapState();
    setState(new StateIdle((this)));

    unitFactory = new UnitFactory();
		addActor(unitFactory);
		unitFactory.addListener(this);
  }

  private void initTiles () {
    float offsetX = (World.WIDTH - width) / 2;
    float offsetY = (World.HEIGHT - height) / 2;

    Tile tile;
    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COLUMN; j++) {
        tile = new Tile(i, j);
        tile.addListener(this);
        tile.setPosition(
            offsetX + Tile.WIDTH * j,
            offsetY + Tile.HEIGHT * i);
        tiles[i][j] = tile;
        addActor(tile);
      }
    }
  }

  private void initResources () {
    Tile tile;
    (tile = findEmptyTile()).setResource(3);
    tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(3);
    for (int i = 0; i < 2; i++) {
      (tile = findEmptyTile()).setResource(2);
      tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(2);
    }
    for (int i = 0; i < 4; i++) {
      (tile = findEmptyTile()).setResource(1);
      tiles[ROW - tile.getRow() - 1][COLUMN - tile.getColumn() - 1].setResource(1);
    }
  }

  private Tile findEmptyTile () {
    Tile tile;
    do {
      int row = MathUtils.random(2, (ROW / 2) - 1);
      int column = MathUtils.random(0, COLUMN - 1);
      tile = tiles[row][column];
    } while(tile == null || tile.getResource() != 0);
    return tile;
  }

  private void initCastles () {
    Castle castle;
    for (int column = 0; column < COLUMN; column++) {
      castle = new Castle(Team.BLUE, 0, column);
      castle.addListener(this);
      castle.setOnTile(tiles[0][column]);
      castles.add(castle);
      addActor(castle);

      castle = new Castle(Team.RED, ROW - 1, column);
      castle.addListener(this);
      castle.setOnTile(tiles[ROW - 1][column]);
      castles.add(castle);
      addActor(castle);
    }
  }


  public Tile getTile (int row, int column) {
    return tiles[row][column];
  }

  public Tile[][] getTiles () {
    return tiles;
  }

  public ArrayList<Castle> getCastles () {
    return castles;
  }

  public Castle getCastle (int row, int column) {
    for (Castle castle : castles) {
      if (castle.getRow() == row && castle.getColumn() == column) {
        return castle;
      }
    }
    return null;
  }

  public ArrayList<Unit> getUnits () {
    return units;
  }

  public Unit getUnit (int row, int column) {
    for (Unit unit : units) {
      if (unit.getRow() == row && unit.getColumn() == column) {
        return unit;
      }
    }
    return null;
  }

  public void addUnit (Unit unit) {
    unit.addListener(this);
    unit.setOnTile(tiles[unit.getRow()][unit.getColumn()]);
    units.add(unit);
    addActor(unit);
  }

  public Unit getSelectedUnit () {
    return this.selectedUnit;
  }

  public void selectUnit (Unit selectedUnit) {
    this.selectedUnit = selectedUnit;
  }

  public void setState (MapState.State state) {
    mapState.set(state);
  }

  public void endTurn () {
    if (currentTeam == Team.RED) {
      redResourceRate = calculateResourceRate();
      redResourceAmount += redResourceRate;

      currentTeam = Team.BLUE;
    } else {
      blueResourceRate = calculateResourceRate();
      blueResourceAmount += blueResourceRate;

      currentTeam = Team.RED;
    }
  }

  public int calculateResourceRate () {
    int resourceRate = 0;

    for (Unit unit : units) {
      if (unit.getTeam() == currentTeam) {
        resourceRate += getTile(unit.getRow(), unit.getColumn()).getResource();
      }
    }

    return resourceRate;
  }

  @Override
  public void onTileClicked (Tile tile, int row, int column) {
    if (mapState.is(StateUnitSelected.class)) {
      if (tile.isSelectionVisible()) {
        setState(new StateUnitMove(this, getSelectedUnit(), tile));
      } else {
        setState(new StateIdle(this));
      }
    }
  }

  @Override
  public void onUnitClicked (Unit unit, int row, int column) {
    if (mapState.is(StateIdle.class) && unit.getTeam() == currentTeam) {
      setState(new StateUnitSelected(this, unit));
    } else if (mapState.is(StateUnitSelected.class)) {
      if (unit.getTeam() == currentTeam) {
        setState(new StateUnitSelected(this, unit));
      } else if (getTile(unit.getRow(), unit.getColumn()).isSelectionVisible()) {
        setState(new StateUnitCombat(this, getSelectedUnit(), unit));
      }
    }
  }

  @Override
  public void onCastleClicked (Castle castle, int row, int column) {
    if (mapState.is(StateUnitSelected.class)) {
      if (castle.getTeam() == getSelectedUnit().getTeam()) {
        setState(new StateIdle(this));
      } else {
        setState(new StateCastleCombat(this, getSelectedUnit(), castle));
      }
    }
  }

  @Override
	public void onUnitFactoryClicked (Unit unit) {
		if (newUnit == null) {
			this.newUnit = unit;
      this.isCreatingUnit = true;
			addActor(newUnit);
		}
	}

  public void act () {
    mapState.run();

    if (newUnit != null) {
			newUnit.setCenter(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
		}
  }
}
