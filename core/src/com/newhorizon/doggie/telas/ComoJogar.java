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

public class ComoJogar extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Label outputLabel;
    private Skin skin;
    
    SpriteBatch sb;
    
	String texto1;
	String texto2;
	String texto3;
	String texto4;
	
	String txtVoltar;
	String txtCreditos;
	
	
	public ComoJogar (GameClass game) {
		this.game = game;
		sb = new SpriteBatch();
		
		if(game.Language == "Portugues")
		{	
			txtCreditos = "Como Jogar";
			texto1 = "Movimentação: Utilize as SETAS do mouse para movimentação e ESPAÇO \n"
					+ "para pular. \n \n"
					+ "O personagem tem como objetivo alcançar o fim de cada fase. \n"
					+ "Para derrotar um inimigo, é necessário pular em cima da cabeça.";
			
			texto2 = "O Doggie perde uma vida cada vez que toca no corpo de um inimigo ou \n"
					+ "come um osso envenenado (levemente esverdeado).";
			
			texto3 = "O jogo se inicia com 3 vidas e se encerra quando o jogador possui suas \n"
					+ "vidas zeradas.";
			
			texto4 = " O jogador ganha uma nova vida a cada 10 ossos coletados.";

			txtVoltar = "Voltar";
		}
		else if(game.Language == "Ingles")
		{
			txtCreditos = "About the Game";
			texto1 = "Movement: Use the ARROW KEYS to move and SPACE to jump. \n"
					+ "The character aims to reach the end of each stage. \n"
					+ "To defeat an enemy, it is necessary to jump over the head.";
			
			texto2 = "Doggie loses a life each time he touches an enemy's body or eats a bone \n"
					+ "poisoned (slightly greenish).";
			
			texto3 = "The game starts with 3 lives and ends when the player has their lives \n"
					+ "zeroed.";
			
			texto4 = " The player gains a new life for every 10 bones collected.";
			
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
			GameClass.fontMenuP.draw(sb, txtCreditos , Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);

		}
		else if(game.Language == "Ingles")
		{
			GameClass.fontMenuP.draw(sb, txtCreditos , Gdx.graphics.getWidth() / 2.5f - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);

		}
		
		GameClass.fontMenu.draw(sb, texto1 + "\n \n" + texto2 + "\n \n" + texto3 + texto4, Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 120);

		
		sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		sb.dispose();
		
	}
}
