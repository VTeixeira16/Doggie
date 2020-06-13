package com.newhorizon.doggie.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class OssosVeneno extends B2DSprites {

	public OssosVeneno (Body body) {
		
		super(body);
		
//		Texture tex = GameClass.res.getTexture("coleiras");
//		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
		
		Texture tex = GameClass.res.getTexture("ossoVeneno");
		TextureRegion[] sprites = TextureRegion.split(tex, 37, 24)[0];
		
		setAnimation(sprites, 1 / 16f );
	}
}
