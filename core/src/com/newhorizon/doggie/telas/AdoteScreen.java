package com.newhorizon.doggie.telas;

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
import static com.newhorizon.doggie.telas.MenuScreen.col_width;
import static com.newhorizon.doggie.telas.MenuScreen.Help_Guides;
import static com.newhorizon.doggie.telas.MenuScreen.row_height;

public class AdoteScreen extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Label outputLabel;
    private Skin skin;
    
    SpriteBatch sb;
    
    private String texto1 = "www.naturezaemforma.org.br/como-adotar/";
    private String texto2 = "www.adoteumgatinho.org.br/";
    private String texto3 = "www.toca.gatinhos.nom.br/";
    private String texto4 = "www.patinhasunidas.com.br/adotar";
    private String texto5 = "www.clubedosviralatas.org.br/adote-a-alegria";
    private String texto6 = "www.apaa.com.br/sample-page/";
    private String texto7 = "www.adoteumfocinho.com.br";
    private String texto8 = "www.ilm.org.br/";
    private String texto9 = "www.ajudaanimal.wixsite.com/ajudaanimal";
    
    private String texto10 = "Adote um animalzinho, leve amor para sua casa e \n ganhe Doggie Coins para utilizar no jogo!!!";

	public AdoteScreen (GameClass game) {
		this.game = game;
		sb = new SpriteBatch();
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
        
		sb.begin();
		
		GameClass.fontMenuP.draw(sb, "Contatos para adoção", Gdx.graphics.getWidth() / 3 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);
		
		GameClass.fontMenu.draw(sb, texto1 + "\n" + texto2 + "\n" + texto3 + "\n" + texto4 + "\n" + texto5 + "\n" + texto6 
				+ "\n" + texto7 + "\n" + texto8 + "\n" + texto9 + "\n",
				Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 140);
		
		GameClass.fontMenuM.draw(sb, texto10, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight() - 440);
	
//		GameClass.fontMenu.draw(game.sb, "Creditos", Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 40);
		
		sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		sb.dispose();
		
	}

}
