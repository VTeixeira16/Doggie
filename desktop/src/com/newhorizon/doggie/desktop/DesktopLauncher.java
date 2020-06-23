package com.newhorizon.doggie.desktop;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Files.FileType;
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
		config.addIcon("OutGame/dogLogo.png", FileType.Internal);
//		config.fullscreen = true;
		config.vSyncEnabled = true;
		// Aqui se controla qual classe é carregada primeiro
		new LwjglApplication(new GameClass(), config); 
	}
}
