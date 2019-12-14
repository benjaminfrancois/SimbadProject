package test;

import simbad.gui.Simbad;
import simbad.sim.Simulator;

public class DeadOps {
	
	private Simbad frame;
	
	private int round;
	
	public DeadOps() {
		
		round = 1;
		
		roundUp();
	}
	
	private void initFrame() {
		frame = new Simbad(new MyEnv(this, round), false);
	}
	
	public void roundUp() {
		if(frame != null) {
			frame.dispose();
		}
		round++;
		initFrame();
	}
	
	public Simulator getSimu() { return frame.getSimu(); }
	
	public static void main(String[] args) {
		
		System.setProperty("j3d.implicitAntialiasing", "true");
		
		new DeadOps();
	}
}
