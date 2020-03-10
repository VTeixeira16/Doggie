package com.newhorizon.doggie;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.handlers.GameStateManager;

public class GameClass implements ApplicationListener {

	public static final String GameName = "Doggie";
	public static final int V_WIDTH = 720;
	public static final int V_HEIGHT = 480;
	
	public static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch sb;
	private OrthographicCamera camera1;
	private OrthographicCamera camera2;
	
	private GameStateManager gsm;
	
	public void create() {
		
		sb = new SpriteBatch();
		camera1 = new OrthographicCamera();
		camera1.setToOrtho(false, V_WIDTH, V_HEIGHT);
		camera2 = new OrthographicCamera();
		camera2.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
	
		gsm = new GameStateManager(this);
		
	}
	@Override
	public void resize(int width, int height) {
		
	}
	@Override
	public void render() {
		 Gdx.app.log("log", "GameClass");
		 accum += Gdx.graphics.getDeltaTime();
		 while(accum >= STEP)
		 {
			 Gdx.app.log("log", "STEP = " + STEP);
			 Gdx.app.log("log", "accum = " + accum);
			 accum -= STEP;
			 gsm.update(STEP);
			 gsm.render();
		 }
		 
		
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
	
	public SpriteBatch getSpriteBatch() { return sb;}
	public OrthographicCamera getCamera1() {return camera1;}
	public OrthographicCamera getCamera2() {return camera2;}
	
}
