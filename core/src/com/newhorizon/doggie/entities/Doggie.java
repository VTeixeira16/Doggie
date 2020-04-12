package com.newhorizon.doggie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class Doggie extends B2DMultiSprites {
	
	private int numColeiras;
	private int totalColeiras;
	
	public Doggie(Body body) {
		
		super(body);
		
		Texture tex = GameClass.res.getTexture("doggie");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 32, 32)[0]; // Recorte do SpriteSheet
		
			
		
		setAnimation(spritesDoggie, 1 / 12f);	// Velocidade da troca de frame;
		setAnimationIdle(spritesDoggie, 1 / 1f);
	}
	
	public void collectColeiras() {numColeiras++;}
	public int getNumColeiras() {return numColeiras / 2;}
	public void setTotalColeiras(int i) {totalColeiras = i; }
	public int getTotalColeiras() {return totalColeiras;}
	
	
	

}
