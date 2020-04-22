package com.newhorizon.doggie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newhorizon.doggie.GameClass;

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
		GameClass.font.draw(sb, "Vidas: " + doggie.getTotalVidas() , Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
		GameClass.font.draw(sb, "Coleiras: " + doggie.getNumColeiras(), 5, Gdx.graphics.getHeight() - 10);
		
		sb.end();
		
		
	}
	

}
