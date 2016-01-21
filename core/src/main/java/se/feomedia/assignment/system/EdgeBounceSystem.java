package se.feomedia.assignment.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.feomedia.assignment.component.Movement;
import se.feomedia.assignment.component.Position;
import se.feomedia.assignment.component.Shape;
import se.feomedia.assignment.component.Size;
import se.feomedia.assignment.Utils;

import static com.artemis.Aspect.all;

public class EdgeBounceSystem extends IteratingSystem {

    @Wire
    private OrthographicCamera camera;

    private ComponentMapper<Position> positionMapper;
    private ComponentMapper<Movement> movementMapper;
    private ComponentMapper<Shape> shapeMapper;
    private ComponentMapper<Size> sizeMapper;

    public EdgeBounceSystem() {
        super(all(Position.class, Movement.class, Shape.class, Size.class));
    }

    @Override
    protected void process(int entityId) {

        Vector2 position = positionMapper.get(entityId).position;
        Vector2 direction = movementMapper.get(entityId).direction;
        Vector2 size = sizeMapper.get(entityId).size;

        Rectangle cameraBounds = Utils.cameraBounds2D(camera);

        if (position.x - size.x * .5f < cameraBounds.x) {

            direction.x = 1f;
        }
        if (position.y - size.y * .5f < cameraBounds.y) {

            direction.y = 1f;
        }

        if (position.x + size.x * .5f > cameraBounds.width) {

            direction.x = -1f;
        }
        if (position.y + size.y * .5f > cameraBounds.height) {

            direction.y = -1f;
        }
    }
}