package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;

public class Hud{

	private Doggie doggie;
	
	private TextureRegion[] blocks;
	
	
	public Hud(Doggie doggie) {
		// TODO Auto-generated constructor stub
		
		this.doggie = doggie;

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
