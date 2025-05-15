package src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Class.ButonRenkEfekti;
import src.Class.Tasima;
import src.Class.ogrenciBilgiGetirici;

public class ogretmenNotGirisi {

    private JFrame frame;
    private JTable table;
    private JLabel lblNewLabel_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ogretmenNotGirisi window = new ogretmenNotGirisi();
                window.formuGoster("ogretmen1@okul.edu.tr"); // örnek e-posta
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public ogretmenNotGirisi() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setBounds(100, 100, 550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Üst panel
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 550, 32);
        panel_1.setBackground(Color.BLACK);
        panel_1.setLayout(null);
        frame.getContentPane().add(panel_1);

        JLabel lblNewLabel = new JLabel("Öğrenci Bilgi Sistemi");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(32, -1, 200, 32);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel();
        lblNewLabel_1.setBounds(0, 0, 31, 32);
        panel_1.add(lblNewLabel_1);

        ImageIcon orijinalIcon = new ImageIcon(Giris.class.getResource("/Source/icon.png"));
        Image scaledImage = orijinalIcon.getImage().getScaledInstance(
                lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(), Image.SCALE_SMOOTH);
        lblNewLabel_1.setIcon(new ImageIcon(scaledImage));

        JButton btnKapat = new JButton("X");
        btnKapat.setForeground(Color.WHITE);
        btnKapat.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnKapat.setBorder(null);
        btnKapat.setBackground(Color.BLACK);
        btnKapat.setBounds(519, -1, 31, 32);
        btnKapat.addActionListener(e -> System.exit(0));
        panel_1.add(btnKapat);

        JButton btnSimge = new JButton("_");
        btnSimge.setVerticalAlignment(SwingConstants.TOP);
        btnSimge.setForeground(Color.WHITE);
        btnSimge.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSimge.setBorder(null);
        btnSimge.setBackground(Color.BLACK);
        btnSimge.setBounds(488, -1, 31, 32);
        btnSimge.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
        panel_1.add(btnSimge);

        new Tasima(frame, panel_1);
        new ButonRenkEfekti(btnKapat, Color.RED);
        new ButonRenkEfekti(btnSimge, Color.DARK_GRAY);

        // Alt panel - isim yazdırma
        JPanel panel = new JPanel();
        panel.setBounds(0, 33, 550, 32);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        lblNewLabel_2 = new JLabel("...");
        lblNewLabel_2.setBounds(29, 0, 494, 32);
        panel.add(lblNewLabel_2);

        // Tablo
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 64, 550, 336);
        frame.getContentPane().add(scrollPane);

        // Tabloyu düzenleyici dinleyiciyi ekliyoruz
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String columnName = table.getColumnName(column);
            Object newValue = table.getValueAt(row, column);

            // Eğer notla ilgili bir değişiklik yapılmışsa, bunu veritabanına güncelle
            if (columnName.equals("vize_notu") || columnName.equals("proje_notu") || columnName.equals("final_notu")) {
                String ogrenciIsim = (String) table.getValueAt(row, 0);  // Öğrenci ismini al
                ogrenciBilgiGetirici getirici = new ogrenciBilgiGetirici("ogretmen1@okul.edu.tr", lblNewLabel_2);
                getirici.updateNot(ogrenciIsim, columnName, newValue);  // Notu güncelle
            }
        });
    }

    public void formuGoster(String eposta) {
        frame.setVisible(true);

        ogrenciBilgiGetirici getirici = new ogrenciBilgiGetirici(eposta, lblNewLabel_2);
        getirici.ismiVeritabaniIleGetir(); // isim etiketi

        DefaultTableModel tabloModeli = getirici.notlariGetir(eposta);
        table.setModel(tabloModeli); // tabloya notları yerleştir

        // Tabloda yapılan değişiklikleri kaydetmek için dinleyici ekliyoruz
     // Tabloyu düzenleyici dinleyiciyi ekliyoruz
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String columnName = table.getColumnName(column);
            Object newValue = table.getValueAt(row, column);

            // Eğer notla ilgili bir değişiklik yapılmışsa, bunu veritabanına güncelle
            if (columnName.equals("vize_notu") || columnName.equals("proje_notu") || columnName.equals("final_notu")) {
                String ogrenciIsim = (String) table.getValueAt(row, 0);  // Öğrenci ismini al
                ogrenciBilgiGetirici getirici1 = new ogrenciBilgiGetirici("ogretmen1@okul.edu.tr", lblNewLabel_2);
                getirici1.updateNot(ogrenciIsim, columnName, newValue);  // Notu güncelle
            }
        });

    }

}
