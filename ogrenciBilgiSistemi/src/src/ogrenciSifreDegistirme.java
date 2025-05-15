package src;

import src.Class.sifreDegistirmeSQL;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ogrenciSifreDegistirme {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ogrenciSifreDegistirme window = new ogrenciSifreDegistirme();
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
	public ogrenciSifreDegistirme() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setBounds(150, 100, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 350, 32);
		panel_1.setName("panel");
		panel_1.setBackground(Color.BLACK);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Öğrenci Bilgi Sistemi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(32, -1, 160, 32);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 31, 32);
		panel_1.add(lblNewLabel_1);

		ImageIcon orijinalIcon = new ImageIcon(Giris.class.getResource("/Source/icon.png"));
		Image scaledImage = orijinalIcon.getImage().getScaledInstance(
				lblNewLabel_1.getWidth(),
				lblNewLabel_1.getHeight(),
						Image.SCALE_SMOOTH
				);
						
		lblNewLabel_1.setIcon(new ImageIcon(scaledImage));
		
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
		btnNewButton.setBounds(319, -1, 31, 32);
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
		btnNewButton_1.setBounds(288, -1, 31, 32);
		panel_1.add(btnNewButton_1);

		new Tasima(frame, panel_1);
		frame.getContentPane().add(panel_1);
		
		
		
		new ButonRenkEfekti(btnNewButton, Color.RED);
		new ButonRenkEfekti(btnNewButton_1, Color.DARK_GRAY);

				
		// Uygulamamızın iconunu ayarlıyoruz
		
						
		lblNewLabel_1.setIcon(new ImageIcon(scaledImage));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setForeground(Color.CYAN);
		panel.setBounds(0, 31, 350, 219);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("E-Postanızı Giriniz");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(52, 10, 120, 38);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(182, 21, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("E-Posta Bilgileriniz veya Şifreniz Yanlış");
		lblNewLabel_2_1.setVisible(false);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(62, 181, 226, 38);
		panel.add(lblNewLabel_2_1);
		
		JButton btnNewButton_2_1 = new JButton("Giriş");
		btnNewButton_2_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String eposta = textField.getText();
		        String yeniSifre = new String(passwordField.getPassword());
		        String yeniSifreTekrar = new String(passwordField_1.getPassword());

		        if (!yeniSifre.equals(yeniSifreTekrar)) {
		            lblNewLabel_2_1.setText("Şifreler uyuşmuyor!");
		            lblNewLabel_2_1.setVisible(true);
		            return;
		        }

		        sifreDegistirmeSQL sql = new sifreDegistirmeSQL();
		        sifreDegistirmeSQL.girisDogrula2 kontrol = sql.new girisDogrula2();
		        boolean girisBasarili = kontrol.dogrula(eposta); 

		        if (girisBasarili) {
		            boolean sonuc = sql.sifreDegistir(eposta, yeniSifre);
		            if (sonuc) {
		                JOptionPane.showMessageDialog(null, "Şifre başarıyla değiştirildi.");
		                frame.dispose(); 
		            } else {
		                JOptionPane.showMessageDialog(null, "Şifre değiştirilemedi.");
		            }
		        } else {
		            lblNewLabel_2_1.setText("Bu e-posta sistemde bulunamadı.");
		            lblNewLabel_2_1.setVisible(true);
		        }
		    }
		});

		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2_1.setBounds(85, 150, 180, 34);
		panel.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Şifrenizi Giriniz");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_2.setBounds(72, 58, 100, 38);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Şifrenizi Yeniden Giriniz");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2_1.setBounds(52, 106, 120, 38);
		panel.add(lblNewLabel_2_2_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 69, 96, 19);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(182, 116, 96, 19);
		panel.add(passwordField_1);
	}
	public void formuGoster() {
        frame.setVisible(true);
    }
}
