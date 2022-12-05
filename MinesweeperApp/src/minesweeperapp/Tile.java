package minesweeperapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Border;
import javafx.scene.shape.Rectangle;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends StackPane {
    static final int TILE_SIZE = 40;
    private int x, y;
    protected boolean hasBomb = false;
    private boolean isOpen = false;
    protected int x_tiles;
    protected int y_tiles;
    protected Tile[][] grid = new Tile[x_tiles][y_tiles];

    private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
    private Text text = new Text();
    
    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        
        border.setStroke(Color.LIGHTGRAY);
        
        text.setFont(Font.font(18));
        text.setText(hasBomb ? "X" : ""); // nanti mau dikasih gambar
        text.setVisible(false);
        
        getChildren().addAll(border, text);
        
        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
        
        // set on click belum ada
        
    }
    
    public List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();
        
        int[][] points = new int[][] {
            {-1,-1},
            {-1,0},
            {-1,1},
            {0,-1},
            {0,1},
            {1,-1},
            {1,0},
            {1,1}
        };
        
        for(int i = 0; i < points.length; ++i) {
            int dx = points[i][0];
            int dy = points[i][1];
            
            int newX = tile.x + dx;
            int newY = tile.y + dy;
            
            if(newX >= 0 && newX < x_tiles
                    && newY >= 0 && newY < y_tiles) {
                neighbors.add(grid[newX][newY]);
            }
        }
        
        return neighbors;
    }
    
    public void open() {
        if(isOpen) return;
        
        if(hasBomb) {
            System.out.println("Game Over");
        }
        
        isOpen = true;
        text.setVisible(true);
        border.setFill(null);
        
        if(text.getText().isEmpty()) {
            getNeighbors(this).forEach(Tile::open);
        }
    }
    
//    public void open() {
//        if(isOpen)
//            return;
//        
//        if(hasBomb) {
//            System.out.println("Game Over");
//            scene.setRoot(createContent());
//            return;
//            
//            isOpen = true;
//            text.setVisible(true);
//            border.setFill(null);
//            
//            if(text.getText().isEmpty()) {
//                getNeighbors(this).forEach(tile::open);
//            }
//        }
//    }
    
    public int getSize() {
        return TILE_SIZE;
    }
    
    public boolean getHasBomb() {
        return hasBomb;
    }
    
    public void setTileText(String bombs) {
        text.setText(bombs);
    }
}
