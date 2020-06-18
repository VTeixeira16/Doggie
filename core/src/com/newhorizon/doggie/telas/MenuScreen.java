package com.newhorizon.doggie.telas;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
    
    private int btnJogar;
    private int btnComoJogar;
    private int btnSobreJogo;
    private int btnCreditos;
    private int btnAdote;
    private int btnSair;
    private int btnIdioma;
    
    private String txtJogar;
    private String txtComoJogar;
    private String txtSobreJogo;
    private String txtCreditos;
    private String txtAdote;
    private String txtSair;
    private String txtIdioma;

    private Sound som;
	
    public static final int Help_Guides = 12;
    public static final int row_height = Gdx.graphics.getHeight() / 12;
    public static final int col_width = Gdx.graphics.getWidth() / 12;
    
    private float menuTimer;
	
	
	public MenuScreen (GameClass game) {

		this.game = game;
		som = GameClass.manager.get("sons/menu/menuClick.mp3", Sound.class);
		
		if(game.Language == "Portugues")
		{
			txtJogar = "Jogar";
			txtSobreJogo = "Sobre o Jogo";
			txtComoJogar = "Como Jogar";
			txtAdote = "Quero adotar";
			txtCreditos = "Créditos";
			txtSair = "Sair";
			txtIdioma = "Idioma";
		}
		else if(game.Language == "Ingles")
		{
			txtJogar = "Play";
			txtAdote = "Adopt a pet";
			txtSobreJogo = "About the Game";
			txtComoJogar = "How to Play";
			txtCreditos = "Credits";
			txtSair = "Exit";
			txtIdioma = "Language";
		}
		
	}

	@Override
	public void show() {
			
		stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("skins/plain-james/skin/plain-james-ui.json"));    
        
        //ImageTextButton
        ImageTextButton btnJogar = new ImageTextButton(txtJogar, skin);
        btnJogar.setSize(col_width*3,(float)(row_height));
        // Pode ser útil para adicionar um logo do cachorro ao lado do botão jogar
        btnJogar.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("OutGame/dogLogo.png"))));
        btnJogar.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("OutGame/dogLogo.png"))));
        btnJogar.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*2.5f);
        btnJogar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	game.telaAtual = "Null";	
            	game.setScreen(new IntroGameScreen(game));
            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnJogar);
        
        ImageTextButton btnComoJogar = new ImageTextButton(txtComoJogar ,skin);
        btnComoJogar.setSize(col_width*3,(float)(row_height));
        btnComoJogar.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*4);
        btnComoJogar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	som.play();
            	game.setScreen(new ComoJogar(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnComoJogar);
        
        ImageTextButton btnSobreJogo = new ImageTextButton(txtSobreJogo ,skin);
        btnSobreJogo.setSize(col_width*3,(float)(row_height));
        btnSobreJogo.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*5.5f);
        btnSobreJogo.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	som.play();
            	game.setScreen(new SobreJogo(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });        
        stage.addActor(btnSobreJogo);
        
        ImageTextButton btnCreditos = new ImageTextButton(txtCreditos ,skin);
        btnCreditos.setSize(col_width*3,(float)(row_height));
        btnCreditos.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*7);
        btnCreditos.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	som.play();
            	game.setScreen(new CreditosScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnCreditos);
        
        ImageTextButton btnAdote = new ImageTextButton(txtAdote , skin);
        btnAdote.setSize(col_width*3,(float)(row_height));
        btnAdote.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*8.5f);
        btnAdote.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	som.play();
            	game.setScreen(new AdoteScreen(game));

            	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnAdote);
        
        //ImageTextButton
        ImageTextButton btnSair = new ImageTextButton(txtSair , skin);
        btnSair.setSize(col_width*3,(float)(row_height));
        btnSair.setPosition(Gdx.graphics.getWidth() / 2 - (col_width * 1.5f),Gdx.graphics.getHeight()-row_height*10);
        btnSair.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	System.exit(0);	
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnSair);
        
        ImageTextButton btnIdioma = new ImageTextButton(txtIdioma , skin);
        btnIdioma.setSize(col_width*1.5f,(float)(row_height));
        btnIdioma.setPosition(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 100);
        btnIdioma.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	if(game.Language == "Portugues")
            		game.Language = "Ingles";
            	else if(game.Language == "Ingles")
            		game.Language = "Portugues";
            	
            	game.setScreen(new MenuScreen(game));
            	System.out.println(game.Language);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnIdioma);
        
	
		
	}

	@Override
	public void render(float delta) {
		
		menuTimer += delta;	
		
		if(menuTimer > 0.1f)
			game.telaAtual = "Menu";
		
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
