package minesweeperapp;


import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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
    
}
