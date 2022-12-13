package minesweeperapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

@SuppressWarnings("unchecked")

public class Score {
    private static int seconds;
    private static int numberOfGames;
    public static Timer timer;
    
    //highsoore global sama high score pribadi
    public ArrayList<Pair<String,Integer>> getHighScore() throws FileNotFoundException {
        ArrayList<Pair<String,Integer>> highScoreList = new ArrayList<>();
        HashSet<Pair> highScoreSet = new HashSet<>();
    
        File file = new File("C:\\FP-PBO-2022\\MinesweeperApp\\src\\minesweeperapp\\PlayerScore.txt");
        Scanner inp = new Scanner(file);
        
        while(inp.hasNext()) {
            String name = new String(inp.next());
            int points = inp.nextInt();
            Pair pair = new Pair(name,points);
            highScoreList.add(pair);
            System.out.println(pair.getValue() + "" + (String) pair.getKey());
        }
        
        for(Pair i : highScoreSet) {
            Pair pair = new Pair(i.getKey(),(int) i.getValue());
            highScoreList.add(pair);
        }
       
        Collections.sort(highScoreList, Comparator.comparing(p -> -p.getValue()));
        return highScoreList;
    }
    
//    public int calculateScore() {
//        
//    }
    
    public void gameWon() {
        numberOfGames++;
    }
    
    public void resetTime() {
        seconds = 0;
    }
    
    public void incTime() {
        seconds++;
    }
    
    public static int getTime() {
        return seconds;
    }
    
    public int getTime(boolean onConsole) {
        if(onConsole) System.out.println("The total time is " + seconds + "seconds");
        return seconds;
    }
    
}
