package com.newhorizon.doggie;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fundo {

	private Texture texture;
	
	public Fundo()
	{
		texture = new Texture("Tiled/tileset/BG/BG.png");
		
		
	}
	
	public void draw(SpriteBatch batch)
	{
		// CRIAR CLASSE DE CONSTANTES
		
		batch.draw(texture, 0, 0, 1000,750);
		batch.draw(texture, 1000, 0, 1000,750);
		batch.draw(texture, 2000, 0, 1000,750);
		batch.draw(texture, 3000, 0, 1000,750);
	}
	
	public void update()
	{
		 // AQUI PODERÁ SER IMPLEMENTADO A MOVIMENTAÇÃO DO MAPA

	}
	
	public void dispose()
	{
		texture.dispose();
	}
}
