package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;
import com.newhorizon.doggie.tools.B2dVariaveis;


public class Doggie extends Sprite{
	public enum Estado {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO};
	public Estado estadoAtual;
	public Estado estadoAnterior;
	
	public World world;
	public Body body;
	
	private int numColeiras;
	private int totalColeiras;
	private int numVidas;
	private int totalVidas;
	public boolean vivo;
	private int flagVivo;
	
	
	// Estrutrura será alterada completamente conforme for implementado os estados de forma correta
	private TextureRegion doggieIdleTex;
	private TextureRegion doggieRunTex;
	private TextureRegion doggieMorreuTex;
	private TextureRegion doggiePulandoTex;
//	private Animation doggieIdleAnim;
//	private Animation doggieRunAnim;
//	private Animation doggieMorreuAnim;
//	private Animation doggiePulandoAnim;
	
	private float stateTimer;
	private boolean correndoDireita;
	private boolean doggieMorreu;
	
	private PlayScreen screen;
	
	protected Animation animation;
	protected float width;
	protected float height;
	
	// Propriedades das animações
	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;
	
	
	// Verificar se vale a pena manter este modelo anterior de flip e emMovimento
	public boolean flip;
	public boolean emMovimento;
	
	
	public Doggie(PlayScreen screen) {
		
		this.screen = screen;
		this.world = screen.getWorld();

		estadoAtual = Estado.PARADO;
		estadoAnterior = Estado.PARADO;
		stateTimer = 0;
		correndoDireita = true;
		
		defineDoggie();
//		setBounds(0,0, 16 / PPM, 16 / PPM);
		
		animation = new Animation();
		animationIdle = new Animation();
		
		Texture tex = GameClass.res.getTexture("doggie");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 82, 60)[0];
		
		totalVidas = 3;
		
		setAnimation(spritesDoggie, 1 / 16f);	
		setAnimationIdle(spritesDoggie, 1 / 16f);	
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
	
	public void update(float dt){

//		if(screen.cl.isPlayerOnGround())
//			Gdx.app.log("log", "Doggie no chão");
			
		// Mata Doggie caso o tempo acaba
//        if (screen.getHud().isTimeUp() && !isDead()) {
//            die();
//        }
		
//		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		
// DESCOMENTAR ESSA PARTE ou usar estrutura antiga do projeto.
//		setRegion(getFrame(dt));
		
		animation.update(dt);
		animationIdle.update(dt);
		
		if(this.getPosition().x < 0)
			body.setTransform(0, body.getPosition().y, 0);
		
		if(this.getPosition().y < 0)
		{
			RecebeDano();
		}
		
		if(this.body.getLinearVelocity().x != 0)
			this.emMovimento = true;
		else
			this.emMovimento = false;
		
	}
	
