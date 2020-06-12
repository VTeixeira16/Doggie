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
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;
import com.newhorizon.doggie.sprites.InimigoCachorro;
import com.newhorizon.doggie.sprites.Inimigos;
import com.newhorizon.doggie.sprites.Ossos;
import com.newhorizon.doggie.threads.DoggieThread;
import com.newhorizon.doggie.threads.InimigoThread;
import com.newhorizon.doggie.tools.B2dVariaveis;
import com.newhorizon.doggie.tools.DetectorColisoes;

public class PlayScreen implements Screen {

	// Se tiver em debug, o render mostrará os colisores
	private boolean debug = true;

	private GameClass game;

	// basic playscreen variables
	private OrthographicCamera gamecam;
	private OrthographicCamera cameraHUD;
	private OrthographicCamera b2dCamera;
	private Viewport gamePort;
	private Hud hud;

	private Array<Ossos> ossos;

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
	private DoggieThread doggieThread;
	private InimigoThread inimigo1Thread;
	private Inimigos inimigo;
	private InimigoThread inimigo2Thread;
	private Inimigos inimigo2;
//    private Coleiras coleiras;
//    private TiledMapManager tiledMapMan;
	
	public float posDoggieX;
	public float posDoggieY;

	public PlayScreen(GameClass game) {
		this.game = game;

		// Cria Camera para seguir o Doggie
		gamecam = new OrthographicCamera();
		gamecam.setToOrtho(false, GameClass.V_WIDTH, GameClass.V_HEIGHT);

		// Câmera dos colisores, deve funcionar apenas em modo Debug
		b2dCamera = new OrthographicCamera();
		b2dCamera.setToOrtho(false, GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM);

		// Cria um ViewPort para ter controle do jogo em diferentes tamanhos de janela.
		gamePort = new FitViewport(GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM, gamecam);

		// Carrega o mapa e configura --- DESATIVADO, ATUALMENTE ESTÁ SENDO EXECUTADO
		// ATRAVÉS DA FUNÇÃO createTiles();
//        tiledMapMan = new TiledMapManager(this);
//        tiledMapMan.createTiles();

		maploader = new TmxMapLoader();
		tiledMap = maploader.load("Tiled/mapa1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);

		// Configuração de posições das câmeras
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		b2dCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// Cria o mundo Box2D, onde o primeiro valor é a gravidade horizontal e o
		// segundo vertical.
		world = new World(new Vector2(0, -10), true);
		cl = new DetectorColisoes();
		world.setContactListener(cl);

		// Cria renderer para o box2D
		b2dDR = new Box2DDebugRenderer();

		// Criando Sprites no mundo.
		// ((this), Posição X, Posição Y)
		doggie = new Doggie(this, 30, 320);
		doggieThread = new DoggieThread(doggie);
		
		inimigo = new InimigoCachorro(this, 230, 180);
		inimigo1Thread = new InimigoThread(inimigo);
		inimigo2 = new InimigoCachorro(this, 400, 180);
		inimigo2Thread = new InimigoThread(inimigo2);
		
		
		doggieThread.start();
		inimigo1Thread.start();
		inimigo2Thread.start();
		
		// coleiras = new Coleiras(this);

		// Cria HUD, onde valores e textos serão exibidos na tela. Na prática é uma
		// câmera com mesmos valores da gamecam.
		hud = new Hud(doggie);
		cameraHUD = new OrthographicCamera();
		cameraHUD.setToOrtho(false, GameClass.V_WIDTH, GameClass.V_HEIGHT);

		// Ambas as funções deverão ser transformadas em objetos no futuro.
		createTiles();
		createOssos();
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
		
		
		
		if(doggie.getTotalVidas() == 0)
			game.setScreen(new GameOver(game));

		world.step(dt, 6, 2);
		handleInput(dt);
		gamecam.update();
//		System.out.println("Inimigo estado:" + inimigo.estadoAtual);

		

		// Apagando Ossos
		Array<Body> bodies = cl.getBodiesToRemove();
		for (int i = 0; i < bodies.size; i++) {

			Body b = bodies.get(i);
			ossos.removeValue((Ossos) b.getUserData(), true);
			world.destroyBody(b);
			doggie.collectOssos();
			
		}

		doggie.update(dt);
		inimigo.update(dt);
		inimigo2.update(dt);
		
		
		
		posDoggieX = doggie.getPosition().x * PPM;
		posDoggieY = doggie.getPosition().y * PPM;
		
		
		
//        ossos.update(dt);
//		Gdx.app.log("log", "Estado atual: " + doggie.estadoAtual);

		bodies.clear();
		for (int i = 0; i < ossos.size; i++) {
			ossos.get(i).update(dt);
		}
		
	
//		ManagerCenas.setScreen(new GameOver(this), game);
	}
	@Override
	public void render(float delta) {
		// Separando a lógica do update do render.
		update(delta);

		// Limpa o fundo do jogo com o RGB escolhido.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Trava a câmera na posição do Doggie
		gamecam.position.set(doggie.body.getPosition().x * PPM, doggie.body.getPosition().y * PPM + 100, 0);
		gamecam.update();

		// Renderiza o mapa do jogo com os sprites.
		tmr.setView(gamecam);
		tmr.render();
		
		// Migrado para DoggieThread
//		doggie.render(game.sb);
		
//		IMPLEMENTAÇÃO USADA APENAS ENQUANTO NÃO FOR RESOLVIDO O PROBLEMA DE MULTITHREADS
		
//		doggieThread.run();
//		inimigo1Thread.run();
//		inimigo2Thread.run();
		inimigo.render(game.sb);
		inimigo2.render(game.sb);
		
		doggie.render(game.sb);

		
		
//		inimigo.render(game.sb);	
//        ossos.render(game.sb);
		// Camadas são renderizadas depois em cima do Doggie e de inimigos.
		tmr.getBatch().begin();
		tmr.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("FrenteCenario"));
		tmr.getBatch().end();
		game.sb.setProjectionMatrix(cameraHUD.combined);
		hud.render(game.sb);

		
		// Renderiza a câmera do Box2D
		if (debug) {
			b2dDR.render(world, b2dCamera.combined);
			b2dCamera.position.set(doggie.body.getPosition().x, doggie.body.getPosition().y + 1f, 0);
			b2dCamera.update();

		}

		game.sb.setProjectionMatrix(gamecam.combined);

		// Desenha ossos
		for (int i = 0; i < ossos.size; i++) {
			ossos.get(i).render(game.sb);
		}

		// Como o sb está sendo executado no game, verificar possibilidade de exclusão
		// dessas funções.
//        game.sb.begin();      
//        game.sb.end();

		// Troca de tela em caso de Game Over (Não implementado)
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

		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Obstaculos");
		createLayer(layer, B2dVariaveis.BIT_OBJETOS);
	}

