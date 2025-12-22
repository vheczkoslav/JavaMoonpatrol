package game;

import java.io.Serializable;

public class Score implements Serializable {
    int currentScore;
    int highScore;
    Score(){
        currentScore = 0;
        highScore = 0;
    }
    Score(Score s){
        highScore = s.highScore;
        currentScore = 0;
    }
    public boolean checkScore(){ // check if score during close up is new high score
        if(currentScore > highScore){
            highScore = currentScore;
            return true;
        }
        return false;
    }
    public void increaseScore(int inc){
        currentScore += inc;
    }
}
