package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Content;


public class GameClass extends Game {

	// Nome e tamanho do jogo
	public static final String GAMENAME = "Doggie";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;	
	
	
	public SpriteBatch sb;
	
	
	// Fontes
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public static BitmapFont font;
	

	// Necessário implementar Manager Cenas
	public static Content res;
	  
	public void create() {
		
		res = new Content();
		res.loadTexture("images/doggie.png", "doggie");
		res.loadTexture("images/doggie.png", "doggieIdle");
		res.loadTexture("images/bunny_idle.png", "dogIdle");
		res.loadTexture("images/crystal.png", "coleiras");
		res.loadTexture("images/hud.png", "hud");
		sb = new SpriteBatch();
		
		
		// Maneira complexa que permite utilização de fontes externas.
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 18;
		fontParameter.borderWidth = 1;
		fontParameter.borderColor = Color.BLUE;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);
		
		
		
		
		
		setScreen(new PlayScreen(this));
		
		
		
	}
	@Override
	public void resize(int width, int height) {	}

	public void render() { 
		super.render();
	}
	
	public void pause() {}
	
	public void resume() {}

	public void dispose() {
		super.dispose();
		sb.dispose();		
	}
	
	
}
