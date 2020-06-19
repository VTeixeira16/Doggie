package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;
import com.newhorizon.doggie.tools.B2dVariaveis;

public class InimigoCachorro extends Inimigos{
	
	Texture tex;

	public InimigoCachorro(PlayScreen screen, int x, int y, int skin) {
		super(screen, x, y);
		this.screen = screen;
		this.world = screen.getWorld();
		animation = new Animation();
		animationIdle = new Animation();
		
		switch(skin) {
		case 0:
			tex = GameClass.res.getTexture("inimigoDoggie");
			velocidade = new Vector2 (2,0);
			break;
		case 1 :
			tex = GameClass.res.getTexture("inimigoDoggie2");
			velocidade = new Vector2 (-3,0);
			break;
		}
		
		
		TextureRegion[] spritesInimigo = TextureRegion.split(tex, 79, 61)[0]; // Recorte do SpriteSheet
		
		criaInimigo(x,y);
//		setBounds(0,0, 16 / PPM, 16 / PPM);
		
		setAnimation(spritesInimigo, 1 / 12f);	// Velocidade da troca de frame;
		setAnimationIdle(spritesInimigo, 1 / 1f);
		

	}
	
	protected void criaInimigo(int x, int y)
	{
		BodyDef EnemybDef = new BodyDef();	
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
					
		
		//Criando Inimigo. necessário reposicionar sempre que um novo for criado	
		EnemybDef.position.set(x/PPM , y/PPM);
		EnemybDef.type = BodyType.DynamicBody;
		
		CircleShape cShape = new CircleShape();
		cShape.setRadius(23 / PPM);
		
		body = world.createBody(EnemybDef);
		body.setFixedRotation(true);
		
//		shape.setAsBox(28/PPM , 28/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = cShape;
		fDef.density = 1000.0f;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_DOGGIE;
		// Faz quicar/
		fDef.restitution = 0.2f;
		body.createFixture(fDef).setUserData(this);
		
		//Criando sensor de pés
		shape.setAsBox(26/PPM, 6/PPM, new Vector2(0, -22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO_PES;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_DOGGIE;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(this);
		
		//Criando sensor da cabeça
		shape.setAsBox(12/PPM, 6/PPM, new Vector2(0, 22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO_HEAD;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_DOGGIE | B2dVariaveis.BIT_DOGGIE_PES;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(this);

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
	
	public void update (float dt) {
		stateTime += dt;
		verificaEstado(dt);
		
		
		animation.update(dt);
		animationIdle.update(dt);
		
		body.setLinearVelocity(velocidade);
		
		if(body.getPosition().x < 30 / PPM)
		{
			revVelocidade(true,false);
			body.setTransform(35 / PPM, body.getPosition().y, 0);
		}
		
		if(body.getPosition().y > 180 /PPM)
			body.setTransform(body.getPosition().x, 180/PPM, 0);
		
//			body.applyLinearImpulse(new Vector2(-1f, 0), body.getWorldCenter(), true);

				
//		animation.update(dt);
//		
//		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeigth() / 2);
//		animationIdle.update(dt);
//		
//		if(this.body.getLinearVelocity().x != 0)
//			this.emMovimento = true;
//		else
//			this.emMovimento = false;
//		
//		if(this.body.getLinearVelocity().x < 0)
//			this.flip = true;
//		else if(this.body.getLinearVelocity().x > 0)
//			this.flip = false;
		
	}

	@Override
	public void hitOnHead(Doggie doggie) {
		// TODO Auto-generated method stub
//		System.out.println("Catapimbas");
		morreu();
//		this.world.destroyBody(this.body);
		
		
	}

	@Override
	public void hitByEnemy(Inimigos inimigos) {
		// TODO Auto-generated method stub
		
	}
	

}
