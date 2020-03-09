package com.newhorizon.doggie;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MainClass extends Game implements ApplicationListener {

	@Override
	public void create() {
		
	}
	@Override
	public void resize(int width, int height) {
		
	}
	@Override
	public void render() {
		 super.render();
		 Gdx.app.log("log", "MainClass");
		
	}
	@Override
	public void pause() {

		
	}
	@Override
	public void resume() {
		
	}
	@Override
	public void dispose() {

	}
	
}
