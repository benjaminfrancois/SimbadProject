package deadops;

import deadops.gui.PlayerInfo;
import deadops.utils.SoundEffect;
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

		roundUp(score, 1, 1, 0);

		if (!sound)
			SoundEffect.volume = SoundEffect.Volume.MUTE;

		SoundEffect.START.play();

		new PlayerInfo(this);

	}

	public void restart() {
		round = initRound;

		roundUp(initScore, 1, 1, 0);
	}

	private void initFrame(int score, int power, int speed, int zombieKill) {
		myEnv = null;
		myEnv = new MyEnv(this, round, score, power, speed, zombieKill);
		frame = new Simbad(myEnv, false, this);
	}

	public void roundUp(int score, int power, int speed, int zombieKill) {
		if (round > 1) {
			SoundEffect.NEXT_ROUND.play();
		}
		if (frame != null) {
			frame.killFrame();
		}
		round++;
		initFrame(score, power, speed, zombieKill);
	}

	public void stopSound() {
		SoundEffect.START.stop();
	}

	public void gameOver() {
		frame.gameOver();
	}

	public MyEnv getEnv() {
		return this.myEnv;
	}

	public Simulator getSimu() {
		return frame.getSimu();
	}

	public int getRound() {
		return round;
	}
}
