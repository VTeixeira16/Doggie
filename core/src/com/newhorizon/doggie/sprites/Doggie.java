package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.newhorizon.doggie.tools.B2dVariaveis;


public class Doggie extends Sprite{
	
	// Necessário pensar se vale a pena copiar modelo de sprite do projeto atual ou implementar animações.
	
	public World world;
	public BodyDef bDef;
	
	private int numColeiras;
	private int totalColeiras;
	private int numVidas;
	private int totalVidas;
	public boolean vivo;
	private int flagVivo;
	
	BodyDef DoggiebDef = new BodyDef();
	
	
	public Doggie(PlayScreen playScreen) {
		
		this.world = playScreen.getWorld();
		
		Texture tex = GameClass.res.getTexture("doggie");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 82, 60)[0]; // Recorte do SpriteSheet
		
		createDoggie();
		
		
	}
	
	public void update(float dt){
	
		
	}
	
private void createDoggie() {
		
		//Cria Plataforma
				

		FixtureDef fDef = new FixtureDef();
		
		PolygonShape shape = new PolygonShape();
		Gdx.app.log("log", "Pos Poligon");
		
		//Criando Doggie		
		DoggiebDef.position.set(50/PPM ,100/PPM);
		DoggiebDef.type = BodyType.DynamicBody;
//				bDef.linearVelocity.set(.5f,0); // Velocidade do Doggie
		Body body = world.createBody(DoggiebDef);
		Gdx.app.log("log", "DoggiebDefCriado");
		shape.setAsBox(13/PPM , 13/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_COLEIRAS | B2dVariaveis.BIT_INIMIGO1;
		// Faz quicar/
		fDef.restitution = 0.2f;
		body.createFixture(fDef).setUserData("doggie");
		Gdx.app.log("log", "fDef");

		//Criando sensor de pés
		shape.setAsBox(12/PPM, 6/PPM, new Vector2(0, -10/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_INIMIGO1;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("footDoggie");
		Gdx.app.log("log", "FootDoggie");

		// Cria Doggie
//				doggie = new Doggie(body);
//				this.setTotalVidas(3);
		body.setUserData(this);
		
	}

}
