# Öğrenci Bilgi Sistemi
## Proje Konusu
Bu proje okullarda öğrenci ve öğretmenlerin bilgi alışverişini daha düzenli ve güvenli bir şekilde gerçekleştirebilmesini sağlayan bir Öğrenci Bilgi Sistemi geliştirmeyi amaçlamaktadır. Sistem öğrencilerin notlarını görüntüleyebilmesini, öğretmenlerin ise öğrenci bilgilerini ve notlarını düzenleyebilmesini sağlar.
# Kurulum Kılavuzu
## Gereksinimler
    • JDK 23 
    • Eclipse IDE
    • PostgreSQL (Veritabanı için)
    • postgresql-42.7.5.jar (Veritabanı ile Eclipse Bağlantısı için)
    • Java Swing UI bileşenleri  
## Eclipse IDE Kurulumu
    1. Eclipse resmi web sitesine giriniz.
    2. ".exe" uzantılı dosyayı indirin.
    3. İndirme bittikten sonra eclipse installer'ı çalıştırınız.
    4. Kurulum aşamasında ekranda bulunan listeden "Eclipse IDE for Java Developers" seçeneğini seç ve install butonuna bas.
    5. Eğer eclipse, bilgisayarda JDK dosyası bulamazsa "A Java Runtime Environment (JRE) or Java Development Kit (JDK) must be available..." hatası verir.
    6. JDK indirdikten sonra eclipse çalıştırılabilir.
## PostgreSQL ve PostgreSQL Jar Dosyası Kurulumu
    1. PostgreSQL Kurulumu:
        ◦ PostgreSQL resmi sitesine gidiniz.
        ◦ "Download the installer" seçeneğini seçiniz.
        ◦ İndirilen dosyayı çalıştırınız ve kurulumu tamamlayınız.
    
    2. PostgreSQL JDBC (Jar) Dosyasını Projeye Ekleme
        ◦ jdbc.postgresql.org sitesine gidiniz.
        ◦ En güncel JAR dosyasını indiriniz.
        ◦ Eclipse'te Projenin üstüne sağ tıklayınız
        ◦ Açılan pencerede Build Path > Configure Build Path seçeneğini seçiniz.
        ◦ Açılan pencerede Libraries sekmesine geliniz.
        ◦ Sağ tarafta bulunan Add External JARs kısmını seçiniz.
        ◦ Açılan sekmede indirdiğiniz jar dosyasını seçiniz ve projeye ekleyiniz.

## Projeyi Çalıştırma
    1. Eclipse IDE üzerinden projeyi açın.
    2. Package Explorer içinde Giris.java dosyasını bul.
    3. Giris.java dosyasının üzerine sağ tıkla.
    4. Açılan menüden Run As > Java Application seçeneğine tıkla.

## Olası Sorunlar ve Çözümleri
    • Veritabanı Bağlantı Hatası: PostgreSQL servisinin aktif olduğundan emin olun.
    • JAR Dosyası Bulunamadı Hatası: JAR dosyasının doğru şekilde projeye eklendiğinden emin olun.
    • Compile Hataları: JDK versiyonunuzun proje ile uyumlu olduğundan emin olun.

## Projenin Yapısı
Projede toplamda 16 adet Java sınıfı bulunmakta ve bunlar farklı paketlerde organize edilmiştir:
├── src/           # Form Sınıfı  
│   ├── AkademisyenGiris.java  
│   ├── Giris.java (Main Class)  
│   ├── IDogrulama.java  
│   ├── ogrenciGiris.java  
│   ├── ogrenciNotlari.java  
│   ├── ogrenciSifreDegistirme.java  
│   ├── ogretmenGirisKontrol.java  
│   ├── ogretmenNotGiris.java  
├── src.Class/     # Projenin Core Kısmı (Java Özellikleri Class'ı)  
│   ├── ButonRenkEfekti.java  
│   ├── ogrenciBilgiGetirici.java  
│   ├── ogrenciDogrulama.java  
│   ├── ogrenciEpostaDogrulama.java  
│   ├── ogrenciEpostaKontrol.java  
│   ├── ogrenciGirisKontrol.java  
│   ├── ogrenciNotBilgileriGetirici.java  
│   ├── ogrenciNotGetirici.java  
│   ├── sifreDegistirmeSQL.java  
│   └── Tasima.java  

