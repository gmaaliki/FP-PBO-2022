package minesweeperapp;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;



public class Main extends Application{
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
    static int foundBombs, numBombs;
    private static final int bombPercent = 10;
    Difficulty difficulty;
    
    private static Tile[][] grid;
    private static Stage stage;
    private static VBox vbox = new VBox();


    private static Parent createContent() {
        numBombs = 0;
        foundBombs = 0;
        Pane root = new Pane();
        root.setPrefSize(WIDTH * Tile.TILE_SIZE, HEIGHT * Tile.TILE_SIZE);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                Tile tile = new Tile(x, y, false);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        
        for(int i = 0; i < HEIGHT * WIDTH / bombPercent; i++){

            Random rand = new Random();

            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);

            if(grid[x][y].hasBomb){
                if (i == 0) {
                    i = 0;
                } else {
                    i--;
                }
            }
            else{
                grid[x][y].hasBomb = true;
                numBombs++;
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                int numNeighboursBomb = 0;

                ArrayList<Tile> neighbours = new ArrayList<>();

                int[] neighboursLocs = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };

                for (int i = 0; i < neighboursLocs.length; i++) {
                    int dx = neighboursLocs[i];
                    int dy = neighboursLocs[++i];

                    int newX = x + dx;
                    int newY = y + dy;

                    if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
                        neighbours.add(grid[newX][newY]);
                        if (grid[newX][newY].hasBomb) {
                            numNeighboursBomb++;
                        }
                    }
                }

                grid[x][y].bombs = numNeighboursBomb;
                grid[x][y].neighbours = neighbours;

                Color[] colors = { null, Color.BLUE, Color.GREEN, Color.RED, Color.DARKBLUE, Color.DARKRED, Color.CYAN,
                        Color.BLACK, Color.DARKGRAY };

                grid[x][y].color = colors[grid[x][y].bombs];
            }
        }
        
        return root;
    }
        
    private static void reload() {
        grid = new Tile[WIDTH][HEIGHT];
        
        vbox.getChildren().remove(0);
        vbox.getChildren().add(createContent());
        stage.sizeToScene();
    }
    
    public static void win() {
        Alert win = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage) win.getDialogPane().getScene().getWindow()).getIcons().add(Tile.mine);
        win.setTitle("Win!");
        win.setHeaderText("Congratulations!");
        win.showAndWait();
        reload();
    }
    
    public static void gameOver() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (grid[x][y].hasBomb) {
                    ImageView mineIcon = new ImageView(Tile.mine);
                    mineIcon.setFitHeight(30);
                    mineIcon.setFitWidth(20);
                    grid[x][y].btn.setGraphic(mineIcon);
                    grid[x][y].btn.setDisable(true);
                }
            }
        }

        Alert gameOver = new Alert(AlertType.INFORMATION);
        ((Stage) gameOver.getDialogPane().getScene().getWindow()).getIcons().add(Tile.mine);
        gameOver.setTitle("Game Over!");
        gameOver.setGraphic(new ImageView(Tile.mine));
        gameOver.setHeaderText("Bomb Exploded!");
        gameOver.setContentText("Oh no! You clicked on a bomb and caused all the bombs to explode! Better luck next time.");
        gameOver.showAndWait();
        
        reload();
    }
    
    public Parent mainMenu() {
        VBox root = new VBox();
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn1.setText("Easy");
        btn2.setText("Medium");
        btn3.setText("Hard");
        
        btn1.setOnAction(e -> {
            difficulty = Difficulty.EASY;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        btn2.setOnAction(e -> {
            difficulty = Difficulty.MEDIUM;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        btn2.setOnAction(e -> {
            difficulty = Difficulty.HARD;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        root.getChildren().addAll(btn1, btn2, btn3);
        return root;
    }
    
    @Override
    public void start(Stage primaryStage) {
        grid = new Tile[WIDTH][HEIGHT];
        try {
            stage = primaryStage;
            Scene scene = new Scene(mainMenu());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}