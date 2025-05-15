package src.Class;

import java.sql.*;
import java.util.List;

import javax.swing.JTable;

public class ogrenciNotBilgileriGetirici {

    private static final String URL = "jdbc:postgresql://localhost:5432/obs";
    private static final String USER = "postgres";
    private static final String PASSWORD = "6923609.ilk";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Epostaya göre öğrenci ismini çekme
    public String isimGetir(String eposta) {
        String isim = "";
        String query = "SELECT isim FROM ogrenci WHERE eposta = ?";  // ogrenci tablosunda eposta ve isim sütunları olmalı

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, eposta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isim = rs.getString("isim");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isim;
    }

    // NOTLARI ÇEKME İŞİNİ SENİN ogrenciNotGetirici sınıfına bırakıyoruz
    // Burada notları getiren sınıfı kullanalım:

    public List<ogrenciNotGetirici.DersNotu> notlariGetir(String ogrenciIsim) {
        ogrenciNotGetirici notGetirici = new ogrenciNotGetirici();
        return notGetirici.veriCek(ogrenciIsim);
    }

    // Tabloyu güncelleme metodu, GUI tarafında çağrılır
    public void tabloyuYenile(JTable table, String ogrenciIsim) {
        ogrenciNotGetirici notGetirici = new ogrenciNotGetirici();
        notGetirici.tabloyuYenile(table, ogrenciIsim);
    }
}
