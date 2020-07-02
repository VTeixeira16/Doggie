package com.newhorizon.doggie.threads;

import com.badlogic.gdx.Gdx;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;
import com.newhorizon.doggie.telas.PlayScreen;

public class InputsThreads extends Thread{
	
	private PlayScreen screen;
	private Doggie doggie;

	public InputsThreads(PlayScreen screen, Doggie doggie)
	{
		this.screen = screen;
		this.doggie = doggie;
	}
	
	public void run()
	{
		while(doggie.estadoAtual != Estado.MORTO)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread ativa");
			screen.handleInput(screen.deltaTime);
		}
	}
}
