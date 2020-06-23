package com.newhorizon.doggie.threads;

import com.badlogic.gdx.Gdx;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Doggie.Estado;

public class DoggieThread extends Thread {
	
	private Doggie doggie;
	
	public DoggieThread(Doggie doggie) {
		this.doggie = doggie;		
		System.out.println("Thread Iniciada");
	}
	
	public void run() {

//		FUTURAMENTE, O ESTADO DEVERÁ SER ALTERADO PARA GAMEOVER, VISTO QUE HÁ ANIMAÇÕES DE MORTE

		while(doggie.estadoAtual != Estado.MORTO)
		{
//			{
				System.out.println("Doggie thread");
////						System.out.println("Timer:" + doggie.getPosition().x);
//				doggie.render(GameClass.sb);		
				doggie.update(Gdx.graphics.getDeltaTime());
//			}
				
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		
		
//			System.out.println("Estado atual" + doggie.estadoAtual);
//			Gdx.app.postRunnable(new Runnable(){

//				public void run() {
//				}
//	          });
//		}
//		IMPLEMENTAÇÃO PROVISÓRIA ATÉ RESOLVER PROBLEMAS DE MULTITHREADS NA GDX
//		A IMPLEMENTAÇÃO ABAIXO ESTÁ INCORRETA E FUNCIONANDO COMO UM RENDER NORMAL E SERVE APENAS PARA TESTAR O JOGO.
//		if(doggie.estadoAtual != Estado.MORTO)
//		{
//			doggie.render(GameClass.sb);			
////			System.out.println("Thread Doggie rodando");
//		}
	}
}
