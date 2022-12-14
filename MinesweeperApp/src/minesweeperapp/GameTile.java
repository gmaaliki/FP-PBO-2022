package minesweeperapp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameTile extends BaseTile implements ClickBehaviour {
    
    ArrayList<GameTile> neighbours = new ArrayList<>();
    
    private boolean active = true;

    int bombs = 0;
    private boolean flagged = false;
    
    Color color = null;
    static Image flag = new Image("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\flag.png");
    static Image mine = new Image("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\mine.png");
    
    public GameTile(int x, int y, boolean hasBomb) {
        super.hasBomb = hasBomb;
        
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
                try {
                    Main.gameOver();
                } catch (Exception ex) {
                    Logger.getLogger(GameTile.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                ImageView flagIcon = new ImageView(flag);
                flagIcon.setFitHeight(15);
                flagIcon.setFitWidth(10);
                btn.setGraphic(flagIcon);
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
    public void blankClick(GameTile tile) {
        for (int i = 0; i < tile.neighbours.size(); i++) {
            if (tile.neighbours.get(i).active) {
                tile.neighbours.get(i).btn.setDisable(true);
                tile.neighbours.get(i).btn.setBackground(null);
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