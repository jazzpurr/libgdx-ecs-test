package se.feomedia.assignment.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import se.feomedia.assignment.component.Position;
import se.feomedia.assignment.component.Shape;
import se.feomedia.assignment.component.SnakePosition;

public class SnakeSystem extends IteratingSystem {

    @Wire
    private ShapeRenderer shapeRenderer;

    private ComponentMapper<Shape> shapeMapper;
    private ComponentMapper<SnakePosition> snakeMapper;
    private ComponentMapper<Position> positionMapper;

    public SnakeSystem() {
        super(Aspect.all(SnakePosition.class, Position.class));
    }

    @Override
    protected void begin() {

        shapeRenderer.begin();
    }

    @Override
    protected void end() {

        shapeRenderer.end();
    }

    @Override
    protected void process(int entityId) {

        Shape shape = shapeMapper.get(entityId);
        SnakePosition snakePosition = snakeMapper.get(entityId);


        snakePosition.positionArray.removeIndex(0);
        snakePosition.positionArray.add(positionMapper.get(entityId).position.cpy());


        for (int i = 0; i < snakePosition.positionArray.size; i++)
            shapeRenderer.rect(snakePosition.positionArray.get(i).x-50, snakePosition.positionArray.get(i).y-50, 100f, 100f);

    }
}