package se.feomedia.assignment.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public final class Shape extends Component {

    public ShapeGeometry shapeGeometry = ShapeGeometry.RECTANGLE;
    public Color shapeColor = Color.WHITE;
    public boolean filled = false;

    public enum ShapeGeometry {
        RECTANGLE, CIRCLE
    }
}