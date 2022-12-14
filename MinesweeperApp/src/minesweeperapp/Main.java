package minesweeperapp;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class Main extends Application{
    private static int width;
    private static int height;
    private static int bombPercent;
    private static int gamesCount = 0;
    static int foundBombs, numBombs;

    
    private static GameTile[][] grid;
    private static Stage stage;
    private Score score = new Score();
    private static VBox vbox = new VBox();

    private static Parent createContent() {
        numBombs = 0;
        foundBombs = 0;
        Pane root = new Pane();
        root.setPrefSize(width * GameTile.TILE_SIZE, height * GameTile.TILE_SIZE);
        grid = new GameTile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                GameTile tile = new GameTile(x, y, false);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        
        for(int i = 0; i < height * width / bombPercent; i++){

            Random rand = new Random();

            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int numNeighboursBomb = 0;

                ArrayList<GameTile> neighbours = new ArrayList<>();

                int[] neighboursLocs = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };

                for (int i = 0; i < neighboursLocs.length; i++) {
                    int dx = neighboursLocs[i];
                    int dy = neighboursLocs[++i];

                    int newX = x + dx;
                    int newY = y + dy;

                    if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
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
        grid = new GameTile[Main.width][Main.height];
        
        Score score = new Score();
        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                score.incTime();
            };
        };
        Score.timer.cancel();
        Score.timer = new Timer();
        Score.timer.schedule(task, 1000, 1000);
        
        vbox.getChildren().remove(0);
        vbox.getChildren().add(createContent());
        stage.sizeToScene();
    }
    
    public static void win() {
                
        Image winImage = new Image("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\ThumbsUp.png");
        ImageView winImageView = new ImageView(winImage);
        
        winImageView.setSmooth(true);
        winImageView.setPreserveRatio(true);
        winImageView.setFitHeight(50);

        
        Alert win = new Alert(Alert.AlertType.CONFIRMATION);
        ((Stage) win.getDialogPane().getScene().getWindow()).getIcons().add(GameTile.mine);
        win.setGraphic(winImageView);
        win.setTitle("Win!");
        win.setHeaderText("Keep Going!");
        win.setContentText("You found all the bombs in " + Score.getTime() + " seconds.");
        win.showAndWait();
        reload();
    }
    
    public static void gameOver() throws Exception {
        VBox root = new VBox();
        Score score = new Score();
        ArrayList<Pair<String,Integer>> highScoreList = null;
        highScoreList = new ArrayList<>(score.getHighScore());
        
        for(int i = 0; i < 5; i++) {
            String str1 = new String();
            String str2 = new String();
            
            str1 = highScoreList.get(i).getKey();
            str2 = Integer.toString(highScoreList.get(i).getValue());
            
            Text text = new Text(str1 + " " + str2);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
            root.getChildren().add(text);
        }
        

        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        
        Scene scene = new Scene(root, 500, 500);
        
        Alert gameOver = new Alert(AlertType.INFORMATION);
        ((Stage) gameOver.getDialogPane().getScene().getWindow()).getIcons().add(GameTile.mine);
        gameOver.setTitle("Game Over!");
        gameOver.setGraphic(new ImageView(GameTile.mine));
        gameOver.setHeaderText("Bomb Exploded!");
        gameOver.setContentText("Oh no! You clicked on a bomb and caused all the bombs to explode! Better luck next time.");
        gameOver.show(); 
        
        stage.setScene(scene);
    }          
    
    public void setButton(Button btn, Font font, int height, int width) {
        btn.setFont(font);
        btn.setPrefHeight(height);
        btn.setPrefWidth(width);
    }
    
    public Parent mainMenu() {
        VBox root = new VBox();
        Button btn1 = new Button("Easy");
        Button btn2 = new Button("Medium");
        Button btn3 = new Button("Hard");
        
        Font font = new Font("Verdana", 25);
        setButton(btn1, font, 50, 100);
        setButton(btn2, font, 50, 150);
        setButton(btn3, font, 50, 100);
        
        Text text = new Text("MINESWEEPER");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        
        btn1.setOnAction(e -> {
            width = Setting.EASY.width;
            height = Setting.EASY.height;
            bombPercent = Setting.EASY.bombPercent;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        btn2.setOnAction(e -> {
            width = Setting.MEDIUM.width;
            height = Setting.MEDIUM.height;
            bombPercent = Setting.MEDIUM.bombPercent;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        btn3.setOnAction(e -> {
            width = Setting.HARD.width;
            height = Setting.HARD.height;
            bombPercent = Setting.HARD.bombPercent;
            vbox.getChildren().add(createContent());
            Scene scene = new Scene(vbox);
            stage.setScene(scene);
        });
        
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        root.getChildren().addAll(text, btn1, btn2, btn3);
        return root;
    }
    
    @Override
    public void start(Stage primaryStage) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                score.incTime();
            }
        };

        Score.timer = new Timer();
        Score.timer.scheduleAtFixedRate(task, 1000, 1000);
        
        try {
            primaryStage.setTitle("Minesweeper");
            stage = primaryStage;
            Scene scene = new Scene(mainMenu(), 500, 500);

            stage.getIcons().add(GameTile.mine);
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