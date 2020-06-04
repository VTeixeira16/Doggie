package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie.Estado;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;
import com.newhorizon.doggie.tools.B2dVariaveis;

public class Inimigos extends Sprite{
	public enum EstadoInimigos {PARADO, CORRENDO, ATACANDO, FERIDO, MORTO, GAMEOVER}; 
	public EstadoInimigos estadoAtual;
	public EstadoInimigos estadoAnterior;
	
	private boolean inimigoMorreu;
	protected Body body;
	
	protected Animation animation;	
	protected float width;
	protected float height;
	
	// Propriedades das animações
	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;
	
	private boolean flip;
	
	private PlayScreen screen;
	private World world;
	
	public Inimigos(PlayScreen screen) {
		this.screen = screen;
		this.world = screen.getWorld();
		animation = new Animation();
		animationIdle = new Animation();
		
		Texture tex = GameClass.res.getTexture("dogIdle");
		TextureRegion[] spritesInimigo = TextureRegion.split(tex, 82, 60)[0]; // Recorte do SpriteSheet
		
		criaInimigo();
//		setBounds(0,0, 16 / PPM, 16 / PPM);
		
		setAnimation(spritesInimigo, 1 / 12f);	// Velocidade da troca de frame;
		setAnimationIdle(spritesInimigo, 1 / 1f);
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
		
		verificaEstado(dt);
		
		animation.update(dt);
		animationIdle.update(dt);
				
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
	
	public void render (SpriteBatch sb) {
		sb.begin();

		// Ideal é aprimorar a máquina de estados do jogo.
		if(flip && estadoAtual == EstadoInimigos.CORRENDO)
		{
		sb.draw(
				animation.getFrame(),
				body.getPosition().x * PPM + width / 2,
				body.getPosition().y * PPM - height / 2, 
				-width,
				height);
		}	
		else if (!flip && estadoAtual == EstadoInimigos.CORRENDO)
		{
			sb.draw(
			animation.getFrame(),
			body.getPosition().x * PPM - width / 2,
			body.getPosition().y * PPM - height / 2
			);
		}
		else if(flip && estadoAtual == EstadoInimigos.PARADO)
		{
		sb.draw(
				animationIdle.getFrame(),
				body.getPosition().x * PPM + widthIdle / 2,
				body.getPosition().y * PPM - heightIdle / 2, 
				-widthIdle,
				heightIdle);		
		}
		else if (!flip && estadoAtual == EstadoInimigos.PARADO)
		{
			sb.draw(
			animationIdle.getFrame(),
			body.getPosition().x * PPM - widthIdle / 2,
			body.getPosition().y * PPM - heightIdle / 2
			);
		}
		
		sb.end();
	}
	
  public EstadoInimigos verificaEstado(float dt){
//		  {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO};
	  estadoAnterior = estadoAtual;
	  estadoAtual = getState();
	  
//		  TextureRegion region;
	  
	//depending on the state, get corresponding animation keyFrame.
        switch(estadoAtual){
            case MORTO:
                morreu();
                break;
            case CORRENDO:
            	estadoAtual = EstadoInimigos.CORRENDO;
//	                correndo(); // Classe que será criada caso seja necessário
                break;
//	            case PARADO:
//	                parado(); // Classe que será criada caso seja necessário	            	
//	            	break;
//	            case GAMEOVER:
//	            	SERÁ CRIADO FUNÇÃO QUE DESTRÓI O OBJETO
            default:
                estadoAtual = EstadoInimigos.PARADO;
//	                Gdx.app.log("log", "NENHUMESTADODETECTADO");
        }
	     
        return estadoAtual;
  }
    public EstadoInimigos getState(){
    	
        // Não há else pois se a velocidade for 0, o ideal é que ele permaneça no flip atual
        if(body.getLinearVelocity().x < 0)
        	flip = true;
        else if (body.getLinearVelocity().x > 0)
        	flip = false;
        
//	        Gdx.app.log("log", "velocidade:" + body.getLinearVelocity().x);
    	
        if(inimigoMorreu)
            return EstadoInimigos.MORTO;
        // Se a velocidade for diferente de 0 significa que está em movimento
        else if(body.getLinearVelocity().x != 0)
            return EstadoInimigos.CORRENDO;
        // Cairá nessa função caso nenhuma condição anterior seja acionada. Vericar posssibilidade de criar ataques.
        else
            return EstadoInimigos.PARADO;
    }    
    
    public void morreu()
    {
        if (!inimigoMorreu) {

        	// Para música do jogo e toca som de morte. 
        	
        	inimigoMorreu = true;
            Filter filter = new Filter();
            filter.maskBits = B2dVariaveis.BIT_NADA;

//            for (Fixture fixture : body.getFixtureList()) {
//                fixture.setFilterData(filter);
//            }
            Gdx.app.log("log","Morreeeeeeu");
//            body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
            
//            COLOCAR UM TIMER PARA TROCAR PARA O ESTADO DE GAME OVER APÓS O FIM DA ANIMAÇÃO.
//            IMPLEMENTAÇÃO DEVE ROLAR AQUI OU NA MÁQUINA DE ESTADOS.
            
        }
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
		EnemybDef.position.set(400/PPM ,204/PPM);
		EnemybDef.type = BodyType.DynamicBody;
				
		CircleShape cShape = new CircleShape();
		cShape.setRadius(23 / PPM);
		
		body = world.createBody(EnemybDef);
		
//		shape.setAsBox(28/PPM , 28/PPM); // Controla tamanho da caixa de colusão.
		fDef.shape = cShape;
		fDef.density = 1000.0f;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO1;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_DOGGIE;
		// Faz quicar/
		fDef.restitution = 0.2f;
		body.createFixture(fDef).setUserData("inimigo1");
		
		//Criando sensor de pés
		shape.setAsBox(26/PPM, 6/PPM, new Vector2(0, -22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO1_PES;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_DOGGIE;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("footInimigo");
		
		//Criando sensor da cabeça
		shape.setAsBox(26/PPM, 6/PPM, new Vector2(0, 22/PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_INIMIGO1_HEAD;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_DOGGIE | B2dVariaveis.BIT_DOGGIE_PES;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData("headInimigo");

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
