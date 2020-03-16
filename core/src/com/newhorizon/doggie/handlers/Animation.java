package com.newhorizon.doggie.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int frameAtual;
	private int timesPlayed;
	
	public Animation() {}
	
	public Animation(TextureRegion[] frames)
	{
		this(frames, 1 / 12f);
	}
	
	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}
	
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		time = 0;
		frameAtual = 0;
		timesPlayed = 0;
	}
	
	public void update (float dt) {
		if(delay <=0) return;
		time+= dt;
		while( time >= delay) {
			step();
		}	
	}
	
	private void step() {
		time -= delay;
		frameAtual++;
		if(frameAtual == frames.length) {
			frameAtual = 0;
			timesPlayed++;
		}
	}

	public TextureRegion getFrame() { return frames[frameAtual];}
	public int getTimesPlayed() { return timesPlayed;}
	
	
}