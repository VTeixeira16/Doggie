package com.newhorizon.doggie.telas;

import static com.newhorizon.doggie.telas.MenuScreen.col_width;
import static com.newhorizon.doggie.telas.MenuScreen.row_height;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.newhorizon.doggie.GameClass;

public class CreditosScreen extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Label outputLabel;
    private Skin skin;
    
    SpriteBatch sb;
    
	String texto1;
	String texto2;
	String texto3;
	String textoDuque;
	
	String txtVoltar;
	String txtCreditos;
	
	String texto4 = "https://www.gameart2d.com/free-platformer-game-tileset.html";
	String texto5 = "https://opengameart.org/content/bricks-tiled-texture-64x64";
	String texto6 = "https://craftpix.net/file-licenses/";
	String texto7 = "https://opengameart.org/content/ocean-background";
	String texto8 = "https://opengameart.org/content/pixel-art-femur";
	String texto9 = "https://www.designevo.com/res/templates/thumb_small/brown-and-white-dog.png";
	String texto10 = "https://github.com/czyzby/gdx-skins";
	
	
	public CreditosScreen (GameClass game) {
		this.game = game;
		sb = new SpriteBatch();
		
		if(game.Language == "Portugues")
		{	
			txtVoltar = "Voltar";
			texto1 = "Programação, Game Design, Roteiro e Level Design: Victor Teixeira.";
			texto2 = "Arte e Sound Desing: Gabriel Ventura.";
			texto3 = "Assets utilizados para conclusão do projeto:";
			textoDuque = "Apoio ao desenvolvimento da arte grafica: Narriman Duque";
			txtCreditos = "Créditos";
		}
		else if(game.Language == "Ingles")
		{
			txtVoltar = "Return";
			texto1 = "Programming, Game Design, Screenplay and Level Design: Victor Teixeira.";
			texto2 = "Art and sound design: Gabriel Ventura.";
			texto3 = "Assets used in the project:";
			textoDuque = "Support for the development of graphic art: Narriman Duque";	
			txtCreditos = "Credits";
		}
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("skins/plain-james/skin/plain-james-ui.json"));

        //ImageTextButton
        ImageTextButton btnVoltar = new ImageTextButton(txtVoltar, skin);
        btnVoltar.setSize(col_width*2,(float)(row_height));
        btnVoltar.setPosition(Gdx.graphics.getWidth() / 1.2f - (col_width * 1),Gdx.graphics.getHeight()-row_height*11.5f);
        btnVoltar.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	game.setScreen(new MenuScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {return true;}
        });
        stage.addActor(btnVoltar);
		
	}

	@Override
	public void render(float delta) {
		
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        
		sb.begin();
		
		GameClass.fontMenuP.draw(sb, txtCreditos , Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);
		
		GameClass.fontMenu.draw(sb, texto1 , Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 120);
		GameClass.fontMenu.draw(sb, texto2, Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 145);
		GameClass.fontMenu.draw(sb, textoDuque, Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 170);
		
		GameClass.fontMenuM.draw(sb, texto3, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 210);
		
		GameClass.fontMenu.draw(sb, texto4 + "\n" + texto5 + "\n" + texto6 + "\n" + texto7 + "\n" + texto8 + "\n" + texto9
				+ "\n" + texto10 ,
				Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 250);
		
		sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		sb.dispose();
		
	}
}
