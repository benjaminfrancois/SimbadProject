package deadops;

import deadops.utils.*;
import simbad.gui.Simbad;
import simbad.sim.Simulator;

public class DeadOps {
	
	private Simbad frame;
	
	private int initScore, initRound;
	private boolean initSound;
	
	private int round;
	private MyEnv myEnv;
	
	public DeadOps(int score, int round, boolean sound) {
		
		this.initScore = score;
		this.initRound = round;
		this.initSound = sound;
		
		this.round = round;
		
		System.out.println(round);
		
		roundUp(score, 1, 1);
		
		if(!sound) SoundEffect.volume = SoundEffect.Volume.MUTE;

		SoundEffect.START.play();
		
	}
	
	public void restart() {
		round = initRound;
		
		roundUp(initScore, 1, 1);
	}
	
	private void initFrame(int score, int power, int speed) {
		myEnv = null;
		myEnv = new MyEnv(this, round, score, power, speed);
		frame = new Simbad(myEnv, false, this);
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
	
	public void stopSound() {
		SoundEffect.START.stop();
	}
	
	public void gameOver() {
		frame.gameOver();
	}
	
	public Simulator getSimu() { return frame.getSimu(); }
}