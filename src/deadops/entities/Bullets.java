package deadops.entities;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.sim.Agent;

public class Bullets extends Agent {

	private int count;

	public Bullets(Vector3d pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
		this.setColor(new Color3f(255, 255, 255));
	}

	private boolean fired;

	public void setFired(boolean state) {

		fired = state;
		count = 0;

	}

	public boolean isFired() {
		return fired;
	}

	@Override
	public void initBehavior() {
	}

	@Override
	public void performBehavior() {

		if (fired) {
			setTranslationalVelocity(10);
		} else {
			setTranslationalVelocity(0.0);
		}

		if (isFired()) {
			count++;
			if (count == 50) {
				setFired(false);
				moveToStartPosition();
			}
		}
	}

}
