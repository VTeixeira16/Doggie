package com.newhorizon.doggie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.handlers.b2dVariaveis;

public class HUD {
	
	private Doggie doggie;
	
	private TextureRegion[] blocks;
	
	public HUD(Doggie doggie)
	{
		this.doggie = doggie;
		
		Texture tex = GameClass.res.getTexture("hud");
		
		blocks = new TextureRegion[2];
		for(int i = 0; i < blocks.length; i++)
		{
			blocks [i] = new TextureRegion(tex, 32 + i * 16, 0, 16, 16);
		}
	}
	
	public void render (SpriteBatch sb) {
		
		short bits = doggie.getBody().getFixtureList().first()
						.getFilterData().maskBits;
		sb.begin();
		if((bits & b2dVariaveis.BIT_PLATAFORMA) != 0) 
		{
			sb.draw(blocks[0], 40, 200);
		}
		if((bits & b2dVariaveis.BIT_PLATAFORMA_ELEV)!= 0)
		{
			sb.draw(blocks[1], 40, 200);
		}
		sb.end();
		
		
	}
	

}
