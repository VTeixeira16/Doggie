package com.newhorizon.doggie.handlers;

import java.util.Stack;

import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.states.GameState;
import com.newhorizon.doggie.states.Play;

public class GameStateManager {
	
	private GameClass game;
	
	private Stack<GameState> gameStates;
	public static final int PLAY = 912837;
	
	public GameStateManager (GameClass game)
	{
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	public GameClass game() {return game;}
	
	public void update (float dt)
	{
		gameStates.peek().update(dt);
	}
	public void render ()
	{
		gameStates.peek().render();
	}
	public GameState getState(int state)
	{
		if(state == PLAY) return new Play(this);
		return null;
	}
	public void setState(int state)
	{
		popState();
		pushState(state);
	}
	public void pushState(int state)
	{
		gameStates.push(getState(state));
	}
	public void popState()
	{
		GameState gPop = gameStates.pop();
		gPop.dispose();
	}
	
}
