package com.newhorizon.doggie.tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ManagerCenas {
	
	private static Screen currentScreen;
	private Game game;
	
	public static void setScreen(Screen screen, Game game){
//        if(currentScreen != null){
//            currentScreen.dispose();
//            System.out.println("Screen disposed");
//        }
        currentScreen = screen;
        game.setScreen(currentScreen);
    }
	
	

}
