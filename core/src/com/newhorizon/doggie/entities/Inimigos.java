package com.newhorizon.doggie.entities;

import static com.newhorizon.doggie.handlers.b2dVariaveis.PixelsPorMetro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class Inimigos extends B2DMultiSprites{

	public Inimigos(Body body) {
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
		
	}
	
	
}
