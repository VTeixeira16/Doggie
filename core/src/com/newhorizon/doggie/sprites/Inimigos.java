package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;
import com.newhorizon.doggie.tools.B2dVariaveis;

public class Inimigos extends Sprite{
	
	protected Body body;
	
	protected Animation animation;	
	protected float width;
	protected float height;
	
	// Propriedades das animações
	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;
	
	public boolean flip;
	public boolean emMovimento;
	private PlayScreen screen;
	private World world;
	
	public Inimigos(PlayScreen screen) {
		this.screen = screen;
		this.world = screen.getWorld();
		animation = new Animation();
		animationIdle = new Animation();
		
		Texture tex = GameClass.res.getTexture("dogIdle");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 32, 32)[0]; // Recorte do SpriteSheet
		
		criaInimigo();
//		setBounds(0,0, 16 / PPM, 16 / PPM);
		
		setAnimation(spritesDoggie, 1 / 12f);	// Velocidade da troca de frame;
		setAnimationIdle(spritesDoggie, 1 / 1f);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
		
	}
	
	public void setAnimationIdle(TextureRegion[] reg, float delay) {
		animationIdle.setFrames(reg, delay);
		widthIdle = reg[0].getRegionWidth();
		heightIdle = reg[0].getRegionHeight();
		
	}
	
	public void update (float dt) {
			
		animation.update(dt);
		
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeigth() / 2);
		animationIdle.update(dt);
		
		if(this.body.getLinearVelocity().x != 0)
			this.emMovimento = true;
		else
			this.emMovimento = false;
		
		if(this.body.getLinearVelocity().x < 0)
			this.flip = true;
		else if(this.body.getLinearVelocity().x > 0)
			this.flip = false;
		
	}
	
	public void render (SpriteBatch sb) {
		sb.begin();
		if(flip && emMovimento)
		{
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * PPM + width / 2,
				body.getPosition().y * PPM - height / 2, 
				-width,
				height);
		}
		else if (!flip && emMovimento)
		{
			sb.draw(
			animation.getFrame(),
			body.getPosition().x * PPM - width / 2,
			body.getPosition().y * PPM - height / 2
			);
		}
		else if(flip && !emMovimento)
		{
		sb.draw(
				animationIdle.getFrame(),
				body.getPosition().x * PPM + widthIdle / 2,
				body.getPosition().y * PPM - heightIdle / 2, 
				-widthIdle,
				heightIdle);		
		}
		else if (!flip && !emMovimento)
		{
			sb.draw(
			animationIdle.getFrame(),
			body.getPosition().x * PPM - widthIdle / 2,
			body.getPosition().y * PPM - heightIdle / 2
			);
		}
		sb.end();
	}
	
	public Body getBody() {return body;}
	public Vector2 getPosition() {return body.getPosition();}
	public float getWidth() { return width;}
	public float getHeigth() {return height;}
	
	private void criaInimigo() {
		
		BodyDef EnemybDef = new BodyDef();	
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
					
		
		//Criando Inimigo. necessário reposicionar sempre que um novo for criado	
		EnemybDef.position.set(500/PPM ,204/PPM);
		EnemybDef.type = BodyType.DynamicBody;
		
				
		body = world.createBody(EnemybDef);
		
		shape.setAsBox(13/PPM , 13/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = shape;
		fDef.density = 1000.0f;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO1;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_DOGGIE;
		// Faz quicar/
		fDef.restitution = 0.2f;
		body.createFixture(fDef).setUserData("inimigo1");

		//Criando sensor de pés
//		shape.setAsBox(10/PixelsPorMetro, 6/PixelsPorMetro, new Vector2(0, -10/PixelsPorMetro), 0);
//		fDef.shape = shape;
//		fDef.filter.categoryBits = b2dVariaveis.BIT_DOGGIE;
//		fDef.filter.maskBits = b2dVariaveis.BIT_PLATAFORMA;
//		fDef.isSensor = true;
//		body.createFixture(fDef).setUserData("footDoggie");
		

		// Cria inimigo
//		enemy1 = new Inimigos(body);
//		body.setUserData(this);
		
		
	}

}
