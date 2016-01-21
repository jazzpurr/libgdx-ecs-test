package se.feomedia.assignment.system;

import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import se.feomedia.assignment.component.Movement;
import se.feomedia.assignment.component.Position;

import static com.artemis.Aspect.all;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<Position> positionMapper;
    private ComponentMapper<Movement> movementMapper;

    public MovementSystem() {
        super(all(Position.class, Movement.class));
    }

    @Override
    protected void process(int entityId) {

        Vector2 position = positionMapper.get(entityId).position;
        Vector2 direction = movementMapper.get(entityId).direction;
        float speed = movementMapper.get(entityId).speed;

        position.x += speed * direction.x * Gdx.graphics.getDeltaTime();
        position.y += speed * direction.y * Gdx.graphics.getDeltaTime();
    }
}