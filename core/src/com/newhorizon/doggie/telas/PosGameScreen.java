package com.newhorizon.doggie.telas;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;

public class PosGameScreen extends ApplicationAdapter implements Screen{

	public GameClass game;
	public Screen screen;
	
	SpriteBatch sb;
	
	private float posGameTimer;
	private String texto0;
	private String texto1;
	private String texto2;
	private String texto3;
	private String texto4;
	private String texto5;
	private String texto6;
	private String texto7;
	private String texto8;

	public PosGameScreen (GameClass game) {
		this.game = game;
		posGameTimer = 0;
		
		if(game.faseAtual == 1 && game.Language == "Portugues")
		{
			texto0 = "Apesar de adentrar em local hostial, Doggie continuou sua";
			texto1 = "jornada. Estava anoitecendo, e as ruas se tornavam cada ";
			texto2 = "vez mais desconhecidas... Ele sentia muito frio, fome ";
			texto3 = "e saudades de seus amigos. ";
			texto4 = "Mesmo com tudo isso, Doggie levantou o focinho e decidiu";
			texto5 = "seguir em frente!";
			
			texto6 = "Carregando próxima fase.";
			texto7 = "Carregando próxima fase..";
			texto8 = "Carregando próxima fase...";
		}
		else if(game.faseAtual == 1 && game.Language == "Ingles")
		{
			texto0 = "Despite entering a hostile location, Doggie continued his";
			texto1 = "journey. It was getting dark, and the streets became  ";
			texto2 = "increasingly unknown... He felt very cold, hungry and missed ";
			texto3 = "his friends.";
			texto4 = "Even with all this, Doggie lifted his nose and decided to";
			texto5 = "move on!";
			
			texto6 = "Loading next level.";
			texto7 = "Loading next level..";
			texto8 = "Loading next level...";
		}
		else if(game.faseAtual == 2 && game.Language == "Portugues")
		{
			texto0 = "Naquela noite, Doggie continuou sua jornada. Após";
			texto1 = "enfrentar tantas dificuldades, ele estava muito orgulhoso";
			texto2 = "de si mesmo. Mas para chegar na \"Terra sem Males\"";
			texto3 = "era preciso muito mais que apenas sair de sua cidade...";			
			
			texto4 = "... Porém, isto ficará para uma próxima história!";
			texto5 = "Muito obrigado, por jogar o nosso jogo.";
			
			texto6 = "Redirecionando ao menu principal em: ";
		}
		else if(game.faseAtual == 2 && game.Language == "Ingles")
		{
			texto0 = "That night, Doggie continued his journey. After facing";
			texto1 = "so many difficulties, he was very proud of himself.";
			texto2 = "But getting to \"The Land Without Evil\" needed much more";
			texto3 = "than. just leaving your city ...";			
			
			texto4 = "... However, this will be for a next story!";
			texto5 = "Thank you very much for playing our game.";
			
			texto6 = "Redirecting to the main menu at: ";
			
		}
		
		game.telaAtual = "Null";	
		sb = new SpriteBatch();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void render(float delta) {
		posGameTimer += delta;
//		Gdx.app.log("INTRO", "introTimer" + introTimer);
		
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	
        
        if(game.faseAtual == 1 && posGameTimer > 0.2f)
		{
        	game.telaAtual = "PosGame1";
		}
		else if(game.faseAtual == 2 && posGameTimer > 0.2f)
		{
			game.telaAtual = "PosGame2";
		}
        
        sb.begin();
        
        if(game.faseAtual == 1 )
        {
            if(posGameTimer > 1 && posGameTimer < 13)
        			GameClass.fontIntro.draw(sb, texto0 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 40);
            if(posGameTimer > 3 && posGameTimer < 13)
        			GameClass.fontIntro.draw(sb, texto1 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 70);
            if(posGameTimer > 5 && posGameTimer < 13)
            	GameClass.fontIntro.draw(sb, texto2 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 100);
            if(posGameTimer > 7 && posGameTimer < 13)
            	GameClass.fontIntro.draw(sb, texto3 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 130);
            if(posGameTimer > 9 && posGameTimer < 13)
            	GameClass.fontIntro.draw(sb, texto4 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 180);
            if(posGameTimer > 11 && posGameTimer < 13)
            	GameClass.fontIntro.draw(sb, texto5 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 210);
            
            if(posGameTimer > 13 &&  posGameTimer > 13.5f)
            	GameClass.fontIntro.draw(sb, texto6 , Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 300);
            if(posGameTimer > 13.5f &&  posGameTimer > 14)
            	GameClass.fontIntro.draw(sb, texto7 , Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 300);
          	if(posGameTimer > 14 &&  posGameTimer > 14.5f)
          		GameClass.fontIntro.draw(sb, texto8 , Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - 300);
        	
        }
        else if (game.faseAtual == 2)
        {
    	if(posGameTimer > 1 && posGameTimer < 9)
    			GameClass.fontIntro.draw(sb, texto0 , Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() - 40);
        if(posGameTimer > 3 && posGameTimer < 9)
    			GameClass.fontIntro.draw(sb, texto1 , Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() - 70);
        if(posGameTimer > 5 && posGameTimer < 9)
        	GameClass.fontIntro.draw(sb, texto2 , Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() - 100);
        if(posGameTimer > 7 && posGameTimer < 9)
        	GameClass.fontIntro.draw(sb, texto3 , Gdx.graphics.getWidth() / 13, Gdx.graphics.getHeight() - 130);
        if(posGameTimer > 9.5f && posGameTimer < 14.5f)
			GameClass.fontIntro.draw(sb, texto4 , Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() /2 + 15);
        if(posGameTimer > 11.5f && posGameTimer < 14.5f)
        	GameClass.fontIntro.draw(sb, texto5 , Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() / 2 - 15);
        if(posGameTimer > 11 && posGameTimer < 16)
        	GameClass.fontIntro.draw(sb, texto6 + (16 - (int)posGameTimer), Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() /2 - 220);
        	
        }

        sb.end();
		
        if((posGameTimer > 14.5f || (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) && game.faseAtual == 1)
        {
        	game.telaAtual = "Null";
        	game.faseAtual = 2;
        	game.setScreen(new PlayScreen(game));
        }
        else if ((posGameTimer > 16 || (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) && game.faseAtual == 2)
        {
        	game.telaAtual = "Null";
        	game.setScreen(new MenuScreen(game));
        }
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public void dispose() {
		sb.dispose();
	}
}
