# Vocabulity - Dil Öğrenme Uygulaması

**Vocabulity**, kullanıcıların kelime öğrenmesini ve tekrar etmesini kolaylaştıran bir dil öğrenme uygulamasıdır. Kullanıcılar, kelime kartlarını sağa veya sola kaydırarak bu kartları kaydedebilirler. Uygulama modern Android teknolojilerini kullanarak hızlı, akıcı ve güvenilir bir deneyim sunar.

https://play.google.com/store/apps/details?id=com.baitent.vocabulity

## Özellikler

- **Kart Sistemi**: Kullanıcılar yeni kelime kartlarını sağa veya sola kaydırarak öğrenebilir ve favori kartlarını kaydedebilirler.
- **Swipe to Refresh**: Kelime kartları listesini yenilemek için aşağı çekip bırakma hareketi.
- **Kelime Kayıt ve Yönetimi**: Kelimeler Room veritabanında saklanır ve offline olarak erişilebilir.
- **MVVM Mimari Deseni**: Uygulama, modern MVVM mimarisini takip eder. ViewModel, LiveData ve Repository kullanımı ile temiz kod ve kolay yönetilebilirlik sağlanır.
- **Hilt ile Dependency Injection**: Hilt kullanılarak bağımlılık yönetimi yapılır ve bileşenlerin kolayca yönetimi sağlanır.
- **Jetpack Navigation**: Uygulama içerisinde ekranlar arasında gezinti Jetpack Navigation ile sağlanır.
- **ViewBinding**: Kullanıcı arayüzü bileşenlerine güvenli ve verimli bir şekilde erişmek için ViewBinding kullanılır.

## Ekranlar

### 1. Ana Ekran (Word Cards)
Kullanıcılar burada kelime kartlarını görebilirler. Her kartta bir kelime ve anlamı bulunur. Kartı sağa kaydırarak kartı "öğrenildi" olarak işaretleyebilir veya sola kaydırarak tekrar edilmesi gereken kartlar arasına ekleyebilir.

### 2. Öğrenilen ve Öğrenilmesi Gerekenler Listesi
Kullanıcıların sağa kaydırarak kaydettiği favori kartların listelendiği ekran. Bu ekran, daha sonra tekrar etmek istedikleri kelimeleri içerir.

### 3. Kelime Listesi Yenileme (Swipe to Refresh)
Kullanıcılar, kart listesini yukarıdan aşağı çekerek listeyi yenileyebilirler. Bu özellik, yeni kelimelerin yüklenmesini sağlar.

## Kullanılan Teknolojiler

- **Room Database**: Kelimeleri saklamak ve yönetmek için kullanılır.
- **ViewModel**: Uygulama durumunu ve verileri yönetir.
- **LiveData**: UI güncellemeleri için kullanılır.
- **Hilt**: Dependency Injection (bağımlılık enjeksiyonu) framework'ü ile kolay bileşen yönetimi sağlar.
- **Jetpack Navigation**: Uygulamadaki ekranlar arasında geçiş yapmayı sağlar.
- **SwipeFlingAdapterView**: Kelime kartlarının sağa sola kaydırılarak yönetilmesi için kullanılır.
- **Swipe to Refresh**: Kart listesi yenileme özelliğini sağlar.
- **ViewBinding**: Güvenli ve performanslı UI bileşenlerine erişim sağlar.

## Kurulum

1. Projeyi klonlayın:

   ```bash
   git clone https://github.com/HacerYaman/Vocabulity.git

<img src="https://github.com/user-attachments/assets/7fa59d22-26ac-4705-b8a0-048ef7351b9d" alt="0" width="400">

<img src="https://github.com/user-attachments/assets/6680f2b9-31e7-4022-bef8-304c28cd2321" alt="1" width="400">

<img src="https://github.com/user-attachments/assets/f808affd-8f03-44b5-be40-50e505d79e3b" alt="2" width="400">

<img src="https://github.com/user-attachments/assets/2a16c5b5-9b8e-4c11-a8a6-e6999343eab7" alt="3" width="400">

<img src="https://github.com/user-attachments/assets/cf31f985-b2ab-4f86-848c-c5dd546e8406" alt="4" width="400">

