package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.newhorizon.doggie.telas.MenuScreen;
import com.newhorizon.doggie.tools.Content;



public class GameClass extends Game {

	// Nome e tamanho do jogo
	public static final String GAMENAME = "Doggie";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 600;	
	
	
	public static SpriteBatch sb;
	
	
	// Fontes
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public static BitmapFont font;
	
	private FreeTypeFontGenerator fontMenuGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontMenuParameter;
	public static BitmapFont fontMenu;

	private FreeTypeFontGenerator fontMenuPGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontMenuPParameter;
	public static BitmapFont fontMenuP;
	
	private FreeTypeFontGenerator fontMenuMGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontMenuMParameter;
	public static BitmapFont fontMenuM;
	
	private FreeTypeFontGenerator fontIntroGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontIntroParameter;
	public static BitmapFont fontIntro;
	
	private FreeTypeFontGenerator fontGameOverGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontGameOverParameter;
	public static BitmapFont fontGameOver;

	public static Content res;
	
	public static AssetManager manager;
	
	public static String telaAtual;
	  
	public void create() {
		
		
		//		ASSETS
		res = new Content();
		res.loadTexture("images/crystal.png", "coleiras");
		res.loadTexture("images/ossinho.png", "ossinho");
		res.loadTexture("images/ossoVeneno.png", "ossoVeneno");
		res.loadTexture("images/DoggieSprites.png", "doggieAndando");
		res.loadTexture("images/inimigoDoggieRun.png", "inimigoDoggie");
//		res.loadTexture("images/InimigoDoggie.png", "inimigoDoggie");
		
		
		// SONS
		manager = new AssetManager();
		manager.load("bin/main/sons/menu/DoggieMusicaMenu.wav", Music.class);
		manager.finishLoading();
		
		sb = new SpriteBatch();
		
		
		// Maneira complexa que permite utilização de fontes externas.
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 18;
		fontParameter.borderWidth = 2;
		fontParameter.borderColor = Color.BLUE;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);
		
		fontMenuGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontMenuParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontMenuParameter.size = 16;
		fontMenuParameter.borderWidth = 0.1f;
		fontMenuParameter.borderColor = Color.RED;
		fontMenuParameter.color = Color.BLACK;
		fontMenuParameter.spaceX = 0;
		fontMenu = fontGenerator.generateFont(fontMenuParameter);
		
		fontMenuPGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontMenuPParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontMenuPParameter.size = 32;
		fontMenuPParameter.borderWidth = 0f;
		fontMenuPParameter.borderColor = Color.RED;
		fontMenuPParameter.color = Color.BLACK;
		fontMenuPParameter.spaceX = 1;
		fontMenuP = fontGenerator.generateFont(fontMenuPParameter);
		
		fontMenuMGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontMenuMParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontMenuMParameter.size = 22;
		fontMenuMParameter.borderWidth = 0f;
		fontMenuMParameter.borderColor = Color.RED;
		fontMenuMParameter.color = Color.GOLDENROD;
		fontMenuMParameter.spaceX = 0;
		fontMenuM = fontGenerator.generateFont(fontMenuMParameter);
		
		fontIntroGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontIntroParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontIntroParameter.size = 22;
		fontIntroParameter.borderWidth = 0f;
		fontIntroParameter.borderColor = Color.BLUE;
		fontIntroParameter.color = Color.WHITE;
		fontIntroParameter.spaceX = 0;
		fontIntro = fontGenerator.generateFont(fontIntroParameter);
		
		fontGameOverGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontGameOverParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontGameOverParameter.size = 72;
		fontGameOverParameter.borderWidth = 0.5f;
		fontGameOverParameter.borderColor = Color.BLUE;
		fontGameOverParameter.color = Color.BLACK;
		fontGameOverParameter.spaceX = 0;
		fontGameOver = fontGenerator.generateFont(fontGameOverParameter);
		
		
		
		
		
		
		
//		setScreen(new PlayScreen(this));
		setScreen(new MenuScreen(this));
		
		
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
