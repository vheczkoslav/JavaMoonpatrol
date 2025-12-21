package game;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundMountains extends RenderEntity {
    private int yoffset = 48; // offset vuci prednim kopcum
    private double slideSpeed = -50;

    BackgroundMountains(Track track, int GROUND_HEIGHT){
        super(track);
        image = new Image[1];
        image[0] = new Image(FrontHills.class.getResourceAsStream("back-mountains.png"));
        pos = new Point2D(0, 0 + (track.getSize().getHeight() - GROUND_HEIGHT) - image[0].getHeight() - yoffset);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image[0], pos.getX(), pos.getY());
        gc.drawImage(image[0], pos.getX() + image[0].getWidth(), pos.getY());
    }

    public void simulate(double deltaTime){
        if((int)pos.getX() <= (0 - image[0].getWidth())){
            pos = pos.add(image[0].getWidth(),0);
        }
        pos = pos.add(slideSpeed * deltaTime, 0);
    }

}
