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
		
		return true;
	}
	
}
