package game;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class FlyingEnemy extends RenderEntity{
    final int moveSpeed = 10;
    FlyingEnemy(Track track, FE_SPRITE fes){
        super(track);
        this.image = new Image[1];
        image[0] = new Image(FrontHills.class.getResourceAsStream(fes == FE_SPRITE.YELLOW ? "flying_enemy.png" : "flying_enemy2.png"));
        pos = new Point2D(0, 20);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
    }

    @Override
    public void simulate(double deltaTime) {
        pos = pos.add(moveSpeed * deltaTime, 0);
    }
}
