package com.newhorizon.doggie.states;

import static com.newhorizon.doggie.handlers.b2dVariaveis.PixelsPorMetro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.entities.Coleiras;
import com.newhorizon.doggie.entities.Doggie;
import com.newhorizon.doggie.entities.HUD;
import com.newhorizon.doggie.handlers.GameInputs;
import com.newhorizon.doggie.handlers.GameStateManager;
import com.newhorizon.doggie.handlers.ListenerContatos;
import com.newhorizon.doggie.handlers.b2dVariaveis;

public class Play extends GameState{
	
	private boolean debug = true;
	
	private World world;
	
	private Box2DDebugRenderer b2dDR;
	
	private OrthographicCamera b2dCamera;
	private ListenerContatos cl;
	 	
	private TiledMap tiledMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Doggie doggie;
	private Array <Coleiras> coleiras;
	
	private HUD hud;
	
	BodyDef DoggiebDef = new BodyDef();
	
	public Play(GameStateManager gsm)
	{
		super(gsm);
		
		// Controla gravidade
		world = new World(new Vector2(0, -9.81f), true); 
		cl = new ListenerContatos();
		world.setContactListener(cl);
		b2dDR = new Box2DDebugRenderer();
		
		// Criando Doggie
		createDoggie();
		
		// Criando Tiles
		createTiles();
		
		//Cria coleiras
		createColeiras();
			
		b2dCamera = new OrthographicCamera();
		b2dCamera.setToOrtho(false, GameClass.V_WIDTH/PixelsPorMetro, GameClass.V_HEIGHT/PixelsPorMetro);
		
		//set up HUD
		hud = new HUD(doggie);
					
	}
	