	public void RecebeDano() 
	{

		if(this.totalVidas > 0)
			body.setTransform(50 / PPM, 100 / PPM, 0);
		
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
	
	public void render (SpriteBatch sb) {
		sb.begin();

		// Ideal é aprimorar a máquina de estados do jogo.
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
	
	  public TextureRegion getFrame(float dt){
//		  {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO};
		  estadoAtual = getState();
		  
		  TextureRegion region;
		  
		//depending on the state, get corresponding animation keyFrame.
	        switch(estadoAtual){
	            case MORTO:
	                region = doggieMorreuTex;
	                break;
	            case PULANDO:
	                region = doggiePulandoTex;
	                break;
	            case CORRENDO:
	                region = doggieRunTex;
	                break;
	            case CAINDO:
	            case PARADO:
	            default:
	                region = doggieIdleTex;
	        }
	        Gdx.app.log("log", "getframeDoggie");
	        Gdx.app.log("log", "getframeDoggie");
	        //if mario is running left and the texture isnt facing left... flip it.
	        if((body.getLinearVelocity().x < 0 || !correndoDireita) && !region.isFlipX()){
	            region.flip(true, false);
	            correndoDireita = false;
	        }

	        //if mario is running right and the texture isnt facing right... flip it.
	        else if((body.getLinearVelocity().x > 0 || correndoDireita) && region.isFlipX()){
	            region.flip(true, false);
	            correndoDireita = true;
	        }
		  
	        stateTimer = estadoAtual == estadoAnterior ? stateTimer + dt : 0;
	        
	        estadoAnterior = estadoAtual;
	        
	        return region;
	  }
	    public Estado getState(){
	        //Test to Box2D for velocity on the X and Y-Axis
	        //if mario is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
	        if(doggieMorreu)
	            return Estado.MORTO;
	        else if((body.getLinearVelocity().y > 0 && estadoAtual == Estado.PULANDO) || (body.getLinearVelocity().y < 0 && estadoAnterior == Estado.PULANDO))
	            return Estado.PULANDO;
	        //if negative in Y-Axis mario is falling
	        else if(body.getLinearVelocity().y < 0)
	            return Estado.CAINDO;
	        //if mario is positive or negative in the X axis he is running
	        else if(body.getLinearVelocity().x != 0)
	            return Estado.CORRENDO;
	        //if none of these return then he must be standing
	        else
	            return Estado.PARADO;
	    }
	    
	    public void morreu()
	    {
	        if (!doggieMorto()) {

	        	// Para música do jogo e toca som de morte. 
	        	
	            doggieMorreu = true;
	            Filter filter = new Filter();
	            filter.maskBits = B2dVariaveis.BIT_NADA;

	            for (Fixture fixture : body.getFixtureList()) {
	                fixture.setFilterData(filter);
	            }

	            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
	        }
    	}
	    
	    public boolean doggieMorto(){
	        return doggieMorreu;
	    }

	    public float getStateTimer(){
	        return stateTimer;
	    }  
	    
	    public void jump(){
	    	
	    	// Comentado provisoriamente enquanto os Estados não são atualizados.
//	        if ( estadoAtual != Estado.PULANDO ) {
	            body.applyLinearImpulse(new Vector2(0, 5f), body.getWorldCenter(), true);
	            estadoAtual = Estado.PULANDO;
//	        }
	    }
	    
//	    public void hit(Enemy enemy){
//	        if(enemy instanceof Turtle && ((Turtle) enemy).currentState == Turtle.State.STANDING_SHELL)
//	            ((Turtle) enemy).kick(enemy.b2body.getPosition().x > b2body.getPosition().x ? Turtle.KICK_RIGHT : Turtle.KICK_LEFT);
//	        else {
//	            if (marioIsBig) {
//	                marioIsBig = false;
//	                timeToRedefineMario = true;
//	                setBounds(getX(), getY(), getWidth(), getHeight() / 2);
//	                MarioBros.manager.get("audio/sounds/powerdown.wav", Sound.class).play();
//	            } else {
//	                die();
//	            }
//	        }
//	    }
	  
private void defineDoggie() {
		
		//Cria Doggie
		BodyDef DoggiebDef = new BodyDef();				

		FixtureDef fDef = new FixtureDef();
		
		PolygonShape shape = new PolygonShape();
		
		//Criando Doggie		
		DoggiebDef.position.set(50/PPM ,100/PPM);
		DoggiebDef.type = BodyType.DynamicBody;
//				bDef.linearVelocity.set(.5f,0); // Velocidade do Doggie
		body = world.createBody(DoggiebDef);
//		Gdx.app.log("log", "DoggiebDefCriado");
		shape.setAsBox(13/PPM , 13/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_COLEIRAS | B2dVariaveis.BIT_INIMIGO1;
		// Faz quicar/
		fDef.restitution = 0.2f;
		body.createFixture(fDef).setUserData("doggie");
//		Gdx.app.log("log", "fDef");

		//Criando sensor de pés
		shape.setAsBox(12/PPM, 6/PPM, new Vector2(0, -10/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_INIMIGO1;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("footDoggie");
		
		// IMPLEMENTAR FUTURAMENTE O DOGGIEHEAD

		// Cria Doggie
//				doggie = new Doggie(body);
//				this.setTotalVidas(3);
		body.createFixture(fDef).setUserData(this);
		
	}

	public Body getBody() {return body;}
	public Vector2 getPosition() {return body.getPosition();}
	
//	A princípio estas funções não são necessários para o funcionamento de animações
	public float getWidth() { return width;}
	public float getHeigth() {return height;}

}
