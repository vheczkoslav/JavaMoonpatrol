package game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class RenderEntity implements Renderable{
    protected Point2D pos;
    protected Image[] image;
    RenderEntity(Track t){}
}
