package com.newhorizon.doggie.entities;

import static com.newhorizon.doggie.handlers.b2dVariaveis.PixelsPorMetro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class Doggie extends B2DMultiSprites {
	
	private int numColeiras;
	private int totalColeiras;
	private int numVidas;
	private int totalVidas;
	public boolean vivo;
	private int flagVivo;
	
	
	public Doggie(Body body) {
		
		super(body);
		
		Texture tex = GameClass.res.getTexture("doggie");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 32, 32)[0]; // Recorte do SpriteSheet
		
			
		
		setAnimation(spritesDoggie, 1 / 12f);	// Velocidade da troca de frame;
		setAnimationIdle(spritesDoggie, 1 / 1f);
	}
	public void update(float dt)
	{
//		System.out.print("update Doggie \n");
		
		animation.update(dt);
		animationIdle.update(dt);
		
		if(this.getPosition().y < (0))
		{
			RecebeDano();
		}
	}
	
	public void RecebeDano() 
	{

		if(this.totalVidas > 0)
			body.setTransform(100 /PixelsPorMetro, 200 /PixelsPorMetro, 0);
		
		setTotalVidas(this.totalVidas -1);

		if(this.totalVidas < 0)
			this.totalVidas = 0;
		
	}
	
	public void collectColeiras() {numColeiras++;}
	public int getNumColeiras() {return numColeiras / 2;}
	public void setTotalColeiras(int i) {totalColeiras = i; }
	public int getTotalColeiras() {return totalColeiras;}
	

	public int getNumVidas() {return numVidas;}
	public void setTotalVidas(int i) {totalVidas = i;}
	public int getTotalVidas() {return totalVidas;}
	
	

}
