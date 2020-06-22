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

public class SobreJogo extends ApplicationAdapter implements Screen {

	public GameClass game;
	public Screen screen;
	
    private Stage stage;
    private Skin skin;
    
    SpriteBatch sb;
    
	String texto1;
	String texto2;
	
	String txtVoltar;
	String txtCreditos;
	
	
	public SobreJogo (GameClass game) {
		this.game = game;
		sb = new SpriteBatch();
		
		if(game.Language == "Portugues")
		{	
			txtCreditos = "Sobre o jogo";
			texto1 = "Jogo desenvolvido por Victor Teixeira e Gabriel Ventura, estudantes de \n"
					+ "Jogos Digitais na Fatec Carapicuíba. O jogo possui o objetivo de divertir \n"
					+ "trazendo uma reflexão sobre as dificuldades enfrentadas por animais \n "
					+ "abandonados.";
			
			texto2 = "Projeto desenvolvido como Trabalho de conclusão do 3° semestre. \n"
					+ "Desenvolvido em Java utilizando a biblioteca Libgdx.";

			txtVoltar = "Voltar";
		}
		else if(game.Language == "Ingles")
		{
			txtCreditos = "About the Game";
			texto1 = "Game developed by Victor Teixeira and Gabriel Ventura, students of Digital \n"
					+ "Games at Fatec Carapicu�ba. The game aims to have fun bringing a \n"
					+ "reflection on the difficulties faced by abandoned animals.";
			
			texto2 = "Project developed as work to conclude the 3rd semester. Developed in \n"
					+ "Java using the Libgdx library.";
			
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
		
		GameClass.fontMenuP.draw(sb, txtCreditos , Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight() - 40);
		
		GameClass.fontMenu.draw(sb, texto1 + "\n \n" + texto2, Gdx.graphics.getWidth() / 24, Gdx.graphics.getHeight() - 120);

		sb.end();
		
	}

	@Override
	public void hide() {
		stage.dispose();
		sb.dispose();
		
	}
}
