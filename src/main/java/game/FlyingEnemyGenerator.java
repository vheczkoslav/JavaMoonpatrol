package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemyGenerator {
    Random random;
    long nextTime;
    final int second = 1000;
    Track track;
    List<FlyingEnemy> flyingEnemies = new ArrayList<>();
    List<FlyingEnemy> toRemove = new ArrayList<>();

    FlyingEnemyGenerator(Track track){
        random = new Random();
        nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
        this.track = track;
    }

    public void draw(GraphicsContext gc){
        for(FlyingEnemy flyingEnemy: flyingEnemies){
            flyingEnemy.draw(gc);
        }
    }
    void simulate(double deltaTime){
        if(System.currentTimeMillis() >= nextTime){
            nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
            FlyingEnemy fe = new FlyingEnemy(track, (Math.random() > 0.5 ? FE_SPRITE.BLUE : FE_SPRITE.YELLOW));
            flyingEnemies.add(fe);
            track.addCollisionable(fe);
        }
        for(FlyingEnemy flyingEnemy : flyingEnemies){
            flyingEnemy.simulate(deltaTime);
            if(flyingEnemy.checkBounds()){
                toRemove.add(flyingEnemy);
                track.removeCollisionable(flyingEnemy);
            }
        }

        flyingEnemies.removeAll(toRemove);

        toRemove.clear();
    }
}
