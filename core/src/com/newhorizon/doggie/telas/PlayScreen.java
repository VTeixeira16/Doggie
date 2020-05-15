package com.newhorizon.doggie.telas;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.tiled.B2DColisores;
import com.newhorizon.doggie.tiled.TiledMapManager;

public class PlayScreen implements Screen{
	
	private GameClass game;
	
    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
	
    //Tiled map (Deve ser migrado para o TiledMapManager)
    private TmxMapLoader maploader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    
    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dDR;
    private B2DColisores creator;
    
    //sprites
    private Doggie doggie;
    
    private TiledMapManager tiledMapMan;
	
	
	
	
	public PlayScreen(GameClass game) {
		
        this.game = game;

        // Cria Camera para seguir o Doggie
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false,GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM);

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(GameClass.V_WIDTH / PPM, GameClass.V_HEIGHT / PPM, gamecam);

        // área reservada para o HUD
//        hud = new Hud(game.batch);

        // Carrega o mapa e configura
        tiledMapMan = new TiledMapManager();
        tiledMapMan.createTiles();
        
        maploader = new TmxMapLoader();
        tiledMap = maploader.load("Tiled/mapa1.tmx");
        tmr = new OrthogonalTiledMapRenderer(tiledMap, 1  / PPM);

        //initially set our gamcam to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true);
        
        //allows for debug lines of our box2d world.
        b2dDR = new Box2DDebugRenderer();

//        creator = new B2DColisores(this);

        //Criando Doggie no world
        doggie = new Doggie(this);

//        world.setContactListener(new WorldContactListener());
        
        
        
        

	}

	@Override
	public void show() {
		
	}
	
	public void update(float dt){
		
		//aqui será implementado função para verificar se alguma tecla foi apertada
		
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world.
        tmr.setView(gamecam);
		 
	}

	@Override
	public void render(float delta) {
        //separate our update logic from render
        update(delta);
        
        //Clear the game screen with Black
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        doggie.update(delta);
        
//		gamecam.position.set(
//				doggie.getPosition().x * PixelsPorMetro,
//				doggie.getPosition().y * PixelsPorMetro,
//				0);
//		gamecam.update();
        
        
        
        //render our game map
        tmr.setView(gamecam);
        tmr.render();

        //renderer our Box2DDebugLines
        b2dDR.render(world, gamecam.combined);
        
        game.sb.setProjectionMatrix(gamecam.combined);
        game.sb.begin();     
        
        game.sb.end();
        
//        game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
//        hud.stage.draw();
        
		
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

	
    public World getWorld(){
        return world;
    }
	
	public void dispose() {
        //dispose of all our opened resources
        tiledMap.dispose();
        tmr.dispose();
        world.dispose();
        b2dDR.dispose();
//        hud.dispose();
		
	}

}
