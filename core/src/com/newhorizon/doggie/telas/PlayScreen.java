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
import com.newhorizon.doggie.sprites.OssosVeneno;
import com.newhorizon.doggie.threads.DoggieThread;
import com.newhorizon.doggie.threads.InimigoThread;
import com.newhorizon.doggie.tools.B2dVariaveis;
import com.newhorizon.doggie.tools.DetectorColisoes;

public class PlayScreen implements Screen {

	// Se tiver em debug, o render mostrar� os colisores

//	private boolean debug = false;
	

	private GameClass game;
	
	private float playTimer;

	// basic playscreen variables
	private OrthographicCamera gamecam;
	private OrthographicCamera cameraHUD;
	private OrthographicCamera b2dCamera;
	private Viewport gamePort;
	private Hud hud;

	private Array<Ossos> ossos;
	private Array<OssosVeneno> ossosVeneno;

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
	private Inimigos inimigo;
	private Inimigos inimigo2;
	private Inimigos inimigo3;
	private Inimigos inimigo4;
	private Inimigos inimigo5;
	private Inimigos inimigo6;
	private Inimigos inimigo7;
	private Inimigos inimigo8;
	private Inimigos inimigo9;
	private Inimigos inimigo10;
	private Inimigos inimigo11;
	private Inimigos inimigo12;
	private Inimigos inimigo13;
	private Inimigos inimigo14;
	private Inimigos inimigo15;
	//Especificos para fase 2
	private Inimigos inimigo16;
	private Inimigos inimigo17;
	private Inimigos inimigo18;
	private Inimigos inimigo19;
	private Inimigos inimigo20;
	private Inimigos inimigo21;
	private Inimigos inimigo22;
	private Inimigos inimigo23;
	private Inimigos inimigo24;
	private Inimigos inimigo25;
	private Inimigos inimigo26;
	private Inimigos inimigo27;
	private Inimigos inimigo28;
	private Inimigos inimigo29;
	private Inimigos inimigo30;
	
	private InimigoThread inimigo1Thread;
	private InimigoThread inimigo2Thread;
//    private Coleiras coleiras;
//    private TiledMapManager tiledMapMan;
	
	public float posDoggieX;
	public float posDoggieY;
	public boolean debug;
	
	public PlayScreen(GameClass game) {	

		this.game = game;
		
		debug = game.debug;
		
		if(game.faseAtual == 1)
		{
			
		}
		else if(game.faseAtual == 2)
		{
			
		}
		
		
		

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
		// ((this), Posi��o X, Posi��o Y)
		doggie = new Doggie(this, 400, 400);
//		doggie = new Doggie(this, 9650, 400);
		doggieThread = new DoggieThread(doggie);
		doggie.terminouFase = false;
		
		inimigo = new InimigoCachorro(this, 230, 180, 0);
		inimigo2 = new InimigoCachorro(this, 400, 180, 0);
		inimigo3 = new InimigoCachorro(this, 1000, 180, 0);
		inimigo4 = new InimigoCachorro(this, 1300, 180, 0);
		inimigo5 = new InimigoCachorro(this, 1700, 180, 0);
		inimigo6 = new InimigoCachorro(this, 2400, 180, 1);
		inimigo7 = new InimigoCachorro(this, 3000, 180, 0);
		inimigo8 = new InimigoCachorro(this, 4000, 180, 0);
		inimigo9 = new InimigoCachorro(this, 4500, 180, 1);
		inimigo10 = new InimigoCachorro(this, 5000, 180, 1);
		inimigo11= new InimigoCachorro(this, 6000, 180, 1);
		inimigo12 = new InimigoCachorro(this, 6500, 180, 0);
		inimigo13 = new InimigoCachorro(this, 7000, 180, 1);
		inimigo14 = new InimigoCachorro(this, 8000, 180, 0);
		inimigo15 = new InimigoCachorro(this, 9000, 180, 1);
		
		if(game.faseAtual == 2)
		{
			inimigo16 = new InimigoCachorro(this, 800, 180, 1);
			inimigo17 = new InimigoCachorro(this, 3500, 180, 1);
			inimigo18 = new InimigoCachorro(this, 2700, 180, 1);
			inimigo19 = new InimigoCachorro(this, 3800, 180, 1);
			inimigo20 = new InimigoCachorro(this, 5100, 180, 0);
			inimigo21 = new InimigoCachorro(this, 6200, 180, 0);
			inimigo22 = new InimigoCachorro(this, 6700, 180, 1);
			inimigo23 = new InimigoCachorro(this, 8500, 180, 0);
			inimigo24 = new InimigoCachorro(this, 8600, 180, 1);
			inimigo25 = new InimigoCachorro(this, 7250, 180, 1);
			inimigo26 = new InimigoCachorro(this, 7500, 180, 1);
			inimigo27 = new InimigoCachorro(this, 6250, 180, 1);
			inimigo28 = new InimigoCachorro(this, 3200, 180, 1);
			inimigo29 = new InimigoCachorro(this, 2000, 180, 1);
			inimigo30 = new InimigoCachorro(this, 3300, 180, 0);
		}
		
//		inimigo1Thread = new InimigoThread(inimigo);
//		inimigo2Thread = new InimigoThread(inimigo2);
		
		
//		doggieThread.start();
//		inimigo1Thread.start();
//		inimigo2Thread.start();
		
		// coleiras = new Coleiras(this);

		// Cria HUD, onde valores e textos ser�o exibidos na tela. Na pr�tica � uma
		// c�mera com mesmos valores da gamecam.
		hud = new Hud(doggie, this.game);
		cameraHUD = new OrthographicCamera();
		cameraHUD.setToOrtho(false, GameClass.V_WIDTH, GameClass.V_HEIGHT);

		// Ambas as fun��es dever�o ser transformadas em objetos no futuro.
		createTiles();
		createOssos();
		createOssosVeneno();		
	}

