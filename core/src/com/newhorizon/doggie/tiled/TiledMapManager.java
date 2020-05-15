package com.newhorizon.doggie.tiled;
import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.newhorizon.doggie.tools.B2dVariaveis;

public class TiledMapManager {
	
	
	// Variáveis da PlayScreen vão ser migradas para cá
    //Tiled map (Deve ser migrado para o TiledMapManager)
    private TmxMapLoader maploader;
    private TiledMap tiledMap;
    private float tileSize;
    private OrthogonalTiledMapRenderer tmr;
    // Necessário copiar world do PlayScreen para evitar que PolygonShape quebre
    private World world = new World(new Vector2(0, -10), true);
    private Box2DDebugRenderer b2dDR = new Box2DDebugRenderer();
    public void createTiles() {
    	
		// Carregando mapa
		tiledMap = new TmxMapLoader().load("Tiled/mapa1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);
		tileSize = (int) tiledMap.getProperties().get("tilewidth");
		
		
		TiledMapTileLayer layer;
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("Plataformas");
		createLayer(layer, B2dVariaveis.BIT_PLATAFORMA);
		
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("PlataformasElevadas");
		createLayer(layer, B2dVariaveis.BIT_PLATAFORMA);
		
	}
	
    public void createLayer(TiledMapTileLayer layer, short bits) {
		
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
					(col + 0.5f) * tileSize / PPM,
					(linha + 0.5f) * tileSize / PPM
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
				if(layer == (TiledMapTileLayer) tiledMap.getLayers().get("Plataformas"))
					shape.setAsBox(32/PPM , 32/PPM);
				else if(layer == (TiledMapTileLayer) tiledMap.getLayers().get("PlataformasElevadas"))
					shape.setAsBox(32/PPM , 24/PPM, new Vector2(0,-9/PPM),0);
					
				
//				cs.createChain(v);
				fDef.friction = 0;
				fDef.shape = shape;
				fDef.filter.categoryBits = bits;
				fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE | B2dVariaveis.BIT_INIMIGO1;
				fDef.isSensor = false;
				world.createBody(bDef).createFixture(fDef);
				
			}
			
		}
		 
	}

}
