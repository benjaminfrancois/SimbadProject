package test;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;

import simbad.gui.Simbad;

import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Menu {
	
	static final double SCORE_UP = 0.5; 

	private JFrame frame;
	private Simbad simbad;

	/**
	 * Create the application.
	 */
	public Menu(Simbad sim) {
		this.simbad = sim;
		initialize();
		frame.setResizable(false);


		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png" );
			BufferedImage myImg = ImageIO.read(imgStream);
			frame.setIconImage(myImg);
		} catch (Exception whoJackedMyIco) {
			System.out.println("Could not load program icon.");
		}
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel lblScorescore = new JLabel("Score : " + ((simbad.getChara() != null)?simbad.getChara().getScore():"0"));
		lblScorescore.setForeground(Color.WHITE);
		lblScorescore.setBackground(Color.WHITE);
		lblScorescore.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblScorescore.setBounds(10, 11, 181, 28);
		frame.getContentPane().add(lblScorescore);
		
		JButton btnStartRound = new JButton("Start round !");
		btnStartRound.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				frame.dispose();
				simbad.getSimu().startSimulation();
			}
		});
		btnStartRound.setFont(new Font("Wide Latin", Font.PLAIN, 14));
		btnStartRound.setBounds(79, 227, 269, 23);
		frame.getContentPane().add(btnStartRound);
		
		JLabel lblSpeedAttack = new JLabel("Speed attack : " + simbad.getChara().getSpeed());
		lblSpeedAttack.setForeground(Color.WHITE);
		lblSpeedAttack.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblSpeedAttack.setBounds(42, 71, 181, 14);
		frame.getContentPane().add(lblSpeedAttack);
		
		JLabel lblAttackPower = new JLabel("Attack power : " + simbad.getChara().getSpeed());
		lblAttackPower.setForeground(Color.WHITE);
		lblAttackPower.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblAttackPower.setBounds(42, 96, 181, 14);
		frame.getContentPane().add(lblAttackPower);
		
		JButton btnNewButton = new JButton("Up  speed ( - " + Math.round((simbad.getChara().getSpeed() + 2) * (SCORE_UP*simbad.getChara().getSpeed())) + " )");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int count = (int) Math.round((simbad.getChara().getSpeed() + 2) * (SCORE_UP*simbad.getChara().getSpeed()));
				
				if(simbad.getChara().getSpeed() < 35) {
					if(simbad.getChara().getScore() >= count) {
						simbad.getChara().setSpeed(1);
						simbad.getChara().setScore(-count);
						lblSpeedAttack.setText("Speed attack : " + simbad.getChara().getSpeed());
						btnNewButton.setText("Up  speed ( - " + count + " )");
						lblScorescore.setText("Score : " + ((simbad.getChara() != null)?simbad.getChara().getScore():"0"));
						btnNewButton.setBackground(Color.WHITE);
					} else {
						btnNewButton.setBackground(Color.red);
					}
				}
				if(simbad.getChara().getSpeed() >= 35) {
					btnNewButton.setText("MAX SPEED !");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnNewButton.setBounds(233, 66, 233, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpPower = new JButton("Up  power ( - " + Math.round((simbad.getChara().getPower() + 2) * (SCORE_UP*simbad.getChara().getPower())) + " )");
		btnUpPower.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int count = (int) Math.round((simbad.getChara().getPower() + 2) * (SCORE_UP*simbad.getChara().getPower()));
				
				if(simbad.getChara().getScore() >= count) {
					simbad.getChara().setPower(1);
					simbad.getChara().setScore(-count);
					lblAttackPower.setText("Attack power : " + simbad.getChara().getPower());
					btnUpPower.setText("Up  power ( - " + count + " )");
					lblScorescore.setText("Score : " + ((simbad.getChara() != null)?simbad.getChara().getScore():"0"));
					btnUpPower.setBackground(Color.WHITE);
				} else {
					btnUpPower.setBackground(Color.red);
				}
			}
		});
		btnUpPower.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnUpPower.setBounds(233, 91, 233, 23);
		frame.getContentPane().add(btnUpPower);
		frame.setBounds(100, 100, 492, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
