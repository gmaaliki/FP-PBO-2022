package minesweeperapp;

import javafx.scene.input.MouseEvent;

public interface ClickBehaviour {
    // Object tile harus bisa di click
    abstract void onClick(MouseEvent e);
    abstract void blankClick(Tile tile);
}
