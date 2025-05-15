package src;

import java.sql.*;


public class OgretmenGirisKontrol implements IDogrulama {

    @Override
    public boolean girisDogrula(String eposta, String sifre) {
        boolean dogruMu = false;

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/obs";
            String user = "postgres";
            String dbPassword = "6923609.ilk";

            Connection conn = DriverManager.getConnection(url, user, dbPassword);

            String query = "SELECT * FROM calisan WHERE eposta = ? AND sifre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, eposta);
            stmt.setString(2, sifre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dogruMu = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dogruMu;
    }
}

