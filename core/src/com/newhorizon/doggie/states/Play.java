package com.newhorizon.doggie.states;

import static com.newhorizon.doggie.handlers.b2dVariaveis.PixelsPorMetro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.entities.Doggie;
import com.newhorizon.doggie.handlers.GameInputs;
import com.newhorizon.doggie.handlers.GameStateManager;
import com.newhorizon.doggie.handlers.ListenerContatos;
import com.newhorizon.doggie.handlers.b2dVariaveis;

public class Play extends GameState{
	
	private World world;
	
	private Box2DDebugRenderer b2dDR;
	
	private OrthographicCamera b2dCamera;
	private ListenerContatos cl;
	 	
	private TiledMap tiledMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Doggie doggie;
	
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
			
		b2dCamera = new OrthographicCamera();
		b2dCamera.setToOrtho(false, GameClass.V_WIDTH/PixelsPorMetro, GameClass.V_HEIGHT/PixelsPorMetro);
					
	}
	

	public void handleInput() 
	{
		if(GameInputs.isPressed(GameInputs.BUTTON1))
		{
			if(cl.isPlayerOnGround())
			{

				doggie.getBody().applyForceToCenter(0, 0, true);

			}
		}
		if(GameInputs.isPressed(GameInputs.BUTTON2))
		{
			
		}
		
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		// Controle de colisões
		world.step(dt, 6, 2);
		
		doggie.update(dt);
		
	}
	public void render () {
		
		//Limpando a tela
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Desenha o Mapa
		tmr.setView(camera1);
		tmr.render();
		
		
		//Desenha doggie
		sb.setProjectionMatrix(camera1.combined);
		doggie.render(sb);
		
		// Desenha mundo do Box2D
		b2dDR.render(world, b2dCamera.combined);
	}
	public void dispose() {}
	
	private void createDoggie() {
		
		//Cria Plataforma
				BodyDef bDef = new BodyDef();
				FixtureDef fDef = new FixtureDef();
				PolygonShape shape = new PolygonShape();

				
				//Criando Doggie		
//				bDef.position.set(160/PixelsPorMetro ,200/PixelsPorMetro);
				bDef.position.set(700/PixelsPorMetro ,200/PixelsPorMetro);
				bDef.type = BodyType.DynamicBody;
				bDef.linearVelocity.set(-1,0); // Velocidade do Doggie, o "-1" é para que ele não suma na tela!
				Body body = world.createBody(bDef);
				
				shape.setAsBox(13/PixelsPorMetro , 13/PixelsPorMetro); // Controla tamanho da caixa de colusão.
				fDef.shape = shape;
				fDef.filter.categoryBits = b2dVariaveis.BIT_DOGGIE;
				fDef.filter.maskBits = b2dVariaveis.BIT_PLATAFORMA;
				// Faz quicar/
				fDef.restitution = 0.5f;
				body.createFixture(fDef).setUserData("doggie");
				
				//Criando sensor de pés
				shape.setAsBox(2/PixelsPorMetro, 2/PixelsPorMetro, new Vector2(13, -13/PixelsPorMetro), 0);
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
				
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(
						-tileSize / 2 / PixelsPorMetro, -tileSize / 2 / PixelsPorMetro);
				v[1] = new Vector2(
						-tileSize / 2 / PixelsPorMetro, tileSize / 2 / PixelsPorMetro);
				v[2] = new Vector2(
						tileSize / 2 / PixelsPorMetro, tileSize / 2 / PixelsPorMetro);
				cs.createChain(v);
				fDef.friction = 0;
				fDef.shape = cs;
				fDef.filter.categoryBits = bits;
				fDef.filter.maskBits = b2dVariaveis.BIT_DOGGIE;
				fDef.isSensor = false;
				world.createBody(bDef).createFixture(fDef);
				
			}
			
		}
		
	}
	
}
