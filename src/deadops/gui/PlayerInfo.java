package deadops.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import deadops.DeadOps;

public class PlayerInfo extends JFrame {

	private DeadOps ctrl;

	/**
	 * Create the application.
	 */
	public PlayerInfo(DeadOps ctrl) {
		this.ctrl = ctrl;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setTitle("Information");
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setFont(new Font("Wide Latin", Font.PLAIN, 11));
		getContentPane().setLayout(null);

		JLabel lblScore = new JLabel("Score : ");
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblScore.setBounds(10, 21, 102, 14);
		getContentPane().add(lblScore);

		JLabel lblPlayerLife = new JLabel("Player life : ");
		lblPlayerLife.setForeground(Color.WHITE);
		lblPlayerLife.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblPlayerLife.setBounds(10, 179, 139, 14);
		getContentPane().add(lblPlayerLife);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setBounds(20, 204, 180, 30);
		getContentPane().add(progressBar);

		JLabel lblRound = new JLabel("Round : ");
		lblRound.setForeground(Color.WHITE);
		lblRound.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblRound.setBounds(10, 46, 139, 14);
		getContentPane().add(lblRound);

		JLabel lblZombieKilled = new JLabel("Zombie killed : ");
		lblZombieKilled.setForeground(Color.WHITE);
		lblZombieKilled.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		lblZombieKilled.setBounds(10, 71, 170, 14);
		getContentPane().add(lblZombieKilled);
		setBounds(100, 100, 229, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Timer timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblScore.setText("Score : " + ctrl.getEnv().getChara().getScore());
				progressBar.setValue(ctrl.getEnv().getChara().getLife());
				System.out.println(ctrl.getEnv().getChara().getLife());
				lblZombieKilled.setText("Zombie killed : " + ctrl.getEnv().getZombieKill());
				lblRound.setText("Round : " + ctrl.getRound());
			}
		});
		timer.start();

		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png");
			BufferedImage myImg = ImageIO.read(imgStream);
			setIconImage(myImg);
		} catch (Exception whoJackedMyIco) {
		}

		this.setLocation(830, 200);
		this.setVisible(true);
	}

}
