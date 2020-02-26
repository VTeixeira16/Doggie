package com.newhorizon.doggie;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.newhorizon.doggie.MainClass;

public class Doggie {
		

	private AssetManager manager = new AssetManager();	
	public Rectangle corpo;
	
	public Texture[] framesCao;
	
	private float auxFrames;	
		
	public Doggie (int posX, int posY)
	{

		
		corpo = new Rectangle(0,0, 10, 10);
		
		// Futuramente os frames do Cao devem ser migrados para o Manager
		framesCao = new Texture[3];
		for (int i =1; i<=3;i++)
		{

			framesCao[i-1] = new Texture("cao" + i + ".png");
		}
		
	}
	
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(framesCao[(int)auxFrames%3], corpo.x, corpo.y);
		Gdx.app.log("log","frameAtual " + auxFrames);
		
	}
	public void update(float time)
	{
		auxFrames += time / 30; // Controle da troca de frames. Quanto maior, mais lento.
	}
	public void dispose()
	{
		for(int i=0;i<3;i++)
		{
			framesCao[i].dispose();
		}
	}
	

	//Assets do Doggie são carregados na MainClass
	
	
}
