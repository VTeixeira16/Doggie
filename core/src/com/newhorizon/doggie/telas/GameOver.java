package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;

public class GameOver implements Screen{
	
	private GameClass game;
	private Screen screen;
	private float timer;
	
	private Sound som;
	
	SpriteBatch sb;
	
	private String texto1;
	private String texto2;

	// Classe dever� ser corrigida e adaptada para funcionar com o Manager Cenas

	public GameOver(GameClass game) {
		this.game = game;
		
		sb = new SpriteBatch();
		
		if(game.Language == "Portugues")
		{	
			texto1 = "Fim de jogo";
			texto2 = "Doggie não obteve sucesso em sua jornada.";		
		}
		else if(game.Language == "Ingles")
		{
			texto1 = "Game Over";
			texto2 = "Doggie was unsuccessful on his journey.";		
		}
		
		som = GameClass.manager.get("sons/GameOver/GameOver_noLoop.mp3", Sound.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		som.play();
		
	}

	@Override
	public void render(float delta) {
		timer += delta;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.begin();
		
		GameClass.fontGameOver.draw(sb, texto1, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);

		GameClass.fontMenu.draw(sb, texto2, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4); //Gdx.graphics.getHeight() / 10);
		
		
		sb.end();
		
		if(timer > 5)
			game.setScreen(new MenuScreen(game));
		
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
		GameClass.fontMenu.dispose();
		GameClass.fontGameOver.dispose();
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
//		sb.dispose();
		
	}
	

}
