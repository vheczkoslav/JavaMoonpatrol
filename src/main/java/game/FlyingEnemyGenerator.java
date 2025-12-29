package game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlyingEnemyGenerator {
    private Random random;
    private long nextTime;
    private final int second = 1000;
    private CollisionableManager collisionableManager;
    private final int widthLimit;

    FlyingEnemyGenerator(CollisionableManager collisionableManager, int widthLimit){
        random = new Random();
        nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
        this.collisionableManager = collisionableManager;
        this.widthLimit = widthLimit;
    }

    void simulate(){
        if(System.currentTimeMillis() >= nextTime){
            nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
            FlyingEnemy fe = new FlyingEnemy(widthLimit, (Math.random() > 0.5 ? FE_SPRITE.BLUE : FE_SPRITE.YELLOW));
            collisionableManager.addCollisionable(fe);
        }
    }
}