	@Override
	public void show() {

	}

	public void handleInput(float dt) {

		// Controle do Doggie usando impulsos
		if (doggie.estadoAtual != Estado.MORTO) {
			if ((Gdx.input.isKeyJustPressed(Input.Keys.UP) || (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) && cl.isPlayerOnGround())
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
		playTimer += dt;
		
		if(playTimer > 0.1f)
		{
			if(game.faseAtual == 1)
				game.telaAtual = "Fase1";
			else if(game.faseAtual == 2)
				game.telaAtual = "Fase2";
			
		}
        			
		if((Gdx.input.isKeyJustPressed(Input.Keys.P)) && game.debug)
			doggie.terminouFase = true;
			
		if((doggie.getTotalVidas() <= 0 || ((Gdx.input.isKeyJustPressed(Input.Keys.O)) && game.debug)))
		{
        	game.telaAtual = "Null";	
			game.setScreen(new GameOver(game));
		}
		
		if(doggie.terminouFase)
		{
        	game.telaAtual = "Null";	
			game.setScreen(new PosGameScreen(game));
		}

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

		// Apagando Ossos
		Array<Body> bodiesVeneno = cl.getBodiesVToRemove();
		for (int i = 0; i < bodiesVeneno.size; i++) {

			Body bV = bodiesVeneno.get(i);
			ossosVeneno.removeValue((OssosVeneno) bV.getUserData(), true);
			world.destroyBody(bV);
			//Colocar fun��o para remover vida do Doggie
			doggie.Envenenado();
		}
		
		doggie.update(dt);
		inimigo.update(dt);
		inimigo2.update(dt);
		inimigo3.update(dt);
		inimigo4.update(dt);
		inimigo5.update(dt);
		inimigo6.update(dt);
		inimigo7.update(dt);
		inimigo8.update(dt);
		inimigo9.update(dt);
		inimigo10.update(dt);
		inimigo11.update(dt);
		inimigo12.update(dt);
		inimigo13.update(dt);
		inimigo14.update(dt);
		inimigo15.update(dt);
		if(game.faseAtual == 2)
		{
			inimigo16.update(dt); 	
			inimigo17.update(dt); 	
			inimigo18.update(dt); 	
			inimigo19.update(dt); 	
			inimigo20.update(dt); 	
			inimigo21.update(dt); 	
			inimigo22.update(dt); 	
			inimigo23.update(dt); 	
			inimigo24.update(dt); 		
			inimigo25.update(dt); 		
			inimigo26.update(dt); 		
			inimigo27.update(dt); 		
			inimigo28.update(dt); 		
			inimigo29.update(dt); 		
			inimigo30.update(dt); 		
		}
	
		posDoggieX = doggie.getPosition().x * PPM;
		posDoggieY = doggie.getPosition().y * PPM;
		
		
		
//        ossos.update(dt);
//		Gdx.app.log("log", "Estado atual: " + doggie.estadoAtual);

		bodies.clear();
		for (int i = 0; i < ossos.size; i++) {
			ossos.get(i).update(dt);
		}
		
		bodiesVeneno.clear();
		for (int i = 0; i < ossosVeneno.size; i++) {
			ossosVeneno.get(i).update(dt);
		}
	
//		ManagerCenas.setScreen(new GameOver(this), game);
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
		
		// Migrado para DoggieThread
//		doggie.render(game.sb);
		
//		IMPLEMENTA��O USADA APENAS ENQUANTO N�O FOR RESOLVIDO O PROBLEMA DE MULTITHREADS
		
//		doggieThread.run();
//		inimigo1Thread.run();
//		inimigo2Thread.run();
		inimigo.render(game.sb);
		inimigo2.render(game.sb);
		inimigo3.render(game.sb);
		inimigo4.render(game.sb);
		inimigo5.render(game.sb);
		inimigo6.render(game.sb);
		inimigo7.render(game.sb);
		inimigo8.render(game.sb);
		inimigo9.render(game.sb);
		inimigo10.render(game.sb);
		inimigo11.render(game.sb);
		inimigo12.render(game.sb);
		inimigo13.render(game.sb);
		inimigo14.render(game.sb);
		inimigo15.render(game.sb);
		if(game.faseAtual == 2)
		{
			inimigo16.render(game.sb);
			inimigo17.render(game.sb);
			inimigo18.render(game.sb);
			inimigo19.render(game.sb);
			inimigo20.render(game.sb);
			inimigo21.render(game.sb);
			inimigo22.render(game.sb);
			inimigo23.render(game.sb);
			inimigo24.render(game.sb);
			inimigo25.render(game.sb);
			inimigo26.render(game.sb);
			inimigo27.render(game.sb);
			inimigo28.render(game.sb);
			inimigo29.render(game.sb);
			inimigo30.render(game.sb);

		}
		
		doggie.render(game.sb);
		

		
		
//		inimigo.render(game.sb);	
//        ossos.render(game.sb);
		// Camadas s�o renderizadas depois em cima do Doggie e de inimigos.
		tmr.getBatch().begin();
		tmr.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get("FrenteCenario"));
		tmr.getBatch().end();
		game.sb.setProjectionMatrix(cameraHUD.combined);
		hud.render(game.sb);

		
		// Renderiza a c�mera do Box2D
		if (game.debug) {
			b2dDR.render(world, b2dCamera.combined);
			b2dCamera.position.set(doggie.body.getPosition().x, doggie.body.getPosition().y + 1f, 0);
			b2dCamera.update();

		}

		game.sb.setProjectionMatrix(gamecam.combined);
		
		// Desenha ossos
		for (int i = 0; i < ossos.size; i++) {
			ossos.get(i).render(game.sb);
		}
		
		for (int i = 0; i < ossosVeneno.size; i++) {
			ossosVeneno.get(i).render(game.sb);
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
	if(game.faseAtual == 1)
		tiledMap = new TmxMapLoader().load("Tiled/mapa1.tmx");			
	else if(game.faseAtual == 2)
		tiledMap = new TmxMapLoader().load("Tiled/mapa2.tmx");
	
//	tiledMap = new TmxMapLoader().load("Tiled/mapa1.tmx");
	
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
	
	private void createOssosVeneno() {

		ossosVeneno = new Array<OssosVeneno>();

		MapLayer layer = tiledMap.getLayers().get("OssosVeneno");

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
			fDef.filter.categoryBits = B2dVariaveis.BIT_OSSOS_ENVENENADOS;
			fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE;

			Body body = world.createBody(bDef);
			body.createFixture(fDef).setUserData("ossosVeneno");

			OssosVeneno c = new OssosVeneno(body);
			ossosVeneno.add(c);

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
		inimigo3.dispose();
		inimigo4.dispose();
		inimigo5.dispose();
		inimigo6.dispose();
		inimigo7.dispose();
		inimigo8.dispose();
		inimigo9.dispose();
		inimigo10.dispose();
		inimigo11.dispose();
		inimigo12.dispose();
		inimigo13.dispose();
		inimigo14.dispose();
		inimigo15.dispose();
//        hud.dispose();

	}

}
