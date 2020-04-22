package com.newhorizon.doggie.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.handlers.Animation;
import com.newhorizon.doggie.handlers.b2dVariaveis;

public class B2DMultiSprites extends B2DSprite{

	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;

	
	public B2DMultiSprites(Body body) {
		super(body);
		
		this.body = body;
		animationIdle = new Animation();

	}
	
	public void setAnimationIdle(TextureRegion[] reg, float delay) {
		animationIdle.setFrames(reg, delay);
		widthIdle = reg[0].getRegionWidth();
		heightIdle = reg[0].getRegionHeight();
		
	}
	
	public void update (float dt) {
		//A Classe Doggie possui essa implementação própria. Avaliar no futuro a possibilidade de exclusão dessa função.
		animation.update(dt);
		animationIdle.update(dt);
		
		if(this.body.getLinearVelocity().x != 0)
			this.emMovimento = true;
		else
			this.emMovimento = false;
	}
	
	public void render (SpriteBatch sb) {
		sb.begin();
		
		if(flip && emMovimento)
		{
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * b2dVariaveis.PixelsPorMetro + width / 2,
				body.getPosition().y * b2dVariaveis.PixelsPorMetro - height / 2, 
				-width,
				height);
		}
		else if (!flip && emMovimento)
		{
			sb.draw(
			animation.getFrame(),
			body.getPosition().x * b2dVariaveis.PixelsPorMetro - width / 2,
			body.getPosition().y * b2dVariaveis.PixelsPorMetro - height / 2
			);
		}
		else if(flip && !emMovimento)
		{
		sb.draw(
				animationIdle.getFrame(),
				body.getPosition().x * b2dVariaveis.PixelsPorMetro + widthIdle / 2,
				body.getPosition().y * b2dVariaveis.PixelsPorMetro - heightIdle / 2, 
				-widthIdle,
				heightIdle);		
		}
		else if (!flip && !emMovimento)
		{
			sb.draw(
			animationIdle.getFrame(),
			body.getPosition().x * b2dVariaveis.PixelsPorMetro - widthIdle / 2,
			body.getPosition().y * b2dVariaveis.PixelsPorMetro - heightIdle / 2
			);
		}
		
		sb.end();
	}
	

}
