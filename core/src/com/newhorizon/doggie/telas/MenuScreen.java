package com.newhorizon.doggie.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.tools.MenuListener;

public class MenuScreen implements Screen{
	
	public GameClass game;
	private MenuListener menuLis;
	
	public MenuScreen (GameClass game) {
		this.game = game;
		menuLis = new MenuListener();		
	}

	@Override
	public void show() {
		menuLis.create();
		
	}

	@Override
	public void render(float delta) {
		menuLis.render();
		
		if(Gdx.input.isButtonJustPressed(menuLis.button4))
		{
			System.out.println("Botão pressionado");
			game.setScreen(new PlayScreen(game));
			
//			Deverá ser aplicado no btn sair
//			System.exit(0);		
		}
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
