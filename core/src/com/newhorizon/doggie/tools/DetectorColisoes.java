package com.newhorizon.doggie.tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.newhorizon.doggie.sprites.Doggie;
import com.newhorizon.doggie.sprites.Inimigos;

public class DetectorColisoes implements ContactListener{
	
	private int numDoggieGround;
	private Array<Body> bodiesToRemove;
	private Array<Body> bodiesVToRemove;
	
	public DetectorColisoes() 
	{
		super();
		bodiesToRemove = new Array<Body>();
		bodiesVToRemove = new Array<Body>();
	}
	
	//Chamado quando 2 fixtures colidem
	public void beginContact(Contact contact)
	{
		// Estruta de fixture deverá ser trocada pela de category bits
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		int fixA = fixtureA.getFilterData().categoryBits;
		int fixB = fixtureB.getFilterData().categoryBits;
		int fixC = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;
				
		switch(fixC) {
			case B2dVariaveis.BIT_DOGGIE_PES | B2dVariaveis.BIT_INIMIGO_HEAD:	
	            if(fixtureA.getFilterData().categoryBits == B2dVariaveis.BIT_INIMIGO_HEAD)
	            {
                    ((Inimigos)fixtureA.getUserData()).hitOnHead((Doggie) fixtureB.getUserData());
                    ((Doggie)fixtureB.getUserData()).jumpEnemy();
                    ((Doggie)fixtureB.getUserData()).somLatido();;
	            }
	            else
	            {
                    ((Inimigos)fixtureB.getUserData()).hitOnHead((Doggie) fixtureA.getUserData());
                    ((Doggie)fixtureA.getUserData()).jumpEnemy();
                    ((Doggie)fixtureA.getUserData()).somLatido();;
	            }
				break;	
			case B2dVariaveis.BIT_INIMIGO | B2dVariaveis.BIT_OBJETOS:
	            if(fixtureA.getFilterData().categoryBits == B2dVariaveis.BIT_INIMIGO)
                    ((Inimigos)fixtureA.getUserData()).revVelocidade(true, false);
	            else
	            	((Inimigos)fixtureB.getUserData()).revVelocidade(true, false);
				break;
			case B2dVariaveis.BIT_DOGGIE | B2dVariaveis.BIT_INIMIGO:
	            if(fixtureA.getFilterData().categoryBits == B2dVariaveis.BIT_DOGGIE)
	            {
                    ((Doggie)fixtureA.getUserData()).RecebeDano();
                    ((Inimigos)fixtureB.getUserData()).somRosnar();
                    ((Inimigos)fixtureB.getUserData()).revVelocidade(true, false);
                    
	            }
                else
                {
	            	((Doggie)fixtureB.getUserData()).RecebeDano();
	            	((Inimigos)fixtureA.getUserData()).somRosnar();
                    ((Inimigos)fixtureA.getUserData()).revVelocidade(true, false);
                }
				break;
			
				
		}
		
		if(fixA == B2dVariaveis.BIT_DOGGIE_PES || fixB == B2dVariaveis.BIT_DOGGIE_PES)
			numDoggieGround ++;
		 			  
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("ossos"))
		{
			//Remove Ossos
			bodiesToRemove.add(fixtureA.getBody());
			((Doggie)fixtureB.getUserData()).somOsso();
			
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("ossos"))
		{
			//Remove Ossos
			bodiesToRemove.add(fixtureB.getBody());	
			((Doggie)fixtureA.getUserData()).somOsso();
		}		
		
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("ossosVeneno"))
		{
			//Remove Ossos
			bodiesVToRemove.add(fixtureA.getBody());
			((Doggie)fixtureB.getUserData()).somVeneno();

		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("ossosVeneno"))
		{
			//Remove Ossos
			bodiesVToRemove.add(fixtureB.getBody());	
			((Doggie)fixtureA.getUserData()).somVeneno();
		}
	}

	//Chamado quando 2 fixtures deixam de colidir
	public void endContact(Contact contact) 
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		int fixA = fixtureA.getFilterData().categoryBits;
		int fixB = fixtureB.getFilterData().categoryBits;
		int fixC = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;
		
		
		if(fixtureA == null || fixtureB == null) return;
		
		if(fixA == B2dVariaveis.BIT_DOGGIE_PES | fixB == B2dVariaveis.BIT_DOGGIE_PES)
			numDoggieGround --;		
	}
	
	public boolean isPlayerOnGround() {return numDoggieGround > 0;}
	public Array<Body> getBodiesToRemove() { return bodiesToRemove;}
	public Array<Body> getBodiesVToRemove() { return bodiesVToRemove;}
	

	public void preSolve(Contact contact, Manifold oldManifold) {}

	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
