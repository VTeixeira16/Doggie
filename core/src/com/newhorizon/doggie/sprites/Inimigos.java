package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.telas.PlayScreen;
import com.newhorizon.doggie.tools.Animation;

public abstract class Inimigos extends Sprite{
	public enum EstadoInimigos {PARADO, CORRENDO, ATACANDO, FERIDO, MORTO, GAMEOVER}; 
	public EstadoInimigos estadoAtual;
	public EstadoInimigos estadoAnterior;
	
	protected float stateTime;
	
	public Sound som;
	
	protected boolean inimigoMorreu;
	protected Body body;
	
	protected Animation animation;	
	protected float width;
	protected float height;
	
	// Propriedades das anima��es
	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;
	
	private boolean flip;
	
	protected PlayScreen screen;
	protected World world;
	
	protected Vector2 velocidade;
	
	public Inimigos(PlayScreen screen, int x, int y) {
		
		stateTime = 0;
		velocidade = new Vector2 (2,0);
		
		som = GameClass.manager.get("sons/latido/rosnadoinimigo.mp3", Sound.class);
	}
	
	
    public abstract void hitOnHead(Doggie doggie);
    
    public void revVelocidade(boolean x, boolean y) {
    	if(x)
    		velocidade.x = - velocidade.x;
		if(y)
    		velocidade.y = - velocidade.y;
    }
    
    public void somRosnar()
    {
    	som.play();
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
		stateTime += dt;
		verificaEstado(dt);
		
		
		animation.update(dt);
		animationIdle.update(dt);
		
		if(body.getPosition().x < 30 / PPM)
		{
			revVelocidade(true,false);
			body.setTransform(31 / PPM, body.getPosition().y, 0);
		}		
	}
	
	public void render (SpriteBatch sb) {
		sb.begin();

		// Ideal � aprimorar a m�quina de estados do jogo.
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
        switch(estadoAtual){
        
            case MORTO:          	  
                morreu();
                break;
            case CORRENDO:
            	estadoAtual = EstadoInimigos.CORRENDO;
//	                correndo(); // Classe que ser� criada caso seja necess�rio
                break;
//	            case PARADO:
//	                parado(); // Classe que ser� criada caso seja necess�rio	            	
//	            	break;
//	            case GAMEOVER:
//	            	SER� CRIADO FUN��O QUE DESTR�I O OBJETO
            default:
                estadoAtual = EstadoInimigos.PARADO;
//	                Gdx.app.log("log", "NENHUMESTADODETECTADO");
        }
	     
        return estadoAtual;
  }
    public EstadoInimigos getState(){
    	
        // N�o h� else pois se a velocidade for 0, o ideal � que ele permane�a no flip atual
        if(body.getLinearVelocity().x < 0)
        	flip = true;
        else if (body.getLinearVelocity().x > 0)
        	flip = false;
        
        if(inimigoMorreu)
            return EstadoInimigos.MORTO;
        // Se a velocidade for diferente de 0 significa que est� em movimento
        else if(body.getLinearVelocity().x != 0)
            return EstadoInimigos.CORRENDO;
        // Cair� nessa fun��o caso nenhuma condi��o anterior seja acionada. Vericar posssibilidade de criar ataques.
        else
            return EstadoInimigos.PARADO;
    }    
    
    public void morreu()
    {
    	
        if (!inimigoMorreu) {
        	inimigoMorreu = true;
            Gdx.app.postRunnable(new Runnable(){
                public void run(){
                    world.destroyBody(body);
                }
            });           
        }
	}
	
	public Body getBody() {return body;}
	public Vector2 getPosition() {return body.getPosition();}
	public float getWidth() { return width;}
	public float getHeigth() {return height;}
	
	protected abstract void criaInimigo(int x, int y);
	
	public void dispose() {
		this.dispose();
	}
}
