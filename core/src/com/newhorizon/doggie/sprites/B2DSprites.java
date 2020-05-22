package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;

public class B2DSprites {
	// Classe será descontinuada, o ideal é migrar para cada inimigos e para objetos de acordo com as suas diferenças.
	// Devido a dificuldades de implmentação, esta classe está sendo utilizada pelas coleiras.
	protected Body body;
	protected Animation animation;
	protected float width;
	protected float height;
	public boolean flip;
	public boolean emMovimento;
	
	public B2DSprites(Body body) {
		this.body = body;
		animation = new Animation();
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
		
	}
	
	public void update (float dt) {
		
		animation.update(dt);
	}
	
	public void render (SpriteBatch sb) {
		sb.begin();
		
		if(flip)
		{
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * PPM + width / 2,
				body.getPosition().y * PPM - height / 2, 
				-width,
				height);
		}
		else
		{
			sb.draw(
			animation.getFrame(),
			body.getPosition().x * PPM - width / 2,
			body.getPosition().y * PPM - height / 2
			);
		}
		
		sb.end();
	}
	
	public Body getBody() {return body;}
	public Vector2 getPosition() {return body.getPosition();}
	public float getWidth() { return width;}
	public float getHeigth() {return height;}

}
