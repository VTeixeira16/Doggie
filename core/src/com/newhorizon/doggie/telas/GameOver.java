package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.tools.ManagerCenas;

public class GameOver implements Screen{
	
	private GameClass game;
	private Screen screen;
	private float timer;

	// Classe deverá ser corrigida e adaptada para funcionar com o Manager Cenas

	public GameOver(PlayScreen playScreen) {
		this.screen = playScreen;
		timer = 0;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		timer += delta;


		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(timer > 2)
			ManagerCenas.setScreen(new PlayScreen(game), game);
		
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
	

}
