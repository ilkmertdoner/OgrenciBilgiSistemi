package src;

import src.Class.ogrenciDogrulama;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;
import src.Class.ogrenciGirisKontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class ogrenciGiris {

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
					ogrenciGiris window = new ogrenciGiris();
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
	public ogrenciGiris() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(150, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		panel.setBackground(Color.CYAN);
		panel.setForeground(Color.CYAN);
		panel.setBounds(0, 31, 550, 369);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ogrenciGiris.class.getResource("/Source/graduated.png")));
		lblNewLabel_2.setBounds(197, 10, 147, 126);
		panel.add(lblNewLabel_2);
		
		ImageIcon icon1 = new ImageIcon(Giris.class.getResource("/Source/graduated.png"));
		Image scaled1 = icon1.getImage().getScaledInstance(
		lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(scaled1));
				
		JLabel lblNewLabel_3 = new JLabel("E-Posta");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(180, 163, 58, 27);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Şifre");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(188, 211, 42, 27);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("E-Postanız veya Şifreniz Yanlış");
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(173, 325, 207, 34);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Giriş");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String eposta = textField.getText();
                String sifre = new String(passwordField.getPassword());

                ogrenciDogrulama kontrol = new ogrenciGirisKontrol();
                boolean girisBasarili = kontrol.girisDogrula(eposta, sifre);

                if (girisBasarili) {
                	ogrenciNotlari ogr = new ogrenciNotlari();
    				ogr.formuGoster(eposta);
    				frame.dispose();
                } else {
                	lblNewLabel_5.setVisible(true);
                    textField.setText("");
                    passwordField.setText("");
                }
            }

		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(180, 282, 180, 34);
		panel.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(248, 169, 112, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(248, 217, 112, 19);
		panel.add(passwordField);
		
		JLabel lblNewLabel_6 = new JLabel("Şifremi unuttum");
		lblNewLabel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(197, 248, 147, 24);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        ogrenciSifreDegistirme ogr = new ogrenciSifreDegistirme();
		        ogr.formuGoster();
		        frame.dispose();
		    }
		});

		
	}
	public void formuGoster() {
        frame.setVisible(true);
    }
}
