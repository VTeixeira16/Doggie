package com.newhorizon.doggie.threads;

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
//		while(doggie.estadoAtual != Estado.MORTO)
//		{
//	
//			System.out.println("Thread Doggie rodando");
////			System.out.println("Estado atual" + doggie.estadoAtual);
//			Gdx.app.postRunnable(new Runnable(){
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					System.out.println("RUN RUNNABLE");
//					doggie.render(GameClass.sb);		
//				}
//				
//	               //Type thread code here. Will be seperate to main thread but part of it at the same time
//	          });
//		}
		
//		IMPLEMENTA��O PROVIS�RIA AT� RESOLVER PROBLEMAS DE MULTITHREADS NA GDX
//		A IMPLEMENTA��O ABAIXO EST� INCORRETA E FUNCIONANDO COMO UM RENDER NORMAL E SERVE APENAS PARA TESTAR O JOGO.
		
		if(doggie.estadoAtual != Estado.MORTO)
		{
			doggie.render(GameClass.sb);			
//			System.out.println("Thread Doggie rodando");
		}
		
	}
}
