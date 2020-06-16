package com.newhorizon.doggie.telas;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.threads.ThreadMusica;

public class IntroGameScreen extends ApplicationAdapter implements Screen{

	public GameClass game;
	public Screen screen;
	
	private float introTimer;
	private String texto0 = "Ainda pequeno, Doggie foi abandonado na Rua.";
	private String texto1 = "Teve que enfrentar a fome, a solidão, o frio,";
	private String texto2 = "brigar com outros animais abandonados...";
	private String texto3 = "... E lidar com a maldade humana.";
	private String texto4 = "Um dia, seus amigos lhe contaram uma história que mudou";
	private String texto5 = "a sua vida, uma lenda sobre um local onde animal nenhum";
	private String texto6 = "passaria fome ou teria qualquer sofrimento...";
	private String texto7 = "Este local seria 'A Terra Sem Males'.";
	private String texto8 = "Mas para chegar neste local, Doggie precisará sobreviver...";
	private String texto9 = "E é aqui que iniciamos a sua jornada.";
	
	
	private ThreadMusica threadMusica;
	
//	
	ArrayList<String> textos;
	
			
	
	public IntroGameScreen (GameClass game) {
		this.game = game;
		introTimer = 0;
		
		textos = new ArrayList();
		textos.add(texto0);
		textos.add(texto1);
		textos.add(texto2);
		textos.add(texto3);
		textos.add(texto4);
		textos.add(texto5);
		textos.add(texto6);
		textos.add(texto7);
		textos.add(texto8);
		textos.add(texto9);
		
		game.telaAtual = "IntroGame";
		threadMusica = new ThreadMusica(this.game);
		
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		threadMusica.start();
		
		
	}
	@Override
	public void render(float delta) {
		introTimer += delta;
//		Gdx.app.log("INTRO", "introTimer" + introTimer);
		
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.sb.begin();

        if(introTimer > 1 && introTimer < 18)
    			GameClass.fontIntro.draw(game.sb, textos.get(0) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 40);
        if(introTimer > 3 && introTimer < 18)
    			GameClass.fontIntro.draw(game.sb, textos.get(1) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 70);
        if(introTimer > 5 && introTimer < 18)
        	GameClass.fontIntro.draw(game.sb, textos.get(2) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 100);
        if(introTimer > 7 && introTimer < 18)
        	GameClass.fontIntro.draw(game.sb, textos.get(3) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 130);
        if(introTimer > 9 && introTimer < 18)
        	GameClass.fontIntro.draw(game.sb, textos.get(4) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 180);
        if(introTimer > 11 && introTimer < 18)
        	GameClass.fontIntro.draw(game.sb, textos.get(5) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 210);
        if(introTimer > 13 && introTimer < 18)
        	GameClass.fontIntro.draw(game.sb, textos.get(6) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 240);
        if(introTimer > 19)
        	GameClass.fontIntro.draw(game.sb, textos.get(7) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 270);
        if(introTimer > 22)
        	GameClass.fontIntro.draw(game.sb, textos.get(8) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 300);
        if(introTimer > 25)
        	GameClass.fontIntro.draw(game.sb, textos.get(9) , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 330);
        	

        game.sb.end();
		
        if(introTimer > 28)
        {
        	threadMusica.musica.stop();
        	game.telaAtual = "Null";	
        	game.setScreen(new PlayScreen(game));
        }
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
