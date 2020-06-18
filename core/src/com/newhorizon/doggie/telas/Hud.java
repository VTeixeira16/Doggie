package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;

public class Hud{

	private Doggie doggie;
	
	private TextureRegion[] blocks;
	
	private String vidas;
	private String ossos;
	
	
	public Hud(Doggie doggie, GameClass game) {
		// TODO Auto-generated constructor stub
		
		this.doggie = doggie;
		
		if(game.Language == "Portugues")
		{	
			vidas = "Vidas: ";
			ossos = "Ossos: ";			
		}
		else if(game.Language == "Ingles")
		{
			vidas = "Lives: ";
			ossos = "Bones: ";			
		}

	}

	public void render (SpriteBatch sb) {
		
		short bits = doggie.getBody().getFixtureList().first()
						.getFilterData().maskBits;
		sb.begin();
		GameClass.font.draw(sb, vidas + doggie.getTotalVidas() , Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);
		GameClass.font.draw(sb, ossos + doggie.getNumOssos(), 5, Gdx.graphics.getHeight() - 10);
		
		sb.end();
			
	}
}
