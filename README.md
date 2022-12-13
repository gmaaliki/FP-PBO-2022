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
...
System.out.println(pair.getValue() + "" + (String) pair.getKey());
...
```

###### Constructor
Constructor telah banyak saya pakai dalam kode ini. Tetapi yang ingin saya tunjukkan adalah penggunaan konstruktor dalam enum class saya yang berguna untuk menentukan tingkat kesusahan game
```
    ...
    Setting(int width, int height, int bombPercent) {
        this.width = width;
        this.height = height;
        this.bombPercent = bombPercent;
    }
    ...
```

###### Overloading
Overloading yang saya terapkan adalah di sebuah metode yang dapat mereturn waktu dan juga antara mengoutputkan ke console atau tidak
```
    ...
    public static int getTime() {
        return seconds;
    }
    
    public int getTime(boolean onConsole) {
        if(onConsole) System.out.println("The total time is " + seconds + "seconds");
        return seconds;
    }
    ...
```

###### Overriding
Saya melakukan override kepada metode yang ada di interface clickBehaviour untuk diimplementasikan kepada kelas GameTile dan juga override metode run untuk menghitung waktu
```
    @Override
    public void onClick(MouseEvent e) {
    .....
```

###### Encapsulation
Saya mengengkapsulasi berbagai hal sebagai bentuk dari penerapan OOP. Salah satunya adalah jumlah permainan per sesi dan waktu yang tercatat. dan saya akan mengakses variable tersebut dengan metode.
```
    private static int seconds;
    private static int numberOfGames;
    
    public void gameWon() {
        numberOfGames++;
    }
    
    public void resetTime() {
        seconds = 0;
    }
    
    public void incTime() {
        seconds++;
    }
```
###### Inheritance
Saya menginherit basis dari GameTile yaitu BaseTile yang berisi ukuran serta catatan bomb yang ada.
```
public class GameTile extends BaseTile implements ClickBehaviour {
...
```
###### Polymorphism
Beberapa contoh dalam polymorphism adalah dari overloading dan overriding yang sudah saya jelaskan sebelumnya.

###### ArrayList
Array List saya gunakan untuk melakukan sort kepada skor dari HashSet dan juga sebagai penampung object Pair yang menyimpan data nama pemain dan skor terbaik
```
ArrayList<Pair<String,Integer>> highScoreList = new ArrayList<>();
```

###### Exception Handling
Pada metode getHighScore saya perlu menggunakan input file jadi saya gunakan throw FileNotFoundException untuk mengantisipasi apabila filenya hilang
```
public ArrayList<Pair<String,Integer>> getHighScore() throws FileNotFoundException {
  ...
```
###### GUI
GUI yang saya terapkan adalah menggunakan JavaFX dan sebagian besar terdiri dari button yang menggambarkan petak-petak pada game minesweeper
```
```
###### Interface
Saya memakai sebuah interface mouseBehaviour untuk GameTile yang berfungsi untuk menaruh keharusan mengimplementasikan fungsi klik pada tile
```
public interface ClickBehaviour {
    // Object tile harus bisa di click
    abstract void onClick(MouseEvent e);
    abstract void blankClick(GameTile tile);
}
```
###### Abstract Class
Saya menggunakan abstract class BaseTile sebagai basis pembangunan GameTile yang akan dipakai untuk game.
```
public abstract class BaseTile extends StackPane {
    
    boolean hasBomb;
    Button btn = new Button();
    static final int TILE_SIZE = 30;
    
    BaseTile () {
        this.hasBomb = hasBomb;

        if (hasBomb) {
            Main.numBombs++;
        }

        btn.setMinHeight(TILE_SIZE);
        btn.setMinWidth(TILE_SIZE);
    }
    
}public abstract class BaseTile extends StackPane {
    
    boolean hasBomb;
    Button btn = new Button();
    static final int TILE_SIZE = 30;
    
    BaseTile () {
        this.hasBomb = hasBomb;

        if (hasBomb) {
            Main.numBombs++;
        }

        btn.setMinHeight(TILE_SIZE);
        btn.setMinWidth(TILE_SIZE);
    }
    
}

```
###### Collection
Saya menerapkan fitur skor dimana skor terbaik untuk setiap nama pemain akan disimpan dan akan ditunjukkan di akhir game. Oleh karena itu saya menggunakan HashSet agar tidak terjadi duplikat diantara nama yang sama.
```
HashSet<Pair> highScoreSet = new HashSet<>();
...
        for(Pair i : highScoreSet) {
            Pair pair = new Pair(i.getKey(),(int) i.getValue());
            highScoreList.add(pair);
        }
```
###### Input Output
Penyimpanan skor adalah menggunakan file .txt jadi saya menerapkan fungsi input output untuk melakukan fungsi read dan write agar bisa mendapat rekaman skor dari semua permainan yang dimainkan di lokal.
https://github.com/gmaaliki/FP-PBO-2022/blob/main/MinesweeperApp/src/minesweeperapp/Score.java#L29-L38
