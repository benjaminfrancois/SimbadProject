package deadops.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import simbad.gui.Simbad;

public class GameOver extends JFrame implements MouseListener {

	private JButton btnRestart;
	private JButton btnQuitTheGame;
	private Simbad sim;

	/**
	 * Create the application.
	 */
	public GameOver(Simbad sim) {
		this.sim = sim;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		this.setTitle("Game Over !");
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setBounds(100, 100, 450, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		btnQuitTheGame = new JButton("Quit the game");
		btnQuitTheGame.addMouseListener(this);
		btnQuitTheGame.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnQuitTheGame.setBounds(240, 113, 184, 23);
		this.getContentPane().add(btnQuitTheGame);

		btnRestart = new JButton("Restart");
		btnRestart.addMouseListener(this);
		btnRestart.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnRestart.setBounds(10, 112, 184, 23);
		this.getContentPane().add(btnRestart);

		JLabel label = new JLabel("");

		try {
			label.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/gameover.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png");
			BufferedImage myImg = ImageIO.read(imgStream);
			this.setIconImage(myImg);
		} catch (Exception whoJackedMyIco) {
			System.out.println("Could not load program icon.");
		}

		label.setBounds(92, 11, 270, 110);
		this.getContentPane().add(label);

		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == this.btnQuitTheGame) {
			System.exit(0);
		}
		if (arg0.getSource() == this.btnRestart) {
			this.dispose();
			sim.getCtrl().restart();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
