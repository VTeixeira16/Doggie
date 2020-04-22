package com.newhorizon.doggie;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.newhorizon.doggie.handlers.Content;
import com.newhorizon.doggie.handlers.GameInputProcessor;
import com.newhorizon.doggie.handlers.GameInputs;
import com.newhorizon.doggie.handlers.GameStateManager;


public class GameClass implements ApplicationListener {

	public static final String GameName = "Doggie";
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 480;
	public static final int SCALE = 2;
	
	public static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch sb;
	private OrthographicCamera camera1;
	private OrthographicCamera cameraHUD;
	
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public static BitmapFont font;
	
	private GameStateManager gsm;
	public static Content res;
	  
	public void create() {
		

		
		Gdx.input.setInputProcessor(new GameInputProcessor());
		
		res = new Content();
		res.loadTexture("images/bunny.png", "doggie");
		res.loadTexture("images/bunny_idle.png", "doggieIdle");
		res.loadTexture("images/crystal.png", "coleiras");
		res.loadTexture("images/hud.png", "hud");
		sb = new SpriteBatch();
		camera1 = new OrthographicCamera();
		camera1.setToOrtho(false, V_WIDTH, V_HEIGHT);
		cameraHUD = new OrthographicCamera();
		cameraHUD.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		//fonts
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Arial Black.ttf"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 18;
		fontParameter.borderWidth = 1;
		fontParameter.borderColor = Color.BLUE;
		fontParameter.color = Color.WHITE;
		font = fontGenerator.generateFont(fontParameter);
		
		
		
		
	
		gsm = new GameStateManager(this);
		
	}
	@Override
	public void resize(int width, int height) {
		
	}
	@Override
	public void render() {
		 accum += Gdx.graphics.getDeltaTime();
		 while(accum >= STEP)
		 {
			 accum -= STEP;
			 gsm.update(STEP);
			 gsm.render();
			 
			 GameInputs.update();
		 }
		 
		 sb.setProjectionMatrix(cameraHUD.combined);
		 sb.begin();
//		 sb.draw(res.getTexture("doggie"), 0 , 0 );
		 sb.end();
		 
		
	}
	@Override
	public void pause() {

		
	}
	@Override
	public void resume() {
		
	}
	@Override
	public void dispose() {
		font.dispose();
	}
	
	public SpriteBatch getSpriteBatch() { return sb;}
	public OrthographicCamera getCamera1() {return camera1;}
	public OrthographicCamera getcameraHUD() {return cameraHUD;}
	
}
