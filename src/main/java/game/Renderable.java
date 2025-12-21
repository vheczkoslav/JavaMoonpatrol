package game;

import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Renderable {
    public void draw(GraphicsContext gc);
    public void simulate(double deltaTime);
}
