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
import java.io.File;
import java.io.IOException;
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

public class Launcher {

	private JFrame frmMicrobeOpsArcade;

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
			Image icon = Toolkit.getDefaultToolkit().getImage("data/icon.png");  
			frmMicrobeOpsArcade.setIconImage(icon);
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}
		
		JButton btnNewButton = new JButton("! PLAY !");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					Launcher.displayTray("Game launched !", "Have fun !");
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//new DeadOps();
			}
		});
		btnNewButton.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(70, 80, 131, 64);
		frmMicrobeOpsArcade.getContentPane().add(btnNewButton);
		
		JLabel lblMicrobeOpsArcade = new JLabel("Microbe Ops Arcade");
		lblMicrobeOpsArcade.setForeground(Color.WHITE);
		lblMicrobeOpsArcade.setFont(new Font("Wide Latin", Font.PLAIN, 16));
		lblMicrobeOpsArcade.setBounds(83, 11, 292, 58);
		frmMicrobeOpsArcade.getContentPane().add(lblMicrobeOpsArcade);
		
		JButton btnOption = new JButton("OPTION");
		btnOption.setFont(new Font("Wide Latin", Font.PLAIN, 11));
		btnOption.setBackground(Color.WHITE);
		btnOption.setBounds(245, 80, 131, 64);
		frmMicrobeOpsArcade.getContentPane().add(btnOption);
		
		JLabel lblThomasCogezalix = new JLabel("Thomas Cogez--Alix");
		lblThomasCogezalix.setFont(new Font("Calibri", Font.BOLD, 13));
		lblThomasCogezalix.setForeground(Color.WHITE);
		lblThomasCogezalix.setBounds(20, 175, 120, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblThomasCogezalix);
		
		JLabel lblBenjaminFranois = new JLabel("Benjamin Fran\u00E7ois");
		lblBenjaminFranois.setForeground(Color.WHITE);
		lblBenjaminFranois.setFont(new Font("Calibri", Font.BOLD, 13));
		lblBenjaminFranois.setBounds(185, 175, 102, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblBenjaminFranois);
		
		JLabel lblLoPrvost = new JLabel("L\u00E9o Pr\u00E9vost");
		lblLoPrvost.setForeground(Color.WHITE);
		lblLoPrvost.setFont(new Font("Calibri", Font.BOLD, 13));
		lblLoPrvost.setBounds(365, 175, 102, 14);
		frmMicrobeOpsArcade.getContentPane().add(lblLoPrvost);
		frmMicrobeOpsArcade.setBounds(100, 100, 450, 230);
		frmMicrobeOpsArcade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

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
	
}
