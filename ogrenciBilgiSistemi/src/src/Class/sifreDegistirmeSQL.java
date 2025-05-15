package src.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class sifreDegistirmeSQL {

    public boolean sifreDegistir(String eposta, String sifre) {
        try (Connection conn = DBBaglanti.baglantiAl()) {
            String sql = "UPDATE ogrenci SET sifre = ? WHERE LOWER(eposta) = LOWER(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, sifre);
            stmt.setString(2, eposta);

            int guncellenenSatir = stmt.executeUpdate();

            return guncellenenSatir > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public class girisDogrula2 {
        public boolean dogrula(String eposta) {
            try (Connection conn = DBBaglanti.baglantiAl()) {
                String sql = "SELECT * FROM ogrenci WHERE LOWER(eposta) = LOWER(?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, eposta);

                ResultSet rs = stmt.executeQuery();
                return rs.next(); // eposta varsa true döner
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}

class DBBaglanti {
    public static Connection baglantiAl() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/obs";
        String kullanici = "postgres";
        String sifre = "6923609.ilk";

        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, kullanici, sifre);
    }
}
