package deadops.entities;

import javax.vecmath.Vector3d;

import deadops.MyEnv;
import simbad.sim.Agent;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;

public class Character extends Agent implements Cloneable {

	private int life;
	RangeSensorBelt sonars;

	private int power;
	private int speed;

	private MyEnv env;

	private int score = 10;

	private long countDead;

	public Character(Vector3d pos, String name, MyEnv env) {
		super(pos, name);
		sonars = RobotFactory.addBumperBeltSensor(this, 8);
		life = 100;
		power = 1;
		speed = 1;
		this.env = env;
	}

	public void initBehavior() {
	}

	public void performBehavior() {

		setCanBeTraversed(false);

		for (int i = 0; i < sonars.getNumSensors(); i++) {
			if (sonars.hasHit(i) && getCounter() % 10 == 0) {
			}
		}


//		System.out.println(getVeryNearAgent());
		/** Joueur touch� par un zombie */
		if (getVeryNearAgent() instanceof Zombie) {
			System.out.println("[DEBUG] vie du joueur :"+life);
			if (countDead + 200 < System.currentTimeMillis()) {
				countDead = System.currentTimeMillis();
				life -= 20;
				
				if (life <= 0) {
					env.gameOver();
				}
			}
		}
		
		/** Recup�ration d'un bonus par le joueur */
		if (getVeryNearAgent() instanceof Bonus) {
			char type = ((Bonus) getVeryNearAgent()).getType();
			getVeryNearAgent().moveToPosition(new Vector3d(20, 0, 20));
			if(type == 'H') {
				this.life += 20;
				System.out.println("[DEBUG] vie du joueur:"+life);
			}else {
				System.out.println("[DEBUG] score du joueur:"+score);
				this.setScore(10);
			}
			
		}
	}

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	public void setScore(int score) {
		this.score += score;
	}

	public void setPower(int power) {
		this.power += power;
	}

	public void setSpeed(int speed) {
		this.speed += speed;
	}

	public int getPower() {
		return this.power;
	}

	public int getScore() {
		return this.score;
	}

	public int getSpeed() {
		return this.speed;
	}
}
