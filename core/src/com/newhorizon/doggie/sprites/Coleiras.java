package com.newhorizon.doggie.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.newhorizon.doggie.GameClass;

public class Coleiras extends B2DSprites {

	public Coleiras (Body body) {
		
		super(body);
		
//		Texture tex = GameClass.res.getTexture("coleiras");
//		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
		
		Texture tex = GameClass.res.getTexture("ossinho");
		TextureRegion[] sprites = TextureRegion.split(tex, 37, 24)[0];
		
		setAnimation(sprites, 1 / 16f );
	}
}


//public class Coleiras extends Sprite{
//	// DESATIVADA PROVISORIAMENTE NO PLAYSCREEN
//	// CLASSE QUEBRANDO NO GETBODY POIS NÃO ESTÁ SENDO POSSÍVEL VINCULAR NENHUM BODY
//	// PROVAVELMENTE O PROBLEMA ESTÁ RELACIONADO AO ARRAY.
//	
//	protected Body body;
//	
//	protected Animation animation;	
//	protected float width;
//	protected float height;
//	
//	// Propriedades das animações
//	protected Animation animationIdle;
//	protected float widthIdle;
//	protected float heightIdle;
//	
//	private Array <Coleiras> coleiras;
//	
//	public boolean flip;
//	public boolean emMovimento;
//	private PlayScreen screen;
//	private World world;
//	
//	public Coleiras(PlayScreen screen) {
//		this.screen = screen;
//		this.world = screen.getWorld();
//		animation = new Animation();
//		
//		Texture tex = GameClass.res.getTexture("coleiras");
//		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
//		
//		criaColeiras();
////		setBounds(0,0, 16 / PPM, 16 / PPM);
//		
//		setAnimation(sprites, 1 / 12f);	// Velocidade da troca de frame;
//	}
//	
//	public void setAnimation(TextureRegion[] reg, float delay) {
//		animation.setFrames(reg, delay);
//		width = reg[0].getRegionWidth();
//		height = reg[0].getRegionHeight();
//		
//	}
//	
//	public void setAnimationIdle(TextureRegion[] reg, float delay) {
//		animationIdle.setFrames(reg, delay);
//		widthIdle = reg[0].getRegionWidth();
//		heightIdle = reg[0].getRegionHeight();
//		
//	}
//	
//	public void update (float dt) {
//			
//		animation.update(dt);	
//		System.out.println("body " + getBody());
////		System.out.println(body.getPosition().x);
////		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeigth() / 2);
//		
//	}
//	
//	public void render (SpriteBatch sb) {
//		sb.begin();
//		sb.draw(
//				animation.getFrame(),
//				body.getPosition().x * PPM + width / 2,
//				body.getPosition().y * PPM - height / 2, 
//				-width,
//				height);
//		sb.end();
//	}
//	
//	public Body getBody() {return body;}
//	public Vector2 getPosition() {return body.getPosition();}
//	public float getWidth() { return width;}
//	public float getHeigth() {return height;}
//	
//	public void criaColeiras() {
//		coleiras = new Array<Coleiras>();
//		
//		MapLayer layer = screen.tiledMap.getLayers().get("Coleiras");
//		
//		BodyDef bDef = new BodyDef();
//		FixtureDef fDef = new FixtureDef();
//		
//		for(MapObject mo : layer.getObjects()) {
//			
//			bDef.type = BodyType.StaticBody;
//			
//			float x = (float) mo.getProperties().get("x") / PPM;
//			float y = (float) mo.getProperties().get("y") / PPM;
//			
//			bDef.position.set(x, y);
//			CircleShape cShape = new CircleShape();
//			cShape.setRadius(8 / PPM);
//			
//			fDef.shape = cShape;
//			fDef.isSensor = true;
//			fDef.filter.categoryBits = B2dVariaveis.BIT_COLEIRAS;
//			fDef.filter.maskBits = B2dVariaveis.BIT_DOGGIE;
//			
//			Body body = world.createBody(bDef);
//			body.createFixture(fDef).setUserData("coleiras");
//			
////			Coleiras c = Coleiras(world.createBody(bDef));
////			coleiras.add(c);
//			
//			body.setUserData(this);
//			
//		}
//		
//	}
//	
//}
