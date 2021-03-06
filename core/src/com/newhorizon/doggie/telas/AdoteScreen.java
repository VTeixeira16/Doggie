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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.newhorizon.doggie.GameClass;

public class AdoteScreen extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
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
    private String texto10;
    private String txtVoltar;
    private String txtContatos;

	public AdoteScreen (GameClass game) {
		this.game = game;
		sb = new SpriteBatch();
		
		if(game.Language == "Portugues")
		{	
			txtContatos = "Contatos para adoção";
			texto10 = "Adote um animalzinho, leve amor para sua casa e \n ganhe Doggie Coins para utilizar no jogo!!!";
			txtVoltar = "Voltar";

		}
		else if(game.Language == "Ingles")
		{
			txtContatos = "Contacts for adoption";
			texto10 = "Adopt a pet, bring love to your home and \n earn Doggie Coins to use in the game!!!";
			txtVoltar = "Return";
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
		
		if(game.Language == "Portugues")
		{	
			GameClass.fontMenuP.draw(sb, txtContatos, Gdx.graphics.getWidth() / 3.5f  , Gdx.graphics.getHeight() - 40);
			
			GameClass.fontMenuM.draw(sb, texto10, Gdx.graphics.getWidth() / 6, Gdx.graphics.getHeight() - 440);

		}
		else if(game.Language == "Ingles")
		{
			GameClass.fontMenuP.draw(sb, txtContatos, Gdx.graphics.getWidth() / 3.5f  , Gdx.graphics.getHeight() - 40);
			
			GameClass.fontMenuM.draw(sb, texto10, Gdx.graphics.getWidth() / 4.5f, Gdx.graphics.getHeight() - 440);

		}
		
		GameClass.fontMenu.draw(sb, texto1 + "\n" + texto2 + "\n" + texto3 + "\n" + texto4 + "\n" + texto5 + "\n" + texto6 
				+ "\n" + texto7 + "\n" + texto8 + "\n" + texto9 + "\n",
				Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 140);
				
		sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		sb.dispose();
		
	}

}
