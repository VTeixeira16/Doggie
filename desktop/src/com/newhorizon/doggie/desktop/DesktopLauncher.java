package com.newhorizon.doggie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.newhorizon.doggie.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = GameClass.GAMENAME;
		config.width = GameClass.V_WIDTH;
		config.height = GameClass.V_HEIGHT;
		config.resizable = false;
		
		// Aqui se controla qual classe � carregada primeiro
		new LwjglApplication(new GameClass(), config); 
	}
}
