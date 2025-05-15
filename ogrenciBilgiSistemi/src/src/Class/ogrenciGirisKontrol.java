package src.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ogrenciGirisKontrol extends ogrenciDogrulama {

    private final String url = "jdbc:postgresql://localhost:5432/obs";
    private final String user = "postgres";
    private final String password = "6923609.ilk";

    @Override
    public boolean girisDogrula(String eposta, String sifre) {
        String sql = "SELECT * FROM ogrenci WHERE eposta = ? AND sifre = ?";
        
        // Veritabanı bağlantısı ve kaynakları try-with-resources içinde yönetiyoruz.
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eposta);
            stmt.setString(2, sifre);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Eğer eşleşen bir kayıt varsa true döner
                    return true;
                } else {
                    // Eğer eşleşen bir kayıt yoksa false döner
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Hata detaylarını görmeniz için
            return false;
        }
    }
}
