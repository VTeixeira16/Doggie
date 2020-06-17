package com.newhorizon.doggie.threads;

import com.badlogic.gdx.audio.Music;
import com.newhorizon.doggie.GameClass;


public class ThreadMusica extends Thread{
	
	String url;
	GameClass game;
	public Music musica;
	
	
	
//	public ThreadMusica(String url) {
//		this.url = url;			 
//
//	}
	
	public ThreadMusica(GameClass game) {
		this.game = game;			 

	}
	
	public void run() {
		while(true)
		{
			if(game.telaAtual == "Menu")
			{
				musica = GameClass.manager.get("sons/menu/DoggieMusicaMenu.mp3", Music.class);
				musica.setVolume(1);
				musica.play();
				musica.setLooping(true);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(game.telaAtual == "IntroGame")
			{
				musica = GameClass.manager.get("sons/musicas/DoggieMusica03.mp3", Music.class);
				musica.setVolume(1);
				musica.play();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(game.telaAtual == "Fase1")
			{
				musica = GameClass.manager.get("sons/musicas/DoggieMusica01.mp3", Music.class);
				musica.setVolume(0.05f);
				musica.play();
				musica.setLooping(true);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
			
//		musica = GameClass.manager.get(url, Music.class);
//		musica.play();
//		musica.setLooping(true);
		
		}
		
		
	
	}

}
