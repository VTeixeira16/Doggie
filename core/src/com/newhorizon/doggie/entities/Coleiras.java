package com.newhorizon.doggie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class Coleiras extends B2DSprite {

	public Coleiras (Body body) {
		
		super(body);
		
		Texture tex = GameClass.res.getTexture("coleiras");
		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
		
		setAnimation(sprites, 1 / 12f );
	}
}
