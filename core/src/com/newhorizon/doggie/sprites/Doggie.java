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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;
import com.newhorizon.doggie.tools.B2dVariaveis;


public class Doggie extends Sprite{
	public enum Estado {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO, GAMEOVER}; // NECESSÁRIO CRIAR UM ESTADO DE GAME OVER (PÓS MORTE)
	public Estado estadoAtual;
	public Estado estadoAnterior;
	
	public World world;
	public Body body;
	
	
	private int numColeiras;
	private int totalColeiras;
	private int numVidas;
	private int totalVidas;
	public boolean vivo;

	private float stateTimer;
	private boolean doggieMorreu;
	
	private PlayScreen screen;
	
	public Animation animation;
	protected float width;
	protected float height;
	
	// Propriedades das animações
	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;

	private boolean flip;
	
	public float flagTimer;
	
	public Doggie(PlayScreen screen, int x, int y) {
		
		this.screen = screen;
		this.world = screen.getWorld();
		

		estadoAtual = Estado.PARADO;
		estadoAnterior = Estado.PARADO;
		stateTimer = 0;
		flip = false;
		
		defineDoggie(x, y);
//		setBounds(0,0, 16 / PPM, 16 / PPM);
		
		
		// Animações do Doggie
		animation = new Animation();
		animationIdle = new Animation();
		
		Texture tex = GameClass.res.getTexture("doggieAndando");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 80, 60)[0];
		
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
		stateTimer += dt;
		
		flagTimer += dt;
//		
//		if(flagTimer > 0.2)
//			flagTimer = 0;
	
//		Gdx.app.log("Doggie", "TIMER: " + stateTimer);

		
		verificaEstado(dt);

//		if(screen.cl.isPlayerOnGround())
//			Gdx.app.log("log", "Doggie no chão");
			
		// Mata Doggie caso o tempo acaba
//        if (screen.getHud().isTimeUp() && !isDead()) {
//            die();
//        }
		
//		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		
		animation.update(dt);
		animationIdle.update(dt);
		
		// Evita que Doggie saia da tela 
		if(this.getPosition().x < 30 / PPM)
			body.setTransform(30 / PPM, body.getPosition().y, 0);
		
		if(this.getPosition().y < 64/PPM )
		{
			body.setTransform(50 / PPM, 204/ PPM, 0);
			RecebeDano();
		}
		
		
		
