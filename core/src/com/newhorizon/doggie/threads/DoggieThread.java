package com.newhorizon.doggie.threads;

import com.badlogic.gdx.Gdx;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;

public class DoggieThread extends Thread {
	
	private Doggie doggie;
	
	public DoggieThread(Doggie doggie) {
		this.doggie = doggie;		
	}
	
	public void run() {
//		FUTURAMENTE, O ESTADO DEVER� SER ALTERADO PARA GAMEOVER, VISTO QUE H� ANIMA��ES DE MORTE

		while(doggie.estadoAtual != Estado.MORTO)
		{
			{
//				System.out.println(doggie.flagTimer);
//						System.out.println("Timer:" + doggie.getPosition().x);
//				doggie.render(GameClass.sb);		
//				doggie.update(Gdx.graphics.getDeltaTime());
			}
		}
		
		
	
			System.out.println("Thread Doggie rodando");
//			System.out.println("Estado atual" + doggie.estadoAtual);
//			Gdx.app.postRunnable(new Runnable(){

//				public void run() {
//				}
//	          });
//		}
//		IMPLEMENTA��O PROVIS�RIA AT� RESOLVER PROBLEMAS DE MULTITHREADS NA GDX
//		A IMPLEMENTA��O ABAIXO EST� INCORRETA E FUNCIONANDO COMO UM RENDER NORMAL E SERVE APENAS PARA TESTAR O JOGO.
//		if(doggie.estadoAtual != Estado.MORTO)
//		{
//			doggie.render(GameClass.sb);			
////			System.out.println("Thread Doggie rodando");
//		}
	}
}
