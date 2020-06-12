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

import static com.newhorizon.doggie.telas.MenuScreen.col_width;
import static com.newhorizon.doggie.telas.MenuScreen.Help_Guides;
import static com.newhorizon.doggie.telas.MenuScreen.row_height;

public class CreditosScreen extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Label outputLabel;
    private Skin skin;
    
	String texto1 = "Programação, Game Design, Roteiro e Level Design: Victor Teixeira.";
	String texto2 = "Arte e sonoplastia: Gabriel Ventura.";
	String texto3 = "Assets utilizados para conclusão do projeto:";
	
	String texto4 = "Tiles: (Cenário inicial)";
	String texto5 = "https://www.gameart2d.com/free-platformer-game-tileset.html";
	String texto6 = "Bricks: (tijolo)";
	String texto7 = "https://opengameart.org/content/bricks-tiled-texture-64x64";
	String texto8 = "Cidade:";
	String texto9 = "https://craftpix.net/file-licenses/";
	String texto10 = "Fundo do jogo (céu)";
	String texto11 = "https://opengameart.org/content/ocean-background";
	String texto12 = "Osso:";
	String texto13 = "https://opengameart.org/content/pixel-art-femur";
	String texto14 = "Logo:";
	String texto15 = "https://www.designevo.com/res/templates/thumb_small/brown-and-white-dog.png";
	String texto16 = "Skins:";
	String texto17 = "https://github.com/czyzby/gdx-skins";
	
	
	public CreditosScreen (GameClass game) {
		this.game = game;
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        
        skin = new Skin(Gdx.files.internal("skins/plain-james/skin/plain-james-ui.json"));

        //ImageTextButton
        ImageTextButton btnVoltar = new ImageTextButton("Voltar", skin);
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
        
		game.sb.begin();
		
		GameClass.fontMenuP.draw(game.sb, "Créditos", Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);
		GameClass.fontMenu.draw(game.sb, texto1 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 120);
		GameClass.fontMenu.draw(game.sb, texto2, Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 160);
		GameClass.fontMenuM.draw(game.sb, texto3, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight() - 200);
		
		GameClass.fontMenu.draw(game.sb, texto4 + "\n" + texto5 + "\n" + texto6 + "\n" + texto7 + "\n" + texto8 + "\n" + texto9
				+ "\n" + texto10 + "\n" + texto11 + "\n" + texto12 + "\n" + texto13 + "\n" + texto14 
				+ "\n" + texto15 + "\n" + texto16 + "\n" + texto17, 
				Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 240);
		
		game.sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		
	}
}
