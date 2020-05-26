package com.newhorizon.doggie.telas;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Coleiras;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;
import com.newhorizon.doggie.sprites.Inimigos;
import com.newhorizon.doggie.tools.B2dVariaveis;
import com.newhorizon.doggie.tools.DetectorColisoes;

public class PlayScreen implements Screen {

	// Se tiver em debug, o render mostrar� os colisores
	private boolean debug = true;

	private GameClass game;

	// basic playscreen variables
	private OrthographicCamera gamecam;
	private OrthographicCamera cameraHUD;
	private OrthographicCamera b2dCamera;
	private Viewport gamePort;
	private Hud hud;

	private Array<Coleiras> coleiras;

	public DetectorColisoes cl;

	// Tiled map (Deve ser migrado para o TiledMapManager)
	private TmxMapLoader maploader;
	public TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize;
	// Box2d variables
	public World world;
	private Box2DDebugRenderer b2dDR;

	// sprites
	private Doggie doggie;
	private Inimigos inimigo;
//    private Coleiras coleiras;
//    private TiledMapManager tiledMapMan;

	public PlayScreen(GameClass game) {
		this.game = game;

		// Cria Camera para seguir o Doggie
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false, GameClass.V_WIDTH, GameClass.V_HEIGHT);

		// C�mera dos colisores, deve funcionar apenas em modo Debug
		b2dCamera = new OrthographicCamera();
		b2dCamera.setToOrtho(false, GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM);

		// Cria um ViewPort para ter controle do jogo em diferentes tamanhos de janela.
		gamePort = new FitViewport(GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM, gamecam);

		// Carrega o mapa e configura --- DESATIVADO, ATUALMENTE EST� SENDO EXECUTADO
		// ATRAV�S DA FUN��O createTiles();
//        tiledMapMan = new TiledMapManager(this);
//        tiledMapMan.createTiles();

		maploader = new TmxMapLoader();
		tiledMap = maploader.load("Tiled/mapa1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);

		// Configura��o de posi��es das c�meras
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		b2dCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// Cria o mundo Box2D, onde o primeiro valor � a gravidade horizontal e o
		// segundo vertical.
		world = new World(new Vector2(0, -10), true);
		cl = new DetectorColisoes();
		world.setContactListener(cl);

		// Cria renderer para o box2D
		b2dDR = new Box2DDebugRenderer();

		// Criando Sprites no mundo.
		doggie = new Doggie(this);
		inimigo = new Inimigos(this);
		// coleiras = new Coleiras(this);

		// Cria HUD, onde valores e textos ser�o exibidos na tela. Na pr�tica � uma
		// c�mera com mesmos valores da gamecam.
		hud = new Hud(doggie);
		cameraHUD = new OrthographicCamera();
		cameraHUD.setToOrtho(false, GameClass.V_WIDTH, GameClass.V_HEIGHT);

		// Ambas as fun��es dever�o ser transformadas em objetos no futuro.
		createTiles();
		createColeiras();
	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {

		// Controle do Doggie usando impulsos
		if (doggie.estadoAtual != Estado.MORTO) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && cl.isPlayerOnGround())
				doggie.jump();

			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && doggie.body.getLinearVelocity().x <= 2)
				doggie.body.applyLinearImpulse(new Vector2(0.1f, 0), doggie.body.getWorldCenter(), true);
			else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && doggie.body.getLinearVelocity().x >= -2)
				doggie.body.applyLinearImpulse(new Vector2(-0.1f, 0), doggie.body.getWorldCenter(), true);
