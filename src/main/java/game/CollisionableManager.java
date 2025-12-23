package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollisionableManager {
    List<Collisionable> collisionableList = new ArrayList<>();
    List<Collisionable> toRemove = new ArrayList<>();

    private GameController gameController;

    private Ship ship;

    private FlyingEnemyGenerator flyingEnemyGenerator;
    private ProjectileManager projectileManager;

    private double nextStone;
    private Random random = new Random();

    Score score;
    Rectangle groundRect;

    CollisionableManager(Track track, int groundHeight, Score score, Rectangle groundRect, GameController gameController){
        flyingEnemyGenerator = new FlyingEnemyGenerator(this, (int)track.getSize().getWidth());
        ship = new Ship(track, groundHeight);
        this.groundRect = groundRect;
        collisionableList.add(ship);
        projectileManager = new ProjectileManager(track, groundHeight, this);
        this.score = score;
        nextStone = System.currentTimeMillis() + random.nextInt(5_000, 15_000);
        this.gameController = gameController;
    }
    public void checkCollisions(){
        for (int i = 0; i < collisionableList.size() - 1; i++) {
            for (int j = 1; j < collisionableList.size(); j++) {
                Collisionable c1 = collisionableList.get(i);
                Collisionable c2 = collisionableList.get(j);
                if (c1 != c2 && c1.getBoundingBox().intersects(c2.getBoundingBox())) {
                    if (c1 instanceof Ship && c2 instanceof Stone) {
                        System.out.println("Konec");
                    }
                    if (c1 instanceof HorizontalProjectile && c2 instanceof Stone) {
                        score.increaseScore(50);
                        toRemove.add(c2);
                        gameController.setScoreText();
                    }
                    if (c1 instanceof VerticalProjectile && c2 instanceof FlyingEnemy) {
                        score.increaseScore(100);
                        toRemove.add(c2);
                        gameController.setScoreText();
                    }
                }
            }
        }
    }
    public void draw(GraphicsContext gc){
        //flyingEnemyGenerator.draw(gc);
        for(Collisionable c: collisionableList){
            c.draw(gc);
        }
        //projectileManager.draw(gc);
    }
    public void simulate(double deltaTime){
        flyingEnemyGenerator.simulate(deltaTime);
        ship.move();
        for(Collisionable c: collisionableList){
            c.simulate(deltaTime);
        }
        //projectileManager.simulate(deltaTime);

        if(System.currentTimeMillis() > nextStone){
            collisionableList.add(new Stone(new STONE_SIZE[]{STONE_SIZE.SMALL, STONE_SIZE.MEDIUM, STONE_SIZE.LARGE}[random.nextInt(3)], (int) groundRect.getWidth(), (int) groundRect.getY()));
            nextStone = System.currentTimeMillis() + random.nextInt(5_000, 15_000);
        }

        for(Collisionable collisionable: collisionableList){
            if(collisionable instanceof Stone){
                if(((Stone) collisionable).isOutOfBounds()) collisionableList.remove(collisionable);
            }
        }
        checkCollisions();

        collisionableList.removeAll(toRemove);
        toRemove.clear();
    }
    public void shootProjectiles(){
        projectileManager.shootProjectiles((int)ship.pos.getX());
    }

    public Ship getShip(){return ship;}

    public void addCollisionable(Collisionable c){
        collisionableList.add(c);
    }
}
