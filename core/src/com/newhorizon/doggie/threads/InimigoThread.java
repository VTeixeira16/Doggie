package com.newhorizon.doggie.threads;

import com.newhorizon.doggie.GameClass;
import com.newhorizon.doggie.sprites.Inimigos;
import com.newhorizon.doggie.sprites.Inimigos.EstadoInimigos;
import com.newhorizon.doggie.sprites.Doggie.Estado;

public class InimigoThread {

	private Inimigos inimigo;
	
	public InimigoThread(Inimigos inimigo) {
		this.inimigo = inimigo;		
	}
	
	public void run() {
//		FUTURAMENTE, O ESTADO DEVERÁ SER ALTERADO PARA GAMEOVER, VISTO QUE HÁ ANIMAÇÕES DE MORTE
		if(inimigo.estadoAtual != EstadoInimigos.MORTO)
		{
			System.out.println("Thread Inimigo rodando");
			inimigo.render(GameClass.sb);			
		}
	}
	
}
