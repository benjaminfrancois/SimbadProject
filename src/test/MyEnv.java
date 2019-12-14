package test;

import java.util.ArrayList;
import java.util.Objects;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class MyEnv extends EnvironmentDescription {
	
	Character character;
	ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	DeadOps controller;
	
	public MyEnv(DeadOps controller, int round) {
		
		this.controller = controller;
		
		// world options
		setUsePhysics(false);
		setWorldSize((float)24.9);

		Wall w1 = new Wall(new Vector3d(0,      0,  12.45), 25, 1, this);
		Wall w2 = new Wall(new Vector3d(0,      0, -12.45), 25, 2, this);
		Wall w3 = new Wall(new Vector3d(-12.45, 0, 0     ), 25, 1, this);
		Wall w4 = new Wall(new Vector3d( 12.45, 0, 0     ), 25, 1, this);
		w1.setColor(new Color3f(0, 0, 0));
		w2.setColor(new Color3f(0, 0, 0));
		w3.setColor(new Color3f(0, 0, 0));
		w4.setColor(new Color3f(0, 0, 0));
		w3.rotate90(1);
		w4.rotate90(1);
		add(w1); add(w2); add(w3); add(w4);
			
		//
		character = new Character(new Vector3d(0, 0, 0), "Le tueur");
		
		
		for (int i = 0; i < round*5; i++) {
			add(new Zombie(new Vector3d((int)(Math.random()* 9), 0, (int)(Math.random()* 9)), "Le tueur", character, this));
		}
		
		for(int i = 0; i < 200; i++) {
			bullets.add(new Bullets(new Vector3d(-20, 0, 0), ""));
			add(bullets.get(i));
		}
		
		add(character);
	}
	
	
	public void roundFinish() {
		boolean roundCleared = true;
		
		for (Object object : controller.getSimu().getAgentList()) {
			if(object instanceof Zombie) {
				Zombie tmp = (Zombie) object;
				Point3d coord = new Point3d();
				tmp.getCoords(coord);
				if(coord.getX() > 15 && coord.getZ() > 15) {
					
				} else {
					roundCleared = false;
					break;
				}
			}
		}
		if(roundCleared) {
			System.out.println("Round finish !!!!");
			controller.roundUp();
		}
		
	}

}