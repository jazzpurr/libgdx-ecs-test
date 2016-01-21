package se.feomedia.assignment.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import se.feomedia.assignment.component.Position;
import se.feomedia.assignment.component.Shape;
import se.feomedia.assignment.component.Size;

import static com.artemis.Aspect.all;

public class ShapeOutlineSystem extends IteratingSystem {

    private ComponentMapper<Position> positionMapper;
    private ComponentMapper<Size> sizeMapper;
    private ComponentMapper<Shape> shapeMapper;

    @Wire
    private ShapeRenderer shapeRenderer;

    @Wire
    private OrthographicCamera camera;

    public ShapeOutlineSystem() {
        super(all(Position.class, Size.class, Shape.class));
    }

    @Override
    protected void initialize() {

        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    protected void begin() {

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
    }

    @Override
    protected void end() {

        shapeRenderer.end();
    }

    @Override
    protected void process(int i) {

        Vector2 position = positionMapper.get(i).position;
        Vector2 size = sizeMapper.get(i).size;

        shapeRenderer.setColor(shapeMapper.get(i).shapeColor);

        if (shapeMapper.get(i).filled) {

            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        } else {

            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        }

        switch (shapeMapper.get(i).shapeGeometry) {
            case RECTANGLE:

                shapeRenderer.rect(position.x - size.x * .5f, position.y - size.y * .5f, size.x, size.y);
                break;

            case CIRCLE:

                shapeRenderer.circle(position.x, position.y, size.x * .5f);
                break;
        }
    }
}