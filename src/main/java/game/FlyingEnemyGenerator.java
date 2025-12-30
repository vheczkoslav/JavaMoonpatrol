package game;

import java.util.Random;

public class FlyingEnemyGenerator {
    private Random random;
    private long nextTime;
    private final int second = 1000;
    private CollisionableManager collisionableManager;
    private final int widthLimit, heightLimit;

    FlyingEnemyGenerator(CollisionableManager collisionableManager, int widthLimit, Track track){
        random = new Random();
        nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
        this.collisionableManager = collisionableManager;
        this.widthLimit = widthLimit;
        this.heightLimit = (int)track.getSize().getHeight() - Track.groundHeight;
    }

    void simulate(){
        if(System.currentTimeMillis() >= nextTime){
            nextTime = System.currentTimeMillis() + random.nextInt(second * 10, second * 20);
            FlyingEnemy fe = new FlyingEnemy(widthLimit, heightLimit, (Math.random() > 0.5 ? FE_SPRITE.BLUE : FE_SPRITE.YELLOW), collisionableManager);
            collisionableManager.addCollisionable(fe);
        }
    }
}
