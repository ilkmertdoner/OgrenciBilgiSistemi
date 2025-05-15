package src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;
import src.Class.ogrenciNotBilgileriGetirici;
import src.Class.ogrenciNotGetirici;
import java.awt.Component;

public class ogrenciNotlari {

    private JFrame frame;
    private JLabel lblOgrenciIsmi;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ogrenciNotlari window = new ogrenciNotlari();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ogrenciNotlari() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setBounds(100, 100, 550, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 550, 32);
        panel_1.setName("panel");
        panel_1.setBackground(Color.BLACK);
        panel_1.setLayout(null);

        JLabel lblTitle = new JLabel("Öğrenci Bilgi Sistemi");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitle.setBounds(32, -1, 160, 32);
        lblTitle.setForeground(Color.WHITE);
        panel_1.add(lblTitle);

        JLabel lblIcon = new JLabel("");
        lblIcon.setBounds(0, 0, 31, 32);
        ImageIcon orijinalIcon = new ImageIcon(Giris.class.getResource("/Source/icon.png"));
        Image scaledImage = orijinalIcon.getImage().getScaledInstance(
                lblIcon.getWidth(),
                lblIcon.getHeight(),
                Image.SCALE_SMOOTH);
        lblIcon.setIcon(new ImageIcon(scaledImage));
        panel_1.add(lblIcon);

        JButton btnClose = new JButton("X");
        btnClose.setForeground(Color.WHITE);
        btnClose.addActionListener(e -> System.exit(0));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnClose.setBorder(null);
        btnClose.setBackground(Color.BLACK);
        btnClose.setBounds(519, -1, 31, 32);
        panel_1.add(btnClose);

        JButton btnMinimize = new JButton("_");
        btnMinimize.setVerticalAlignment(SwingConstants.TOP);
        btnMinimize.setForeground(Color.WHITE);
        btnMinimize.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
        frame.getContentPane().setLayout(null);
        btnMinimize.setBackground(Color.BLACK);
        btnMinimize.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnMinimize.setBorder(null);
        btnMinimize.setBounds(488, -1, 31, 32);
        panel_1.add(btnMinimize);

        new Tasima(frame, panel_1);
        frame.getContentPane().add(panel_1);

        new ButonRenkEfekti(btnClose, Color.RED);
        new ButonRenkEfekti(btnMinimize, Color.DARK_GRAY);

        JPanel panel = new JPanel();
        panel.setBounds(0, 33, 550, 32);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        lblOgrenciIsmi = new JLabel("");
        lblOgrenciIsmi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOgrenciIsmi.setHorizontalAlignment(SwingConstants.LEFT);
        lblOgrenciIsmi.setBounds(35, 0, 515, 32);
        panel.add(lblOgrenciIsmi);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 65, 550, 285);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Ders Ad\u0131", "Vize Notu", "Proje Notu", "Final Notu"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		true, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        for (int i = 0; i < 4; i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
    }

    public void formuGoster(String ogrenciEposta) {
        // Burada ogrenciNotBilgileriGetirici üzerinden isim alınmalı
        ogrenciNotBilgileriGetirici bilgiGetirici = new ogrenciNotBilgileriGetirici();
        String ogrenciAdi = bilgiGetirici.isimGetir(ogrenciEposta);

        lblOgrenciIsmi.setText("Öğrenci: " + ogrenciAdi); // Artık doğru isim gösteriliyor

        // Not bilgilerini çekme işlemi aynı kalabilir
        ogrenciNotGetirici notBilgi = new ogrenciNotGetirici();
        List<ogrenciNotGetirici.DersNotu> dersNotlari = notBilgi.veriCek(ogrenciAdi);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (ogrenciNotGetirici.DersNotu not : dersNotlari) {
            model.addRow(new Object[] {
                not.dersAdi,
                not.vizeNotu,
                not.projeNotu,
                not.finalNotu
            });
        }

        frame.setVisible(true);
    }






}
