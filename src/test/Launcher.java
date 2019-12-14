package test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Launcher implements MouseListener{

	private JFrame frmMicrobeOpsArcade;
	private OptionFrame optionFrame;
	private JButton btnNewButton;
	private JButton btnOption;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
					window.frmMicrobeOpsArcade.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

	/**
	 * Create the application.
	 */
	public Launcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMicrobeOpsArcade = new JFrame();
		frmMicrobeOpsArcade.setFont(new Font("Broadway", Font.PLAIN, 12));
		frmMicrobeOpsArcade.setType(Type.POPUP);
		frmMicrobeOpsArcade.setTitle("Microbe Ops Arcade");
		frmMicrobeOpsArcade.setResizable(false);
		frmMicrobeOpsArcade.getContentPane().setBackground(Color.DARK_GRAY);
		frmMicrobeOpsArcade.getContentPane().setLayout(null);
		
		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png" );
			BufferedImage myImg = ImageIO.read(imgStream);
			frmMicrobeOpsArcade.setIconImage(myImg);
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}
		
		btnNewButton = new JButton("! PLAY !");
		btnNewButton.addMouseListener(this);
		btnNewButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(10, 136, 125, 58);
		frmMicrobeOpsArcade.getContentPane().add(btnNewButton);
		
		btnOption = new JButton("OPTIONS");
		btnOption.addMouseListener(this);
		btnOption.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnOption.setBackground(Color.WHITE);
		btnOption.setBounds(345, 142, 130, 52);
		frmMicrobeOpsArcade.getContentPane().add(btnOption);
		
		JLabel lblThomasCogezalix = new JLabel("Thomas Cogez--Alix");
		lblThomasCogezalix.setFont(new Font("Calibri", Font.BOLD, 13));
		lblThomasCogezalix.setForeground(Color.WHITE);
		lblThomasCogezalix.setBounds(10, 211, 120, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblThomasCogezalix);
		
		JLabel lblBenjaminFranois = new JLabel("Benjamin Fran\u00E7ois");
		lblBenjaminFranois.setForeground(Color.WHITE);
		lblBenjaminFranois.setFont(new Font("Calibri", Font.BOLD, 13));
		lblBenjaminFranois.setBounds(207, 175, 102, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblBenjaminFranois);
		
		JLabel lblLoPrvost = new JLabel("L\u00E9o Pr\u00E9vost");
		lblLoPrvost.setForeground(Color.WHITE);
		lblLoPrvost.setFont(new Font("Calibri", Font.BOLD, 13));
		lblLoPrvost.setBounds(373, 211, 102, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblLoPrvost);
		
		JLabel label = new JLabel("");
		
		try {
			label.setIcon(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/image1.png"))));
		} catch (IOException e) {}
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.PLAIN, 16));
		label.setBounds(-185, 0, 748, 433);
		frmMicrobeOpsArcade.getContentPane().add(label);
		frmMicrobeOpsArcade.setBounds(100, 100, 491, 265);
		frmMicrobeOpsArcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SoundEffect.AMBIANCE.play();
		
		
	}
	
	public static void displayTray(String title, String descr) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("data/icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage(title, descr, MessageType.WARNING);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == this.btnNewButton) {
			if(this.optionFrame == null) {
				new DeadOps(0, 1, true);
			} else {
				new DeadOps(this.optionFrame.getStartPoint(), this.optionFrame.getRoundStart(), this.optionFrame.getSound());
			}
		}
		if(e.getSource() == this.btnOption) {
			optionFrame = new OptionFrame();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
