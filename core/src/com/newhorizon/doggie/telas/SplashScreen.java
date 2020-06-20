package com.newhorizon.doggie.telas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;

public class SplashScreen extends ApplicationAdapter implements Screen{
	
	public GameClass game;
	public Screen screen;
	
    private float splashTimer;
    private float introAlphaTimer;
    private int alphaTimer;
    private int logoTimer;
    
    private SpriteBatch sb;
    
    private Texture fatecTex;
    private Texture libTex;
    private Texture doggieTex;
    private Texture newHorizonTex;
    
    
    private Sprite fatecLogo;
    private Sprite libLogo;
    private Sprite doggieLogo;
    private Sprite newHorizonLogo;
    
    
	
	
	public SplashScreen (GameClass game) {

		this.game = game;
		
		fatecTex = GameClass.res.getTexture("fatecLogo");
		libTex = GameClass.res.getTexture("libLogo");
		doggieTex = GameClass.res.getTexture("doggieLogo");
		newHorizonTex = GameClass.res.getTexture("nhLogo");
		
		fatecLogo = new Sprite(fatecTex);
		libLogo = new Sprite(libTex);
		doggieLogo = new Sprite(doggieTex);
		newHorizonLogo = new Sprite(newHorizonTex);
		
		
		if(game.Language == "Portugues")
		{
		}
		else if(game.Language == "Ingles")
		{

		}
		
		sb = new SpriteBatch();
		
		
		
		fatecLogo.setPosition(Gdx.graphics.getWidth() / 10 * 8 , 30);
		libLogo.setPosition(30 , 30);
		doggieLogo.setPosition(Gdx.graphics.getWidth() /10 * 1 , Gdx.graphics.getHeight() / 2);
		
		newHorizonLogo.setScale(0.5f);
		newHorizonLogo.setPosition(- Gdx.graphics.getWidth() / 2.75f, - Gdx.graphics.getHeight() / 7);
	}

	@Override
	public void show() {
			      
	}

	@Override
	public void render(float delta) {
		if(splashTimer > 0.1f)
			game.telaAtual = "Splash";
		
		
		splashTimer += delta;	
		
		// Escurecimento da tela
		introAlphaTimer += delta * 2 ;
	
//		if(splashTimer >3f)	
//			logoTimer += delta * 150;
//		if(splashTimer >= 6.5f)
//		{
//			logoTimer -= delta * 300; 
//		}
		if(introAlphaTimer > 1)
			introAlphaTimer = 1;
		
		if(splashTimer >0.5f)	
		{
			logoTimer += delta * 300;	
		}
		if(splashTimer >2.5f)	
		{
			logoTimer -= delta * 500; 
		}
		if(splashTimer >3.5f)	
		{
			alphaTimer += delta * 150;	
		}		
		if(splashTimer >= 7.5f)
		{
			alphaTimer -= delta * 300; 
		}
		
		if(splashTimer > 9.5f)
			game.setScreen(new MenuScreen(game));
		
		if(alphaTimer > 254)
			alphaTimer = 254;
		else if(alphaTimer < 0)
			alphaTimer = 0;
		
		if(logoTimer > 254)
			logoTimer = 254;
		else if(logoTimer < 0)
			logoTimer = 0;
		
		Gdx.gl.glClearColor(introAlphaTimer, introAlphaTimer, introAlphaTimer, 1);
		//Adicionar efeito de tela preta ficando branca
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        sb.begin();
        
        if(splashTimer < 4)
        {
        fatecLogo.draw(sb, 255 - alphaTimer);
        libLogo.draw(sb, 255 - alphaTimer);
        doggieLogo.draw(sb, 255 - alphaTimer);
        newHorizonLogo.draw(sb, 255 - logoTimer);
        }
        else if (splashTimer > 4 && splashTimer < 10)
        {
            fatecLogo.draw(sb, 255 - alphaTimer);
            libLogo.draw(sb, 255 - alphaTimer);
            doggieLogo.draw(sb, 255 - alphaTimer);
            newHorizonLogo.draw(sb, 255 - logoTimer);
        }
        
        if(game.debug)
        	GameClass.fontMenu.draw(sb, "AlphaTimer:" + alphaTimer, 0 , Gdx.graphics.getHeight() - 145); 	

        sb.end();
        
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
		
	}

	@Override
	public void dispose() {
		sb.dispose();
		
	}

}
