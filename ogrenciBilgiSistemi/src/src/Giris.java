package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Giris {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giris window = new Giris();
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
	public Giris() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setType(Type.NORMAL);
		frame.setResizable(true);
		frame.setBounds(100, 100, 450, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JPanel panel = new JPanel();
		panel.setBounds(0, 30, 450, 220);
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Akademisyen Girişi");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AkademisyenGiris akademisyen = new AkademisyenGiris();
				akademisyen.formuGoster();
				frame.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(165, 45, 148, 51);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Öğrenci Girişi\r\n");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogrenciGiris ogr = new ogrenciGiris();
				ogr.formuGoster();
				frame.dispose();
			}
		});
		btnNewButton_2_1.setBackground(Color.WHITE);
		btnNewButton_2_1.setBorder(null);
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2_1.setBounds(165, 106, 148, 51);
		panel.add(btnNewButton_2_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 450, 32);
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
		btnNewButton.setBounds(419, 0, 31, 32);
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
		btnNewButton_1.setBounds(388, 0, 31, 32);
		panel_1.add(btnNewButton_1);

		new Tasima(frame, panel_1);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(100, 52, 55, 51);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Giris.class.getResource("/Source/graduated.png")));
		lblNewLabel_3.setBounds(100, 114, 55, 39);
		panel.add(lblNewLabel_3);
		
		
		// Butonların rengini ayarlıyoruz
		
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
		
		// akademisyen ve öğrenci girişi iconlarının ayarlanması.
		ImageIcon icon1 = new ImageIcon(Giris.class.getResource("/Source/student-login.png"));
		Image scaled1 = icon1.getImage().getScaledInstance(
				lblNewLabel_3.getWidth(), lblNewLabel_3.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel_3.setIcon(new ImageIcon(scaled1));

		ImageIcon icon2 = new ImageIcon(Giris.class.getResource("/Source/teacher-login.png"));
		Image scaled2 = icon2.getImage().getScaledInstance(
				lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(scaled2));

	}
}
