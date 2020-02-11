package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen extends Game implements Screen {
	
	private Game game;
	private AssetManager manager;
	
	private SpriteBatch batch;
	private Texture barra;
	
	private float time = 0;
	
	
	
//	public static int Numero = 0;
	
	public SplashScreen(Game game, AssetManager manager) {
		this.game = game;
		this.manager = manager;
		Gdx.app.log("log", "SplashScreen");
				
	}
	


	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		barra = new Texture("barra.png");
		
		Gdx.app.log("log", "SplashScreen Show");
		
		 
		
	}

	@Override
	public void render(float delta) {
		 super.render();
		Gdx.app.log("log", "SplashScreen Render");
		time += delta;
		
		if (manager.update() && time >= 0 ) { // Original é 2 segundos
//			game.setScreen(new MainScreen(game, manager));
			game.setScreen(new GameScreenIntro(game, manager));
			Gdx.app.log("log","Splash to Game");
			
		}
//		manager.update();
		
		Gdx.gl.glClearColor(6/250f,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.app.log("log", "SplashScreen");
		
		batch.begin();
		
		
		batch.draw(barra, 0.1f*Gdx.graphics.getWidth(), 0.15f*Gdx.graphics.getHeight(),
				0.8f*Gdx.graphics.getWidth()*Math.min(manager.getProgress(), time/2f),
				0.1f*Gdx.graphics.getHeight()
				);
			
		
		
		
		batch.end();
		
	}
	public void render() {
	    super.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

}