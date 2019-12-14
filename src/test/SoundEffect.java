package test;

import java.io.*;
import javax.sound.sampled.*;
import java.net.URL;

public enum SoundEffect
{
	TIRE        ( "/do_fire.wav"     ),
	NEXT_ROUND  ( "/do_nextRound.wav"),
	AMBIANCE    ( "/do_ambiance.wav" ),
	START       ( "/do_start.wav"    );


	public static enum Volume{ MUTE, LOW, MEDIUM, HIGH }

	public static Volume volume = Volume.MEDIUM;
	private Clip clip;

	SoundEffect( String source)
	{
		try
		{
			InputStream ips  = this.getClass().getResourceAsStream(source);
			InputStream bufferedIn = new BufferedInputStream(ips);
			AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);
			clip = AudioSystem.getClip();
			clip.open (stream);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}

	public void play()
	{
		if(volume != SoundEffect.Volume.MUTE) {			
			if ( clip.isRunning() )
				clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}

	static void init()
	{
		values();
	}
	
	public void stop()
	{
			if ( clip.isRunning() )
 				clip.stop();
			
	}
}