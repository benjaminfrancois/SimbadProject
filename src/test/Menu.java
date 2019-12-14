package test;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;

import simbad.gui.Simbad;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {

	private JFrame frame;
	private Simbad simbad;

	/**
	 * Create the application.
	 */
	public Menu(Simbad sim) {
		this.simbad = sim;
		initialize();
		
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
