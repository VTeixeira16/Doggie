package com.newhorizon.doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen, InputProcessor{
	
	private AssetManager manager;
	
	private SpriteBatch batch;

//	private Texture soldiers[] = new Texture [8];


//	private Animation<Texture> animationSoldier;

	
	
	
	public GameScreen(Game game, AssetManager manager) {
		this.manager = manager;
		
	}
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.app.log("log", "GameScreen show");
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
//		for (int c = 1; c<=7;c++) {
//			soldiers[c -1] = manager.get("soldier" + c + ".png", Texture.class);
//			soldiers[5] = manager.get("soldier" + 5+ ".png", Texture.class);
//			Gdx.app.log("log", "Soldiers carregando");
			
			
//		}
	
		
		// Tiled
//		TiledMap tiledMap1 = manager.get("Tiled/Mapa10.tmx");


		

		
//		Gdx.input.setInputProcessor(this);
	}

	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.app.log("log", "GameScreen Render");
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))

        if(Gdx.input.isKeyPressed(Input.Keys.UP))

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))

        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP))

    	if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN))

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2))
        */

		
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
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	    return true;
	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
