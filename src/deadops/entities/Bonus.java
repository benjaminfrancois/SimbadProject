package deadops.entities;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.sim.CherryAgent;

public class Bonus extends CherryAgent {
	
	public final Color3f HEAL_COLOR  = new Color3f(0.2f, 1.0f, 0.2f);
	public final Color3f POINT_COLOR = new Color3f(Color.YELLOW);
	private int rand;
	public Bonus(Vector3d pos, String name, float radius) {
		super(pos, name, radius);
	}
	
	public char getType() {
		
		return (rand != 0 ? 'P' : 'H' );
		
	}
	public void setRandomType() {
		
		/** rand == 0 type == heal || rand == 1 type == point */
		rand = (int)(Math.random()*(2));
		
		if(rand == 0) {	//heal
			this.setColor(HEAL_COLOR);
		}else {			//point
			this.setColor(POINT_COLOR);
		}
	}
	

	

}
