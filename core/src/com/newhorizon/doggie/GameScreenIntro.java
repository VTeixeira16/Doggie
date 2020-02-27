package com.newhorizon.doggie;
import com.newhorizon.doggie.Doggie;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreenIntro implements Screen, InputProcessor{
	
	private AssetManager manager;
	private SpriteBatch batch;
	TiledMap tiledMapIntro;
	OrthographicCamera camera1;
	Viewport viewport;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	
//	private Sprite doggie;
//	private Texture cao[];
	
	private Fundo fundo;
	
	private float tempoProx;
	private int frameAtual;
	
	private Doggie doggie;
	
	

//	private Texture soldiers[] = new Texture [8];


	
	
	
	public GameScreenIntro(Game game, AssetManager manager) {
		this.manager = manager;
		
	}
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.app.log("log", "GameScreen show");
		
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		fundo = new Fundo();
		
		
		camera1 = new OrthographicCamera();
		camera1.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera1.position.set(camera1.viewportWidth/2, camera1.viewportHeight/2, 0);
		viewport = new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		camera1.zoom = 2.5f;
		camera1.update();

		// Tiled
		TiledMap tiledMapIntro = manager.get("Tiled/mapa1.tmx", TiledMap.class);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMapIntro);
		
		batch = new SpriteBatch();
//		cao = new Texture[3];
//		for(int i = 1; i<=3; i++) {
//			cao[i-1] = manager.get("cao" + i + ".png");
//		}
		
		
		tempoProx = 0.08f;
		frameAtual = 0;
		
		Gdx.app.log("log","Desenhando Doggie");
		doggie = new Doggie(0,0);
		
//		Texture doggieTx = manager.get("cao2.png", Texture.class);
//		doggie = new Sprite(cao[frameAtual]);
//		doggie.setSize(100, 100);
//		doggie.setCenter(0.5f,0.5f);
//		doggie.setPosition(128, 128);
		Gdx.input.setInputProcessor(this);
	}

	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
//		Gdx.app.log("log", "GameScreenIntro Render");
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		


		tempoProx -= Gdx.graphics.getDeltaTime();
		if(tempoProx <= 0) {
			tempoProx = 0.08f;
			frameAtual++;
			if(frameAtual ==3) frameAtual = 0;
		}
		
		batch.setProjectionMatrix(camera1.combined);
		batch.begin();
		
		Desenha();
		

		
//		doggie.draw(batch);
		
		

		batch.end();
		
		
		camera1.update();
		camera1.position.set(doggie.corpo.getX() ,doggie.corpo.getY (),0);
		tiledMapRenderer.setView(camera1);
		tiledMapRenderer.render();


		
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	doggie.corpo.setX(doggie.corpo.getX() - 6);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	doggie.corpo.setX(doggie.corpo.getX() + 6);
        }
        	
        	/*

        if(Gdx.input.isKeyPressed(Input.Keys.UP))

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))

        if(Gdx.input.isKeyPressed(Input.Keys.PAGE_UP))

    	if(Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN))

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1))

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2))
        */

        doggie.update(frameAtual);
		
	}
	private void Desenha()
	{
		
		
//		batch.draw(cao[frameAtual],256,128,100,60);
//		Gdx.app.log("log","Desenhando Doggie");
		fundo.draw(batch);
		
		doggie.draw(batch);
		
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		viewport.update(width, height);
		
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
