package test;

import javax.vecmath.Vector3d;

import simbad.sim.Agent;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Character extends Agent implements Cloneable {
	
	private int life;
	RangeSensorBelt sonars;
	
	private int power;
	private int speed;
	
	
	private int score = 100;
	
	
	
	public Character(Vector3d pos, String name) {
		super(pos, name);
		sonars = RobotFactory.addBumperBeltSensor(this, 8);
		life = 100;
		power = 1;
		speed = 1;
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
		
	}
	
    public Object clone() throws CloneNotSupportedException { 
    	    	
		return super.clone(); 
	} 

	public void setScore(int score) { this.score += score; }
	public void setPower(int power) { this.power += power; }
	public void setSpeed(int speed) { this.speed += speed; }
	
	public int getPower() { return this.power; }
	public int getScore() { return this.score; }
	public int getSpeed() { return this.speed; }
}
