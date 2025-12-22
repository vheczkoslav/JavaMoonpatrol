package game;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class Track {
    private Dimension2D size;
    private Ground g;
    private Ship s;

    private static final int GROUND_HEIGHT = 48;
    private Score score;

    private FlyingEnemyGenerator feg;
    private ProjectileManager projectileManager;

    private final List<RenderEntity> r = new ArrayList<>();
    private final List<Collisionable> collisionables = new ArrayList<>();

    private GameController gameController;

    public Track(double width, double height, Score score, GameController gameController) {
        this.score = score;

        size = new Dimension2D(width, height);
        g = new Ground(this, GROUND_HEIGHT);
        s = new Ship(this, GROUND_HEIGHT);
        r.add(new BackgroundMountains(this, GROUND_HEIGHT));
        r.add(new FrontHills(this, GROUND_HEIGHT));
        r.add(s);

        feg = new FlyingEnemyGenerator(this);
        projectileManager = new ProjectileManager(GROUND_HEIGHT, this);
        collisionables.add(s);

        this.gameController = gameController;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,size.getWidth(),size.getHeight());
//        for(Renderable renderable : r){
//            renderable.draw(gc);
//        }
        r.forEach(e -> e.draw(gc));
        g.draw(gc);
        feg.draw(gc);
        projectileManager.draw(gc);
    }

    public void simulate(double deltaTime) {
        for (Collisionable c1 : collisionables) {
            for (Collisionable c2 : collisionables) {
                if (c1 != c2 && c1.getBoundingBox().intersects(c2.getBoundingBox())) {
                    if (c1 instanceof Ship && c2 instanceof Stone) {
                        System.exit(-1);
                    }
                    if (c1 instanceof HorizontalProjectile && c2 instanceof Stone) {
                        score.increaseScore(50);
                        collisionables.remove(c2);
                        gameController.setScoreText();
                    }
                    if(c1 instanceof VerticalProjectile && c2 instanceof FlyingEnemy){
                        score.increaseScore(100);
                        collisionables.remove(c2);
                        gameController.setScoreText();
                    }
                }
            }
        }

        g.simulate(deltaTime);
//        for(Renderable renderable : r){
//            renderable.simulate(deltaTime);
//        }
        r.forEach(e -> e.simulate(deltaTime));
        s.move();
        feg.simulate(deltaTime);
        projectileManager.simulate();
    }

    public Dimension2D getSize(){
        return size;
    }

    public Ship getS() {
        return s;
    }

    public void shoot(){
        if(!s.isDidJump()) projectileManager.shootProjectiles((int)getS().pos.getX());
    }

    public void addCollisionable(Collisionable c){
        this.collisionables.add(c);
    }

    public void removeCollisionable(Collisionable c){
        this.collisionables.remove(c);
    }
}
