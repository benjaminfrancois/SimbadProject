package test;

import simbad.gui.Simbad;
import simbad.sim.Simulator;

public class DeadOps {
	
	private Simbad frame;
	
	private int round;
	private MyEnv myEnv;
	
	public DeadOps(int score, int round, boolean sound) {
		
		this.round = round;
		
		System.out.println(round);
		
		roundUp(score, 1, 1);
		
		if(!sound) SoundEffect.volume = SoundEffect.Volume.MUTE;

		SoundEffect.START.play();
		
	}
	
	private void initFrame(int score, int power, int speed) {
		myEnv = null;
		myEnv = new MyEnv(this, round, score, power, speed);
		frame = new Simbad(myEnv, false);
	}
	
	public void roundUp(int score, int power, int speed) {
		if(round > 1) {
			SoundEffect.NEXT_ROUND.play();
		}
		if(frame != null) {
			frame.killFrame();
		}
		round++;
		initFrame(score, power, speed);
	}
	
	public Simulator getSimu() { return frame.getSimu(); }
}