# Teknik Detaylar
## Kullanılan Veritabanı Türü
    • PostgreSQL veritabanı
    • JDBC kullanımı mevcut
    • PostgreSQL JDBC Driver kullanıldı
## Kullanılan Geliştirme Ortamı
    • Eclipse IDE
    • Java Swing eklentisi
## PostgreSQL Avantajları:
    • Açık kaynak ve ücretsiz, güçlü bir veritabanı sistemi
    • Büyük ölçekli ve karmaşık verilerle rahatça çalışabilir
    • Gelişmiş güvenlik ve performans özellikleri sunar
## PostgreSQL Dezavantajları:
    • Kaynak tüketimi küçük, basit projeler için gereksiz olabilir
    • Yönetim ve bakım için biraz daha teknik bilgi gerektirir
# Mimari
## Katmanlı Mimari:
    • SRC Klasörü: Bu klasörde projedeki arayüzler ve interface’ler yer almaktadır. Yani kullanıcı ile etkileşimi sağlayan Java Swing formları ve tanımlı interface’ler burada bulunur.
    • src.class Klasörü: Burada ise veritabanı işlemleri, iş mantığı ve yardımcı sınıflar toplanmıştır. Örneğin şifre değiştirme, öğretmen ve öğrenci giriş işlemleri gibi fonksiyonlar bu klasörde yer alır. Ayrıca taşıma ve ButonRenkDeğiştirme gibi yardımcı sınıflar da buradadır.
## Projenin Özellikleri
    • Kullanıcıların güvenli şekilde giriş yapabildiği bir sistemdir.
    • Öğrenci bilgileri ve notları takip edilebilir.
    • Öğretmenler öğrenci notlarını kolayca güncelleyebilir.
## Geliştirmek İçin Neler Yapılabilir?
    1. Kullanıcı arayüzü tasarımı daha modern hale getirilebilir.
    2. Şifre Değiştirme, E-Posta ile giriş kısımlarına doğrulama yöntemleri eklenebilir. 
    3. Uygulama içinden Öğretmen ve Öğrenci ekleme, çıkarma gibi işlemler gerçekleştirilmesi için "Müdür" girişi eklenebilir.
    4. Şifreler veritabanında güvenli şekilde, hash ve salt kullanılarak saklanabilir.
## Olumlu Yönler
    • Java Swing kullanılarak kullanıcı dostu arayüz oluşturuldu.
    • PostgreSQL veritabanı ile kolay veri yönetimi sağlandı.
    • Proje temel öğrenci ve öğretmen işlemlerini kapsıyor ve test edilebilir durumda.
## Olumsuz Yönler
    • Arayüz Swing teknolojisiyle yapıldığı için modern görünümden uzak.
    • Veritabanında şifreler hash ve salt kullanılarak saklanmadı ve güvenlik açığı oluştu.
    • Küçük bir projede PostgreSQL kullanıldı.   
    • Uygulama içinden Öğrenci, Öğretmen bilgileri güncelleme kısmı bulunmuyor.
## Bu Projeyle Neler Yapılabilir?
    • Öğrenci ve öğretmen kullanıcıları için güvenli giriş ve kayıt işlemleri yapılabilir.
    • Öğrencilerin kişisel bilgileri, ders kayıtları ve notları takip edilebilir.
    • Öğretmenler öğrenci notlarını girebilir, güncelleyebilir ve raporlayabilir.
    • Kullanıcı dostu grafik arayüz ile etkileşimli ve kolay kullanım sağlanabilir.
# Hazırlayanlar
    • İlkmert Döner
    • Emirhan Tunca
    • Enes Bekdemir
