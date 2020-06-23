package com.newhorizon.doggie.sprites;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.audio.Sound;
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

public class Doggie extends Sprite {
	public enum Estado {
		CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO, GAMEOVER
	};

	public Estado estadoAtual;
	public Estado estadoAnterior;

	public boolean terminouFase;
	
	public World world;
	public Body body;

	private int numOssos;
	private int totalOssos;
	private int numVidas;
	private int totalVidas;
	public boolean vivo;
	public boolean envenenado;

	private float stateTimer;
	private boolean doggieMorreu;

	private PlayScreen screen;

	// Propriedades das animacões
	public Animation animationRun;
	protected float width;
	protected float height;

	protected Animation animationIdle;
	protected float widthIdle;
	protected float heightIdle;

	private boolean flip;

	public float flagTimer;
	
	public Sound som;
	public Sound somOsso;
	public Sound somVeneno;

	public Doggie(PlayScreen screen, int x, int y) {
		
		terminouFase = false;

		this.screen = screen;
		this.world = screen.getWorld();

		estadoAtual = Estado.PARADO;
		estadoAnterior = Estado.PARADO;
		stateTimer = 0;
		flip = false;

		defineDoggie(x, y);

		// Animacoes do Doggie
		animationRun = new Animation();
		animationIdle = new Animation();

		Texture tex = GameClass.res.getTexture("doggieAndando");
		TextureRegion[] spritesDoggie = TextureRegion.split(tex, 80, 60)[0];

		totalVidas = 10;
		
		if(screen.debug == false)
			totalVidas = 5;
		
		envenenado = false;
		
		som = GameClass.manager.get("sons/latido/latidodoggie.mp3", Sound.class);
		somOsso = GameClass.manager.get("sons/bones/up1.mp3", Sound.class);
		somVeneno = GameClass.manager.get("sons/bones/veneno1.mp3", Sound.class);

		setAnimationIdle(spritesDoggie, 1 / 16f);
		setAnimationRun(spritesDoggie, 1 / 16f);
	}

	public void setAnimationRun(TextureRegion[] reg, float delay) {
		animationRun.setFrames(reg, delay);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();

	}

	public void setAnimationIdle(TextureRegion[] reg, float delay) {
		animationIdle.setFrames(reg, delay);
		widthIdle = reg[0].getRegionWidth();
		heightIdle = reg[0].getRegionHeight();

	}
	public void somLatido()
	{
		som.play();
	}
	
	public void somOsso()
	{
		somOsso.play();
	}
	
	public void somVeneno()
	{
		somVeneno.play();
	}

	public void update(float dt) {
		stateTimer += dt;
		flagTimer += dt;

		verificaEstado(dt);

		animationRun.update(dt);
		animationIdle.update(dt);

		// Evita que Doggie saia da tela
		if (this.getPosition().x < 400 / PPM)
			body.setTransform(401 / PPM, body.getPosition().y, 0);
		if (this.getPosition().x > 9800 / PPM)
			terminouFase = true;
		
		if (this.getPosition().y > 870 / PPM)
		{
			body.setTransform(body.getPosition().x, 868 / PPM, 0);
			body.applyLinearImpulse(new Vector2(0f, -3f), body.getWorldCenter(), true);
			
		}

		if (this.getPosition().y < 64 / PPM) {
			body.setTransform(50 / PPM, 204 / PPM, 0);
			RecebeDano();
		}

		// A cada 10 ossos, ganha 1 vida
		if (this.getNumOssos() >= 10) {
			this.numOssos = 0;
			this.setTotalVidas(this.totalVidas + 1);

		}

	}
	
	public void Envenenado() {
		this.envenenado = true;
		RecebeDano();
	}

	public void RecebeDano() {
		if (this.totalVidas <= 0) {
			this.totalVidas = 0;
			morreu();

		}
		if (this.totalVidas > 0 && this.envenenado == false) {
			// Joga pra posicao "inicial" caso tenha vidas
//			body.setTransform(50 / PPM, 204/ PPM, 0);
			body.applyLinearImpulse(new Vector2(0f, 6f), body.getWorldCenter(), true);
		}
		else if(this.totalVidas > 0 && envenenado == true)
		{
			body.applyLinearImpulse(new Vector2(0f, -3f), body.getWorldCenter(), true);
			envenenado = false;
		}

			setTotalVidas(this.totalVidas - 1);
			
		if (this.totalVidas < 0)
			this.totalVidas = 0;
		
	}

	public void collectOssos() {
		numOssos++;
	}

	public int getNumOssos() {
		return numOssos;
	}

	public void setTotalOsso(int i) {
		totalOssos = i;
	}

	public int getTotalOssos() {
		return totalOssos;
	}

	public int getNumVidas() {
		return numVidas;
	}

	public void setTotalVidas(int i) {
		totalVidas = i;
	}

	public int getTotalVidas() {
		return totalVidas;
	}

