package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.telas.SplashScreen;
import com.newhorizon.doggie.threads.ThreadMusica;
import com.newhorizon.doggie.tools.Content;

public class GameClass extends Game {

	public String Language;
	public boolean debug;
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
	
	public ThreadMusica threadMusica;
	public static int faseAtual;
	public static String telaAtual;
	  
	public void create() {
		
		debug = false;
		
		//		ASSETS
		res = new Content();
		res.loadTexture("images/ossinho.png", "ossinho");
		res.loadTexture("images/ossoVeneno.png", "ossoVeneno");
		res.loadTexture("images/DoggieSprites.png", "doggieAndando");
		res.loadTexture("images/inimigoDoggieRun.png", "inimigoDoggie");
		res.loadTexture("images/inimigoDoggie2.png", "inimigoDoggie2");
		
		res.loadTexture("OutGame/dogLogo.png", "dogLogo");
		res.loadTexture("OutGame/DoggieLogo.png", "doggieLogo");
		res.loadTexture("OutGame/fateclogo.png", "fatecLogo");
		res.loadTexture("OutGame/liblogo.png", "libLogo");
		res.loadTexture("OutGame/newHorizonLogo.png", "nhLogo");
		
		// SONS
		manager = new AssetManager();
		manager.load("sons/menu/DoggieMusicaMenu.mp3", Music.class);
		manager.load("sons/musicas/DoggieMusica01.mp3", Music.class);
		manager.load("sons/musicas/DoggieMusica02.mp3", Music.class);
		manager.load("sons/musicas/DoggieMusica03.mp3", Music.class);
		manager.load("sons/musicas/DoggieMusica04.mp3", Music.class);
		manager.load("sons/musicas/DoggieMusica05.mp3", Music.class);
		
		manager.load("sons/GameOver/GameOver_noLoop.mp3", Sound.class);
		manager.load("sons/latido/latidodoggie.mp3", Sound.class);
		manager.load("sons/latido/rosnadoinimigo.mp3", Sound.class);
		manager.load("sons/menu/menuClick.mp3", Sound.class);
		manager.load("sons/bones/up1.mp3", Sound.class);
		manager.load("sons/bones/veneno1.mp3", Sound.class);
		manager.finishLoading();
		
		sb = new SpriteBatch();
		
		
		// Maneira complexa que permite utilizacao de fontes externas.
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 18;
		fontParameter.borderWidth = 2;
		fontParameter.borderColor = Color.BLUE;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);
		
		fontMenuGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontMenuParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontMenuParameter.size = 18;
		fontMenuParameter.borderWidth = 0f;
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
		fontGameOverParameter.borderWidth = 0f;
		fontGameOverParameter.borderColor = Color.BLUE;
		fontGameOverParameter.color = Color.BLACK;
		fontGameOverParameter.spaceX = 0;
		fontGameOver = fontGenerator.generateFont(fontGameOverParameter);
				
		threadMusica = new ThreadMusica(this);
		
		threadMusica.start();
		
		
		
		
		faseAtual = 2; // Serve apenas para testes na playscreen. Valor � alterado na IntroGameScreen
		Language = "Portugues";
//		Language = "Ingles";
		
		setScreen(new PlayScreen(this));

		if(!debug)
		{			
			Language = "Ingles";
			setScreen(new SplashScreen(this));
		}
		
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
