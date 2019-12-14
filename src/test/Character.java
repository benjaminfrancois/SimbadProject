package test;

import javax.vecmath.Vector3d;

import simbad.sim.Agent;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Character extends Agent {
	
	private int life;
	RangeSensorBelt sonars;
	private int power;
	
	private int score;
	
	public Character(Vector3d pos, String name) {
		super(pos, name);
		sonars = RobotFactory.addBumperBeltSensor(this, 8);
		life = 100;
		power = 20;
	}
	
	public void initBehavior() {}
	
	public void performBehavior() {

		setCanBeTraversed(false);
		
		for(int i = 0; i < sonars.getNumSensors(); i++) {
			if(sonars.hasHit(i) && getCounter()%10 == 0) {
				life--;
			}
		}


		if(getVeryNearAgent() != null) {
			if(getVeryNearAgent() instanceof Zombie) {
				System.out.println("touché");
			}
		}
		
		System.out.println("Score : " + score);
		
	}

	public void setScore(int score) { this.score += score; }
	
	public int getPower() { return this.power; }
	public int getScore() { return this.score; }
}
