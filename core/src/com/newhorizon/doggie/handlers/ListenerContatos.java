package com.newhorizon.doggie.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class ListenerContatos implements ContactListener {
	
	private int numDoggieGround;
	private Array<Body> bodiesToRemove;
	
	
	public ListenerContatos() 
	{
		super();
		bodiesToRemove = new Array<Body>();
		
	}
	
	//Chamado quando 2 fixtures colidem
	public void beginContact(Contact contact)
	{
//		Gdx.app.log("log", "Colisão começou!");
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		
		
		if(fixtureA == null || fixtureB == null) return;
		
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("footDoggie"))
		{
			numDoggieGround ++;
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("footDoggie"))
		{
			numDoggieGround ++;
		}
				
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("coleiras"))
		{
			//Remove coleira
			bodiesToRemove.add(fixtureA.getBody());

		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("coleiras"))
		{
			//Remove coleira
			bodiesToRemove.add(fixtureB.getBody());	
		}
		
	}

	//Chamado quando 2 fixtures deixam de colidir
	public void endContact(Contact contact) 
	{
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();	
		
		if(fixtureA == null || fixtureB == null) return;
		
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("footDoggie"))
		{
			numDoggieGround --;			
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("footDoggie"))
		{
			numDoggieGround --;
		}
		if(fixtureA.getUserData() != null && fixtureA.getUserData().equals("coleiras"))
		{
			//Remove coleira
			bodiesToRemove.add(fixtureA.getBody());
		}
		if(fixtureB.getUserData() != null && fixtureB.getUserData().equals("coleiras"))
		{
			//Remove coleira
			bodiesToRemove.add(fixtureB.getBody());			
		}
		
	}
	
	public boolean isPlayerOnGround() {return numDoggieGround > 0;}
	public Array<Body> getBodiesToRemove() { return bodiesToRemove;}
	

	public void preSolve(Contact contact, Manifold oldManifold) {}

	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
}
