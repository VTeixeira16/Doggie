package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.newhorizon.doggie.GameClass;

public class GameOver implements Screen{
	
	private GameClass game;
	private Screen screen;
	private float timer;
	
	private String texto1 = "Game Over";
	private String texto2 = "Doggie não obteve sucesso em sua jornada.";

	// Classe deverá ser corrigida e adaptada para funcionar com o Manager Cenas

	public GameOver(GameClass game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		timer += delta;


		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.sb.begin();
		
		GameClass.fontGameOver.draw(game.sb, texto1, 0,
				Gdx.graphics.getHeight() /2 + 40);

		GameClass.fontMenu.draw(game.sb, texto2, 0, Gdx.graphics.getHeight() / 10);
		
		
		game.sb.end();
		
		if(timer > 10)
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
		
	}
	

}