		// A cada 100 coleiras, ganha 1 vida
		if(this.getNumColeiras() == 100)
		{
			this.numColeiras = 0;
			this.setTotalVidas(this.totalVidas + 1);
			
		}

		
		
	}
	
	public void RecebeDano() 
	{
		if(this.totalVidas <= 0)
		{
			this.totalVidas = 0;
			morreu();
			
		}
		if(this.totalVidas > 0)
		{
			// Joga pra posição "inicial" caso tenha vidas
//			body.setTransform(50 / PPM, 204/ PPM, 0);
			body.applyLinearImpulse(new Vector2(0f, 6f), body.getWorldCenter(), true);
		}
		
		if(stateTimer > 0.7f)
		{
		stateTimer = 0;
		setTotalVidas(this.totalVidas -1);
		}
		if (this.totalVidas < 0)
			this.totalVidas = 0;		
	}
	
	public void collectColeiras() {numColeiras++;}
	public int getNumColeiras() {return numColeiras;}
	public void setTotalColeiras(int i) {totalColeiras = i; }
	public int getTotalColeiras() {return totalColeiras;}
	

	public int getNumVidas() {return numVidas;}
	public void setTotalVidas(int i) {totalVidas = i;}
	public int getTotalVidas() {return totalVidas;}
	
	public void render (SpriteBatch sb) {
		sb.begin();

		// Ideal é aprimorar a máquina de estados do jogo.
		if(flip && estadoAtual == Estado.CORRENDO)
		{
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * PPM + width / 2,
				body.getPosition().y * PPM - height / 2, 
				-width,
				height);
		}	
		else if (!flip && estadoAtual == Estado.CORRENDO)
		{
			sb.draw(
			animation.getFrame(),
			body.getPosition().x * PPM - width / 2,
			body.getPosition().y * PPM - height / 2
			);
		}
		else if(flip && estadoAtual == Estado.PARADO)
		{
		sb.draw(
				animationIdle.getFrame(),
				body.getPosition().x * PPM + widthIdle / 2,
				body.getPosition().y * PPM - heightIdle / 2, 
				-widthIdle,
				heightIdle);		
		}
		else if (!flip && estadoAtual == Estado.PARADO)
		{
			sb.draw(
			animationIdle.getFrame(),
			body.getPosition().x * PPM - widthIdle / 2,
			body.getPosition().y * PPM - heightIdle / 2
			);
		}
		
		sb.end();
	}
	
	  public Estado verificaEstado(float dt){
//		  {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO};
		  estadoAnterior = estadoAtual;
		  estadoAtual = getState();
		  
//		  TextureRegion region;
		  
		//depending on the state, get corresponding animation keyFrame.
	        switch(estadoAtual){
	            case MORTO:
	                morreu();
	                break;
	            case CAINDO:
	            	caindo();
	            	break;
	            case PULANDO:
	                jump();
	                break;
	            case CORRENDO:
	            	estadoAtual = Estado.CORRENDO;
//	                correndo(); // Classe que será criada caso seja necessário
	                break;
//	            case PARADO:
//	                parado(); // Classe que será criada caso seja necessário	            	
//	            	break;
//	            case GAMEOVER:
//	            	SERÁ CRIADO FUNÇÃO QUE DESTRÓI O OBJETO
	            default:
	                estadoAtual = Estado.PARADO;
//	                Gdx.app.log("log", "NENHUMESTADODETECTADO");
	        }
		  
//	        stateTimer = estadoAtual == estadoAnterior ? stateTimer + dt : 0;
        
	        return estadoAtual;
	  }
	    public Estado getState(){
	    	
	        // Não há else pois se a velocidade for 0, o ideal é que ele permaneça no flip atual
	        if(body.getLinearVelocity().x < 0)
	        	flip = true;
	        else if (body.getLinearVelocity().x > 0)
	        	flip = false;
	        
//	        Gdx.app.log("log", "velocidade:" + body.getLinearVelocity().x);
	    	
	        if(doggieMorreu)
	            return Estado.MORTO;
	        else if((body.getLinearVelocity().y > 0 && estadoAtual == Estado.PULANDO)) // || (body.getLinearVelocity().y < 0 && estadoAnterior == Estado.PULANDO))
	            return Estado.PULANDO;
	        else if(body.getLinearVelocity().y < 0 ) // && estadoAtual == Estado.CAINDO)
	            return Estado.CAINDO;
	        // Se a velocidade for diferente de 0 significa que está em movimento
	        else if(body.getLinearVelocity().x != 0)
	            return Estado.CORRENDO;
	        // Cairá nessa função caso nenhuma condição anterior seja acionada. Vericar posssibilidade de criar ataques.
	        else
	            return Estado.PARADO;
	    }
	    
	    public void morreu()
	    {
	        if (!doggieMorreu) {

	        	// Para música do jogo e toca som de morte. 
	        	
	            doggieMorreu = true;
	            Filter filter = new Filter();
	            filter.maskBits = B2dVariaveis.BIT_NADA;
	            filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS;

//	            for (Fixture fixture : body.getFixtureList()) {
//	                fixture.setFilterData(filter);
//	            }
	            Gdx.app.log("log","Morreeeeeeu");
//	            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
	            
//	            COLOCAR UM TIMER PARA TROCAR PARA O ESTADO DE GAME OVER APÓS O FIM DA ANIMAÇÃO.
//	            IMPLEMENTAÇÃO DEVE ROLAR AQUI OU NA MÁQUINA DE ESTADOS.
	            
	        }
    	}

	    public float getStateTimer(){
	        return stateTimer;
	    }  
	    
	    public void jumpEnemy() {
	    	body.applyLinearImpulse(new Vector2(0, 10f), body.getWorldCenter(), true);
	    }
	    
	    public void jump(){
	    	
	    	// Comentado provisoriamente enquanto os Estados não são atualizados.
	        if ( estadoAtual != Estado.PULANDO ) {
	            body.applyLinearImpulse(new Vector2(0, 6f), body.getWorldCenter(), true);
	            estadoAtual = Estado.PULANDO;
	        }
	    }
	    public void caindo(){
	    	
	    	// Comentado provisoriamente enquanto os Estados não são atualizados.
	        if ( estadoAtual != Estado.CAINDO ) {
	            estadoAtual = Estado.CAINDO;
	        }
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
	  
	    private void defineDoggie(int x, int y) {
		
		//Cria Doggie
		BodyDef DoggiebDef = new BodyDef();				

		FixtureDef fDef = new FixtureDef();
		
		PolygonShape shape = new PolygonShape();
		
		CircleShape cShape = new CircleShape();
		cShape.setRadius(23 / PPM);
		
		//Criando Doggie		
		DoggiebDef.position.set(x/PPM ,y/PPM);
		DoggiebDef.type = BodyType.DynamicBody;
		
		body = world.createBody(DoggiebDef);

//		shape.setAsBox(28/PPM , 28/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = cShape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_COLEIRAS | B2dVariaveis.BIT_INIMIGO;
		// Faz quicar/
		fDef.restitution = 0f;
		body.createFixture(fDef).setUserData(this);

		//Criando sensor de pés
		shape.setAsBox(26/PPM, 6/PPM, new Vector2(0, -22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE_PES;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_INIMIGO | B2dVariaveis.BIT_INIMIGO_HEAD;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(this);
		
		//Criando sensor da cabeça
		shape.setAsBox(26/PPM, 6/PPM, new Vector2(0, 22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE_HEAD;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_INIMIGO;
		fDef.isSensor = true;
//		body.createFixture(fDef).setUserData("headDoggie");
		
		body.createFixture(fDef).setUserData(this);		
	}

	public Body getBody() {return body;}
	public Vector2 getPosition() {return body.getPosition();}
	
//	A princípio estas funções não são necessários para o funcionamento de animações
	public float getWidth() { return width;}
	public float getHeigth() {return height;}

	public void dispose()
	{
		this.dispose();
	}
}
