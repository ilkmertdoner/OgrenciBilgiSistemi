package src.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ogrenciNotGetirici {
    private static final String URL = "jdbc:postgresql://localhost:5432/obs";
    private static final String USER = "postgres";
    private static final String PASSWORD = "6923609.ilk";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Öğrenci ismine göre ders notlarını çekme
    public List<DersNotu> veriCek(String ogrenciIsim) {
        List<DersNotu> dersNotlari = new ArrayList<>();
        
        String[] tabloIsimleri = {
            "\"Matematik\"",
            "\"Görsel Programlama\"",
            "\"Veritabanı\"",
            "\"Web Programlama\""
        };

        try (Connection connection = connect()) {
            for (String tabloAdi : tabloIsimleri) {
                // Öğrenciye ait notları çekmek için öğrenci ismini sorguya dahil ediyoruz
                String query = "SELECT vize_notu, proje_notu, final_notu FROM " + tabloAdi 
                                + " WHERE ogrenci_isim = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, ogrenciIsim); // Öğrenci ismini parametre olarak kullanıyoruz
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            float vizeNotu = resultSet.getFloat("vize_notu");
                            float projeNotu = resultSet.getFloat("proje_notu");
                            float finalNotu = resultSet.getFloat("final_notu");

                            dersNotlari.add(new DersNotu(tabloAdi, vizeNotu, projeNotu, finalNotu));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dersNotlari;
    }

    // DersNotu sınıfı
    public static class DersNotu {
        public String dersAdi;
        public float vizeNotu;
        public float projeNotu;
        public float finalNotu;

        public DersNotu(String dersAdi, float vizeNotu, float projeNotu, float finalNotu) {
            this.dersAdi = dersAdi;
            this.vizeNotu = vizeNotu;
            this.projeNotu = projeNotu;
            this.finalNotu = finalNotu;
        }
    }

    // Tabloyu güncelleme
    public void tabloyuYenile(JTable table, String ogrenciIsim) {
        List<DersNotu> notlar = veriCek(ogrenciIsim);  // veriCek'i burada doğrudan çağırıyoruz

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Tabloyu temizle

        for (DersNotu not : notlar) {
            // Ders adını temizlerken replace'i burada kullanıyoruz
            model.addRow(new Object[]{not.dersAdi.replace("\"", ""), not.vizeNotu, not.projeNotu, not.finalNotu});
        }
        
    }
    

}