//			else
//				doggie.body.applyLinearImpulse(new Vector2(0,0), doggie.body.getWorldCenter(), true);
//            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
//            	doggie.fire();
		}

	}

	public void update(float dt) {

		world.step(dt, 6, 2);
		handleInput(dt);
		gamecam.update();

		

		// Apagando coleiras
		Array<Body> bodies = cl.getBodiesToRemove();
		for (int i = 0; i < bodies.size; i++) {

			Body b = bodies.get(i);
			coleiras.removeValue((Coleiras) b.getUserData(), true);
			world.destroyBody(b);
			doggie.collectColeiras();
		}

		doggie.update(dt);
		inimigo.update(dt);
//        coleiras.update(dt);
		
//		Gdx.app.log("log", "Estado atual: " + doggie.estadoAtual);

		bodies.clear();

		for (int i = 0; i < coleiras.size; i++) {
			coleiras.get(i).update(dt);
		}
		
	}

	@Override
	public void render(float delta) {
		// Separando a l�gica do update do render.
		update(delta);

		// Limpa o fundo do jogo com o RGB escolhido.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Trava a c�mera na posi��o do Doggie
		gamecam.position.set(doggie.body.getPosition().x * PPM, doggie.body.getPosition().y * PPM + 100, 0);
		gamecam.update();

		// Renderiza o mapa do jogo com os sprites.
		tmr.setView(gamecam);
		tmr.render();
		doggie.render(game.sb);
		inimigo.render(game.sb);
//        coleiras.render(game.sb);
		// Camadas s�o renderizadas depois em cima do Doggie e de inimigos.
		tmr.getBatch().begin();
		tmr.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("FrenteCenario"));
		tmr.getBatch().end();
		game.sb.setProjectionMatrix(cameraHUD.combined);
		hud.render(game.sb);

		
		// Renderiza a c�mera do Box2D
		if (debug) {
			b2dDR.render(world, b2dCamera.combined);
			b2dCamera.position.set(doggie.body.getPosition().x, doggie.body.getPosition().y + 1f, 0);
			b2dCamera.update();

		}

		game.sb.setProjectionMatrix(gamecam.combined);

		// Desenha coleiras
		for (int i = 0; i < coleiras.size; i++) {
			coleiras.get(i).render(game.sb);
		}

		// Como o sb est� sendo executado no game, verificar possibilidade de exclus�o
		// dessas fun��es.
//        game.sb.begin();      
//        game.sb.end();

		// Troca de tela em caso de Game Over (N�o implementado)
//        if(gameOver()){
//            game.setScreen(new GameOverScreen(game));
//            dispose();
//        }
	}

	// Estrutura de game over com possibilidade de jogo funcionar com timer
//    public boolean gameOver(){
//        if(player.currentState == doggie.Estado.MORTO && doggie.getStateTimer() > 3){
//            return true;
//        }
//        return false;
//    }

	private void createTiles() {

		// Carregando mapa
		tiledMap = new TmxMapLoader().load("Tiled/mapa1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);
		tileSize = (int) tiledMap.getProperties().get("tilewidth");

		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Calcada");
		createLayer(layer, B2dVariaveis.BIT_PLATAFORMA);
		
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Muro");
		createLayer(layer, B2dVariaveis.BIT_PLATAFORMA);

	}

	private void createLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// Indo para linha e coluna de cada layer
		for (int linha = 0; linha < layer.getHeight(); linha++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// Pegando a c�lula
				Cell cell = layer.getCell(col, linha);

				// Checa se c�lula existe
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// Criando um corpo vinculado a celula (create a body + fixture from cell)
				bDef.type = BodyType.StaticBody;
				bDef.position.set((col + 0.5f) * tileSize / PPM, (linha + 0.5f) * tileSize / PPM);

				if (layer == (TiledMapTileLayer) tiledMap.getLayers().get("Calcada"))
					shape.setAsBox(32 / PPM, 16 / PPM, new Vector2(0, -16 /PPM), 0);
				else if (layer == (TiledMapTileLayer) tiledMap.getLayers().get("Muro"))
					shape.setAsBox(32 / PPM, 4 / PPM, new Vector2(0, 13 / PPM), 0);

				fDef.friction = 0;
				fDef.shape = shape;
				fDef.filter.categoryBits = bits;
				fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE | B2dVariaveis.BIT_INIMIGO1;
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

		for (MapObject mo : layer.getObjects()) {

			bDef.type = BodyType.StaticBody;

			float x = (float) mo.getProperties().get("x") / PPM;
			float y = (float) mo.getProperties().get("y") / PPM;

			bDef.position.set(x, y);
			CircleShape cShape = new CircleShape();
			cShape.setRadius(8 / PPM);

			fDef.shape = cShape;
			fDef.isSensor = true;
			fDef.filter.categoryBits = B2dVariaveis.BIT_COLEIRAS;
			fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE;

			Body body = world.createBody(bDef);
			body.createFixture(fDef).setUserData("coleiras");

			Coleiras c = new Coleiras(body);
			coleiras.add(c);

			body.setUserData(c);

		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	public World getWorld() {
		return world;
	}

	public void dispose() {
		// dispose of all our opened resources
		tiledMap.dispose();
		tmr.dispose();
		world.dispose();
		b2dDR.dispose();
//        hud.dispose();

	}

}