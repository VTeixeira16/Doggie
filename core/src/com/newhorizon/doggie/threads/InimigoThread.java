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
//		FUTURAMENTE, O ESTADO DEVERÁ SER ALTERADO PARA GAMEOVER, VISTO QUE HÁ ANIMAÇÕES DE MORTE
//		while(inimigo.estadoAtual != EstadoInimigos.MORTO)
//		{
//			inimigo.render(GameClass.sb);			
//			System.out.println("Thread Inimigo rodando");
//		}
		
//		IMPLEMENTAÇÃO PROVISÓRIA ATÉ RESOLVER PROBLEMAS DE MULTITHREADS NA GDX
//		A IMPLEMENTAÇÃO ABAIXO ESTÁ INCORRETA E FUNCIONANDO COMO UM RENDER NORMAL E SERVE APENAS PARA TESTAR O JOGO.
		
		if(inimigo.estadoAtual != EstadoInimigos.MORTO)
		{
			inimigo.render(GameClass.sb);			
//			System.out.println("Thread Inimigo rodando");
		}
	}
	
}
