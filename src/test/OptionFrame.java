package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class OptionFrame extends JFrame implements MouseListener {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	
	private JCheckBox checkBox  ;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	
	private JCheckBox chckbxOui;

	
	/**
	 * Create the frame.
	 */
	public OptionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCommencerAuRound = new JLabel("Commencer au round :");
		lblCommencerAuRound.setForeground(Color.WHITE);
		lblCommencerAuRound.setBounds(10, 38, 122, 28);
		contentPane.add(lblCommencerAuRound);
		
		checkBox = new JCheckBox("1");
		checkBox.setForeground(Color.WHITE);
		checkBox.setBackground(Color.DARK_GRAY);
		buttonGroup.add(checkBox);
		checkBox.setBounds(172, 41, 45, 23);
		contentPane.add(checkBox);
		
		checkBox_1 = new JCheckBox("5");
		checkBox_1.setForeground(Color.WHITE);
		checkBox_1.setBackground(Color.DARK_GRAY);
		buttonGroup.add(checkBox_1);
		checkBox_1.setBounds(230, 41, 45, 23);
		contentPane.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("10");
		checkBox_2.setForeground(Color.WHITE);
		checkBox_2.setBackground(Color.DARK_GRAY);
		buttonGroup.add(checkBox_2);
		checkBox_2.setBounds(296, 41, 45, 23);
		contentPane.add(checkBox_2);
		
		checkBox_3 = new JCheckBox("20");
		checkBox_3.setForeground(Color.WHITE);
		checkBox_3.setBackground(Color.DARK_GRAY);
		buttonGroup.add(checkBox_3);
		checkBox_3.setBounds(364, 41, 45, 23);
		contentPane.add(checkBox_3);
		
		JLabel lblOptions = new JLabel("OPTIONS");
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setBounds(160, 11, 46, 14);
		contentPane.add(lblOptions);
		
		JLabel lblCommencerAvec = new JLabel("Commencer avec :");
		lblCommencerAvec.setForeground(Color.WHITE);
		lblCommencerAvec.setBounds(10, 107, 122, 14);
		contentPane.add(lblCommencerAvec);
		
		textField = new JTextField();
		textField.setBounds(172, 104, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setForeground(Color.WHITE);
		lblPoints.setBounds(268, 107, 46, 14);
		contentPane.add(lblPoints);
		
		JButton btnValidert = new JButton("Valider");
		btnValidert.addMouseListener(this);
		btnValidert.setBounds(172, 227, 89, 23);
		contentPane.add(btnValidert);
		
		JLabel lblActiverLeSon = new JLabel("Activer le son : ");
		lblActiverLeSon.setForeground(Color.WHITE);
		lblActiverLeSon.setBounds(10, 166, 122, 14);
		contentPane.add(lblActiverLeSon);
		
		chckbxOui = new JCheckBox("Oui");
		chckbxOui.setSelected(true);
		chckbxOui.setForeground(Color.WHITE);
		chckbxOui.setBackground(Color.DARK_GRAY);
		chckbxOui.setBounds(161, 162, 45, 23);
		contentPane.add(chckbxOui);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		try {
			InputStream imgStream = this.getClass().getResourceAsStream("/icon.png" );
			BufferedImage myImg = ImageIO.read(imgStream);
			this.setIconImage(myImg);
		} catch (Exception whoJackedMyIco) {
			System.out.println("Could not load program icon.");
		}

		this.setResizable(false);
		this.setTitle("Options");

		this.setVisible(true);
	}
	
	 public int getRoundStart() {
		 if (this.checkBox.isSelected()) {
			 System.out.println("Selected : 1");
			return 1;
		}
		 if (this.checkBox_1.isSelected()) {
			 System.out.println("Selected : 5");
			return 5;
		}
		 if (this.checkBox_2.isSelected()) {
			 System.out.println("Selected : 10");
			return 10;
		}
		 if (this.checkBox_3.isSelected()) {
			 System.out.println("Selected : 20");
			return 20;
		}
		 return 0;
	 }

	 public int getStartPoint() {
		 return Integer.parseInt((textField.getText().equals("")?"0":textField.getText()));
	 }

	 public boolean getSound() {
		 return chckbxOui.isSelected();
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
		this.dispose();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
