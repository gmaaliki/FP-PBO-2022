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
import javafx.scene.shape.Rectangle;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class startGame{
    private int x_tiles;
    private int y_tiles;
    
    public startGame(int widthOfTiles, int heightOfTiles) {
        this.x_tiles = widthOfTiles;
        this.y_tiles = heightOfTiles;
    }
    
    private int Width = x_tiles * Tile.TILE_SIZE;
    private int Height = y_tiles * Tile.TILE_SIZE;
    
    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Width, Height);
        
        for(int x = 0; x < x_tiles; ++x) {
            for(int y = 0; y < y_tiles; ++y) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);
                
                tile.grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        
        Tile tile = new Tile(1,1,true);
        for(int x = 0; x < x_tiles; ++x) {
            for(int y = 0; y < y_tiles; ++y) {
                tile = tile.grid[x][y];
                
                if(tile.getHasBomb()) continue;
                
                long bombs = tile.getNeighbors(tile).stream().filter(t -> t.getHasBomb()).count();
                
                if(bombs > 0)
                    tile.setTileText(Long.toString(bombs));
            }
        }
        return root;
    }
    
}
