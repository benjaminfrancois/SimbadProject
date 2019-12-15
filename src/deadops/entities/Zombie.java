package deadops.entities;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import deadops.MyEnv;
import simbad.sim.Agent;
import simbad.sim.RangeSensorBelt;
import simbad.sim.RobotFactory;



public class Zombie extends Agent {


	final double BASE_SPEED = 0.1;
	private final double DROP_RATE = 0.1;  //10 %
	private double speed = 1;
	
	private Character cible;
	boolean isDead = false;
	private int power;
	RangeSensorBelt sonars;
	private MyEnv env;
	private Bonus b;
	
	private int life;
	
	public Zombie(Vector3d pos, String name, Character cible, MyEnv env, int round, Bonus b){
		super(pos, name);
		this.env = env;
		setColor(new Color3f(255, 0, 0));
		this.cible = cible;
		setCanBeTraversed(false);
		sonars = RobotFactory.addBumperBeltSensor(this, 16);
		power = 10;
		this.b = b;
		life = 50+round*2;
		
	}
	
	public void initBehavior() {}
	
	public void performBehavior() {
		
		boolean test = true;

		if(!isDead) {

			if(this.life <= 0 && isDead == false) {
				double rand = Math.random();
				if (rand > DROP_RATE) {
					System.out.println("drop");
					
					Point3d currPos = new Point3d();
					this.getCoords(currPos);
					b.setRandomType();
					b.moveToPosition(new Vector3d(currPos.getX(),0,currPos.getZ()));
				}
				moveToPosition(20,  20);
				isDead = true;
				env.roundFinish();
				cible.setScore(1);
			}
			
			
			setCanBeTraversed(true);
			Point3d ciblePos = new Point3d();
			cible.getCoords(ciblePos);
			double cibleX = ciblePos.getX();
			double cibleZ = ciblePos.getZ();
			

			Point3d myPos = new Point3d();
			getCoords(myPos);
			double myPosX = myPos.getX();
			double myPosZ = myPos.getZ();

			for(int i = 0; i < sonars.getNumSensors(); i++) {
				if(sonars.hasHit(i)) {
					rotateY(Math.floor(Math.random() * 2*Math.PI) + 1);
					setTranslationalVelocity(3);
					test = false;
				}
			}

			if(getVeryNearAgent() != null) {
				if(getVeryNearAgent() instanceof Bullets) {
					Bullets tmp = (Bullets)getVeryNearAgent();
					tmp.moveToStartPosition();
					tmp.setFired(false);
					life -= (cible.getPower()+20) * (cible.getPower()*0.2);
				}
			}
			
			if(test) {
				setTranslationalVelocity(0);
				if(cibleX-0.3 > myPosX) {
					if     (cibleZ-0.3 > myPosZ) translateTo(new Vector3d(+BASE_SPEED * speed, 0, +BASE_SPEED * speed));
					else if(cibleZ+0.3 < myPosZ) translateTo(new Vector3d(+BASE_SPEED * speed, 0, -BASE_SPEED * speed));
					else                         translateTo(new Vector3d(+BASE_SPEED * speed, 0,  0  ));
				}
				else if(cibleX+0.3 < myPosX) {
					if     (cibleZ-0.3 > myPosZ) translateTo(new Vector3d(-BASE_SPEED * speed, 0, +BASE_SPEED * speed));
					else if(cibleZ+0.3 < myPosZ) translateTo(new Vector3d(-BASE_SPEED * speed, 0, -BASE_SPEED * speed));
					else                         translateTo(new Vector3d(-BASE_SPEED * speed, 0,  0  ));
				}
				else {
					if     (cibleZ-0.3 > myPosZ) translateTo(new Vector3d(0, 0, +BASE_SPEED * speed));
					else if(cibleZ+0.3 < myPosZ) translateTo(new Vector3d(0, 0, -BASE_SPEED * speed));
					else                         translateTo(new Vector3d(0, 0,  0  ));
				}
			}
		} else {
			translateTo(new Vector3d(0, 0, 0));
			setTranslationalVelocity(0);
		}
		
	}
}
