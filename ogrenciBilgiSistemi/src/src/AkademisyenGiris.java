package src;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class AkademisyenGiris {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AkademisyenGiris window = new AkademisyenGiris();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AkademisyenGiris() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150, 100, 550, 400);
		frame.getContentPane().setLayout(null);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 550, 32);
		panel_1.setName("panel");
		panel_1.setBackground(Color.BLACK);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("X");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(519, 0, 31, 32);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("_");
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		frame.getContentPane().setLayout(null);
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(488, 0, 31, 32);
		panel_1.add(btnNewButton_1);

		new Tasima(frame, panel_1);
		frame.getContentPane().add(panel_1);
		
		
		
		new ButonRenkEfekti(btnNewButton, Color.RED);
		new ButonRenkEfekti(btnNewButton_1, Color.DARK_GRAY);
		
		// Uygulamamızın adını gösteriyoruz
		JLabel lblNewLabel = new JLabel("Öğrenci Bilgi Sistemi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(32, -1, 160, 32);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 31, 32);
		panel_1.add(lblNewLabel_1);
		
		// Uygulamamızın iconunu ayarlıyoruz
		ImageIcon orijinalIcon = new ImageIcon(Giris.class.getResource("/Source/icon.png"));
		Image scaledImage = orijinalIcon.getImage().getScaledInstance(
				lblNewLabel_1.getWidth(),
				lblNewLabel_1.getHeight(),
				  Image.SCALE_SMOOTH
				);
				
		lblNewLabel_1.setIcon(new ImageIcon(scaledImage));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 30, 550, 370);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AkademisyenGiris.class.getResource("/Source/teacher.png")));
		lblNewLabel_2.setBounds(187, 24, 173, 100);
		panel.add(lblNewLabel_2);
		
		ImageIcon icon1 = new ImageIcon(Giris.class.getResource("/Source/teacher.png"));
		Image scaled1 = icon1.getImage().getScaledInstance(
		lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(scaled1));
		
		JLabel lblNewLabel_3 = new JLabel("E-Posta");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(187, 163, 58, 27);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(260, 169, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Şifre");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(197, 211, 41, 27);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("Giriş");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(187, 288, 173, 34);
		panel.add(btnNewButton_2);
		

		
		passwordField = new JPasswordField();
		passwordField.setBounds(260, 217, 96, 19);
		panel.add(passwordField);
		
		JLabel lblNewLabel_5 = new JLabel("E-Postanız veya Şifreniz Yanlış");
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(178, 248, 207, 34);
		panel.add(lblNewLabel_5);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eposta = textField.getText();
				String sifre = new String(passwordField.getPassword());

				IDogrulama kontrol = new OgretmenGirisKontrol();
				boolean girisBasarili = kontrol.girisDogrula(eposta, sifre);

				if (girisBasarili) {
					new OgretmenGirisKontrol();
					ogretmenNotGirisi akademisyen = new ogretmenNotGirisi();
					akademisyen.formuGoster(eposta);
					frame.dispose();
				} else {
					lblNewLabel_5.setVisible(true);
					textField.setText("");
					passwordField.setText("");
				}
			}
		});

	}

	 public void formuGoster() 
	 {
	        frame.setVisible(true);
	 }
	 
}
