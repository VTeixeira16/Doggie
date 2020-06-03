package com.newhorizon.doggie.threads;

import static com.newhorizon.doggie.tools.B2dVariaveis.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;

public class DoggieThread extends Thread{
	
	Doggie doggie;
	
	public SpriteBatch sb;

	public DoggieThread(Doggie doggie) {
		this.doggie = doggie;
		
		sb = new SpriteBatch();
		
		
		
	}
	
	public void run() {
		if(doggie.estadoAtual != Estado.MORTO)
		{
			System.out.println("Thread rodando");
//			System.out.println("Estado atual" + doggie.estadoAtual);
			doggie.render(GameClass.sb);			
		}
	}

}
