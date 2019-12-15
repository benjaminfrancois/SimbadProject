/*
 *  Simbad - Robot Simulator
 *  Copyright (C) 2004 Louis Hugues
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 -----------------------------------------------------------------------------
 * $Author: sioulseuguh $ 
 * $Date: 2005/08/07 12:24:56 $
 * $Revision: 1.14 $
 * $Source: /cvsroot/simbad/src/simbad/gui/Simbad.java,v $
 */

package simbad.gui;

import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import javax.imageio.ImageIO;
import javax.media.j3d.Transform3D;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import deadops.DeadOps;
import deadops.entities.*;
import deadops.entities.Character;
import deadops.gui.GameOver;
import deadops.gui.Menu;
import deadops.utils.SoundEffect;
import simbad.sim.Agent;
import simbad.sim.EnvironmentDescription;
import simbad.sim.SimpleAgent;
import simbad.sim.Simulator;
import simbad.sim.World;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * This is the Simbad application mainframe.
 * 
 */
public class Simbad extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
	
	private DeadOps ctrl;

	private static final long serialVersionUID = 1L;
	private int indBullet = 0;
	ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	static final String version = "1.4";
	static int SIZEX = 800;
	static int SIZEY = 700;
	JDesktopPane desktop;
	World world;
	Simulator simulator;
	Console console = null;
	AgentInspector agentInspector = null;
	boolean backgroundMode;


    protected long jolieTimer;
	
	private Character character;

	static Simbad simbadInstance = null;
	
	private boolean isPaused;

	/** Construct Simbad application with the given environement description */
	public Simbad(EnvironmentDescription ed, boolean backgroundMode, DeadOps ctrl) {
		super("Simbad  - version " + version);
		simbadInstance = this;
		this.backgroundMode = backgroundMode;
		desktop = new JDesktopPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SIZEX, SIZEY);
		setResizable(false);
		createGUI();
		start(ed);
		keyy();
		
		this.ctrl = ctrl;

		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png" );
			BufferedImage myImg = ImageIO.read(imgStream);
			this.setIconImage(myImg);
		} catch (Exception whoJackedMyIco) {
			System.out.println("Could not load program icon.");
		}
		
		simulator.setFramesPerSecond(30);

		// world.changeViewPoint(World.VIEW_FROM_TOP, null);
		ArrayList<Object> tmp = simulator.getAgentList();
		for (Object o : tmp) {
			if (o instanceof Character) {
				character = (Character) o;
			}
			if (o instanceof Bullets) {
				bullets.add((Bullets) o);
			}
		}
		
		isPaused = false;
		setVisible(true);
		
		
		pauseFrame();
	}

	private void keyy() {
				
		String forward = "forward";
		String backward = "backward";
		String left = "left";
		String right = "right";

		String forward_R = "forward_R";
		String backward_R = "backward_R";
		String left_R = "left_R";
		String right_R = "right_R";
		
		String pause = "pause";

		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Set actions pressed
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), forward);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), backward);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), left);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), right);

		// Set actions released
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), forward_R);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), backward_R);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), left_R);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true), right_R);
		
		
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), pause);

		ActionMap actionMap = getRootPane().getActionMap();

		// PRESSED
		actionMap.put(forward, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setTranslationalVelocity(5);
			}
		});
		actionMap.put(backward, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setTranslationalVelocity(-5);
			}
		});
		actionMap.put(left, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setRotationalVelocity(-5);
			}
		});
		actionMap.put(right, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setRotationalVelocity(5);
			}
		});

		// REALESED
		actionMap.put(forward_R, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setTranslationalVelocity(0);
			}
		});
		actionMap.put(backward_R, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setTranslationalVelocity(0);
			}
		});
		actionMap.put(left_R, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setRotationalVelocity(0);
			}
		});
		actionMap.put(right_R, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				character.setRotationalVelocity(0);
			}
		});
		

		actionMap.put(pause, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isPaused) {
					isPaused = !isPaused;
					simulator.stopSimulation();
					pauseFrame();
				}
			}
		});
	}

	/** Create the main GUI. Only called once. */
	private void createGUI() {
		desktop.setFocusable(true);
		getContentPane().add(desktop);
	}
	
	private void pauseFrame() {
		
		new Menu(this);
		
	}

	/** Starts (or Restarts after releaseRessources) the world and simulator. */
	private void start(EnvironmentDescription ed) {
		System.out.println("Starting environmentDescription: " + ed.getClass().getName());
		world = new World(ed);
		simulator = new Simulator(desktop, world, ed);
		createInternalFrames();
		if (backgroundMode) {
			runBackgroundMode();
		}
	}

	/** Release all ressources. */
	private void releaseRessources() {
		simulator.dispose();
		world.dispose();
		disposeInternalFrames();
	}

	/**
	 * Creates the windows as Swing InternalFrames
	 */
	private void createInternalFrames() {
		world.getCanvas3D().addMouseListener(this);
		world.getCanvas3D().addMouseMotionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add("Center", world.getCanvas3D());
		
		this.add(panel);
	}

	/**
	 * Dispose the windows- used before restart.
	 */
	private void disposeInternalFrames() {
		simulator.dispose();
		if (agentInspector != null)
			agentInspector.dispose();
	}

	/**
	 * creates agent inspector window
	 */
	private AgentInspector createAgentInspector(Simulator simulator, int x, int y) {
		ArrayList<Object> agents = simulator.getAgentList();
		SimpleAgent a = ((SimpleAgent) agents.get(0));
		if (a instanceof Agent) {
			AgentInspector ai = new AgentInspector((Agent) a, !backgroundMode, simulator);
			desktop.add(ai);
			ai.show();
			ai.setLocation(x, y);
			return ai;
		} else
			return null;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "demo") {
			releaseRessources();
		}
	}

	/**
	 * Runs Simbad in background mode for computation intensive application.
	 * Minimize graphic display and renderer computation.
	 */
	private void runBackgroundMode() {
		// TODO pb with collision , pb with camera in this mode.
		setTitle(this.getTitle() + " - Background Mode");
		System.out.println("---------------------------------------------");
		System.out.println("Simbad is running in 'Background Mode");
		System.out.println("World is rendered very rarely. UI is disabled");
		System.out.println("--------------------------------------------");
		// slow down
		agentInspector.setFramesPerSecond(0.5f);
		// Show a small indication window
		JInternalFrame frame = new JInternalFrame();
		JPanel p = new JPanel();
		p.add(new JLabel("BACKGROUND MODE"));
		frame.setContentPane(p);
		frame.setClosable(false);
		frame.pack();
		frame.setLocation(SIZEX / 2, SIZEY * 3 / 4);
		desktop.add(frame);
		frame.show();
		world.changeViewPoint(World.VIEW_FROM_TOP, null);
		// start
		simulator.startBackgroundMode();
	}

	public JDesktopPane getDesktopPane() {
		return desktop;
	}

	/////////////////////////
	// Class methods
	public static Simbad getSimbadInstance() {
		return simbadInstance;
	}

	public Simulator getSimu() { return simulator; }
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println("mouseEntered");

	}

	@Override
	public void mouseExited(MouseEvent e) {
//		System.out.println("mouseExited");

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouseReleased");

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		if(!isPaused) {
			
			long count = (200-(long) ((character.getSpeed()+10) * (character.getSpeed()*0.1)));

			if(jolieTimer+count < System.currentTimeMillis()) {
				
				try {
					double mouseX, mouseY;
					mouseX = ((arg0.getX() - 80) * 100.0) / (this.getSize().width - (80 * 2));
					mouseY = ((arg0.getY() - 17) * 100.0) / (this.getSize().height - (30 * 2));

					Point3d p = new Point3d();
					character.getCoords(p);

					double playerX, playerZ;
					playerX = ((p.getX() + 24.9 / 2.0) * 100.0) / 24.9;
					playerZ = ((p.getZ() + 24.9 / 2.0) * 100.0) / 24.9;

					Vector3d mouse = new Vector3d(mouseX, 0, mouseY);
					Vector3d center = new Vector3d(playerX, 0, playerZ);

					mouse.sub(center);

					mouse.normalize();

					bullets.get(indBullet).moveToPosition(p.getX(), p.getZ());
					bullets.get(indBullet).rotateY(-Math.atan2(mouse.getZ(), mouse.getX()));

					if (!bullets.get(indBullet).isFired()) {
						bullets.get(indBullet).setFired(true);

						if (indBullet == 199)
							indBullet = 0;
						indBullet++;
					}
					try {
						Robot objMouse = new Robot();
						PointerInfo a = MouseInfo.getPointerInfo();

						objMouse.mouseMove((int) a.getLocation().getX(), (int) a.getLocation().getY());
					} catch (Exception e) {}
					
					SoundEffect.TIRE.play();

					
					jolieTimer = System.currentTimeMillis();
				} catch(Exception e) {e.printStackTrace();}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
	
	public void killFrame() {
		this.simulator.stopSimulation();
		this.dispose();
	}
	
	public void gameOver() {
		System.out.println("Game over !");
		simulator.stopSimulation();
		isPaused = true;
		new GameOver(this);
	}
	
	public Character getChara()           { return character;  }
	public void      setPaused(boolean t) { this.isPaused = t; }
	public DeadOps   getCtrl()            { return this.ctrl;  }
}