	public void render(SpriteBatch sb) {
		sb.begin();

		// Ideal e aprimorar a maquina de estados do jogo.
		if (flip && estadoAtual == Estado.CORRENDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM + width / 2,
					body.getPosition().y * PPM - height / 2, -width, height);
		} else if (!flip && estadoAtual == Estado.CORRENDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM - width / 2,
					body.getPosition().y * PPM - height / 2);
		} else if (flip && estadoAtual == Estado.PARADO) {
			sb.draw(animationIdle.getFrame(), body.getPosition().x * PPM + widthIdle / 2,
					body.getPosition().y * PPM - heightIdle / 2, -widthIdle, heightIdle);
		} else if (!flip && estadoAtual == Estado.PARADO) {
			sb.draw(animationIdle.getFrame(), body.getPosition().x * PPM - widthIdle / 2,
					body.getPosition().y * PPM - heightIdle / 2);
		}else if (flip && estadoAtual == Estado.PULANDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM + width / 2,
					body.getPosition().y * PPM - height / 2, -width, height);
		} else if (!flip && estadoAtual == Estado.PULANDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM - width / 2,
					body.getPosition().y * PPM - height / 2);
		}
		else if (flip && estadoAtual == Estado.CAINDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM + width / 2,
					body.getPosition().y * PPM - height / 2, -width, height);
		} else if (!flip && estadoAtual == Estado.CAINDO) {
			sb.draw(animationRun.getFrame(), body.getPosition().x * PPM - width / 2,
					body.getPosition().y * PPM - height / 2);
		}

		sb.end();
	}

	public Estado verificaEstado(float dt) {
//		  {CAINDO, PULANDO, PARADO, CORRENDO, ATACANDO, MORTO};
		estadoAnterior = estadoAtual;
		estadoAtual = getState();

		switch (estadoAtual) {
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
//	                correndo(); // Classe que sera criada caso seja necessario
			break;
//	            case PARADO:
//	                parado(); // Classe que sera criada caso seja necessario	            	
//	            	break;
//	            case GAMEOVER:
//	            	SERA CRIADO FUNCAO QUE DESTROI O OBJETO
		default:
			estadoAtual = Estado.PARADO;
		}
		
		return estadoAtual;
	}

	public Estado getState() {

		if (body.getLinearVelocity().x < 0)
			flip = true;
		else if (body.getLinearVelocity().x > 0)
			flip = false;

		if (doggieMorreu)
			return Estado.MORTO;
		else if ((body.getLinearVelocity().y > 0 && estadoAtual == Estado.PULANDO))
			return Estado.PULANDO;
		else if (body.getLinearVelocity().y < 0) // && estadoAtual == Estado.CAINDO)
			return Estado.CAINDO;
		// Se a velocidade for diferente de 0 significa que esta em movimento
		else if (body.getLinearVelocity().x != 0)
			return Estado.CORRENDO;
		// Caira nessa funcao caso nenhuma condicao anterior seja acionada. Vericar
		// posssibilidade de criar ataques.
		else
			return Estado.PARADO;
	}

	public void morreu() {
		if (!doggieMorreu) {

			// Para musica do jogo e toca som de morte.

			doggieMorreu = true;
			Filter filter = new Filter();
			filter.maskBits = B2dVariaveis.BIT_NADA;
			filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS;
		}
	}

	public float getStateTimer() {
		return stateTimer;
	}

	public void jumpEnemy() {
		body.applyLinearImpulse(new Vector2(0, 10f), body.getWorldCenter(), true);
	}

	public void jump() {

		// Comentado provisoriamente enquanto os Estados nao sao atualizados.
		if (estadoAtual != Estado.PULANDO) {
			body.applyLinearImpulse(new Vector2(0, 6f), body.getWorldCenter(), true);
			estadoAtual = Estado.PULANDO;
		}
	}

	public void caindo() {

		// Comentado provisoriamente enquanto os Estados nao sao atualizados.
		if (estadoAtual != Estado.CAINDO) {
			estadoAtual = Estado.CAINDO;
		}
	}

	private void defineDoggie(int x, int y) {

		// Cria Doggie
		BodyDef DoggiebDef = new BodyDef();

		FixtureDef fDef = new FixtureDef();

		PolygonShape shape = new PolygonShape();

		CircleShape cShape = new CircleShape();
		cShape.setRadius(23 / PPM);

		// Criando Doggie
		DoggiebDef.position.set(x / PPM, y / PPM);
		DoggiebDef.type = BodyType.DynamicBody;

		body = world.createBody(DoggiebDef);

//		shape.setAsBox(28/PPM , 28/PPM); // Controla tamanho da caixa de colusao.
		fDef.shape = cShape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_OSSOS
				| B2dVariaveis.BIT_OSSOS_ENVENENADOS | B2dVariaveis.BIT_INIMIGO;
		// Faz quicar/
		fDef.restitution = 0f;
		body.createFixture(fDef).setUserData(this);

		// Criando sensor de pes
		shape.setAsBox(26 / PPM, 6 / PPM, new Vector2(0, -22 / PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE_PES;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_OBJETOS | B2dVariaveis.BIT_INIMIGO
				| B2dVariaveis.BIT_INIMIGO_HEAD;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(this);

		// Criando sensor da cabeca
		shape.setAsBox(26 / PPM, 6 / PPM, new Vector2(0, 22 / PPM), 0);
		fDef.shape = shape;
		fDef.filter.categoryBits = B2dVariaveis.BIT_DOGGIE_HEAD;
		fDef.filter.maskBits = B2dVariaveis.BIT_PLATAFORMA | B2dVariaveis.BIT_INIMIGO;
		fDef.isSensor = true;
		body.createFixture(fDef).setUserData(this);
	}

	public Body getBody() {
		return body;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}


	public float getWidth() {
		return width;
	}

	public float getHeigth() {
		return height;
	}

	public void dispose() {
		this.dispose();
	}
}
