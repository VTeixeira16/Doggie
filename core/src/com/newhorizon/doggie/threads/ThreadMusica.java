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
				musica = GameClass.manager.get("bin/main/sons/menu/DoggieMusicaMenu.wav", Music.class);
				musica.play();
				
				try {
					Thread.sleep(10);
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