	private void createLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// Indo para linha e coluna de cada layer
		for (int linha = 0; linha < layer.getHeight(); linha++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// Pegando a célula
				Cell cell = layer.getCell(col, linha);

				// Checa se célula existe
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
				else if (layer == (TiledMapTileLayer) tiledMap.getLayers().get("Obstaculos"))
					shape.setAsBox(32 / PPM, 31 / PPM, new Vector2(0, 37 / PPM), 0);

				fDef.friction = 0;
				fDef.shape = shape;
				fDef.filter.categoryBits = bits;
				
				if (layer != (TiledMapTileLayer) tiledMap.getLayers().get("Obstaculos"))
					fDef.filter.maskBits = B2dVariaveis.BIT_INIMIGO | B2dVariaveis.BIT_INIMIGO_PES | B2dVariaveis.BIT_DOGGIE |B2dVariaveis.BIT_DOGGIE_PES;
				else
					fDef.filter.maskBits = B2dVariaveis.BIT_INIMIGO | B2dVariaveis.BIT_DOGGIE |B2dVariaveis.BIT_DOGGIE_PES;
				
				fDef.isSensor = false;
				
				
				
				
				world.createBody(bDef).createFixture(fDef);

			}

		}

	}

	private void createOssos() {

		ossos = new Array<Ossos>();

		MapLayer layer = tiledMap.getLayers().get("Ossos");

		BodyDef bDef = new BodyDef();
		FixtureDef fDef = new FixtureDef();

		for (MapObject mo : layer.getObjects()) {

			bDef.type = BodyType.StaticBody;

			float x = (float) mo.getProperties().get("x") / PPM;
			float y = (float) mo.getProperties().get("y") / PPM;

			bDef.position.set(x, y);
			CircleShape cShape = new CircleShape();
			cShape.setRadius(9 / PPM);

			fDef.shape = cShape;
			fDef.isSensor = true;
			fDef.filter.categoryBits = B2dVariaveis.BIT_OSSOS;
			fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE;

			Body body = world.createBody(bDef);
			body.createFixture(fDef).setUserData("ossos");

			Ossos c = new Ossos(body);
			ossos.add(c);

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
		doggie.dispose();
		inimigo.dispose();
		inimigo2.dispose();
//        hud.dispose();

	}

}
