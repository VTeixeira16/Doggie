package com.newhorizon.doggie.telas;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;

public class IntroGameScreen extends ApplicationAdapter implements Screen{

	public GameClass game;
	public Screen screen;
	
	SpriteBatch sb;
	
	private float introTimer;
	private String texto0;
	private String texto1;
	private String texto2;
	private String texto3;
	private String texto4;
	private String texto5;
	private String texto6;
	private String texto7;
	private String texto8;
	private String texto9;
	
	
	
			
	
	public IntroGameScreen (GameClass game) {
		this.game = game;
		introTimer = 0;
		
//		game.Language = "Portugues";
		
		if(game.Language == "Portugues")
		{
		texto0 = "Ainda pequeno, Doggie foi abandonado na Rua.";
		texto1 = "Teve que enfrentar a fome, a solidão, o frio,";
		texto2 = "brigar com outros animais abandonados...";
		texto3 = "... E lidar com a maldade humana.";
		texto4 = "Um dia, seus amigos lhe contaram uma história que mudou";
		texto5 = "a sua vida, uma lenda sobre um local onde animal nenhum";
		texto6 = "passaria fome ou teria qualquer sofrimento...";
		texto7 = "Este local seria \"A Terra Sem Males\".";
		texto8 = "Mas para chegar neste local, Doggie precisa sobreviver...";
		texto9 = "E é aqui que iniciamos a sua jornada.";
		}
		else if(game.Language == "Ingles")
		{
		texto0 = "Still small, Doggie was abandoned on the Street.";
		texto1 = "He had to face hunger, loneliness, cold,";
		texto2 = "fight with other abandoned animals...";
		texto3 = "... and deal with human evil.";
		texto4 = "One day, his friends told him a story that changed";
		texto5 = "his life, a legend about a place where no animal";
		texto6 = "would go hungry or suffer any suffering ...";
		texto7 = "This place would be \"The Land Without Evil\".";
		texto8 = "But to reach this place, Doggie must survive ...";
		texto9 = "And this is where we begin your journey.";
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
		introTimer += delta;
//		Gdx.app.log("INTRO", "introTimer" + introTimer);
		
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(introTimer > 0.2f)
        	game.telaAtual = "IntroGame";
        
        sb.begin();

        if(introTimer > 1 && introTimer < 18)
    			GameClass.fontIntro.draw(sb, texto0 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 40);
        if(introTimer > 3 && introTimer < 18)
    			GameClass.fontIntro.draw(sb, texto1 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 70);
        if(introTimer > 5 && introTimer < 18)
        	GameClass.fontIntro.draw(sb, texto2 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 100);
        if(introTimer > 7 && introTimer < 18)
        	GameClass.fontIntro.draw(sb, texto3 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 130);
        if(introTimer > 9 && introTimer < 18)
        	GameClass.fontIntro.draw(sb, texto4 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 180);
        if(introTimer > 11 && introTimer < 18)
        	GameClass.fontIntro.draw(sb, texto5 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 210);
        if(introTimer > 13 && introTimer < 18)
        	GameClass.fontIntro.draw(sb, texto6 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 240);
        if(introTimer > 19)
        	GameClass.fontIntro.draw(sb, texto7 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 270);
        if(introTimer > 22)
        	GameClass.fontIntro.draw(sb, texto8 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 300);
        if(introTimer > 25)
        	GameClass.fontIntro.draw(sb, texto9 , Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() - 330);
        if(introTimer > 27.5f || (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)))
        	game.telaAtual = "Null";
        	

        sb.end();
		
        if(introTimer > 28 || (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)))
        {
        	game.faseAtual = 1;
        	game.setScreen(new PlayScreen(game));
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
