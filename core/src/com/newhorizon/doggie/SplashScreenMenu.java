package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreenMenu extends Game implements Screen {
	
	private Game game;
	private AssetManager managerIntro;
	
	private SpriteBatch batch;
	private Texture barra;
	
	private float time = 0;
	
	public SplashScreenMenu(Game game, AssetManager managerIntro) {
		this.game = game;
		this.managerIntro = managerIntro;
		Gdx.app.log("log", "SplashScreen");
				
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		barra = new Texture("barra.png");
		Gdx.app.log("log", "SplashScreen Show");

		
	}
	@Override
	public void render(float delta) {
		super.render();
		Gdx.app.log("log", "SplashScreen Render");
		time += delta;
		
		//NÃO ESTÁ FUNCIONANDO CORRETAMENTE COM O TEMPO, NECESSÁRIO TESTAR NO FUTURO.
		if (managerIntro.update() && time >= 2 ) { // Original é 2 segundos
			game.setScreen(new GameLevelIntro(game, managerIntro));
			Gdx.app.log("log","Splash to Game");
			
		}
		managerIntro.update();
		
		
		Gdx.gl.glClearColor(6/250f,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.app.log("log", "SplashScreen Render 1");
		
		batch.begin();
		
		//Desenha a barra de loading
		batch.draw(barra, 0.1f*Gdx.graphics.getWidth(), 0.15f*Gdx.graphics.getHeight(),
				0.8f*Gdx.graphics.getWidth()*Math.min(managerIntro.getProgress(), time/2f),
				0.1f*Gdx.graphics.getHeight()
				);
		
		batch.end();
		
	}
	
	public void render() {
	    super.render();
	    Gdx.app.log("log", "SplashScreen Render 2");
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
