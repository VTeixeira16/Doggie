package com.newhorizon.doggie.telas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.newhorizon.doggie.GameClass;

public class MenuScreen extends ApplicationAdapter implements Screen{
	
	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Label outputLabel;
    private Skin skin;
    
    public int btnJogar;
    public int btnAdote;
    public int btnCreditos;
    public int btnSair;
	
    public static final int Help_Guides = 12;
    public static final int row_height = Gdx.graphics.getHeight() / 12;
    public static final int col_width = Gdx.graphics.getWidth() / 12;
	
	
	public MenuScreen (GameClass game) {
		this.game = game;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("skins/plain-james/skin/plain-james-ui.json"));

        //ImageTextButton
        ImageTextButton btnJogar = new ImageTextButton("Jogar", skin);
        btnJogar.setSize(col_width*3,(float)(row_height));
        // Pode ser útil para adicionar um logo do cachorro ao lado do botão jogar
        btnJogar.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("OutGame/dogLogo.png"))));
        btnJogar.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("OutGame/dogLogo.png"))));
        btnJogar.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*3);
        btnJogar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	game.setScreen(new IntroGameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnJogar);
        
        ImageTextButton btnCreditos = new ImageTextButton("Creditos", skin);
        btnCreditos.setSize(col_width*3,(float)(row_height));
        // Pode ser útil para adicionar um logo do cachorro ao lado do botão jogar
        btnCreditos.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*5);
        btnCreditos.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	game.setScreen(new CreditosScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnCreditos);
        
        ImageTextButton btnAdote = new ImageTextButton("Quero Adotar!", skin);
        btnAdote.setSize(col_width*3,(float)(row_height));
        btnAdote.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*7);
        btnAdote.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	game.setScreen(new AdoteScreen(game));
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnAdote);
        
        //ImageTextButton
        ImageTextButton btnSair = new ImageTextButton("Sair", skin);
        btnSair.setSize(col_width*3,(float)(row_height));
        btnSair.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*9);
        btnSair.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	System.exit(0);	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnSair);
        
	
		
	}

	@Override
	public void render(float delta) {
		
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
		
		
//		if(Gdx.input.isButtonJustPressed(menuLis.btnJogar))
//		{
//			System.out.println("Jogar pressionado");
////			game.setScreen(new PlayScreen(game));
//			
//		}
//		else if(Gdx.input.isButtonJustPressed(menuLis.btnSair))
//		{
//			System.out.println("Sair pressionado");
////			System.exit(0);	
//		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		stage.dispose();
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
	}

}
