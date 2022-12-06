package minesweeperapp;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tiles extends Tile{
    private final int Width;
    private final int Height;
    private int x_tiles;
    private int y_tiles;
    
    public void setXTiles(int x) {
        this.x_tiles = x;
    }
    
    public void setYTiles(int y) {
        this.y_tiles = y;
    }
    
    
    Tiles[][] grid;
    Scene scene = new Scene(createContent());
    
    
    public Tiles(int tile_x, int tile_y, Tiles[][] grid) {
        this.grid = grid;
        this.Width = tile_x * getTileSize();
        this.Height = tile_y * getTileSize();
    }
    
    public Tiles(int tile_x, int tile_y, boolean hasBomb) {
        setTileX(tile_x);
        setTileY(tile_y);
        this.Width = tile_x * getTileSize();
        this.Height = tile_y * getTileSize();
        setTileHasBomb(hasBomb);
        
        getTileBorder().setStroke(Color.LIGHTGRAY);
        
        getTileText().setText(hasBomb ? "X" : "");
        getTileText().setFont(Font.font(18));
        getTileText().setVisible(false);
        
        getChildren().addAll(getTileBorder(),getTileText());
        
        setTranslateX(getTileX() * getTileSize());
        setTranslateY(getTileY() * getTileSize());
        
        setOnMouseClicked(e -> open());
    }
    
     
    
    @Override
    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Width, Height);
        
        for(int i = 0; i < x_tiles; ++i) {
            for(int j = 0; j < y_tiles; ++j) {
                Tiles tiles = new Tiles(i, j, Math.random() < 0.2);
                grid[i][j] = tiles;
                root.getChildren().add(tiles);
            }
        }

        for(int i = 0; i < x_tiles; ++i) {
            for(int j = 0; j < y_tiles; ++j) {
                Tile tile = grid[i][j];
                
                if(tile.getTileHasBomb()) 
                    continue;
                
                long bombs = getNeighbors(tile).stream().filter(t -> t.getTileHasBomb()).count();
                
                if(bombs > 0)
                    tile.setTileText(Long.toString(bombs));
            }
        }
        return root;
    }
    
    @Override
    public void open() {
        if(getTileIsOpen())
            return;
        
        if(getTileHasBomb()) {
            System.out.println("Game Over");
            scene.setRoot(createContent());
            return;
        }
        
        setTileIsOpen(true);
        setTileTextVisible(true);
        setTileBorderFillNull();
        
        if(getTileText().getText().isEmpty()) {
            getNeighbors(this).forEach(Tile::open);
        }
    }
    
    @Override
    public List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();
        
        int[] points = new int[] {
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };
        
        for (int i = 0; i < points.length; ++i) {
            int dx = points[i];
            int dy = points[++i];
            
            int newX = getTileX() + dx;
            int newY = getTileY() + dy;
            
            if(newX >= 0 && newX < x_tiles
                    && newY >= 0 && newY < y_tiles) {
                neighbors.add(grid[newX][newY]);
            }
        }
        
        return neighbors;
    }
}
