package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemyGenerator {
    private Random random;
    private long nextTime;
    private final int second = 1000;
    //private List<FlyingEnemy> flyingEnemies = new ArrayList<>();
    //private List<FlyingEnemy> toRemove = new ArrayList<>();
    private CollisionableManager collisionableManager;
    private final int widthLimit;

    FlyingEnemyGenerator(CollisionableManager collisionableManager, int widthLimit){
        random = new Random();
        nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
        this.collisionableManager = collisionableManager;
        this.widthLimit = widthLimit;
    }

    /*public void draw(GraphicsContext gc){
        for(FlyingEnemy flyingEnemy: flyingEnemies){
            flyingEnemy.draw(gc);
        }
    }*/
    void simulate(double deltaTime){
        if(System.currentTimeMillis() >= nextTime){
            nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
            FlyingEnemy fe = new FlyingEnemy(widthLimit, (Math.random() > 0.5 ? FE_SPRITE.BLUE : FE_SPRITE.YELLOW));
            //flyingEnemies.add(fe);
            collisionableManager.addCollisionable(fe);
        }
        /*for(FlyingEnemy flyingEnemy : flyingEnemies){
            flyingEnemy.simulate(deltaTime);
            if(flyingEnemy.checkBounds()){
                toRemove.add(flyingEnemy);
            }
        }

        flyingEnemies.removeAll(toRemove);

        toRemove.clear();*/
    }
}
