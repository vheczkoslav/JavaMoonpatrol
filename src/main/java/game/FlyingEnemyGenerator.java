package game;

import java.util.Random;

public class FlyingEnemyGenerator {
    Random random;
    long nextTime;
    final int second = 1000;
    Track track;
    FlyingEnemyGenerator(Track track){
        random = new Random();
        nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
        this.track = track;
    }
    void simulate(){
        if(System.currentTimeMillis() >= nextTime){
            nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
            //track.addFlyingEnemy(new FlyingEnemy(track, (Math.random() > 0.5 ? FE_SPRITE.BLUE : FE_SPRITE.YELLOW)));
        }
    }
}
