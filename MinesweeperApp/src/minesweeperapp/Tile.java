package minesweeperapp;

import java.util.List;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;


public abstract class Tile extends StackPane  {
    private static final int TILE_SIZE = 40;
    private int x, y;
    private boolean hasBomb;
    private boolean isOpen = false;
    
    private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
    private Text text = new Text();
    
    private Scene scene;
    abstract public void open();
    abstract public Parent createContent();
    abstract public List<Tile>getNeighbors(Tile tile);
    
    public int getTileSize() {
        return TILE_SIZE;
    }
    
    public int getTileX() {
        return x;
    }
    
    public void setTileX(int x) {
        this.x = x;
    }
        
    public int getTileY() {
        return y;
    }
    
    public void setTileY(int y) {
        this.y = y;
    }
    
    public Rectangle getTileBorder() {
        return border;
    }

    public Text getTileText() {
        return text;
    }
    
    public void setTileText(String str) {
        text.setText(hasBomb ? "X" : "");
    }
    
    public void setTileTextVisible(boolean boo) {
        text.setVisible(boo);
    }
    
    public void setTileBorderFillNull() {
        border.setFill(null);
    }
    
    public boolean getTileIsOpen() {
        return isOpen;
    }    
    
    public void setTileIsOpen(boolean boo) {
        isOpen = true;
    }
    public boolean getTileHasBomb() {
        return hasBomb;
    }
    
    public void setTileHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }
    
}
