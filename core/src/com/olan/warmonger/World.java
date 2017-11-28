package com.olan.warmonger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class World extends Stage implements Unit.UnitListener,
    Tile.TileListener,
    Building.BuildingListener,
    UnitFactory.UnitFactoryListener {
	public static final int WIDTH = Gdx.graphics.getWidth();
	public static final int HEIGHT = Gdx.graphics.getHeight();

	private static World world;
	private Viewport viewport;

	private GameObject background;
  private UnitFactory unitFactory;
	private Map map;
	private Hud hud;
	private boolean end;

  private GameDriven gameDriven;
  private Team currentTeam;

  private Player redPlayer = new Player(Team.RED);
  private Player bluePlayer = new Player(Team.BLUE);

	public World (Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
		this.world = this;
		this.viewport = viewport;

		background = new GameObject(Assets.background);
		addActor(background);

		map = new MapBuilder().build();
		addActor(map);

		unitFactory = new UnitFactory();
    unitFactory.setListener(this);
		addActor(unitFactory);

		hud = new Hud();
    hud.renderBuildingHealths(map.getBuildings());
    hud.renderUnitHealths(map.getUnits());
		addActor(hud);

		currentTeam = Team.BLUE;
    redPlayer.setResourceTexture(Assets.resourceCountGray);

		addActor(redPlayer);
		addActor(bluePlayer);

		gameDriven = new GameDriven();
		setState(new StateIdle((map)));
	}

	public void setEnd (boolean end) {
		this.end = end;
	}

	public boolean isEnd () {
		return this.end;
	}

	public static World instance () {
		return world;
	}

	@Override
	public void	act (float delta) {
		gameDriven.run();
	}

	@Override
	public void draw () {
		super.draw();
  }

	@Override
	public void onTileClicked (Tile tile, int row, int column) {
		if (gameDriven.getState() != null) {
			gameDriven.getState().onTileClicked(tile, row, column);
		}
	}

	@Override
	public void onUnitClicked (Unit unit, int row, int column) {
		if (gameDriven.getState() != null) {
			gameDriven.getState().onUnitClicked(unit, row, column);
		}
	}

	@Override
	public void onBuildingClicked (Building building, int row, int column) {
		if (gameDriven.getState() != null) {
			gameDriven.getState().onBuildingClicked(building, row, column);
		}
	}

	@Override
	public void onUnitFactoryClicked (Unit unit) {
		if (gameDriven.getState() != null) {
			gameDriven.getState().onUnitFactoryClicked(unit);
		}
	}

	public Player getBluePlayer () {
		return this.bluePlayer;
	}

	public Player getRedPlayer () {
		return this.redPlayer;
	}

	public Team getCurrentTeam () {
		return this.currentTeam;
	}

	public void setCurrentTeam (Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public void setState (GameDriven.Action state) {
		gameDriven.setState(state);
	}

  public Hud getHud () {
    return this.hud;
  }

  public UnitFactory getUnitFactory () {
    return this.unitFactory;
  }
}
