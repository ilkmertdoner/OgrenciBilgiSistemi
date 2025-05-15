package src.Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ogretmenNotGetirici {
	private static final String URL = "jdbc:postgresql://localhost:5432/obs";
    private static final String KULLANICI = "postgres";
    private static final String SIFRE = "6923609.ilk";

    // Epostayı al, isim label2'ye ata, rolü bul ve ilgili tablonun verilerini JTable'a yaz
    public void islemleriYap(String eposta, JLabel label2, JTable tablo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, KULLANICI, SIFRE);

            // 1. Epostaya göre isim ve rolü al
            String sorgu = "SELECT isim, rol FROM calisan WHERE eposta = ?";
            ps = conn.prepareStatement(sorgu);
            ps.setString(1, eposta);
            rs = ps.executeQuery();

            String isim = null;
            String rol = null;

            if (rs.next()) {
                isim = rs.getString("isim");
                rol = rs.getString("rol");

                // 2. label2'ye ismi yaz
                label2.setText(isim);
            }

            if (rol != null && !rol.isEmpty()) {
                // 3. Rol (ders adı) ile eşleşen tablonun içeriğini JTable'a aktar
                String dersSorgusu = "SELECT * FROM " + rol.toLowerCase(); // tablo adı küçük harfle varsayıldı
                ps = conn.prepareStatement(dersSorgusu);
                rs = ps.executeQuery();

                // 4. Tablo modelini oluştur
                ResultSetMetaData metaData = rs.getMetaData();
                int sutunSayisi = metaData.getColumnCount();
                DefaultTableModel model = new DefaultTableModel();

                // 5. Sütun adlarını ekle
                for (int i = 1; i <= sutunSayisi; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }

                // 6. Verileri ekle
                while (rs.next()) {
                    Object[] satir = new Object[sutunSayisi];
                    for (int i = 1; i <= sutunSayisi; i++) {
                        satir[i - 1] = rs.getObject(i);
                    }
                    model.addRow(satir);
                }

                // 7. JTable'a modeli aktar
                tablo.setModel(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
