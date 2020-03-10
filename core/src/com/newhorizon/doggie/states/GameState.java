package com.newhorizon.doggie.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.handlers.GameStateManager;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected GameClass game;
	
	protected SpriteBatch sb;
	protected OrthographicCamera camera1;
	protected OrthographicCamera camera2;
	
	protected GameState(GameStateManager gsm)
	{
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		camera1 = game.getCamera1();
		camera2 = game.getCamera2();
		
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render ();
	public abstract void dispose();

}
