package game;

import java.io.Serial;
import java.io.Serializable;

public class Score implements Serializable {
    @Serial
    private static final long serialVersionUID = -2480413298517931742L;
    int currentScore;
    int highScore;

    Score(){
        currentScore = 0;
        highScore = 0;
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
