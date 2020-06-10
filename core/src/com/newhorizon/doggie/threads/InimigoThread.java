package com.newhorizon.doggie.threads;

import com.badlogic.gdx.Gdx;
import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Inimigos;
import com.newhorizon.doggie.sprites.Inimigos.EstadoInimigos;

public class InimigoThread extends Thread{

	private Inimigos inimigo;
	
	public InimigoThread(Inimigos inimigo) {
		this.inimigo = inimigo;		
	}
	
	public void run() {
//		FUTURAMENTE, O ESTADO DEVER� SER ALTERADO PARA GAMEOVER, VISTO QUE H� ANIMA��ES DE MORTE
//		while(inimigo.estadoAtual != EstadoInimigos.MORTO)
//		{
//			inimigo.render(GameClass.sb);			
//			System.out.println("Thread Inimigo rodando");
//		}
		
//		IMPLEMENTA��O PROVIS�RIA AT� RESOLVER PROBLEMAS DE MULTITHREADS NA GDX
//		A IMPLEMENTA��O ABAIXO EST� INCORRETA E FUNCIONANDO COMO UM RENDER NORMAL E SERVE APENAS PARA TESTAR O JOGO.
		
		if(inimigo.estadoAtual != EstadoInimigos.MORTO)
		{
			inimigo.render(GameClass.sb);			
//			System.out.println("Thread Inimigo rodando");
		}
	}
	
}
