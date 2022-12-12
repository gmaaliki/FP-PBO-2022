package minesweeperapp;

import java.util.Timer;

public class Score {
    private static int seconds;
    public static Timer timer;
    
    public void resetTime() {
        seconds = 0;
    }
    
    public void incTime() {
        seconds++;
    }
    
    public static int getTime() {
        return seconds;
    }
    
    
}
