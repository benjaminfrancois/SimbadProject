package test;

import simbad.gui.Simbad;
import simbad.sim.Simulator;

public class DeadOps {
	
	private Simbad frame;
	
	private int round;
	private MyEnv myEnv;
	
	public DeadOps() {
		
		round = 1;
		
		roundUp(0);
	}
	
	private void initFrame(int score) {
		myEnv = null;
		myEnv = new MyEnv(this, round, score);
		frame = new Simbad(new MyEnv(this, round, score), false);
	}
	
	public void roundUp(int score) {
		
		if(frame != null) {
			frame.killFrame();
		}
		round++;
		initFrame(score);
	}
	
	public Simulator getSimu() { return frame.getSimu(); }
	
	public static void main(String[] args) {
		
		System.setProperty("j3d.implicitAntialiasing", "true");
		
		new DeadOps();
	}
}
