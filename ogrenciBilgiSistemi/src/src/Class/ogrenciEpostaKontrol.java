package src.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ogrenciEpostaKontrol extends ogrenciEpostaDogrulama {

    private final String url = "jdbc:postgresql://localhost:5432/obs";
    private final String user = "postgres";
    private final String password = "6923609.ilk";

    @Override
    public boolean girisDogrula1(String eposta) {
        String sql = "SELECT * FROM ogrenci WHERE eposta = ?";
        
        // Veritabanı bağlantısı ve kaynakları try-with-resources içinde yönetiyoruz.
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, eposta);

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
