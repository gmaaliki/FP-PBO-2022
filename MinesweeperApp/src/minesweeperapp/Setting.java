package minesweeperapp;

public enum Setting {
    EASY(10,10,10),
    MEDIUM(16,13,15),
    HARD(30,16,20);
    
    int width;
    int height;
    int bombPercent;
    
    Setting(int width, int height, int bombPercent) {
        this.width = width;
        this.height = height;
        this.bombPercent = bombPercent;
    }
}