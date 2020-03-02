package com.newhorizon.doggie;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MainClass extends Game implements ApplicationListener {
	private AssetManager manager;
	TmxMapLoader loader = new TmxMapLoader();

//	private SplashScreen splashScreen = new SplashScreen();
//	private int number = SplashScreen.Numero;
	public int faseAtual;
	
	

	@Override
	public void create() {
		
		faseAtual = 0;
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, loader);
		manager.load("Tiled/mapa1.tmx", TiledMap.class);
//		manager.load("Tiled/mapaIntro.tmx", TiledMap.class);
		
		
//		for(int c = 1; c<=3; c++) {
//			manager.load("cao" + c + ".png", Texture.class);
//			Gdx.app.log("log", "Carregando Cao" + c);
//
//		}
		manager.finishLoading();
		setScreen(new SplashScreen(this, manager));
	}
	@Override
	public void resize(int width, int height) {
		
	}
	@Override
	public void render() {
		 super.render();
//		 Gdx.app.log("log", "MainClass");
		
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
	public void dispose() {
		// TODO Auto-generated method stub
		manager.dispose();	
	}
	
}
