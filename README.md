# FP-PBO-2022
## Final Project Pemrograman Berorientasi Objek
Nama    : Ghifari Maaliki Syafa Syuhada
NRP     : 5025211158

Untuk final project PBO saya akan membuat game minesweeper sederhana dengan menggunakan bahasa pemrograman Java dan menggunakan javafx untuk GUInya. Minesweeper adalah game yang permainannya terdiri dari beberapa kotak-kotak yang tertutup. Dibalik beberapa kotak-kotak tersebut terdapat beberapa ranjau. Tujuan dari gamenya adalah untuk membuka semua kotak yang tidak memiliki ranjau. Pada kotak kosong yang telah kita buka akan terdapat petunjuk berupa angka yang menggambarkan jumlah ranjau yang disentuh oleh kotak tersebut baik secara vertikal, horizontal, maupun diagonal. Hasil yang diharapkan adalah game minesweeper sederhana yang bisa dimainkan dengan mengikuti aturan-aturan sebelumnya.

Berikut link refernsi yang saya pakai :
https://github.com/avestura/Minesweeper-Persian/tree/master/src/DesignView

## Aspek OOP yang digunakan
###### Casting/Conversion
Saya menggunakan sebuah Pair<String,Integer> yang menggambarkan high score dari pemain. Salah satu dari pair itu akan disortir untuk mencari yang tertinggi dan setelah itu kedua elemen pair akan dijadikan Text. Maka dari itu saya perlu mengcasting Integer menjadi String
```

```

###### Constructor
Constructor telah banyak saya pakai dalam kode ini. Tetapi yang ingin saya tunjukkan adalah penggunaan konstruktor dalam enum class saya yang berguna untuk menentukan tingkat kesusahan game
```

```

###### Overloading
Overloading yang saya terapkan adalah di sebuah metode yang dapat mereturn waktu dan juga antara mengoutputkan ke console atau tidak
```

```

###### Overriding
Saya melakukan override kepada metode yang ada di interface clickBehaviour untuk diimplementasikan kepada kelas GameTile dan juga override metode run untuk menghitung waktu
```
```

###### Encapsulation
Saya mengengkapsulasi berbagai hal sebagai bentuk dari penerapan OOP. Salah satunya adalah jumlah permainan per sesi dan waktu yang tercatat. dan saya akan mengakses variable tersebut dengan metode.
```
```
###### Inheritance
Saya menginherit basis dari GameTile yaitu BaseTile yang berisi ukuran serta catatan bomb yang ada.
```
```
###### Polymorphism
Beberapa contoh dalam polymorphism adalah dari overloading dan overriding yang sudah saya jelaskan sebelumnya.
```

```
###### ArrayList
Array List saya gunakan untuk melakukan sort kepada skor dari HashSet dan juga sebagai penampung object Pair yang menyimpan data nama pemain dan skor terbaik
```
```

###### Exception Handling
Pada metode getHighScore saya perlu menggunakan input file jadi saya gunakan throw FileNotFoundException untuk mengantisipasi apabila filenya hilang
```

```
###### GUI
GUI yang saya terapkan adalah menggunakan JavaFX dan sebagian besar terdiri dari button yang menggambarkan petak-petak pada game minesweeper
```
```
###### Interface
Saya memakai sebuah interface mouseBehaviour untuk GameTile yang berfungsi untuk menaruh keharusan mengimplementasikan fungsi klik pada tile
```
```
###### Abstract Class
Saya menggunakan abstract class BaseTile sebagai basis pembangunan GameTile yang akan dipakai untuk game.
```
```
###### Generics
```

```
###### Collection
Saya menerapkan fitur skor dimana skor terbaik untuk setiap nama pemain akan disimpan dan akan ditunjukkan di akhir game. Oleh karena itu saya menggunakan HashSet agar tidak terjadi duplikat diantara nama yang sama.
```
```
###### Input Output
Penyimpanan skor adalah menggunakan file .txt jadi saya menerapkan fungsi input output untuk melakukan fungsi read dan write agar bisa mendapat rekaman skor dari semua permainan yang dimainkan di lokal.
```

```
