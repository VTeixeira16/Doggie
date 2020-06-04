package com.newhorizon.doggie.threads;

import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;

public class DoggieThread extends Thread{
	
	Doggie doggie;
	
	public DoggieThread(Doggie doggie) {
		this.doggie = doggie;		
	}
	
	public void run() {
//		FUTURAMENTE, O ESTADO DEVERÁ SER ALTERADO PARA GAMEOVER, VISTO QUE HÁ ANIMAÇÕES DE MORTE
		if(doggie.estadoAtual != Estado.MORTO)
		{
			System.out.println("Thread Doggie");
//			System.out.println("Estado atual" + doggie.estadoAtual);
			doggie.render(GameClass.sb);			
		}
	}
}
