package src.Class;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

public class ogrenciBilgiGetirici {
    private String eposta;
    private JLabel lblNewLabel_2;

    public ogrenciBilgiGetirici(String eposta, JLabel lblNewLabel_2) {
        this.eposta = eposta;
        this.lblNewLabel_2 = lblNewLabel_2;
    }

    // Öğretmenin ismini veritabanından alır
    public void ismiVeritabaniIleGetir() {
        String url = "jdbc:postgresql://localhost:5432/obs";
        String user = "postgres";
        String sifre = "6923609.ilk";

        String sql = "SELECT isim FROM calisan WHERE eposta = ?";

        try (Connection con = DriverManager.getConnection(url, user, sifre);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, eposta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String isim = rs.getString("isim");
                lblNewLabel_2.setText(isim); 
            } else {
                lblNewLabel_2.setText("Akademisyen bulunamadı");
            }

        } catch (SQLException e) {
            lblNewLabel_2.setText("Hata: " + e.getMessage());
        }
    }

    // Öğrencilerin notlarını veritabanından alır
    public DefaultTableModel notlariGetir(String eposta) {
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "ogrenci_isim", "vize_notu", "proje_notu", "final_notu" }, 0
        );

        String rol = ogretmenRoluGetir(eposta); // Öğretmenin rolünü alır

        if (rol.equals("")) {
            System.out.println("Rol bilgisi alınamadı.");
            return model;
        }

        try (Connection conn = VeriTabaniBaglantisi.baglantiAl()) {
            String sql = "SELECT ogrenci_isim, vize_notu, proje_notu, final_notu FROM \"" + rol + "\"";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String isim = rs.getString("ogrenci_isim");
                int vize = rs.getInt("vize_notu");
                int proje = rs.getInt("proje_notu");
                int finall = rs.getInt("final_notu");

                model.addRow(new Object[] { isim, vize, proje, finall });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tabloyu düzenleyici dinleyiciyi ekliyoruz
        JTable table = new JTable(model);
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                String columnName = table.getColumnName(column);
                Object newValue = table.getValueAt(row, column);

                // Eğer notla ilgili bir değişiklik yapılmışsa, bunu veritabanına güncelle
                if (columnName.equals("vize_notu") || columnName.equals("proje_notu") || columnName.equals("final_notu")) {
                    String ogrenciIsim = (String) table.getValueAt(row, 0);  // Öğrenci ismini al
                    updateNot(ogrenciIsim, columnName, newValue);  // Notu güncelle
                }
            }
        });

        return model;
    }

    // Öğretmenin rolünü veritabanından alır
    private String ogretmenRoluGetir(String eposta) {
        String rol = "";

        try (Connection conn = VeriTabaniBaglantisi.baglantiAl()) {
            String sql = "SELECT rol FROM calisan WHERE eposta = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, eposta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rol = rs.getString("rol"); // örnek: "matematik"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rol;
    }

    // Notları günceller
    public void updateNot(String ogrenciIsim, String columnName, Object newValue) {
        String rol = ogretmenRoluGetir(eposta); // Öğretmenin rolünü al

        if (rol.equals("")) {
            System.out.println("Rol bilgisi alınamadı.");
            return;
        }

        // SQL komutunu hazırlıyoruz
        String sql = "UPDATE \"" + rol + "\" SET " + columnName + " = ? WHERE ogrenci_isim = ?";

        try (Connection conn = VeriTabaniBaglantisi.baglantiAl()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            // newValue'yu doğru tipe dönüştürme
            if (newValue instanceof String) {
                // Eğer newValue String ise, integer'a dönüştürmeye çalışalım
                try {
                    int newValueInt = Integer.parseInt((String) newValue); // String'den Integer'a dönüşüm
                    ps.setInt(1, newValueInt);  // Yeni değeri Integer olarak ayarla
                } catch (NumberFormatException e) {
                    // Eğer dönüştürme başarısız olursa, hatayı loglayalım
                    System.out.println("Geçersiz not değeri: " + newValue);
                    return;
                }
            } else if (newValue instanceof Integer) {
                // Eğer newValue zaten Integer ise, olduğu gibi kullanabiliriz
                ps.setInt(1, (Integer) newValue);
            }

            ps.setString(2, ogrenciIsim);  // Öğrencinin adı

            int rowsAffected = ps.executeUpdate(); // Veritabanında güncellemeyi gerçekleştir
            if (rowsAffected > 0) {
                System.out.println("Not başarıyla güncellendi.");
            } else {
                System.out.println("Güncellenen hiçbir veri yok.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Veritabanı bağlantısını sağlayan sınıf
    public class VeriTabaniBaglantisi {

        private static final String URL = "jdbc:postgresql://localhost:5432/obs";
        private static final String KULLANICI = "postgres";
        private static final String SIFRE = "6923609.ilk";

        public static Connection baglantiAl() throws SQLException {
            return DriverManager.getConnection(URL, KULLANICI, SIFRE);
        }
    }
}
