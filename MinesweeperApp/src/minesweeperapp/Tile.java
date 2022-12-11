package minesweeperapp;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Tile extends StackPane implements ClickBehaviour {
    
    ArrayList<Tile> neighbours = new ArrayList<>();
    Button btn = new Button();
    
    private final int x = 0, y = 0;
    private boolean active = true;
    static final int TILE_SIZE = 40;
    boolean hasBomb;
    int bombs = 0;
    boolean flagged = false;
    
    Color color = null;
    static Image flag = new Image("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\flag.png");
    static Image mine = new Image("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\mine.png");
        
    public Tile(int x, int y, boolean hasBomb) {
        this.hasBomb = hasBomb;
        
        if (hasBomb) {
            Main.numBombs++;
        }
        
        btn.setMinHeight(TILE_SIZE);
        btn.setMinWidth(TILE_SIZE);
        
        btn.setOnMouseClicked(this::onClick);
        getChildren().addAll(btn);
            
        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);
    }
        
    @Override
    public void onClick(MouseEvent e) {
    // Left Click
    if (e.getButton() == MouseButton.PRIMARY) {
        if(!flagged) {
            btn.setBackground(null);
            btn.setDisable(true);
            active = false;
            if (hasBomb) {
                Main.gameOver();
            } else {
                // Blank
                if (this.bombs == 0) {
                    blankClick(this);
                } else {
                    btn.setText(Integer.toString(bombs));
                    btn.setTextFill(color);
                }
            }
        }
    }
        // Right Click
    else if (e.getButton() == MouseButton.SECONDARY) {
            if (!flagged) {
                flagged = true;
                btn.setGraphic(new ImageView(flag));
                if (this.hasBomb) {
                    Main.foundBombs++;
                    if (Main.foundBombs == Main.numBombs)
                        Main.win();
                }
            } else {
                if (hasBomb) {
                    Main.foundBombs--;
                }
                btn.setGraphic(null);
                flagged = false;
            }
        }
    }

    @Override
    public void blankClick(Tile tile) {
        for (int i = 0; i < tile.neighbours.size(); i++) {
            if (tile.neighbours.get(i).active) {
                tile.neighbours.get(i).btn.setDisable(true);
                tile.neighbours.get(i).btn.setGraphic(null);
                tile.neighbours.get(i).btn.setText(Integer.toString(tile.neighbours.get(i).bombs));
                tile.neighbours.get(i).btn.setTextFill(tile.neighbours.get(i).color);
                tile.neighbours.get(i).active = false;
                if (tile.neighbours.get(i).bombs == 0) {
                    blankClick(tile.neighbours.get(i));
                }

            }
        }
    }
}