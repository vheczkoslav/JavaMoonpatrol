package game;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.swing.text.Position;

public class Wheel extends RenderEntity {
    private String[] imgSources = new String[]{"wheel1.png", "wheel2.png"};
    private boolean currentImage = false;
    private int yoffset = 0;
    private final int defaultY;
    private double deltaCheck = 0;
    double SHIP_X_OFFSET;
    Wheel(Track track, int GROUND_HEIGHT, int SHIP_X_OFFSET){
        super(track);
        image = new Image[2];
        image[0] = new Image(Wheel.class.getResourceAsStream(imgSources[0]));
        image[1] = new Image(Wheel.class.getResourceAsStream(imgSources[1]));
        defaultY = (int)((track.getSize().getHeight() - GROUND_HEIGHT) - image[0].getHeight() - yoffset);
        pos = new Point2D(SHIP_X_OFFSET, (track.getSize().getHeight() - GROUND_HEIGHT) - image[0].getHeight() - yoffset);
        this.SHIP_X_OFFSET = SHIP_X_OFFSET;
    }

    public void simulate(double deltaTime) {
        deltaCheck += deltaTime;
        if(deltaCheck >= (1.00 / 20)){
            deltaCheck = 0;
            currentImage = !currentImage;
        }
    }

    public void simulate(double deltaTime, double jumpHeight) {
        simulate(deltaTime);
        pos = new Point2D(pos.getX(), defaultY - jumpHeight - 8);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image[currentImage ? 0 : 1], pos.getX(), pos.getY());
    }

    public void move(boolean leftOrRight){
        this.pos = new Point2D(pos.getX() + (leftOrRight ? 10 : -7.5), pos.getY());
    }

    public void reset(){
        this.pos = new Point2D(pos.getX(), defaultY);
    }
}
