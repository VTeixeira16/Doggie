package com.newhorizon.doggie.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ListenerContatos implements ContactListener {
	
	private int numDoggieGround;

	//Chamado quando 2 fixtures colidem
	public void beginContact(Contact contact)
	{
//		Gdx.app.log("log", "Colisão começou!");
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("footDoggie"))
		{
			numDoggieGround ++;
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("footDoggie"))
		{
			numDoggieGround ++;
		}
				
//		System.out.println(fixtureA.getUserData() + " , " + fixtureB.getUserData());
		
//		fixtureA.getUserData();
	}

	//Chamado quando 2 fixtures deixam de colidir
	public void endContact(Contact contact) 
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("footDoggie"))
		{
			numDoggieGround --;
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("footDoggie"))
		{
			numDoggieGround --;
		}
	}
	
	public boolean isPlayerOnGround() {return numDoggieGround > 0;}

	public void preSolve(Contact contact, Manifold oldManifold) {}

	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
}