	public void handleInput() 
	{
		float velocidade = 2f;
		Gdx.app.log("log", "Velocidade: " +doggie.getBody().getLinearVelocity().x);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			if(cl.isPlayerOnGround())
				doggie.getBody().setLinearVelocity(-velocidade, 0f);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			if(cl.isPlayerOnGround())
				doggie.getBody().setLinearVelocity(velocidade, 0f);
		}
		else
		{ 
			if(cl.isPlayerOnGround())
				doggie.getBody().setLinearVelocity(0f, 0f);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.Z))
		{
			if(cl.isPlayerOnGround())
			{
				// Controle de pulo
				doggie.getBody().applyForceToCenter(0, 325, true);   
			}
		}
		
		// Trocando plataforma que tera colisao
		if(Gdx.input.isKeyPressed(Input.Keys.X))
		{
			switchBlocks();
		}
		
		
	}
	
	public void update(float dt) {
		
		// Checa input
		handleInput();
		
		// Controle de colisões - Atualizacao box2D
		world.step(dt, 6, 2);
		
		//Apagando coleiras
		Array<Body> bodies = cl.getBodiesToRemove();
		for (int i = 0; i < bodies.size; i++)
		{
			
			Body b = bodies.get(i);
			coleiras.removeValue((Coleiras)b.getUserData(), true);
			world.destroyBody(b);
			doggie.collectColeiras();			
		}
		
		bodies.clear();
		
		doggie.update(dt);

		for(int i = 0; i < coleiras.size; i++) {
			coleiras.get(i).update(dt);	
		}
		
	}
	public void render () {
		
		//Limpando a tela
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Configurando câmera para seguir o Doggie
		camera1.position.set(
				doggie.getPosition().x * PixelsPorMetro,
				doggie.getPosition().y * PixelsPorMetro,
				0);
		camera1.update();
		
		
		// Desenha o Mapa
		tmr.setView(camera1);
		tmr.render();
		
		
		//Desenha doggie
		sb.setProjectionMatrix(camera1.combined);
		doggie.render(sb);
		
		//Desenha coleiras
		for(int i = 0; i < coleiras.size; i++) {
			coleiras.get(i).render(sb);
		}
		
		//Desenha HUD
		sb.setProjectionMatrix(cameraHUD.combined);
		hud.render(sb);

		// Desenha caixas de colisão
		if(debug) {
			b2dDR.render(world, b2dCamera.combined);
			b2dCamera.position.set(
					doggie.getPosition().x,
					doggie.getPosition().y,
					0);
			b2dCamera.update();
		}
		
	}
	public void dispose() {}
	
	private void createDoggie() {
		
		//Cria Plataforma
				
				FixtureDef fDef = new FixtureDef();
				PolygonShape shape = new PolygonShape();

			
				
				//Criando Doggie		
				DoggiebDef.position.set(150/PixelsPorMetro ,400/PixelsPorMetro);
				DoggiebDef.type = BodyType.DynamicBody;
//				bDef.linearVelocity.set(.5f,0); // Velocidade do Doggie
				Body body = world.createBody(DoggiebDef);
				
				shape.setAsBox(13/PixelsPorMetro , 13/PixelsPorMetro); // Controla tamanho da caixa de colusão.
				fDef.shape = shape;
				fDef.filter.categoryBits = b2dVariaveis.BIT_DOGGIE;
				fDef.filter.maskBits = b2dVariaveis.BIT_PLATAFORMA | b2dVariaveis.BIT_COLEIRAS;
				// Faz quicar/
				fDef.restitution = 0.2f;
				body.createFixture(fDef).setUserData("doggie");
				
				//Criando sensor de pés
				shape.setAsBox(10/PixelsPorMetro, 6/PixelsPorMetro, new Vector2(0, -10/PixelsPorMetro), 0);
				fDef.shape = shape;
				fDef.filter.categoryBits = b2dVariaveis.BIT_DOGGIE;
				fDef.filter.maskBits = b2dVariaveis.BIT_PLATAFORMA;
				fDef.isSensor = true;
				body.createFixture(fDef).setUserData("footDoggie");
		
				// Cria Doggie
				doggie = new Doggie(body);
				
				body.setUserData(doggie);
				
	}
	
	private void createTiles() {
		
		// Carregando mapa
		tiledMap = new TmxMapLoader().load("Tiled/mapa1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);
		tileSize = (int) tiledMap.getProperties().get("tilewidth");
		
		
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Plataformas");
		createLayer(layer, b2dVariaveis.BIT_PLATAFORMA);
		
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("PlataformasElevadas");
		createLayer(layer, b2dVariaveis.BIT_PLATAFORMA_ELEV);
		
	}
	
	private void createLayer(TiledMapTileLayer layer, short bits) {
		
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// Indo para linha e coluna de cada layer
		for(int linha = 0; linha < layer.getHeight(); linha++){
			for(int col = 0; col < layer.getWidth(); col++){
			
				//Pegando a célula
				Cell cell = layer.getCell(col, linha);
				
				// Checa se célula existe
				if(cell == null) continue;
				if(cell.getTile() == null) continue;
				
				// Criando um corpo vinculado a celula (create a body + fixture from cell)
				bDef.type = BodyType.StaticBody;
				bDef.position.set(
					(col + 0.5f) * tileSize /PixelsPorMetro,
					(linha + 0.5f) * tileSize / PixelsPorMetro					
				);
				
//				ChainShape cs = new ChainShape();
//				Vector2[] v = new Vector2[3];
//				v[0] = new Vector2(
//						-tileSize / 2 / PixelsPorMetro, -tileSize / 2 / PixelsPorMetro);
//				v[1] = new Vector2(
//						-tileSize / 2 / PixelsPorMetro, tileSize / 2 / PixelsPorMetro);
//				v[2] = new Vector2(
//						tileSize / 2 / PixelsPorMetro, tileSize / 2 / PixelsPorMetro);
//				v[3] = new Vector2(
//						tileSize / 2 / PixelsPorMetro, -tileSize / 2 / PixelsPorMetro);
				

				shape.setAsBox(32/PixelsPorMetro , 32/PixelsPorMetro);
				
//				cs.createChain(v);
				fDef.friction = 0;
				fDef.shape = shape;
				fDef.filter.categoryBits = bits;
				fDef.filter.maskBits = b2dVariaveis.BIT_DOGGIE;
				fDef.isSensor = false;
				world.createBody(bDef).createFixture(fDef);
				
			}
			
		}
		 
	}
	
	private void createColeiras() {
	
		coleiras = new Array<Coleiras>();
		
		MapLayer layer = tiledMap.getLayers().get("Coleiras");
		
		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		
		for(MapObject mo : layer.getObjects()) {
			
			bDef.type = BodyType.StaticBody;
			
			float x = (float) mo.getProperties().get("x") / PixelsPorMetro;
			float y = (float) mo.getProperties().get("y") / PixelsPorMetro;
			
			bDef.position.set(x, y);
			CircleShape cShape = new CircleShape();
			cShape.setRadius(8 / PixelsPorMetro);
			
			fDef.shape = cShape;
			fDef.isSensor = true;
			fDef.filter.categoryBits = b2dVariaveis.BIT_COLEIRAS;
			fDef.filter.maskBits = b2dVariaveis.BIT_DOGGIE;
			
			Body body = world.createBody(bDef);
			body.createFixture(fDef).setUserData("coleiras");
			
			Coleiras c = new Coleiras(body);
			coleiras.add(c);
			
			body.setUserData(c);
						
		}
	}
	
	private void switchBlocks()
	{
		Filter filter = doggie.getBody().getFixtureList().first()
						.getFilterData();
		short bits = filter.maskBits;
		
		//Trocando para a próxima plataforma
		if((bits & b2dVariaveis.BIT_PLATAFORMA) !=0)
		{
			bits &= ~b2dVariaveis.BIT_PLATAFORMA;
			bits |= b2dVariaveis.BIT_PLATAFORMA_ELEV;
		}
		else if((bits & b2dVariaveis.BIT_PLATAFORMA_ELEV) !=0)
		{
			bits &= ~b2dVariaveis.BIT_PLATAFORMA_ELEV;
			bits |= b2dVariaveis.BIT_PLATAFORMA;
		}
		
		
		// Configura nova mascára de bits
		filter.maskBits = bits;
		doggie.getBody().getFixtureList().first().setFilterData(filter);
		
		// Configura mascára de bits para os pés
		filter = doggie.getBody().getFixtureList().get(1).getFilterData();
		bits &= ~b2dVariaveis.BIT_COLEIRAS;
		filter.maskBits = bits;
		doggie.getBody().getFixtureList().get(1).setFilterData(filter);
		
	}
}
