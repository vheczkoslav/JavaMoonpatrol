package game;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.*;

public abstract class RenderEntity implements Renderable{
    protected Point2D pos;
    protected Track t;
    protected Image[] image;
    RenderEntity(Track t){this.t = t;}
}
