package com.newhorizon.doggie.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GameInputProcessor extends InputAdapter {
	
	public boolean keyDown(int k)
	{
		if(k == Keys.Z)
		{
			GameInputs.setKey(GameInputs.BUTTON1, true);
		}
		if(k == Keys.X)
		{
			GameInputs.setKey(GameInputs.BUTTON2, true);
		}
		if(k == Keys.LEFT)
		{
			GameInputs.setKey(GameInputs.BUTTONLEFT, true);
		}
		if(k == Keys.RIGHT)
		{
			GameInputs.setKey(GameInputs.BUTTONRIGHT, true);
		}
				
		return true;
	}
	
	public boolean keyUp(int k)
	{
		if(k == Keys.Z)
		{
			GameInputs.setKey(GameInputs.BUTTON1, false);
		}
		if(k == Keys.X)
		{
			GameInputs.setKey(GameInputs.BUTTON2, false);
		}
		if(k == Keys.LEFT)
		{
			GameInputs.setKey(GameInputs.BUTTONLEFT, false);
		}
		if(k == Keys.RIGHT)
		{
			GameInputs.setKey(GameInputs.BUTTONRIGHT, false);
		}
		
		return true;
	}
	
}
