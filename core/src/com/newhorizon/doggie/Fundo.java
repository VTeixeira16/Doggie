package com.newhorizon.doggie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fundo {

	private Texture texture;
	
	private float posX1;
	private float posX2;
	
	public Fundo()
	{
		texture = new Texture("Tiled/tileset/BG/BG.png");
		
		posX1 = 0;
		posX2 = Gdx.graphics.getWidth();
		
	}
	
	public void draw(SpriteBatch batch)
	{
		// CRIAR CLASSE DE CONSTANTES
		
		batch.draw(texture, posX1, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(texture, posX2, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}
	
	public void update(float time)
	{
		 // AQUI OCORRERÁ A MOVIMENTAÇÃO DO MAPA
		if(posX1 + Gdx.graphics.getWidth() <=0) {
			posX1 = Gdx.graphics.getWidth();
			posX2 = 0;			
		}
		if(posX2 + Gdx.graphics.getWidth() <=0) {
			posX2 = Gdx.graphics.getWidth();
			posX1 = 0;			
		}		
	}
	
	public void dispose()
	{
		texture.dispose();
	}
}
